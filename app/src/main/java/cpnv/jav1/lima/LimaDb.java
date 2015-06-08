package cpnv.jav1.lima;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.util.Log;
import android.widget.Toast;

/**
 * Allows querying a remote database using a web service.
 * The SQL query is sent by HTTP POST to the service, which sends data back in XML.
 * The data returned is parsed in LimaDb and made available to the user through
 * public methods
 *
 * @author Xavier
 * @version 1.0
 *
 */
public class LimaDb {

    /**
     * Constant indicating that the user tried to read the value of a field that is not present in the data returned
     */
    public final String LIMA_ERROR_FIELD_DOES_NOT_EXIST = ">>>Lima error: that field does not exist<<<";

    private String _wsURL; // Address of the webservice
    private String _selection; // Data retrieved from a select query (XML format)

    // for XML parsing of the response to a select
    private XmlPullParserFactory _xmlFactoryObject;
    private XmlPullParser _myparser;
    private int _event;
    private HashMap<String, String> _fields = new HashMap<String, String>();// The pairs key/value

    // ================================== Constructors ==========================================

    /**
     * @param wsURL
     * The URL of the web service, i.e: to which the query will be POSTed
     * It MUST start with "http://"
     */
    public LimaDb (String wsURL)
    {
        _wsURL = wsURL;
    }

    // ================================== Public methods ==========================================

    /**
     * Checks if the Lima database is available
     *
     * @return
     * true if the connection to the web service and the the database is good
     */
    public boolean connectionIsOK ()
    {
        HashMap<String, String> postData = new HashMap<String, String>();// The data of the POST request
        String res = httpPost(postData,"testdb.php");
        if (res.equals("OK"))
        {
            return true;
        }
        else
        {
            Log.i ("LIMA","Database check failed: "+res);
            return false;
        }
    }

    /**
     * Executes the query and returns the number of affected or selected records<br>
     * A negative value indicates that an error occurred<br>
     * In the case of a SELECT, the first record has <b>NOT</b> been read at this point
     *
     * @param query
     * The SQL query to execute on the remote server
     * @return
     * The number of affected or selected records
     */
    public int executeQuery (String query)
    {
        HashMap<String, String> postData = new HashMap<String, String>();// The data of the POST request
        postData.put("sql", query); // $_POST['sql']=query
        _selection = httpPost(postData,"execquery.php");

        //Log.i("LIMA", _selection);
        // Initialise the XML parser
        _xmlFactoryObject = null;
        _myparser = null;
        _event = 0;
        try
        {
            _xmlFactoryObject = XmlPullParserFactory.newInstance();
            _myparser = _xmlFactoryObject.newPullParser();
            _myparser.setInput(new ByteArrayInputStream(_selection.getBytes("UTF-8")), null); // Use input string as a stream for XML input
        }
        catch (XmlPullParserException e)
        {
            Log.i("LIMA","Error initialising XML parser");
            return -1;
        }
        catch (UnsupportedEncodingException e)
        {
            Log.i("LIMA","Bad XML encoding");
            return -1;
        }

        return nbRecords();
    }

    /**
     * Reads the next record from the current recordset
     *
     * @return
     * true if a record was successfully read, false if there was no more records or the XML returned was bad
     */
    public boolean moveNext()
    {
        _fields = new HashMap<String, String>();// Zap the pairs key/value from previous record

        String tag=null;
        String text=null;
        boolean eor = false;

        while ((_event != XmlPullParser.END_DOCUMENT) && (!eor))
        {
            switch (_event){
                case XmlPullParser.START_TAG:
                    tag = _myparser.getName();
                    text=null;
                    break;

                case XmlPullParser.TEXT:
                    text=_myparser.getText();
                    break;

                case XmlPullParser.END_TAG:
                    _fields.put(tag,text);
                    if (_myparser.getName().equals("record"))
                        eor = true;
                    break;
            }
            try
            {
                _event = _myparser.next();
            }
            catch (XmlPullParserException e)
            {
                Log.i("LIMA","Bad XML:"+ e.getMessage()+" ("+_myparser.getText()+")");
            }
            catch (IOException e)
            {
                Log.i("LIMA","IO error parsing XML");
            }
        }

        return eor;
    }

    /**
     * Reads the value of a field in the current record
     *
     * @param fname
     * the name of the field to read
     *
     * @return
     * The value of the field "fname" of the current record, or the constant LIMA_ERROR_FIELD_DOES_NOT_EXIST
     * if the field does not exist in the record
     */
    public String getField(String fname)
    {
        if (!_fields.containsKey(fname)) return LIMA_ERROR_FIELD_DOES_NOT_EXIST; // No Such Field
        return _fields.get(fname);
    }

    // ================================== Private methods ==========================================

    private String httpPost(HashMap<String, String> data, String page)
    {
        // synchronous post: we post in background, then wait for completion
        AsyncHttpPost asyncHttpPost = new AsyncHttpPost(data);
        String url = _wsURL+"/Lima/"+page;
        asyncHttpPost.execute(url);

        // now wait for completion
        while (asyncHttpPost.getMyStatus() == 0)
            try
            {
                Thread.sleep(100); // don't hog the CPU with a loop
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        // Handle result
        if (asyncHttpPost.getMyStatus() == 1) // success
            return asyncHttpPost.getResult();
        else
            return "AsyncHttpPost error on url "+ url + "("+String.valueOf(asyncHttpPost.getMyStatus())+")";
    }

    /**
     * Gets the last insert ID
     *
     * @author Romain Lanz
     * @return The last insert ID
     */
    public Integer lastInsertId()
    {
        try {
            //_event = _myparser.next();
            if (_event == XmlPullParser.START_TAG) {
                if (_myparser.getName().equals("lastinsertid")) {
                    _event = _myparser.next();

                    if (_event == XmlPullParser.TEXT) {
                        int res = Integer.parseInt(_myparser.getText());
                        _event = _myparser.next();
                        if (_event == XmlPullParser.END_TAG) return res;
                    }
                } else if (_myparser.getName().equals("error"))	{
                    _event = _myparser.next();
                    Log.i("LIMA","Server error: "+_myparser.getText());
                    return -1;
                }
            }
        } catch (XmlPullParserException e) {
            Log.i("LIMA","Error reading XML");
        } catch (IOException e)	{
            Log.i("LIMA","Bad XML encoding");
        }

        Log.i ("LIMA","Cant get the last insert id");
        return -1;
    }

    private int nbRecords()
    {
        // Returns the number of affected records (first tag of the XML response: <nbrecords>)
        try
        {
            _event = _myparser.next();
            if (_event == XmlPullParser.START_TAG)
                if (_myparser.getName().equals("nbrecords")) // tag ok
                {
                    _event = _myparser.next();
                    if (_event == XmlPullParser.TEXT) // value
                    {
                        int res = Integer.parseInt(_myparser.getText());
                        _event = _myparser.next();
                        if (_event == XmlPullParser.END_TAG) // check tag closure
                            return res; // Everything ok
                    }
                }
                else
                if (_myparser.getName().equals("error")) // server side error
                {
                    _event = _myparser.next();
                    Log.i("LIMA","Server error: "+_myparser.getText());
                    return -1;
                }
        }
        catch (XmlPullParserException e)
        {
            Log.i("LIMA","Error reading XML");
        }
        catch (IOException e)
        {
            Log.i("LIMA","Bad XML encoding");
        }
        Log.i ("LIMA","Bad response from ws");
        return -1;
    }

}
