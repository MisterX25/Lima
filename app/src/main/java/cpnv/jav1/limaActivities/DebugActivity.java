package cpnv.jav1.limaActivities;

import java.util.ArrayList;
import java.util.zip.Inflater;

import cpnv.jav1.lima.R;
import cpnv.jav1.lima.R.id;
import cpnv.jav1.lima.R.layout;
import android.app.Activity;
import android.content.Intent;
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
    }

	// Any click on this screen will invoke this handler
	@Override
	public void onClick(View btn) {

		// Let's see which action must be performed
		switch (btn.getId()) 
		{
		case R.id.debugAction1: 
			_output.setText(_output.getText()+"\nAction 1");
			break;
		case R.id.debugAction2: 
			_output.setText(_output.getText()+"\nAction 2");
			break;
		case R.id.debugAction3: 
			_output.setText(_output.getText()+"\nAction 3");
			break;
		}
	}
		
}
