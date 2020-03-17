package com.example.kuandaimima;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private EditText haoma;
    private EditText neirong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        haoma = findViewById(R.id.haoma_edittext);
        neirong = findViewById(R.id.neirong_edittext);

        //程序加载时检查xml文件有没有上次保存的设置
        String check_Temp =MySharedPreference.getneirong(MainActivity.this);
        if(!"".equals(check_Temp))
        {
            String haomas = (String) MySharedPreference.gethaoma(this);
            String neirongs = (String)MySharedPreference.getneirong(this);
            haoma.setText(haomas);
            neirong.setText(neirongs);
        }

    }
        public static void fasong (EditText haoma, EditText neirong)
        {
            SmsManager sms = SmsManager.getDefault();//获取默认短信管理器
            String hm = haoma.getText().toString();//发送号码
            String nr = neirong.getText().toString();//发送内容
            sms.sendTextMessage(hm, null, nr, null, null);//发送
        }

        public void queding (View view)
        {

            //检查发短信权限
            if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
                if (haoma.getText().toString().equals(""))
                {
                    Toast.makeText(this, "请输入要发送的号码", Toast.LENGTH_SHORT).show();
                }else if (neirong.getText().toString().equals(""))
                {
                    Toast.makeText(this, "请输入要发送的内容", Toast.LENGTH_SHORT).show();
                }else {
                    //发送短信
                    fasong(haoma, neirong);
                    //将号码和内容保存到xml
                    String haomas = haoma.getText().toString();
                    String neirongs = neirong.getText().toString();
                    MySharedPreference.sethaoma(haomas,MainActivity.this);
                    MySharedPreference.setneirong(neirongs,MainActivity.this);

                    Toast.makeText(MainActivity.this, "发送成功", Toast.LENGTH_SHORT).show();
                }


            } else
                {
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CALL_PHONE}, 1);
                    Toast.makeText(MainActivity.this, "请检查短信发送权限", Toast.LENGTH_SHORT).show();
            }

        }

        public void jiancha(View view)
        {
            if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED)
            {
                Toast.makeText(this, "已取得短信发送权限", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(this, "请将短信发送权限设为允许", Toast.LENGTH_SHORT).show();
                getAppDetailSettingIntent(this);
            }
        }
                //跳转权限申请
                private void getAppDetailSettingIntent(Context context) {
                    Intent localIntent = new Intent();
                    localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    if (Build.VERSION.SDK_INT >= 9) {
                        localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                        localIntent.setData(Uri.fromParts("package", getPackageName(), null));
                    } else if (Build.VERSION.SDK_INT <= 8) {
                        localIntent.setAction(Intent.ACTION_VIEW);
                        localIntent.setClassName("com.android.settings","com.android.settings.InstalledAppDetails");
                        localIntent.putExtra("com.android.settings.ApplicationPkgName", getPackageName());
                    }
                    startActivity(localIntent);
                }

    public void xiangmudizhi(View view) {
        Uri uri =Uri.parse("https://github.com/2631686787/kuandaimima");
        Intent intent = new Intent(Intent.ACTION_VIEW,uri);
        startActivity(intent);
    }

    public void lianxizuozhe(View view) {
        Uri uri =Uri.parse("http://www.coolapk.com/u/990229");
        Intent intent = new Intent(Intent.ACTION_VIEW,uri);
        startActivity(intent);
    }
}
