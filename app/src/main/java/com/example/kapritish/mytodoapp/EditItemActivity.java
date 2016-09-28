package com.example.kapritish.mytodoapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.content.Intent;
import android.view.Menu;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.view.MenuItem;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.DatePicker;
import java.util.Calendar;
import java.util.Date;

public class EditItemActivity extends DialogFragment implements OnEditorActionListener {

    //EditText etEdittodoText;
    EditText etText;
    EditText etDT;
   // DatePicker etDT;
    String Pos;

    public interface EditItemActivityDialogListener {
        void onFinishEditDialog(String inputText, String date);
    }

    public EditItemActivity() {

    }

    public static EditItemActivity newInstance(String title) {
        EditItemActivity frag = new EditItemActivity();
        Bundle args = new Bundle();
        args.putString("title", title);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_edit_item, container);
        etText = (EditText) view.findViewById(R.id.editTodoName);
        etDT = (EditText) view.findViewById(R.id.editTodoDate);
       // etDT = (DatePicker) view.findViewById(R.id.editTodoDate);
        etDT.setOnEditorActionListener(this);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
       /* etDT.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), new DatePicker.OnDateChangedListener() {

            @Override
            public void onDateChanged(DatePicker datePicker, int year, int month, int dayOfMonth) {
                Log.d("Date", "Year=" + year + " Month=" + (month + 1) + " day=" + dayOfMonth);

            }
        });
*/        String title = getArguments().getString("title", "Enter Name");
        getDialog().setTitle(title);
        // Show soft keyboard automatically
        etDT.requestFocus();

        getDialog().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.MyDialog);
        return view;
    }


    public static java.util.Date getDateFromDatePicket(DatePicker datePicker){
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year =  datePicker.getYear();

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);

        return calendar.getTime();
    }


    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (EditorInfo.IME_ACTION_DONE == actionId) {
            EditItemActivityDialogListener listener = (EditItemActivityDialogListener) getActivity();
            listener.onFinishEditDialog(etText.getText().toString(), etDT.getText().toString());
           // listener.onFinishEditDialog(etText.getText().toString(), getDateFromDatePicket(etDT));
            dismiss();
            return true;
        }
        return false;
    }


    /*public void onSubmitSave(View view) {
        Intent item = new Intent();
        item.putExtra("todoItems", etEdittodoText.getText().toString());
        item.putExtra("position", Pos);
        setResult(RESULT_OK, item);
        finish();
    }*/


}
