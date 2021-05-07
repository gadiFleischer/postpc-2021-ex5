package exercise.android.reemh.todo_items;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.EditText;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;


public class MainActivity extends AppCompatActivity {
  TodoItemsHolder holder;
  MyAdapter adapter;
  BroadcastReceiver broadcastReceiverForEdit;;
  MyApp myApp;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    myApp = new MyApp(this);
    if (holder == null) {
      holder = new TodoItemsHolderImpl();
    }
    myApp.loadTodoList();
    holder.setItems(myApp.todoItems);
    adapter = new MyAdapter(this,this.holder);

    //views
    FloatingActionButton addButton = findViewById(R.id.buttonCreateTodoItem);
    EditText editText = findViewById(R.id.editTextInsertTask);
    RecyclerView recycler = findViewById(R.id.recyclerTodoItemsList);
    recycler.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL,false));
    recycler.setAdapter(adapter);
    editText.setText("");

    addButton.setOnClickListener(v->{
      String description = editText.getText().toString();
      if(!description.equals("")){
        this.holder.addNewInProgressItem(description);
        editText.setText("");
        this.adapter.notifyDataSetChanged();
      }
    });

  broadcastReceiverForEdit = new BroadcastReceiver() {
    @Override
    public void onReceive(Context context, Intent intent) {
      if (intent.getAction().equals("itemChanged")) {
        TodoItem changedItem = (TodoItem) intent.getSerializableExtra("rowItem");
        holder.setItem(changedItem);
        myApp.todoItems=holder.getCurrentItems();
        myApp.saveTodoList();
        adapter.notifyDataSetChanged();
      }
    }
  };
  registerReceiver(broadcastReceiverForEdit, new IntentFilter("itemChanged"));
  }

  @Override
  protected void onStop() {
    super.onStop();
    myApp.todoItems=this.holder.getCurrentItems();
    myApp.saveTodoList();
  }

  @Override
  protected void onResume() {
    super.onResume();
    myApp.loadTodoList();
    holder.setItems(myApp.todoItems);
  }

  @Override
  protected void onSaveInstanceState(@NonNull Bundle outState) {
    super.onSaveInstanceState(outState);
    outState.putSerializable("appHolder", this.holder);
  }
  @Override
  protected void onDestroy() {
    super.onDestroy();
    this.unregisterReceiver(broadcastReceiverForEdit);
  }
}