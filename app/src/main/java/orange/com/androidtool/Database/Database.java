package orange.com.androidtool.Database;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Orange on 2016/9/19.
 */

/*
数据库的抽象类
 */

public abstract class Database {

    protected SQLiteOpenHelper databaseHelper;
    protected final Context context;

    public Database(Context mContext, SQLiteOpenHelper mDatabaseHelper){
        this.context = mContext;
        this.databaseHelper = mDatabaseHelper;
    }

}
