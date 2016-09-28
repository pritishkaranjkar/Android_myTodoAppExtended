package com.example.kapritish.mytodoapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.orm.SugarRecord;
import java.util.Date;
public class UpdateItem extends SugarRecord<UpdateItem> {

    String myItem;
    String dt;
    //Date dt;

    public UpdateItem() {

    }

    public UpdateItem(String myItem, String dt) {
        this.myItem = myItem;
            this.dt = dt;
    }

    /*public UpdateItem(String myItem, Date dt) {
        this.myItem = myItem;
        this.dt = dt;
    }*/
}
