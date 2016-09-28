package orange.com.androidtool.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import orange.com.androidtool.Database.Database;
import orange.com.androidtool.Database.DatabaseFactory;
import orange.com.androidtool.R;

public class MainActivity extends AppCompatActivity {
    private Button btn_database;
    private Button btn_list;
    private Button btn_tab;
    private Button btn_photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        btn_database.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DatabaseActivity.class);
                startActivity(intent);
            }
        });

        btn_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListActivity.class);
                startActivity(intent);
            }
        });

        btn_tab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MyTabActivity.class);
                startActivity(intent);
            }
        });

        btn_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PhotoActivity.class);
                startActivity(intent);
            }
        });

    }

    private void initView(){
        btn_database = (Button)findViewById(R.id.btn_database);
        btn_list = (Button)findViewById(R.id.btn_list);
        btn_tab = (Button)findViewById(R.id.btn_tab);
        btn_photo = (Button)findViewById(R.id.btn_photo_load);
    }

}
