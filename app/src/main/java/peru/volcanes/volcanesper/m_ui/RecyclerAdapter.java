//package com.mytrendin.firebaserecyclerviewcardview;
package peru.volcanes.volcanesper.m_ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import peru.volcanes.volcanesper.R;
import peru.volcanes.volcanesper.m_model.erupciones;

import java.util.List;



public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyHoder>{

    List<erupciones> list;
    Context context;

    public RecyclerAdapter(List<erupciones> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public MyHoder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.carderupcion,parent,false);

       // View view = LayoutInflater.from(context).inflate(R.,parent,false);
        MyHoder myHoder = new MyHoder(view);


        return myHoder;
    }

    @Override
    public void onBindViewHolder(MyHoder holder, int position) {
        erupciones mylist = list.get(position);
        holder.name.setText(mylist.getYear());
        holder.email.setText(mylist.getObservaciones());
        holder.address.setText("Fuente: " + mylist.getFuente());
    }

    @Override
    public int getItemCount() {

        int arr = 0;

        try{
            if(list.size()==0){

                arr = 0;

            }
            else{

                arr=list.size();
            }



        }catch (Exception e){



        }

        return arr;

    }

    class MyHoder extends RecyclerView.ViewHolder{
        TextView name,email,address;


        public MyHoder(View itemView) {
            super(itemView);
            email= (TextView) itemView.findViewById(R.id.vemail);
            address= (TextView) itemView.findViewById(R.id.vaddress);
            name = (TextView) itemView.findViewById(R.id.vname);

        }
    }

}

/*
package peru.volcanes.volcanesper.m_ui;
public class RecyclerAdapter {
}
*/