package orange.com.androidtool.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.List;

import orange.com.androidtool.Info.Msg;

/**
 * Created by Orange on 2016/9/19.
 */
public class MsgTable extends Database {

    //创建MsgTable的sql语句
    public static final String CREATE_TABLE = "CREATE TABLE if not exists "
            + MsgEntry.TABLE_NAME + "("
            + MsgEntry._ID + " integer primary key autoincrement, "
            + MsgEntry.USER_NAME + " text not null, "
            + MsgEntry.USER_PASS + " text not null  );";

    public MsgTable(final Context context, final SQLiteOpenHelper sqLiteOpenHelper) {
        super(context, sqLiteOpenHelper);
    }


    /**
     * 查询语句，通过userName查询密码
     *
     * @param userName 用户名
     * @return
     */
    public String selectPasswordByUserName(String userName) {
        SQLiteDatabase database = databaseHelper.getReadableDatabase();
        String password = "";

        try {
            Cursor cursor = database.query(MsgEntry.TABLE_NAME, new String[]{MsgEntry.USER_PASS},
                    MsgEntry.USER_NAME + "=?", new String[]{userName}, null, null, null);
            if (cursor.moveToFirst()) {
                password = cursor.getString(cursor.getColumnIndex(MsgEntry.USER_PASS));
            }
            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return password;
    }

    public List<Msg> selectAllInfo() {
        String n = "";
        String p = "";
        int s = 0;
        Msg msg;

        List<Msg> list = new ArrayList<>();
        SQLiteDatabase database = databaseHelper.getReadableDatabase();
        try {
            Cursor cursor = database.rawQuery("select * from " + MsgEntry.TABLE_NAME, null);
            for (int i = 0; i < cursor.getCount(); i++) {
                if (cursor.moveToNext()) {
                    n = cursor.getString(cursor.getColumnIndex(MsgEntry.USER_NAME));
                    p = cursor.getString(cursor.getColumnIndex(MsgEntry.USER_PASS));
                    s = cursor.getInt(cursor.getColumnIndex(MsgEntry._ID));
                    msg = new Msg(n, p, s);
                    list.add(msg);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 向数据库当中插入用户名和密码
     *
     * @param userName
     * @param userPassword
     * @return
     */
    public boolean insertUserInfo(String userName, String userPassword) {
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        boolean ret = false;
        try {
            ContentValues cv = new ContentValues();
            cv.put(MsgEntry.USER_NAME, userName);
            cv.put(MsgEntry.USER_PASS, userPassword);

            long check = database.insert(MsgEntry.TABLE_NAME, null, cv);

            if (check != -1) {
                ret = true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return ret;
    }

    //插入测试使用的数据，即初始的MsgTable数据
    public void insertTestData() {
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        try {
            //插入第一条数据
            ContentValues cv1 = new ContentValues();
            cv1.put(MsgEntry.USER_NAME, "001");
            cv1.put(MsgEntry.USER_PASS, "firstPass");
            database.insert(MsgEntry.TABLE_NAME, null, cv1);
            //插入第二条数据
            ContentValues cv2 = new ContentValues();
            cv2.put(MsgEntry.USER_NAME, "002");
            cv2.put(MsgEntry.USER_PASS, "secondPass");
            database.insert(MsgEntry.TABLE_NAME, null, cv2);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    //MsgEntry为一些固定字段，继承BaseColumns，例如_ID不需重新定义
    public static class MsgEntry implements BaseColumns {

        //MsgTable表名
        public static final String TABLE_NAME = "message_table";

        //用户的账号
        public static final String USER_NAME = "user_account";

        //用户的密码
        public static final String USER_PASS = "user_password";


    }
}
