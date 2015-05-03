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
            evalArticle();
			break;
		case R.id.debugAction2:
            evalBook();
			break;
		case R.id.debugAction3:
            evalGear();
			break;
		}
	}

    // Evaluation of the Article class implementation
    private void evalArticle()
    {
        int res = 0; //total number of points obtained
        int ev=0; // eval of one criterion
        Article myArticle;

        _output.setText("Evaluation Article:\n");

        ev = 0;
        myArticle = new Article(); // Instanciate one article
        if (myArticle.dump().equals("(à définir)-(à définir)-(à définir)-(à définir)-0.0-0.0-0-false"))
        {
            ev++;
            res++;
        }

        _output.setText(_output.getText()+"\nCréation instance vierge: "+ev+"/1");

        ev = 0;
        myArticle = new Article("Crayon","1234","Toto","Migros",0.65f,8.25f,100,false);
        if (myArticle.dump().equals("Crayon-1234-Toto-Migros-0.65-8.25-100-false"))
        {
            ev++;
            res++;
        }
        myArticle = new Article("Crayon","","Toto","Migros",0.65f,8.25f,100,false);
        if (myArticle.dump().equals("Crayon-(à définir)-Toto-Migros-0.65-8.25-100-false"))
        {
            ev++;
            res++;
        }
        myArticle = new Article("Crayon","1234","To","Migros",0.65f,8.25f,100,false);
        if (myArticle.dump().equals("Crayon-1234-(à définir)-Migros-0.65-8.25-100-false"))
        {
            ev++;
            res++;
        }
        myArticle = new Article("Crayon","1234","Toto","Migros",-0.65f,8.25f,100,false);
        if (myArticle.dump().equals("Crayon-1234-Toto-Migros-0.0-8.25-100-false"))
        {
            ev++;
            res++;
        }
        myArticle = new Article("C","1234","Toto","M",0.65f,8.25f,-100,false);
        if (myArticle.dump().equals("(à définir)-1234-Toto-(à définir)-0.65-8.25-0-false"))
        {
            ev++;
            res++;
        }
        _output.setText(_output.getText()+"\nCréation instance avec valeurs: "+ev+"/5");

        // finish
        _output.setText(_output.getText()+"\nScore final: "+res+"/6");
    }

    // Evaluation of the Article class implementation
    private void evalBook()
    {
        int res = 0; //total number of points obtained
        int ev=0; // eval of one criterion
        Book myBook;

        _output.setText("Evaluation Book:\n");

        ev = 0;
        myBook = new Book(); // Instanciate one Book
        if (myBook.dump().equals("(à définir)-(à définir)-(à définir)-(à définir)-0.0-0.0-0-false-(à définir)-0"))
        {
            ev++;
            res++;
        }

        _output.setText(_output.getText()+"\nCréation instance vierge: "+ev+"/1");

        ev = 0;
        myBook = new Book("Andromaque","5544","Toto","Payot",0.65f,8.25f,100,false,"Racine",98765);
        if (myBook.dump().equals("Andromaque-5544-Toto-Payot-0.65-8.25-100-false-Racine-98765"))
        {
            ev++;
            res++;
        }
        myBook = new Book("Andromaque","","Toto","Payot",0.65f,8.25f,100,false,"Racine",98765);
        if (myBook.dump().equals("Andromaque-(à définir)-Toto-Payot-0.65-8.25-100-false-Racine-98765"))
        {
            ev++;
            res++;
        }
        myBook = new Book("Andromaque","5544","To","Payot",0.65f,8.25f,100,false,"Racine",98765);
        if (myBook.dump().equals("Andromaque-5544-(à définir)-Payot-0.65-8.25-100-false-Racine-98765"))
        {
            ev++;
            res++;
        }
        myBook = new Book("Andromaque","5544","Toto","Payot",0.65f,-8.25f,100,false,"Racine",98765);
        if (myBook.dump().equals("Andromaque-5544-Toto-Payot-0.65-0-100-false-Racine-98765"))
        {
            ev++;
            res++;
        }
        myBook = new Book("An","5544","Toto","P",0.65f,8.25f,-100,false,"Racine",98765);
        if (myBook.dump().equals("(à définir)-5544-Toto-(à définir)-0.65-8.25-0-false-Racine-98765"))
        {
            ev++;
            res++;
        }
        myBook = new Book("Andromaque","5544","Toto","Payot",0.65f,8.25f,100,false,"Ra",98765);
        if (myBook.dump().equals("Andromaque-5544-Toto-Payot-0.65-8.25-100-false-(à définir)-98765"))
        {
            ev++;
            res++;
        }
        myBook = new Book("Andromaque","5544","Toto","Payot",0.65f,8.25f,100,false,"Racine",-95);
        if (myBook.dump().equals("Andromaque-5544-Toto-Payot-0.65-8.25-100-false-Racine-0"))
        {
            ev++;
            res++;
        }

        _output.setText(_output.getText()+"\nCréation instance avec valeurs: "+ev+"/6");

        // finish
        _output.setText(_output.getText()+"\nScore final: "+res+"/7");    }

    // Evaluation of the Article class implementation
    private void evalGear()
    {
        int res = 0; //number of points obtained
        int ev=0; // eval of one criterion
        Gear myGear;

        _output.setText("Evaluation Gear:\n");

        ev = 0;
        myGear = new Gear();
        if (myGear.dump().equals("(à définir)-(à définir)-(à définir)-(à définir)-0.0-0.0-0-false-?"))
        {
            ev++;
            res++;
        }

        _output.setText(_output.getText()+"\nCréation instance vierge: "+ev+"/1");

        ev = 0;
        myGear = new Gear("Blouse","56982","Toto","SUVA",34.5f,8.25f,78,false,"XS");
        if (myGear.dump().equals("Blouse-56982-Toto-SUVA-34.5-8.25-78-false-XS"))
        {
            ev++;
            res++;
        }
        myGear = new Gear("Blouse","","Toto","SUVA",34.5f,8.25f,78,false,"L");
        if (myGear.dump().equals("Blouse-(à définir)-Toto-SUVA-34.5-8.25-78-false-L"))
        {
            ev++;
            res++;
        }
        myGear = new Gear("Blouse","56982","To","SUVA",34.5f,8.25f,78,false,"XL");
        if (myGear.dump().equals("Blouse-56982-(à définir)-SUVA-34.5-8.25-78-false-XL"))
        {
            ev++;
            res++;
        }
        myGear = new Gear("Blouse","56982","Toto","SUVA",34.5f,-8.25f,78,false,"S");
        if (myGear.dump().equals("Blouse-56982-Toto-SUVA-34.5-0-78-false-S"))
        {
            ev++;
            res++;
        }
        myGear = new Gear("An","56982","Toto","P",34.5f,8.25f,-78,false,"M");
        if (myGear.dump().equals("(à définir)-56982-Toto-(à définir)-34.5-8.25-0-false-M"))
        {
            ev++;
            res++;
        }
        myGear = new Gear("Blouse","56982","Toto","SUVA",34.5f,8.25f,78,false,"N");
        if (myGear.dump().equals("Blouse-56982-Toto-SUVA-34.5-8.25-78-false-?"))
        {
            ev++;
            res++;
        }
        myGear = new Gear("Blouse","56982","T","SUVA",34.5f,8.25f,78,false,"Z");
        if (myGear.dump().equals("Blouse-56982-(à définir)-SUVA-34.5-8.25-78-false-?"))
        {
            ev++;
            res++;
        }

        _output.setText(_output.getText()+"\nCréation instance avec valeurs: "+ev+"/6");

        // finish
        _output.setText(_output.getText()+"\nScore final: "+res+"/7");
    }

}
