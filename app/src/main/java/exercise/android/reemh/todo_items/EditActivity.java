package exercise.android.reemh.todo_items;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class EditActivity extends AppCompatActivity {
    private TodoItem item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_activity);

        Intent todoItemIntent=getIntent();
        this.item = (TodoItem) todoItemIntent.getSerializableExtra("rowItem");

        //get views
        EditText description = findViewById(R.id.description);
        TextView modifiedTime = findViewById(R.id.modifiedText);
        TextView creationTime = findViewById(R.id.creationText);
        TextView progText = findViewById(R.id.progStatusText);
        CheckBox checkBox = findViewById(R.id.checkBox2);

        this.setViews(this.item, description, modifiedTime, creationTime, progText, checkBox);

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
                updateModifiedTime(modifiedTime);
            }

        });

        checkBox.setOnClickListener(view -> {
            if (this.item.isDone) {
                this.item.setCheckBoxInProg();
            } else {
                this.item.setCheckBoxDone();
            }
            checkBox.setChecked(this.item.isDone);
            updateModifiedTime(modifiedTime);
        });

    }

    private void updateModifiedTime(TextView modifiedTime) {
        item.updateModifiedTime();
        String modifiedStr = "Modified time: " + item.lastModified.toString();
        modifiedTime.setText(modifiedStr);
    }

    private void setViews(TodoItem item, EditText description, TextView modifiedTime, TextView creationTime, TextView progText, CheckBox checkBox) {
        description.setText(item.description);
        String creationStr = "Creation time: "+item.createdTime.toString();
        creationTime.setText(creationStr);
        String modifiedStr = "Modified time: "+item.lastModified.toString();
        modifiedTime.setText(modifiedStr);
        String proggstr = item.isDone ? "this item is Done": " this item is in-progress";
        progText.setText(proggstr);
        checkBox.setChecked(item.isDone);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent backToMainIntent = new Intent("itemChanged");
        backToMainIntent.putExtra("rowItem",this.item);
        sendBroadcast(backToMainIntent);
    }
}
