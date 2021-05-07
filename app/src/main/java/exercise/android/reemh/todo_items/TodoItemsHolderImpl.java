package exercise.android.reemh.todo_items;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TodoItemsHolderImpl implements TodoItemsHolder {
  List<TodoItem> items;

  public TodoItemsHolderImpl(){
    items = new ArrayList<>();
  }
  public void setItems(List<TodoItem> items){
    this.items=items;
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
    this.items.remove(item);
  }

  @Override
  public void setItem(TodoItem item) {
    for(int i=0;i<items.size();i++) {
      if (items.get(i).createdTime.equals(item.createdTime)) {
        this.items.set(i,item);
      }
    }
    this.sortItems();
  }

  private void sortItems(){
    Collections.sort(this.items, (item1, item2) ->{
      if (item1.isDone == item2.isDone){
        return item1.createdTime.compareTo(item2.createdTime);
      }
      if (item1.isDone){
        return 1;
      }
      return -1;
    });
  }

}
