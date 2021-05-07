package exercise.android.reemh.todo_items;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class EditActivity extends AppCompatActivity {
    private TodoItem item;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_activity);

        Intent todoItemIntent=getIntent();
        this.item = (TodoItem) todoItemIntent.getSerializableExtra("rowItem");
        //get views
        EditText description = findViewById(R.id.description);
        TextView creationTime = findViewById(R.id.creationText);
        TextView progText = findViewById(R.id.progStatusText);
        CheckBox checkBox = findViewById(R.id.checkBox2);

        this.setViews(this.item, description, creationTime, progText, checkBox);

        description.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                item.setDescription(s.toString());
                updateModifiedTime();
            }
        });


        description.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                hideKeyboard(v);
            }
        });

        checkBox.setOnClickListener(view -> {
            if (this.item.isDone) {
                this.item.setCheckBoxInProg();
            } else {
                this.item.setCheckBoxDone();
            }
            checkBox.setChecked(this.item.isDone);
            progText.setText(item.isDone ? "this item is Done": " this item is in-progress");
            this.updateModifiedTime();
        });

    }

    private void updateModifiedTime() {
        TextView modifiedTime = findViewById(R.id.modifiedText);
        Date curDate = new Date();
        long timeDiff = curDate.getTime()-item.modifiedDate.getTime();
        long minutes = (int)timeDiff / (60*1000);
        long hours = (int)minutes / 60;
        String modifiedStr = "";
        if(minutes<60){
            modifiedStr = "Last modified: "+minutes+" minutes ago";
        }else if(hours<24){
            modifiedStr = "Last modified: Today at"+ hours;
        }
        else{
            String date = new SimpleDateFormat("yyyy-dd-MM", Locale.getDefault()).format(item.modifiedDate);
            Calendar cal = Calendar.getInstance();
            cal.setTime(item.modifiedDate);
            modifiedStr = "Last modified: "+date+" at " +cal.get(Calendar.HOUR_OF_DAY);
        }
        modifiedTime.setText(modifiedStr);
        item.updateModifiedTime();
    }

    private void setViews(TodoItem item, EditText description, TextView creationTime, TextView progText, CheckBox checkBox) {
        description.setText(item.description);
        String creationStr = "Creation time: "+item.createdTime.toString();
        creationTime.setText(creationStr);
        this.updateModifiedTime();
        String proggstr = item.isDone ? "this item is Done": " this item is in-progress";
        progText.setText(proggstr);
        checkBox.setChecked(item.isDone);
    }
    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent backToMainIntent = new Intent();
        backToMainIntent.setAction("itemChanged");
        backToMainIntent.putExtra("rowItem",this.item);
        sendBroadcast(backToMainIntent);
    }
}
