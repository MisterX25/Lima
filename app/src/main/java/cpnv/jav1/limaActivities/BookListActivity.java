package cpnv.jav1.limaActivities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import cpnv.jav1.lima.R;

/**
 * Created by Xavier on 05.06.15.
 */
public class BookListActivity extends Activity implements View.OnClickListener {

    private Button btn;
    private TextView output;

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
        output = (TextView)findViewById(R.id.outputzoneGear);

        // Initialize the text field to the app version
        output.setText("Version: " + getString(R.string.app_version));

    }

    @Override
    public void onClick(View view) {
        switch (btn.getId()) {
            case R.id.navDebug:
                startActivity(new Intent(this, DebugActivity.class));
                break;
        }
    }
}
