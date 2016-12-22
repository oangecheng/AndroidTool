package orange.com.androidtool.Utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;

/**
 * Created by Orange on 2016/12/14.
 */

public class PermissionChecker {
    private final Context context;

    public PermissionChecker(Context context){
        this.context = context.getApplicationContext();
    }

    public boolean lackPermissions(String... permissions) {
        for (String permission : permissions) {
            if (lackPermission(permission)) {
                return true;
            }
        }
        return false;
    }

    public boolean lackPermission(String permission){
        boolean ret = (ContextCompat.checkSelfPermission(context, permission)== PackageManager.PERMISSION_DENIED);
        return ret;
    }
}
