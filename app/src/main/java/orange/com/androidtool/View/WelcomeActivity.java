package orange.com.androidtool.View;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import orange.com.androidtool.Database.DatabaseFactory;
import orange.com.androidtool.R;

public class WelcomeActivity extends AppCompatActivity {

    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        //创建一个计数器count，第一次进入app会插入初始数据，之后在打开app不会再插入数据
        preferences = getSharedPreferences("count", MODE_WORLD_READABLE);
        int count = preferences.getInt("count", 0);
        if (count == 0) {
            //插入初始数据
            DatabaseFactory.getMsgTable(WelcomeActivity.this).insertTestData();
        }
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("count", ++count);
        editor.commit();

        /*
        以这种方式启动欢迎界面，持续的时间是固定的，通常用于放置广告
         */
        Handler handler = new Handler();

        //当计时结束的时候，自动跳转到主页面
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                WelcomeActivity.this.finish();
            }
        }, 2000);


//        /*
//        按照下面的方式启动欢迎界面，会在后台任务处理完成后自动结束，适合数据加载
//         */
//        new Thread(new Runnable() {     //开启子线程，后台处理耗时任务
//            @Override
//            public void run() {
//                /*
//                在这里处理耗时的任务，例如网络数据的加载，本例初始化数据库的测试数据
//                 */
//
//                //创建一个计数器count，第一次进入app会插入初始数据，之后在打开app不会再插入数据
//                preferences = getSharedPreferences("count", MODE_WORLD_READABLE);
//                int count = preferences.getInt("count", 0);
//                if (count == 0) {
//                    //插入初始数据
//                    DatabaseFactory.getMsgTable(WelcomeActivity.this).insertTestData();
//                }
//                SharedPreferences.Editor editor = preferences.edit();
//                editor.putInt("count", ++count);
//                editor.commit();
//
//                runOnUiThread(new Runnable() {   //返回主线程
//                    @Override
//                    public void run() {
//                        Intent intent = new Intent(WelcomeActivity.this, DatabaseActivity.class);
//                        startActivity(intent);
//                        WelcomeActivity.this.finish();
//                    }
//                });
//            }
//        }).start();


    }
}
