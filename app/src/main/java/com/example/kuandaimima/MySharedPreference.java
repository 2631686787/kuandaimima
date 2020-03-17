package com.example.kuandaimima;

import android.content.Context;
import android.content.SharedPreferences;

public  class MySharedPreference {
    //保存短信号码和内容到xml，以便下次打开直接读取
    public static SharedPreferences share(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("Temp",Context.MODE_PRIVATE);//标记为Temp
        return sharedPreferences;
    }

    //调用上面的 share(Context context) 方法 获取标识为 "haoma" 的数据
    public static Object gethaoma(Context context){
        return share(context).getString("savd_haoma",null);
    }

    //这里使用的是 apply() 方法保存，将不会有返回值
    public static void sethaoma(String savd_haoma, Context context){
        SharedPreferences.Editor e = share(context).edit();
        e.putString("savd_haoma",savd_haoma);
        e.apply();
    }

    //获取内容保存并标识
    public static String getneirong(Context context){
        return share(context).getString("savd_neirong",null);
    }
    public static void setneirong(String savd_neirong, Context context){
        SharedPreferences.Editor e = share(context).edit();
        e.putString("savd_neirong",savd_neirong);
        e.apply();
    }




}
