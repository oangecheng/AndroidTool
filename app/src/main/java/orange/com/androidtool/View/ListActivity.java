package orange.com.androidtool.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import orange.com.androidtool.Adapter.MyAdapter;
import orange.com.androidtool.Database.DatabaseFactory;
import orange.com.androidtool.Info.Msg;
import orange.com.androidtool.R;

public class ListActivity extends AppCompatActivity {
    private MyAdapter adapter;
    private List<Msg> msgList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        msgList = DatabaseFactory.getMsgTable(ListActivity.this).selectAllInfo();
        //System.out.println(msgList.get(1).getName());

        this.adapter = new MyAdapter(msgList, ListActivity.this);

        ListView listView = (ListView)findViewById(R.id.lv_database);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(ListActivity.this, ""+position, Toast.LENGTH_SHORT).show();
                //System.out.println(id);
                //System.out.println(position);
                System.out.println("**********"+id);

                Intent intent = new Intent(ListActivity.this, ItemActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("s", position);
                intent.putExtras(bundle);
                startActivity(intent);
            }

        });
    }
}
