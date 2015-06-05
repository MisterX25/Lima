package cpnv.jav1.limaActivities;

import cpnv.jav1.lima.LimaDb;
import cpnv.jav1.lima.LimaException;
import cpnv.jav1.lima.R;
import cpnv.jav1.limaEntities.Book;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;

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
    private TextView id ;
    private LimaDb ldb=null;
    private String crecid; // current record id

    Book myBook = null; // one Book object for various tests

    // Create activity event handler
    @Override
    public void onCreate(Bundle savedInstanceState) {
        // Initialize and display view
        super.onCreate(savedInstanceState);
        setContentView(R.layout.debug);

        // Log messages (to logcat)
        Log.i("LIMA", "Started debug activity!!!!!");

        // Setup event handler on action button
        btn = (Button)findViewById(R.id.navList);
        btn.setOnClickListener(this);
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
        btn = (Button)findViewById(R.id.debugAction7);
        btn.setOnClickListener(this);

        // Get the references on the input fields
        title = (EditText)findViewById(R.id.txtTitle);
        artnumb = (EditText)findViewById(R.id.txtArtNumber);
        author = (EditText)findViewById(R.id.txtAuthor);
        isbn = (EditText)findViewById(R.id.txtISBN);
        price = (EditText)findViewById(R.id.txtPrice);
        id = (TextView)findViewById(R.id.txtID);

        // Get reference on the output textview
		output = (TextView)findViewById(R.id.outputzone);

        // Initialize the text field to the app version
        output.setText("Version: " + getString(R.string.app_version));

    }

	// Any click on this screen will invoke this handler
	@Override
	public void onClick(View btn) {

        SimpleDateFormat sdate = new SimpleDateFormat("dd-M-yyyy");
        String query;

		// Let's see which action must be performed
		switch (btn.getId()) 
		{
        case R.id.navList:
            startActivity(new Intent(this, BookListActivity.class));
            break;

		case R.id.debugAction1:
            myBook = new Book();
            try {
                myBook.readFirst();
                showBook(myBook);
                output.setText(output.getText() + "\nPremier livre chargé ("+myBook.getId()+")");
            } catch (LimaException le) {
                output.setText(output.getText()+"\nPas de livres ("+le.getMessage()+")");
            }
			break;
		case R.id.debugAction2: // previous record
            if (myBook == null)
            {
                Toast toast = Toast.makeText(getApplicationContext(), "Pas de livre courant", Toast.LENGTH_SHORT);
                toast.show();
            }
            else
            {
                try {
                    myBook.readPrevious();
                    showBook(myBook);
                    output.setText(output.getText() + "\nLivre précédent chargé ("+myBook.getId()+")");
                } catch (LimaException le) {
                    output.setText(output.getText()+"\nDébut de liste atteint ("+le.getMessage()+")");
                }
            }
			break;
		case R.id.debugAction3: // next record
            if (myBook == null)
            {
                Toast toast = Toast.makeText(getApplicationContext(), "Pas de livre courant", Toast.LENGTH_SHORT);
                toast.show();
            }
            else
            {
                try {
                    myBook.readNext();
                    showBook(myBook);
                    output.setText(output.getText() + "\nLivre suivant chargé ("+myBook.getId()+")");
                } catch (LimaException le) {
                    output.setText(output.getText()+"\nFin de liste atteinte ("+le.getMessage()+")");
                }
            }
            break;
        case R.id.debugAction4: // create
            try {
                myBook = new Book(title.getText().toString(), artnumb.getText().toString(), "", "", Float.parseFloat(price.getText().toString()), 8, 0, false, author.getText().toString(), Long.parseLong(isbn.getText().toString()));
                myBook.create();
                id.setText(Integer.toString(myBook.getId()));
                output.setText(output.getText() + "\nLivre créé (" + Integer.toString(myBook.getId()) + ")");
            } catch (LimaException le) {
                output.setText(output.getText()+"\nEchec de création du livre ("+le.getMessage()+")");
            }
            break;
        case R.id.debugAction5: // read
            try {
                myBook = new Book();
                myBook.setNumber(artnumb.getText().toString());
                myBook.read();
                id.setText(Integer.toString(myBook.getId()));
                showBook(myBook);
                output.setText(output.getText() + "\nLivre lu (" + Integer.toString(myBook.getId()) + ")");
            } catch (LimaException le){
                output.setText(output.getText()+"\nEchec de lecture du livre ("+le.getMessage()+")");
            }
            break;
        case R.id.debugAction6: // update
            if (myBook == null)
            {
                Toast toast = Toast.makeText(getApplicationContext(), "Pas de livre courant", Toast.LENGTH_SHORT);
                toast.show();
            }
            else
            {
                try {
                    myBook.setName(title.getText().toString());
                    myBook.setNumber(artnumb.getText().toString());
                    myBook.setAuthor(author.getText().toString());
                    myBook.setISBN(Long.parseLong(isbn.getText().toString()));
                    myBook.setPrice(Float.parseFloat(price.getText().toString()));
                    myBook.update();
                    output.setText(output.getText() + "\nLivre mis à jour");
                } catch (LimaException le) {
                    output.setText(output.getText() + "\nEchec la mise à jour du livre (" + le.getMessage() + ")");
                }
            }
            break;
        case R.id.debugAction7: // delete
            if (myBook == null)
            {
                Toast toast = Toast.makeText(getApplicationContext(), "Pas de livre courant", Toast.LENGTH_SHORT);
                toast.show();
            }
            else
            {
                try {
                    myBook.delete();
                    output.setText(output.getText() + "\nLivre supprimé");
                    showBook(myBook);
                } catch (LimaException le) {
                    output.setText(output.getText()+"\nErreur de suppression ("+le.getMessage()+")");
                }
            }
            break;

		}
	}

    private void showBook(Book abook) // Put an object in the display
    {
        id.setText(Integer.toString(abook.getId()));
        title.setText(abook.getName());
        artnumb.setText(abook.getNumber());
        author.setText(abook.getAuthor());
        isbn.setText(Long.toString(abook.getISBN()));
        price.setText(Double.toString(abook.getPrice()));
    }
}
