package com.finn.accounting.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.finn.accounting.R;
import com.finn.accounting.entity.Type;

import java.util.List;

/*
 * @description:
 * @author: Finn
 * @create: 2022-03-13-21-32
 */
public class TypeBaseAdapter extends BaseAdapter {

    private Context context;
    private List<Type> mDatas;
    private int selectPos = 0;

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public List<Type> getmDatas() {
        return mDatas;
    }

    public void setmDatas(List<Type> mDatas) {
        this.mDatas = mDatas;
    }

    public void setSelectPos(int selectPos) {
        this.selectPos = selectPos;
    }

    public int getSelectPos() {
        return selectPos;
    }

    public TypeBaseAdapter(Context context, List<Type> mDatas) {
        this.context = context;
        this.mDatas = mDatas;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int i) {
        return mDatas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(context).inflate(R.layout.item_record_fragment, viewGroup, false);

        ImageView icon = view.findViewById(R.id.item_record_fragment_icon);
        TextView name = view.findViewById(R.id.item_record_fragment_name);

        //获取指定位置的数据源
        Type type = mDatas.get(i);
        name.setText(type.getTypeName());

        //判断当前位置是否为选中位置，如果是选中位置，就设置为带颜色的图片，否则为灰色图片
        if (selectPos == i) {
            icon.setImageResource(type.getsImageId());
        }else{
            icon.setImageResource(type.getImageId());
        }
        return view;
    }
}
