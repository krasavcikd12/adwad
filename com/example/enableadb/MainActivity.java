
package com.example.enableadb;

import android.app.Activity;
import android.os.Bundle;
import java.io.DataOutputStream;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            Process su = Runtime.getRuntime().exec("su");
            DataOutputStream outputStream = new DataOutputStream(su.getOutputStream());
            outputStream.writeBytes("setprop service.adb.tcp.port 5555\n");
            outputStream.writeBytes("stop adbd\n");
            outputStream.writeBytes("start adbd\n");
            outputStream.writeBytes("exit\n");
            outputStream.flush();
            su.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }

        finish(); // Закрыть приложение после выполнения
    }
}
