package exercise.android.reemh.todo_items;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

// TODO: implement!
public class TodoItemsHolderImpl implements TodoItemsHolder {
  List<TodoItem> items;

  public TodoItemsHolderImpl(){
    items = new ArrayList<>();
  }

  @Override
  public List<TodoItem> getCurrentItems() { return items; }

  @Override
  public void addNewInProgressItem(String description) {
    TodoItem newItem = new TodoItem(description);
    this.items.add(newItem);
    this.sortItems();

  }

  @Override
  public void markItemDone(TodoItem item) {
    for(TodoItem myItem : this.items) {
      if (myItem.equals(item)) {
        myItem.setCheckBoxDone();
      }
    }
    this.sortItems();
  }

  @Override
  public void markItemInProgress(TodoItem item) {
    for(TodoItem myItem : this.items) {
      if (myItem.equals(item)) {
        myItem.setCheckBoxInProg();
      }
    }
    this.sortItems();
  }

  @Override
  public void deleteItem(TodoItem item) {
    for(TodoItem myItem : this.items) {
      if (myItem.equals(item)) {
        this.items.remove(myItem);
      }
    }
    this.sortItems();
  }

  private void sortItems(){
    Collections.sort(this.items, (item1, item2) ->{
      if (item1.isDone == item2.isDone){
        return item2.createdTime.compareTo(item1.createdTime);
      }
      if (item1.isDone){
        return 1;
      }
      return -1;
    });
  }

}
