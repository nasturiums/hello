package com.example.myapplication;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.models.Box;

import java.util.List;

public class CheckBoxAdapter extends BaseAdapter {
    private List<Box> listData;
    private LayoutInflater layoutInflater;
    private Context context;
    public static int selected_position;

    public CheckBoxAdapter(List<Box> listData, Context context) {
        this.listData = listData;
        this.context = context;

        layoutInflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int i) {
        return listData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view==null){
            view=layoutInflater.inflate(R.layout.item_check,null);
            holder=new ViewHolder();
            holder.flagView=(ImageView) view.findViewById(R.id.imageView);
            holder.decription=(TextView) view.findViewById(R.id.textView1);
            holder.checkBox=(CheckBox) view.findViewById(R.id.checkBox);
            view.setTag(holder);
        }else {
            holder=(ViewHolder)view.getTag();
        }
        Box box=this.listData.get(i);
        holder.decription.setText(box.getDecrition());
        int imageID=this.getMipmapResIdByName(box.getImage());
        holder.flagView.setImageResource(imageID);
        if (i == selected_position) {
            holder.checkBox.setChecked(true);
        } else holder.checkBox.setChecked(false);
        holder.checkBox.setOnClickListener(onStateChangedListener(holder.checkBox,i));
        return view;
    }
    private View.OnClickListener onStateChangedListener(final CheckBox checkBox, final int position) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox.isChecked()) {
                    selected_position = position;

                } else {
                    selected_position = -1;
                }
                notifyDataSetChanged();
            }
        };
    }
    public int getMipmapResIdByName(String resName)  {
        String pkgName = context.getPackageName();
        // Return 0 if not found.
        int resID = context.getResources().getIdentifier(resName , "mipmap", pkgName);
        Log.i("CustomListView", "Res Name: "+ resName+"==> Res ID = "+ resID);
        return resID;
    }
    static class ViewHolder {
        ImageView flagView;
        TextView decription;
        CheckBox checkBox;
    }
}

