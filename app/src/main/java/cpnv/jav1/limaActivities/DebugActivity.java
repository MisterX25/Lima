package cpnv.jav1.limaActivities;

import cpnv.jav1.lima.LimaDb;
import cpnv.jav1.lima.R;
import cpnv.jav1.limaEntities.ClassTeacher;
import cpnv.jav1.limaEntities.Student;
import cpnv.jav1.limaEntities.Teacher;

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
	private Button btn;
	private TextView output;

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

       	// Get reference on the output textview
		output = (TextView)findViewById(R.id.outputzone);

        // Initialize the text field to the app version
        output.setText("Version: " + getString(R.string.app_version));
    }

	// Any click on this screen will invoke this handler
	@Override
	public void onClick(View btn) {

		// Let's see which action must be performed
		switch (btn.getId()) 
		{
		case R.id.debugAction1:
            // prof
            Teacher oneTeacher = new Teacher("john","doe");
            oneTeacher.setsection("info");
            output.setText(output.getText() + "\n" + oneTeacher.dump());
			break;
		case R.id.debugAction2:
            Teacher anotherTeacher = new Teacher("al","capone");
            anotherTeacher.setsection("info");
            ClassTeacher oneMC = new ClassTeacher(anotherTeacher,"SI-T1a");
            output.setText(output.getText() + "\n" + oneMC.dump());
			break;
		case R.id.debugAction3:
            Student oneStudent = new Student();
            oneStudent.setfirstName("Jean-Marc");
            oneStudent.setlastName("Di Giacomo");
            oneStudent.setstartYear(2011);
            output.setText(output.getText() + "\n" + oneStudent.dump());
            break;

        case R.id.debugAction4: // test db
            LimaDb limaDb = new LimaDb("http://192.168.0.10/");
            if (limaDb.connectionIsOK())
                output.setText(output.getText() + "\nConnexion DB OK");
            else
            {
                output.setText(output.getText() + "\nConnexion to DB failed");
                return;
            }
            if (limaDb.executeQuery("SELECT personfirstname, personlastname FROM person WHERE role=2") > 0)
                while (limaDb.moveNext())
                    output.setText(output.getText() + "\n" + limaDb.getField("personfirstname") + " " + limaDb.getField("personlastname"));
            else
                output.setText(output.getText() + "\nAucune personne trouvée");

			break;
		}
	}

}
