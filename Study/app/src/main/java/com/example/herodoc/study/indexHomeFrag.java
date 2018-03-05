package com.example.herodoc.study;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class indexHomeFrag extends Fragment {


    public indexHomeFrag() {
        // Required empty public constructo
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        actUtils.setTile(getActivity(), "开始订餐");


        View view = inflater.inflate(R.layout.fragment_index_home, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.homeRecyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        HomeItemAdapter adapter = new HomeItemAdapter();
        recyclerView.setAdapter(adapter);

        return view;
    }

    class HomeItemAdapter extends RecyclerView.Adapter<ViewHolder>{

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//            View itemView = LayoutInflater.from(getActivity())
//                    .inflate(R.layout.fragment_home_item, parent, false);
            Log.e("fuck", String.valueOf(R.layout.fragment_home_item));
            View itemView = LayoutInflater.from(getActivity())
                    .inflate(R.layout.fragment_home_item, parent, false);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getContext(), vendorAct.class));
                }
            });

            return new ViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
//            actUtils.toastInfo(getContext(), String.valueOf(position));
        }

        @Override
        public int getItemCount() {
            return 20;
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        public ViewHolder(View itemView) {
            super(itemView);

        }
    }


}
