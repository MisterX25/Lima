package cpnv.jav1.lima;

import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import android.os.AsyncTask;
import android.util.Log;

public class AsyncHttpPost extends AsyncTask<String, String, String> {
    private HashMap<String, String> _data = null;// post data
    private int _status;
    private String _result;

    public int getMyStatus() { return _status; }
    public String getResult() { return _result; }

    /**
     * constructor
     */
    public AsyncHttpPost(HashMap<String, String> data) {
        _data = data;
    }

    /**
     * background
     */
    @Override
    protected String doInBackground(String... params) {
        byte[] result = null;
        String str = "";
        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(params[0]);// in this case, params[0] is URL
        try {
            // set up post data
            ArrayList<NameValuePair> nameValuePair = new ArrayList<NameValuePair>();
            Iterator<String> it = _data.keySet().iterator();
            while (it.hasNext()) {
                String key = it.next();
                nameValuePair.add(new BasicNameValuePair(key, _data.get(key)));
            }

            post.setEntity(new UrlEncodedFormEntity(nameValuePair, "UTF-8"));
            HttpResponse response = client.execute(post);
            StatusLine statusLine = response.getStatusLine();
            if(statusLine.getStatusCode() == HttpURLConnection.HTTP_OK){
                result = EntityUtils.toByteArray(response.getEntity());
                str = new String(result, "UTF-8");
                _status = 1; // OK
                _result = str;
                // Log.i ("LIMA",str);
            }
            else
                _status = 2; // Connection error
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            _status = 3; // Encoding error
        }
        catch (Exception e) {
            _status = 4; // Other error
        }
        return str;
    }

    /**
     * before we go
     */
    @Override
    protected void onPreExecute() {
        _status = 0; // Start operation
    }
}