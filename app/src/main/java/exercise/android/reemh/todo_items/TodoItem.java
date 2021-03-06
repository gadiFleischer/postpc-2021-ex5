package exercise.android.reemh.todo_items;

import java.io.Serializable;
import java.util.Date;

public class TodoItem implements Serializable {
    public String description;
    public boolean isDone;
    public Date createdTime;
    public Date modifiedDate;

    public TodoItem(String input){
        description = input;
        isDone = false;
        createdTime = new Date();
        modifiedDate = createdTime;
    }
    public void setCheckBoxDone(){
        this.isDone =true;
    }
    public void setCheckBoxInProg(){
        this.isDone =false;
    }
    public void updateModifiedTime(){
        this.modifiedDate =new Date();
    }
    public void setDescription(String newDesc){
        this.description=newDesc;
    }
}
