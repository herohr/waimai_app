package com.example.herodoc.study;


import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.models.User;


/**
 * A simple {@link Fragment} subclass.
 */
public class indexMineFrag extends Fragment {
    public TextView loginRegisterText;
    boolean login = false;


    public indexMineFrag() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        actUtils.setTile(getActivity(), "个人设置");

        View view = inflater.inflate(R.layout.fragment_index_mine, container, false);
        loginRegisterText = view.findViewById(R.id.loginRegisterText);
        loginRegisterText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!login){
                    toLoginAct();
                }
            }
        });
        return view;
    }

    void toLoginAct(){
        Intent intent = new Intent(getContext() ,loginAct.class);
        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
        FragmentActivity act = getActivity();
        if(act==null)return;
        MainApplication app = (MainApplication) act.getApplication();
        if(app.isLogin()){
            User user = app.getUser();
            loginRegisterText.setText("用户: "+ user.phone);
        }else{
            loginRegisterText.setText("登录/注册");
        }
    }
}
