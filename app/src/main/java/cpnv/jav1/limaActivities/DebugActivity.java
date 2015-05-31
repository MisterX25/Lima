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
    private EditText title ;
    private EditText artnumb ;
    private EditText author ;
    private EditText isbn ;
    private EditText price ;
    private LimaDb ldb=null;

    // Create activity event handler
    @Override
    public void onCreate(Bundle savedInstanceState) {
        // Initialize and display view
        super.onCreate(savedInstanceState);
        setContentView(R.layout.debug);

        // Log messages (to logcat)
        Log.i("LIMA", "Started debug activity!!!!!");

        // Setup event handler on action button
        btn = (Button)findViewById(R.id.debugAction1);
        btn.setOnClickListener(this);
        btn = (Button)findViewById(R.id.debugAction2);
        btn.setOnClickListener(this);
        btn = (Button)findViewById(R.id.debugAction3);
        btn.setOnClickListener(this);
        btn = (Button)findViewById(R.id.debugAction4);
        btn.setOnClickListener(this);
        btn = (Button)findViewById(R.id.debugAction5);
        btn.setOnClickListener(this);
        btn = (Button)findViewById(R.id.debugAction6);
        btn.setOnClickListener(this);

        // Get the references on the input fields
        title = (EditText)findViewById(R.id.txtTitle);
        artnumb = (EditText)findViewById(R.id.txtArtNumber);
        author = (EditText)findViewById(R.id.txtAuthor);
        isbn = (EditText)findViewById(R.id.txtISBN);
        price = (EditText)findViewById(R.id.txtPrice);

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
            // Perform initialization
            ldb = new LimaDb("http://192.168.0.51/"); // don't forget the http:// at the beginning and the / at the end
            if (ldb.executeQuery("Select articlename, articlenumber, author, ISBN, price from article INNER JOIN bookdetail ON fk_article=idarticle") == 0)
                output.setText(output.getText()+"\nAucun livre trouvé");
            else
            {
                ldb.moveNext(); // read first element
                title.setText(ldb.getField("articlename"));
                artnumb.setText(ldb.getField("articlenumber"));
                author.setText(ldb.getField("author"));
                isbn.setText(ldb.getField("ISBN"));
                price.setText(ldb.getField("price"));
            }
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
        case R.id.debugAction7:
            break;

		}
	}

}
