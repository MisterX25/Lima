package cpnv.jav1.limaActivities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import cpnv.jav1.lima.LimaException;
import cpnv.jav1.lima.R;
import cpnv.jav1.limaEntities.Book;

/**
 * Created by Xavier on 05.06.15.
 */
public class BookListActivity extends Activity implements View.OnClickListener {

    private Button btn;
    private TextView output;
    private ArrayList<Book> myBooks= new ArrayList<Book>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // Initialize and display view
        super.onCreate(savedInstanceState);
        setContentView(R.layout.booklist);

        // Log messages (to logcat)
        Log.i("LIMA", "Started booklist activity!!!!!");

        // Setup event handler on action button
        btn = (Button)findViewById(R.id.navDebug);
        btn.setOnClickListener(this);

        // Get reference on the output textview
        output = (TextView)findViewById(R.id.outputzoneList);

        initList();
        dumpList();
    }

    @Override
    public void onClick(View view) {
        switch (btn.getId()) {
            case R.id.navDebug:
                startActivity(new Intent(this, DebugActivity.class));
                break;
        }
    }

    private void initList() // Loads the myBooks collection with all books of the db
    {
        Book iterator = new Book();
        try {
            iterator.readFirst();
            while (true) // we'll exit with the exception at end of table
            {
                myBooks.add(new Book(iterator));
                iterator.readNext();
            }
        } catch (LimaException le) {
        }
        output.setText("Nombre de livres dans myBooks: "+ myBooks.size());
    }

    private void dumpList() // outputs all books
    {
        for (Book b : myBooks)
            output.setText(output.getText()+"\n"+b.dump());
    }
}
