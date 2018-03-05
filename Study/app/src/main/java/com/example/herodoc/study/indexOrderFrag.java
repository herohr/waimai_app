package com.example.herodoc.study;


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
public class indexOrderFrag extends Fragment {


    public indexOrderFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        actUtils.setTile(getActivity(), "我的订单");
        View view = inflater.inflate(R.layout.fragment_index_order_express, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.orderRecycleView);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        HomeItemAdapter adapter = new HomeItemAdapter();
        recyclerView.setAdapter(adapter);
        return view;
    }

    class HomeItemAdapter extends RecyclerView.Adapter<indexOrderFrag.ViewHolder>{

        @Override
        public indexOrderFrag.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(getActivity())
                    .inflate(R.layout.fragment_order_item, parent, false);

            return new indexOrderFrag.ViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {

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
