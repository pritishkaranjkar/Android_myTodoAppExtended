package com.example.kapritish.mytodoapp;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.TextView;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import java.util.ArrayList;

public class ItemsAdapter extends ArrayAdapter<UpdateItem> {

        public ItemsAdapter(Context context, ArrayList<UpdateItem> todoItems) {
        super(context, 0, todoItems);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        UpdateItem todoItem = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_update_item, parent, false);
        }
        // Lookup view for data population
        TextView todoName = (TextView) convertView.findViewById(R.id.item);
        todoName.setTextColor(Color.WHITE);
        TextView todoDueDate = (TextView) convertView.findViewById(R.id.dt);
        todoDueDate.setTextColor(Color.WHITE);
        // DatePicker todoDueDate = (DatePicker) convertView.findViewById(R.id.dt);
        // Populate the data into the template view using the data object
        todoName.setText(todoItem.myItem);
        todoDueDate.setText(todoItem.dt);
        //todoDueDate.updateDate(2016,9,27);
        // Return the completed view to render on screen
        return convertView;
}

}