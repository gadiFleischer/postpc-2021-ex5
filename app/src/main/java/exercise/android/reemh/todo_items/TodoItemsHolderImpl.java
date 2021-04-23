package exercise.android.reemh.todo_items;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// TODO: implement!
public class TodoItemsHolderImpl extends RecyclerView.Adapter<TodoItemsHolderImpl.ViewHolder> implements TodoItemsHolder {
  List<TodoItem> items;

  public TodoItemsHolderImpl(){
    items = new ArrayList<TodoItem>();
  }


  @Override
  public List<TodoItem> getCurrentItems() { return items; }

  @Override
  public void addNewInProgressItem(String description) {
    TodoItem newItem = new TodoItem(description);
    this.items.add(newItem);
  }

  @Override
  public void markItemDone(TodoItem item) {
    for(TodoItem myItem : this.items) {
      if (myItem.equals(item)) {
        myItem.setCheckBoxDone();
      }
    }
  }

  @Override
  public void markItemInProgress(TodoItem item) {
    for(TodoItem myItem : this.items) {
      if (myItem.equals(item)) {
        myItem.setCheckBoxInProg();
      }
    }
  }

  @Override
  public void deleteItem(TodoItem item) {
    for(TodoItem myItem : this.items) {
      if (myItem.equals(item)) {
        this.items.remove(myItem);
      }
    }
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    return null;
  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

  }

  @Override
  public int getItemCount() {
    return this.items.size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder {
    public ViewHolder(@NonNull View itemView) {
      super(itemView);
    }
  }
}
