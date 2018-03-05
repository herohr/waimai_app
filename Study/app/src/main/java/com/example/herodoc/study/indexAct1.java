package com.example.herodoc.study;

import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

public class indexAct1 extends AppCompatActivity {
    FragmentTabHost tabHost;
    Class[] fragments =
            {indexHomeFrag.class, indexExpressFrag.class, indexOrderFrag.class, indexMineFrag.class};
    Integer[] bottomImgResIDs =
            {R.drawable.home_tab, R.drawable.express_tab, R.drawable.order_tab, R.drawable.mine_tab};
    String[] bottomTags = {"home", "express", "order", "mine"};
    String[] titles = {"首页", "快递", "订单", "我的"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index_act1);

        tabHost = findViewById(android.R.id.tabhost);
        tabHost.setup(this, getSupportFragmentManager(), R.id.indexContent);
        for(int i=0;i<fragments.length;i++){
            TabHost.TabSpec tabSpec = tabHost.newTabSpec(bottomTags[i]).setIndicator(getTabItemView(i));
            tabHost.addTab(tabSpec, fragments[i], null);
        }
    }

    View getTabItemView(int index){
        View view  = LayoutInflater.from(this).inflate(R.layout.tab_item, null);
        ImageView image = view.findViewById(R.id.tab_image);
        TextView textView = view.findViewById(R.id.tab_text);
        image.setImageResource(bottomImgResIDs[index]);
        textView.setText(titles[index]);
        return view;
    }
}
