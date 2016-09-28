package orange.com.androidtool.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import orange.com.androidtool.Database.Database;
import orange.com.androidtool.Database.DatabaseFactory;
import orange.com.androidtool.Info.Msg;
import orange.com.androidtool.R;

public class ItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        Bundle bundle = getIntent().getExtras();
        int x = bundle.getInt("s");
        System.out.println(x);

        List<Msg> list;
        list = DatabaseFactory.getMsgTable(ItemActivity.this).selectAllInfo();
        System.out.println(list.get(x).getName());
        System.out.println(list.get(x).getPassword());
    }
}
