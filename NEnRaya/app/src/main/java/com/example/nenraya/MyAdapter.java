package com.example.nenraya;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class MyAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private int tableSize;
    protected String [][] table;

    private NInLine nInLine;

    public MyAdapter(Context c, int gridSize) {
        context = c;
        tableSize = gridSize;
        table = new String[gridSize][gridSize];
        initTable();
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

        imageView.setImageResource(R.drawable.empty);

        return convertView;
    }

    private void initTable() {
        for (int i = 0; i < tableSize; i++) {
            for (int j = 0; j < tableSize; j++) {
                table[i][j] = "";
            }
        }
    }

    public interface NInLine {
        void setBox(String player, int position, View v);
        void setOccupied(String player, int row, int col);
        int getTableRow(int position);
        int  getTableCol(int position);
        boolean positionIsEmpty(int row, int col);
    }
}
