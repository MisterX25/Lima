package cpnv.jav1.limaActivities;

import cpnv.jav1.lima.LimaDb;
import cpnv.jav1.lima.LimaException;
import cpnv.jav1.lima.R;
import cpnv.jav1.limaEntities.ClassTeacher;
import cpnv.jav1.limaEntities.Person;
import cpnv.jav1.limaEntities.Student;
import cpnv.jav1.limaEntities.Teacher;

import android.app.Activity;
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
            // prof
            try {
                Teacher oneTeacher = new Teacher(fname.getText().toString(),lname.getText().toString());
                oneTeacher.setsection(param2.getText().toString());
                oneTeacher.setBirthDate(sdate.parse(param1.getText().toString()));
                output.setText(output.getText() + "\n" + oneTeacher.dump());
            }
            catch (LimaException le){
                output.setText(output.getText() + "\nErreur d'instanciation du prof (" + le.getMessage() + ")");
            }
            catch (Exception e) {
                output.setText(output.getText() + "\nErreur d'instanciation du prof de classe (" + e.getMessage() + ")");
            }
			break;
		case R.id.debugAction2:
            try {
                Teacher anotherTeacher = new Teacher(fname.getText().toString(),lname.getText().toString());
                anotherTeacher.setBirthDate(sdate.parse(param1.getText().toString()));
                anotherTeacher.setsection(param2.getText().toString());
                ClassTeacher oneMC = new ClassTeacher(anotherTeacher,param3.getText().toString());
                output.setText(output.getText() + "\n" + oneMC.dump());
            }
            catch (LimaException le) {
                output.setText(output.getText() + "\nErreur d'instanciation du prof de classe (" + le.getMessage() + ")");
            }
            catch (Exception e) {
                output.setText(output.getText() + "\nErreur d'instanciation du prof de classe (" + e.getMessage() + ")");
            }
			break;
		case R.id.debugAction3:
            Student oneStudent = new Student();
            try {
                oneStudent.setfirstName(fname.getText().toString());
                oneStudent.setlastName(lname.getText().toString());
                oneStudent.setBirthDate(sdate.parse(param1.getText().toString()));
                oneStudent.setstartYear(Integer.parseInt(param2.getText().toString()));
                output.setText(output.getText() + "\n" + oneStudent.dump());
            }
            catch (LimaException le){
                output.setText(output.getText() + "\nErreur d'instanciation d'élève (" + le.getMessage() + ")");
            }
            catch (Exception e) {
                output.setText(output.getText() + "\nErreur d'instanciation d'élève (" + e.getMessage() + ")");
            }
            break;

        case R.id.debugAction4: // test db
            output.setText(output.getText() + "\nConnexion DB ... ");
            LimaDb limaDb = new LimaDb("http://192.168.0.51/");
            if (limaDb.connectionIsOK())
                output.setText(output.getText() + " OK");
            else
            {
                output.setText(output.getText() + " failed");
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
