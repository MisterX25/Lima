package cpnv.jav1.limaActivities;

import cpnv.jav1.lima.R;
import cpnv.jav1.limaEntities.Article;
import cpnv.jav1.limaEntities.Book;
import cpnv.jav1.limaEntities.Gear;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class DebugActivity extends Activity 
				   implements OnClickListener {

	// References on the controls of this activity
	private Button _btn;
	private TextView _output;

    // Create activity event handler
	@Override
    public void onCreate(Bundle savedInstanceState) {
		// Initialize and display view
        super.onCreate(savedInstanceState);
        setContentView(R.layout.debug);
        
        // Log messages (to logcat)
        Log.i ("LIMA", "Started debug activity!!!!!");

        // Setup event handler on action button
        _btn = (Button)findViewById(R.id.debugAction1); 
       	_btn.setOnClickListener(this); 
        _btn = (Button)findViewById(R.id.debugAction2); 
       	_btn.setOnClickListener(this); 
        _btn = (Button)findViewById(R.id.debugAction3); 
       	_btn.setOnClickListener(this); 
       	
       	// Get reference on the output textview
		_output = (TextView)findViewById(R.id.outputzone);

        // Initialize the text field to the app version
        _output.setText ("Version: "+getString(R.string.app_version));
    }

	// Any click on this screen will invoke this handler
	@Override
	public void onClick(View btn) {

		// Let's see which action must be performed
		switch (btn.getId()) 
		{
		case R.id.debugAction1:
			break;
		case R.id.debugAction2:
			break;
		case R.id.debugAction3:
			break;
		}
	}

}
