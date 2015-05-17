package cpnv.jav1.lima;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Xavier on 17.05.15.
 */
public class LimaException extends Exception {

    //========================== Constants =============================

    //========================== Attributes =============================
    private int _user;                      // User logged in at the time of exception
    private Date _timestamp;            // Time (GMT, on the device) of the exception
    private String _device;                 // Device serial number

    //========================== Constructors =============================

    public LimaException (String message)
    {
        super(message); // create exception with message
        _user = 0; // by default
        _timestamp = new Date();
        _device = android.os.Build.SERIAL;
        Log.i("LIMA", "LimaException: "+ _timestamp.toString() + ", " + this.getMessage());
    }
}
