package exemple.udemy.java.premissions;

import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;


public class Permissions extends AppCompatActivity {

    private static final int STORAGE_PERMISSION_CODE = 23;

    public boolean checkStoragePermissions(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.R){
            //Android is 11 (R) or above
            return Environment.isExternalStorageManager();
        }else {
            //Below android 11
            int write = ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
            int read = ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE);

            return read == PackageManager.PERMISSION_GRANTED && write == PackageManager.PERMISSION_GRANTED;
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == STORAGE_PERMISSION_CODE) {
            if (grantResults.length > 0) {
                boolean write = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                boolean read = grantResults[1] == PackageManager.PERMISSION_GRANTED;

                if (read && write) {
                    Toast.makeText(this, "Storage Permissions Granted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Storage Permissions Denied", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}

