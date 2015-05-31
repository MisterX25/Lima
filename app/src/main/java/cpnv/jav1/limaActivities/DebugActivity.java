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
    private TextView id ;
    private LimaDb ldb=null;
    private String crecid; // current record id

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
        String query; // for db calls
		// Let's see which action must be performed
		switch (btn.getId()) 
		{
		case R.id.debugAction1:
            // Perform initialization
            ldb = new LimaDb("http://192.168.0.10/"); // don't forget the http:// at the beginning and the / at the end
/*
            if (ldb.connectionIsOK())
            {
                output.setText(output.getText()+"\nEchec de la connexion !");
            }
            else
            {
*/
                query = "Select idarticle, articlename, articlenumber, author, ISBN, price "+
                        "FROM article INNER JOIN bookdetail ON fk_article=idarticle";
                if (ldb.executeQuery(query) == 0)
                    output.setText(output.getText()+"\nAucun livre trouvé");
                else
                {
                    ldb.moveNext(); // read first element
                    id.setText(ldb.getField("idarticle"));
                    title.setText(ldb.getField("articlename"));
                    artnumb.setText(ldb.getField("articlenumber"));
                    author.setText(ldb.getField("author"));
                    isbn.setText(ldb.getField("ISBN"));
                    price.setText(ldb.getField("price"));
                    crecid = ldb.getField("idarticle"); // memorize current record
                }
//            }
			break;
		case R.id.debugAction2: // previous record
            if (ldb == null) return; // no database connection
            query = "Select idarticle, articlename, articlenumber, author, ISBN, price "+
                    "FROM article INNER JOIN bookdetail ON fk_article=idarticle "+
                    "WHERE idarticle < " + crecid + " " +
                    "ORDER by idarticle DESC";
            if (ldb.executeQuery(query) == 0)
                output.setText(output.getText()+"\nOn est arrivé au début de la liste");
            else
            {
                ldb.moveNext(); // read first element
                id.setText(ldb.getField("idarticle"));
                title.setText(ldb.getField("articlename"));
                artnumb.setText(ldb.getField("articlenumber"));
                author.setText(ldb.getField("author"));
                isbn.setText(ldb.getField("ISBN"));
                price.setText(ldb.getField("price"));
                crecid = ldb.getField("idarticle"); // memorize current record
            }
			break;
		case R.id.debugAction3: // next record
            if (ldb == null) return; // no database connection
            query = "Select idarticle, articlename, articlenumber, author, ISBN, price "+
                    "FROM article INNER JOIN bookdetail ON fk_article=idarticle "+
                    "WHERE idarticle > " + crecid + " " +
                    "ORDER by idarticle";
            if (ldb.executeQuery(query) == 0)
                output.setText(output.getText()+"\nOn est arrivé à la fin de la liste");
            else
            {
                ldb.moveNext(); // read first element
                id.setText(ldb.getField("idarticle"));
                title.setText(ldb.getField("articlename"));
                artnumb.setText(ldb.getField("articlenumber"));
                author.setText(ldb.getField("author"));
                isbn.setText(ldb.getField("ISBN"));
                price.setText(ldb.getField("price"));
                crecid = ldb.getField("idarticle"); // memorize current record
            }
            break;
        case R.id.debugAction4: // create
            if (ldb == null) return; // no database connection
            // Now insert data
            query = "INSERT INTO article (articlename, articlenumber, price) VALUES ('"+title.getText()+"','"+artnumb.getText()+"',"+price.getText()+")";
            if (ldb.executeQuery(query) == 0)
                output.setText(output.getText()+"\nErreur de création de l'article");
            else
            {
                // get last insert id
                ldb.executeQuery("Select idarticle as lid from article order by idarticle desc limit 1");
                ldb.moveNext();
                String lid = ldb.getField("lid");
                // Now insert the book details
                query = "INSERT INTO bookdetail (author, ISBN, fk_article) VALUES ('"+author.getText()+"','"+isbn.getText()+"',"+lid+")";
                if (ldb.executeQuery(query) == 0)
                {
                    output.setText(output.getText() + "\nErreur de création de l'article");
                    // we must get rid of the article
                    ldb.executeQuery("DELETE FROM article WHERE idarticle = " + lid);
                }
                else
                {
                    // get last insert id
                    ldb.executeQuery("Select idbookdetail as lid from bookdetail order by idbookdetail desc limit 1");
                    ldb.moveNext();
                    crecid = lid; // current record is the new one
                    output.setText(output.getText()+"\nNouveau livre créé:" + lid);
                    id.setText(crecid);
                }
            }

            break;
        case R.id.debugAction5: // read
            if (ldb == null) return; // no database connection
            query = "Select idarticle, articlename, articlenumber, author, ISBN, price "+
                    "FROM article INNER JOIN bookdetail ON fk_article=idarticle "+
                    "WHERE idarticle = " + id.getText() ;
            if (ldb.executeQuery(query) == 0)
            {
                output.setText(output.getText() + "\nArticle non trouvé");
                id.setText(crecid);
            }
            else
            {
                ldb.moveNext(); // read first element
                id.setText(ldb.getField("idarticle"));
                title.setText(ldb.getField("articlename"));
                artnumb.setText(ldb.getField("articlenumber"));
                author.setText(ldb.getField("author"));
                isbn.setText(ldb.getField("ISBN"));
                price.setText(ldb.getField("price"));
                crecid = ldb.getField("idarticle"); // memorize current record
                output.setText(output.getText()+"\nLivre chargé:" + crecid);
            }
            break;
        case R.id.debugAction6: // update
            if (ldb == null) return; // no database connection
            // We need the bookdetail id
            query = "SELECT idbookdetail FROM bookdetail WHERE fk_article = " + id.getText() ;
            ldb.executeQuery(query);
            ldb.moveNext(); // read element
            String bid = ldb.getField("idbookdetail");

            // Update the article part
            query = "UPDATE article SET articlename = '"+title.getText()+"', articlenumber = '"+artnumb.getText()+"', price = "+price.getText()+ " WHERE idarticle = " + id.getText() ;
            ldb.executeQuery(query);
            // Update the book part
            query = "UPDATE bookdetail SET ISBN = '"+isbn.getText()+"', author = '"+author.getText()+"' WHERE idbookdetail = " + bid ;
            ldb.executeQuery(query);
            output.setText(output.getText() + "\nLivre modifié");
            break;
        case R.id.debugAction7: // delete
            if (ldb == null) return; // no database connection
            query = "DELETE FROM article WHERE idarticle = " + id.getText() ;
            ldb.executeQuery(query);
            output.setText(output.getText() + "\nLivre supprimé.");
            break;

		}
	}

}
