package com.example.nenraya;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class MyAdapter extends BaseAdapter {

    Context context;
    LayoutInflater inflater;
    int tableSize;

    public MyAdapter(Context c, int gridSize) {
        context = c;
        tableSize = gridSize;
    }

    @Override
    public int getCount() {
        return tableSize * tableSize;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null) {
            inflater = (LayoutInflater) context.getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
        }

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.row_item, null);
        }

        ImageView imageView = (ImageView) convertView.findViewById(R.id.imageView);

        imageView.setImageResource(R.drawable.table_game);

        return convertView;
    }
}
