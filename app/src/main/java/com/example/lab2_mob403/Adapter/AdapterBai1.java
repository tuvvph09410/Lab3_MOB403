package com.example.lab2_mob403.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.lab2_mob403.Model.Bai1Model;
import com.example.lab2_mob403.R;

import java.util.List;

public class AdapterBai1 extends BaseAdapter {
    Context context;
    List<Bai1Model> bai1ModelList;

    public AdapterBai1(Context context, List<Bai1Model> bai1ModelList) {
        this.context = context;
        this.bai1ModelList = bai1ModelList;
    }

    @Override
    public int getCount() {
        return bai1ModelList.size();
    }

    @Override
    public Object getItem(int i) {
        return bai1ModelList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        ViewHolder viewHolder;
        if (view == null) {
            viewHolder = new ViewHolder();
            view = layoutInflater.inflate(R.layout.list_view_bai1, null);
            viewHolder.tv_Name = view.findViewById(R.id.tvName);
            viewHolder.tv_Phone = view.findViewById(R.id.tvPhone);
            viewHolder.tv_Email = view.findViewById(R.id.tvEmail);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        Bai1Model bai1Model = bai1ModelList.get(i);
        viewHolder.tv_Phone.setText("0"+String.valueOf(bai1Model.getPhone()));
        viewHolder.tv_Name.setText(bai1Model.getName());
        viewHolder.tv_Email.setText(bai1Model.getEmail());

        return view;
    }

    public static class ViewHolder {
        TextView tv_Name, tv_Phone, tv_Email;
    }
}
