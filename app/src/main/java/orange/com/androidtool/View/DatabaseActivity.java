package orange.com.androidtool.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import orange.com.androidtool.Database.DatabaseFactory;
import orange.com.androidtool.R;

public class DatabaseActivity extends AppCompatActivity {

    private EditText etInsertAccount;
    private EditText etInsertPass;
    private EditText etSelectAccount;
    private EditText etResult;
    private Button btn_register;
    private Button btn_select;
    private Button btn_clear;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);
        initView();

        //插入数据的按钮相应
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = etInsertAccount.getText().toString();
                String password = etInsertPass.getText().toString();
                DatabaseFactory.getMsgTable(DatabaseActivity.this).insertUserInfo(userName, password);
            }
        });

        //通过用户名查询密码的按钮响应
        btn_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = etSelectAccount.getText().toString();
                String result = DatabaseFactory.getMsgTable(DatabaseActivity.this).selectPasswordByUserName(s);
                etResult.setText(result);
            }
        });

        //清空数据库当中的表
        btn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean clearResult = DatabaseFactory.getInstance(DatabaseActivity.this).clearAllTable();
                if (clearResult) {
                    Toast.makeText(DatabaseActivity.this, "清空数据库成功", Toast.LENGTH_SHORT);
                } else {
                    Toast.makeText(DatabaseActivity.this, "清空数据库失败", Toast.LENGTH_SHORT);
                }
            }
        });


        System.out.println("*************************" + System.currentTimeMillis());

    }

    private void initView() {
        etInsertAccount = (EditText) findViewById(R.id.et_user_name);
        etInsertPass = (EditText) findViewById(R.id.et_pass);
        etSelectAccount = (EditText) findViewById(R.id.et_select_name);
        etResult = (EditText) findViewById(R.id.et_result);
        btn_register = (Button) findViewById(R.id.btn_register);
        btn_select = (Button) findViewById(R.id.btn_select);
        btn_clear = (Button)findViewById(R.id.btn_clear);
    }
}