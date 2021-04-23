package exercise.android.reemh.todo_items;

import java.io.Serializable;
import java.util.Date;

public class TodoItem implements Serializable {
  // TODO: edit this class as you want
    public String description;
    public boolean isDone;
    public Date createdTime;

    public TodoItem(String input){
        description = input;
        isDone = false;
        createdTime = new Date();
    }
    public void setCheckBoxDone(){
        this.isDone =true;
    }
    public void setCheckBoxInProg(){
        this.isDone =false;
    }
}
