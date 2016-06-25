package com.android.hubin.themeswitcher;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * 说明：单呼控制按钮适配器
 *
 * @author
 * @Date 2015-3-5
 */
public class ThemeAdapter extends BaseAdapter
{
    public LayoutInflater mInflater;
    private ViewHolder mViewHolder;
    private List<String> mDataSource;
    private Context context;

    /**
     * 构造控制菜单适配器
     * @param context
     * @param dataSrc
     */
    public ThemeAdapter(Context context, List<String> dataSrc)
    {
        this.context = context;
        mInflater = LayoutInflater.from(context);
        mDataSource = dataSrc;
        if (mDataSource == null)
        {
            mDataSource = new ArrayList<String>();
        }
    }

    @Override
    public int getCount()
    {

        return mDataSource == null ? 0 : mDataSource.size();
    }

    @Override
    public Object getItem(int position)
    {
        return null;
    }

    @Override
    public long getItemId(int position)
    {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        if (convertView != null)
        {
            mViewHolder = (ViewHolder) convertView.getTag();
        }
        else
        {
            convertView = mInflater.inflate(R.layout.adapter_theme, null);
            mViewHolder = new ViewHolder();
            mViewHolder.controlItem = (TextView) convertView.findViewById(R.id.text_view);
            convertView.setTag(mViewHolder);
        }
        if(mDataSource == null || mDataSource.size() <= position)
        {
            return convertView;
        }
        String text = mDataSource.get(position);
        if(TextUtils.isEmpty(text))
        {
            return convertView;
        }
        
        mViewHolder.controlItem.setText(text);
        return convertView;
    }

    class ViewHolder
    {
        TextView controlItem;
    }
    
    public void clear()
    {
        this.context = null;
        this.mDataSource = null;
    }

}
