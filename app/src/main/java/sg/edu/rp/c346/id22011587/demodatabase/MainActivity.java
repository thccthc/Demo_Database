package sg.edu.rp.c346.id22011587.demodatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnInsert, btnGetTasks;
    TextView tvResults;
    ListView lv;
    EditText editTask, editDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInsert = findViewById(R.id.btnInsert);
        btnGetTasks = findViewById(R.id.btnGetTasks);
        tvResults = findViewById(R.id.tvResults);
        lv = findViewById(R.id.lv);
        editTask = findViewById(R.id.editTask);
        editDate = findViewById(R.id.editDate);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String task = editTask.getText().toString();
                String date = editDate.getText().toString();

                // Create the DBHelper object, passing in the activity's Context
                DBHelper db = new DBHelper(MainActivity.this);

                // Insert the user-entered task
                db.insertTask(task, date);
            }
        });
        btnGetTasks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper db = new DBHelper(MainActivity.this);
                ArrayList<Task> tasks = db.getTasks();
                db.close();

                String txt = "";
                for (int i = 0; i < tasks.size(); i++) {
                    Log.d("Database Content", i +". "+tasks.get(i));
                    txt += tasks.get(i) + "\n";
                }
                tvResults.setText(txt);

                ArrayAdapter<Task> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, tasks);
                lv.setAdapter(adapter);
            }
        });
    }
}