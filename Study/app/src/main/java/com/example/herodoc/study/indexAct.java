package com.example.herodoc.study;

import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class indexAct extends AppCompatActivity {
    ImageView[] bottomImgViews;
    View selectedBottomView;
    View[] bottomLayoutViews;
    Integer[][] bottomImgID = {
            {R.drawable.ic_home_selected, R.drawable.ic_home_normal},
            {R.drawable.ic_express_selected, R.drawable.ic_express_normal},
            {R.drawable.ic_orders_selected, R.drawable.ic_orders_normal},
            {R.drawable.ic_me_selected, R.drawable.ic_me_normal}
    };

    FragmentTabHost tabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        initViewsMap();
        initBottomBar();

//        tabHost = new FragmentTabHost();
    }

    void initViewsMap(){
        bottomImgViews = new ImageView[]{
                findViewById(R.id.homeImage),
                findViewById(R.id.expressImage),
                findViewById(R.id.orderImage),
                findViewById(R.id.mineImage)
        };
        bottomLayoutViews = new View[]{
                findViewById(R.id.homeBottom),
                findViewById(R.id.expressBottom),
                findViewById(R.id.orderBottom),
                findViewById(R.id.mineBottom),
        };
    }

    void initBottomBar(){
        selectedBottomView = bottomLayoutViews[0];
        Picasso.with(getApplicationContext()).load(bottomImgID[0][0]).into((ImageView) findViewById(R.id.homeImage));
        for(final View view: bottomLayoutViews){
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(v==selectedBottomView){
                        return;
                    }
                    ImageView imgView = getInnerImgView(v);
                    Picasso.with(getApplicationContext())
                            .load(getInnerImgResID(selectedBottomView))
                            .into(getInnerImgView(selectedBottomView));
                    Picasso.with(getApplicationContext()).load(getInnerImgResID(v)).into(imgView);
                    selectedBottomView = v;
//                    switch (viewID){
//                        case R.id.homeImage:{
//                            Picasso.with(getApplicationContext()).load(getReverseImageID(viewID)).into((ImageView) v);
//                            break;
//                        }case R.id.expressImage:{
//                            Picasso.with(getApplicationContext()).load(getReverseImageID(viewID)).into((ImageView) v);
//                            break;
//                        }case R.id.orderImage:{
//                            Picasso.with(getApplicationContext()).load(R.drawable.ic_orders_selected).into((ImageView) v);
//                            break;
//                        }case R.id.mineImage:{
//                            Picasso.with(getApplicationContext()).load(R.drawable.ic_me_selected).into((ImageView) v);
//                        }
//                    }
                }
            });
        }
    }

    public Integer getInnerImgResID(View v){// 这里必须为LayoutView
        int p = 0, count=0;
        if(v == selectedBottomView){
            p = 1;
        }
        for(ImageView vv :bottomImgViews) {
            if (getInnerImgView(v)==vv)break;
            count++;
        }
        return bottomImgID[count][p];
    }

    ImageView getInnerImgView(View v){ // 这里必须为LayoutView
        int count=0;
        for(View view: bottomLayoutViews){
            if(view.getId()==v.getId())return bottomImgViews[count];
            count++;
        }
        return null;
    }
}
