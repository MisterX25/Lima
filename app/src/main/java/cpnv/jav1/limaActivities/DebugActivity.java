package cpnv.jav1.limaActivities;

import cpnv.jav1.lima.LimaDb;
import cpnv.jav1.lima.LimaException;
import cpnv.jav1.lima.R;
import cpnv.jav1.limaEntities.ClassTeacher;
import cpnv.jav1.limaEntities.Person;
import cpnv.jav1.limaEntities.Student;
import cpnv.jav1.limaEntities.Teacher;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DebugActivity extends Activity 
				   implements OnClickListener {

	// References on the controls of this activity
	private Button btn;
	private TextView output;
    // References on the input fields
    private EditText fname ;
    private EditText lname ;
    private EditText param1 ;
    private EditText param2 ;
    private EditText param3 ;

    // Create activity event handler
	@Override
    public void onCreate(Bundle savedInstanceState) {
		// Initialize and display view
        super.onCreate(savedInstanceState);
        setContentView(R.layout.debug);
        
        // Log messages (to logcat)
        Log.i ("LIMA", "Started debug activity!!!!!");

        // Setup event handler on action button
        btn = (Button)findViewById(R.id.debugAction1);
       	btn.setOnClickListener(this);
        btn = (Button)findViewById(R.id.debugAction2);
       	btn.setOnClickListener(this);
        btn = (Button)findViewById(R.id.debugAction3);
        btn.setOnClickListener(this);
        btn = (Button)findViewById(R.id.debugAction4);
        btn.setOnClickListener(this);

        // Get the references on the input fields
        fname = (EditText)findViewById(R.id.txtFName);
        lname = (EditText)findViewById(R.id.txtLName);
        param1 = (EditText)findViewById(R.id.txtParam1);
        param2 = (EditText)findViewById(R.id.txtParam2);
        param3 = (EditText)findViewById(R.id.txtParam3);

        // Get reference on the output textview
		output = (TextView)findViewById(R.id.outputzone);

        // Initialize the text field to the app version
        output.setText("Version: " + getString(R.string.app_version));
    }

	// Any click on this screen will invoke this handler
	@Override
	public void onClick(View btn) {

        SimpleDateFormat sdate = new SimpleDateFormat("dd-M-yyyy");
		// Let's see which action must be performed
		switch (btn.getId()) 
		{
		case R.id.debugAction1:
			break;
		case R.id.debugAction2:
			break;
		case R.id.debugAction3:
            break;
        case R.id.debugAction4:
            break;
        case R.id.debugAction5:
            break;
        case R.id.debugAction6:
            break;

		}
	}

}
