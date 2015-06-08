package cpnv.jav1.limaActivities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

import cpnv.jav1.lima.LimaException;
import cpnv.jav1.lima.R;
import cpnv.jav1.limaEntities.Book;

/**
 * Created by Xavier on 05.06.15.
 */
public class BookListActivity extends Activity implements View.OnClickListener {

    private Button btn;
    private ListView listview;
    private ArrayList<Book> myBooks= new ArrayList<Book>();
    private ArrayAdapter<Book> adapter;

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
        btn = (Button)findViewById(R.id.sortAsc);
        btn.setOnClickListener(this);
        btn = (Button)findViewById(R.id.sortDesc);
        btn.setOnClickListener(this);

        // Get reference on the output listview
        listview = (ListView) findViewById(R.id.listview);

        initList();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.navDebug:
                startActivity(new Intent(this, DebugActivity.class));
                break;
            case R.id.sortAsc:
                Collections.sort(myBooks);
                adapter.notifyDataSetChanged();
                break;
            case R.id.sortDesc:
                Collections.sort(myBooks);
                Collections.reverse(myBooks);
                adapter.notifyDataSetChanged();
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
                // Log.i("LIMA","------Book read: "+iterator.dump());
            }
        } catch (LimaException le) {
        } catch (Exception e) {
            Log.i ("LIMA","Error in initlist: "+e.getMessage());
        }
        adapter = new ArrayAdapter<Book>(this,android.R.layout.simple_list_item_1, android.R.id.text1,myBooks);
        listview.setAdapter(adapter);
    }

}
