package orange.com.androidtool.View;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import orange.com.androidtool.R;

public class MyTabActivity extends TabActivity {

    /*
     * 跳到会话界面的标记
     */
    public static final int TAB_SESSION = 0;
    /*
     *跳到通讯录界面的标记
     */
    public static final int TAB_CONTACT = 1;
    /**
     * 跳到设置界面的标记
     */
    public static final int TAB_SETTING = 2;

    /**
     * 每个tab的名称
     */
    private static final int TAB_NAME[] = {
            R.string.tab_name_1, R.string.tab_name_2, R.string.tab_name_3
    };

    /**
     * 每个tab的图片样式
     */
    private static final int TAB_PICTURE[] = {
            R.drawable.action_tab_1,
            R.drawable.action_tab_2,
            R.drawable.action_tab_3
    };

    /*
    设置默认的tab为会话界面
     */
    private static int default_tab = TAB_SESSION;

    /*
    设置3个intent
     */
    Intent intents[] = new Intent[TAB_NAME.length];

    /*
    声明一个TabHost的对象tabHost
     */
    private TabHost tabHost;

    /*
    声明一个LayoutInflater的对象inflater；
     */
    private LayoutInflater inflater;

    /**
     * 设置tab函数
     * @param val
     */
    public static void setTab(int val){
        if (val>=0&&val<=2){
            default_tab = val;
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);

        initView();
    }

    private void initView(){
        this.tabHost = MyTabActivity.this.getTabHost();
        this.inflater = LayoutInflater.from(MyTabActivity.this);
        tabHost.clearAllTabs();
        tabHost.setup();

        this.intents[TAB_SESSION] = new Intent(MyTabActivity.this, SessionActivity.class);
        this.intents[TAB_CONTACT] = new Intent(MyTabActivity.this, ContacterActivity.class);
        this.intents[TAB_SETTING] = new Intent(MyTabActivity.this, SettingActivity.class);

        for (int i=0; i<intents.length; i++){
            /**
             * 为每个Tab设置图片、文字和内容
             */
            String s = MyTabActivity.this.getResources().getString(TAB_NAME[i]);
            TabHost.TabSpec tabSpec = tabHost.newTabSpec(s).setIndicator(getTabItemView(i)).setContent(intents[i]);
            this.tabHost.addTab(tabSpec);
        }
        tabHost.setCurrentTab(default_tab);
    }

    private View getTabItemView(final int index){
        View view = inflater.inflate(R.layout.layout_tab, null);
        ItemView item = new ItemView();
        item.ivPicture = (ImageView)view.findViewById(R.id.tab_p0000_iv);
        item.ivPicture.setImageDrawable(MyTabActivity.this.getResources().getDrawable(TAB_PICTURE[index]));

        item.tvName = (TextView)view.findViewById(R.id.tab_p0000_tv);
        item.tvName.setText(TAB_NAME[index]);

        item.tvCount = (TextView)view.findViewById(R.id.tab_p0000_tv_count);
        if (index!=TAB_SESSION){
            item.tvCount.setVisibility(View.GONE);
        }
        return view;
    }

    private class ItemView{
        private TextView tvName;
        private ImageView ivPicture;
        private TextView tvCount;
    }
}


