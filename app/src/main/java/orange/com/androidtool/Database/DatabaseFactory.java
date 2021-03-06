package orange.com.androidtool.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Orange on 2016/9/19.
 */
public class DatabaseFactory {

    //数据库的版本号
    private static final int DATABASE_VERSION = 1;

    //数据库名称
    private static final String DATABASE_NAME = "Orange.db";

    //用来实现锁定数据库的表
    private static final Object LOCK = new Object();

    //数据库
    private static DatabaseFactory instance;

    //MsgTable表的对象
    private final MsgTable msgTable;

    //数据库的辅助工具
    private DatabaseHelper databaseHelper;


    /**
     * DatabaseFactory的构造函数，msgTable和databaseHelper
     *
     * @param context
     */
    private DatabaseFactory(final Context context) {
        this.databaseHelper = new DatabaseHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.msgTable = new MsgTable(context, databaseHelper);
    }


    public static MsgTable getMsgTable(final Context context) {
        return getInstance(context).msgTable;
    }


    public static DatabaseFactory getInstance(final Context context) {
        synchronized (LOCK) {
            if (instance == null) {
                instance = new DatabaseFactory(context.getApplicationContext());
            }
            return instance;
        }
    }

    /**
     * 清空指定的数据表，为private是为了提供给clearAllTable调用
     * @param tableName 需要删除的表的名称
     */
    private void clearTable(String tableName){
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        db.beginTransaction();
        try {
            db.delete(tableName, null, null);

            //将数据库中的表自增列清零
            String sql = " update sqlite_sequence SET seq = 0 where name = '" + tableName + "' ;";

            db.execSQL(sql);
            db.setTransactionSuccessful();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            db.endTransaction();
        }
    }

    /*
    清空所有的表，调用的方法为 DatabaseFactory.getInstance(context).clearAllTable();
     */
    public boolean clearAllTable(){
        boolean ret = false;
        List<String> tableList = new ArrayList<>();

        //将需要清空的表加入到tableList
        tableList.add(MsgTable.MsgEntry.TABLE_NAME);

        for (int i=0; i<tableList.size(); i++){
            try {
                clearTable(tableList.get(i));
                ret = true;
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return ret;
    }



    //数据库的辅助类，为内部类
    private static final class DatabaseHelper extends SQLiteOpenHelper {
        private DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        public void onCreate(final SQLiteDatabase db) {
            db.execSQL(MsgTable.CREATE_TABLE);
        }

        public void onUpgrade(final SQLiteDatabase db, final int oldVersion, final int newVersion) {

        }
    }
}
