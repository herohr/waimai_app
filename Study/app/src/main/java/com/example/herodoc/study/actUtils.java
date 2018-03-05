package com.example.herodoc.study;

import android.app.Activity;
import android.content.Context;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by HeroDoc on 2018/2/27.
 */

public class actUtils {
    public static void setTile(Activity activity, String title){
        TextView textView = activity.findViewById(R.id.toolbar_title);
        textView.setText(title);
    }

    public static void toastInfo(Context context, String info){
        Toast.makeText(context, info, Toast.LENGTH_SHORT).show();
    }
}
