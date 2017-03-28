package orange.com.androidtool.View;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.URL;
import java.util.List;

import orange.com.androidtool.R;
import orange.com.androidtool.Widget.ContainsEmojiEditText;

public class MainActivity extends AppCompatActivity {
    private Button btn_database;
    private Button btn_list;
    private Button btn_tab;
    private Button btn_photo;
    private EditText etText;
    private Button btnPermission;
    private ImageView imageView;

    Bitmap bitmap;

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1){
                imageView.setImageBitmap(bitmap);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        new Thread(){
            @Override
            public void run() {
                try {
                    Socket socket = new Socket("192.168.1.17", 30000);
                    BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    String line = br.readLine();
                    Toast.makeText(MainActivity.this, line, Toast.LENGTH_SHORT).show();
                    br.close();
                    socket.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }.start();



        //读取json文件当中的数据
        StringBuilder stringBuilder = new StringBuilder();
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(MainActivity.this.getAssets().open("city_code.json")));
            String line;
            while ((line = bf.readLine())!=null){
                stringBuilder.append(line);
            }
        }catch (IOException e){
            e.printStackTrace();
        }

        String sw = stringBuilder.toString();

//        List<YourBean> list = new ArrayList();
        try {
            JSONArray array = new JSONArray(sw);
            int len = array.length();
            for (int i = 0; i<len;i++){
                JSONObject object = array.getJSONObject(i);
                if (object.has(CityEntry.KEY_NAME)&&object.has(CityEntry.KEY_CITY)&&object.has(CityEntry.KEY_CODE)){
                    //list.add(new YourBean(object.getString(CityEntry.KEY_CITY));
                }
            }
        }catch (JSONException e){
            e.printStackTrace();
        }


        initView();
        initEvents();

        //获取手机型号
        System.out.println(Build.MODEL+"******************");
        //获取SDK版本
        System.out.println(Build.VERSION.SDK+"******************");
        //获取Android版本
        System.out.println(Build.VERSION.RELEASE+"******************");

        String s = Build.VERSION.RELEASE;
        String l = s.substring(0,3);
        float version = Float.valueOf(l);
        if (version<5.2){
            System.out.println("********删除短信");
        }
        System.out.println(l);
    }






    private void initEvents(){

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
                String s = etText.getText().toString();
                boolean ret;
                ret = ContainsEmojiEditText.containsEmoji(s);
                if (ret) {
                    System.out.println("包含表情");
                }
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

        btnPermission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String file = Environment.getExternalStorageDirectory().getAbsolutePath()+"/mmmmm.jpg";

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            URL url = new URL("http://wx.qlogo.cn/mmopen/PiajxSqBRaELbpia4JSoISpqsETE6SXcQlQ0XxpJWAyyYlibUIA0sMyvj0TSCHKqNUkAkEQmW7B511G8U4FarGKrQ/0");
                            InputStream is = url.openStream();
                            bitmap = BitmapFactory.decodeStream(is);
                            handler.sendEmptyMessage(1);
                            is.close();
                            is = url.openStream();

                            OutputStream os = new FileOutputStream(file);

                            //OutputStream os = openFileOutput("headMap.jpg", MODE_PRIVATE);
                            byte[] buff = new byte[1024];
                            int hasRead = 0;
                            while ((hasRead = is.read(buff))>0){
                                os.write(buff, 0, hasRead);
                            }
                            is.close();
                            os.close();

                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }).start();

            }
        });



    }

    private void initView(){
        btn_database = (Button)findViewById(R.id.btn_database);
        btn_list = (Button)findViewById(R.id.btn_list);
        btn_tab = (Button)findViewById(R.id.btn_tab);
        btn_photo = (Button)findViewById(R.id.btn_photo_load);
        etText  =(EditText) findViewById(R.id.et_test);
        btnPermission = (Button)findViewById(R.id.btn_permission);
        imageView = (ImageView)findViewById(R.id.iv_net);
    }

    private static class CityEntry {
        private static final String FILE_NAME = "city_code.json";
        private static final String KEY_CODE = "code";
        private static final String KEY_NAME = "name";
        private static final String KEY_CITY = "cities";

    }

}
