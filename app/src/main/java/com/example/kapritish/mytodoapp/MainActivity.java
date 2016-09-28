package com.example.kapritish.mytodoapp;

import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.content.Intent;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import android.view.Menu;
import android.view.MenuItem;
import org.apache.commons.io.FileUtils;
import java.text.DateFormat;
//import java.util.ArrayList;
import java.util.Date;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements EditItemActivity.EditItemActivityDialogListener{

    ArrayList<UpdateItem> todoItems;
    EditText etEditText;

    //ArrayList<TodoItem> todoItems;
    ItemsAdapter aToDoAdapter;
    ListView lvItems;
    UpdateItem selectedTodoItem;


    final int REQUEST_CODE = 10;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
  //      populateArrayItems();
        lvItems = (ListView) findViewById(R.id.lvItems);
        todoItems = (ArrayList<UpdateItem>)UpdateItem.listAll(UpdateItem.class);
        aToDoAdapter = new ItemsAdapter(this, todoItems);
        lvItems.setAdapter(aToDoAdapter);
        etEditText = (EditText) findViewById(R.id.etEditText);
        lvItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                todoItems.remove(position).delete();
                aToDoAdapter.notifyDataSetChanged();
                //writeItems();
                return true;
            }
        });

        lvItems.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapter,
                                            View view, int position, long id) {
                       /* Intent intent = new Intent(MainActivity.this, EditItemActivity.class);
                        intent.putExtra("todoItems", todoItems.get(position));
                        intent.putExtra("position", String.valueOf(position));
                        startActivityForResult(intent, REQUEST_CODE);*/
                        selectedTodoItem = aToDoAdapter.getItem(position);
                        showEditItemDialog();
                    }
                });

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void showEditItemDialog() {
        FragmentManager fm = getSupportFragmentManager();
        EditItemActivity editItemDialog = EditItemActivity.newInstance("Edit Item");
        editItemDialog.show(fm, "activity_edit_item");
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // REQUEST_CODE is defined above
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
            String item_name = data.getExtras().getString("todoItems");
            int Pos = Integer.parseInt(data.getStringExtra("position"));
            //todoItems.set(Pos, item);
            aToDoAdapter.notifyDataSetChanged();
           // writeItems();
        }
    }


    @Override
    public void onFinishEditDialog(String inputText, String date) {
        selectedTodoItem.myItem = inputText;
        selectedTodoItem.dt = date;
        selectedTodoItem.save();
        aToDoAdapter.notifyDataSetChanged();
    }

    public void onAddItem(View view) {
        etEditText = (EditText) findViewById(R.id.etEditText);
        UpdateItem newItem = new UpdateItem(etEditText.getText().toString(), DateFormat.getDateInstance().format(new Date()));
        aToDoAdapter.add(newItem);
        newItem.save();
        etEditText.setText(R.string.new_item);
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.kapritish.mytodoapp/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.kapritish.mytodoapp/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }

  
}
