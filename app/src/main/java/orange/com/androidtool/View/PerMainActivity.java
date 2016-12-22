package orange.com.androidtool.View;

import android.Manifest;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import orange.com.androidtool.R;
import orange.com.androidtool.Utils.PermissionChecker;

public class PerMainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 0;
    static final String[] PERMISSIONS = new String[]{
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.CAMERA
    };
    private PermissionChecker checker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_per_main);

        checker = new PermissionChecker(PerMainActivity.this);

    }

    @Override
    protected void onResume() {
        super.onResume();

        if (checker.lackPermissions(PERMISSIONS)){
            startPermissionActivity();
        }
    }

    private void startPermissionActivity(){
        PermissionActivity.startActivityForResult(PerMainActivity.this, REQUEST_CODE, PERMISSIONS);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==REQUEST_CODE&&resultCode==PermissionActivity.PERMISSIONS_DENIED){
            finish();
        }
    }
}
