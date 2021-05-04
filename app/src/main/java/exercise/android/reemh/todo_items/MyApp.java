package exercise.android.reemh.todo_items;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MyApp extends Application {

    List<TodoItem> todoItems;
    SharedPreferences sp;
//    public static Context context;


    @Override
    public void onCreate() {
        super.onCreate();
        sp = PreferenceManager.getDefaultSharedPreferences(this);
        loadTodoList();
    }
    public void loadTodoList() {
        todoItems =  new ArrayList<>();
        String itemsJson = sp.getString("todoItems", "");
        if (!itemsJson.equals("")) {
            Type listType = new TypeToken<ArrayList<TodoItem>>(){}.getType();
            todoItems = new Gson().fromJson(itemsJson, listType);
        }
    }

    public void saveTodoList() {
        String itemsJson = new Gson().toJson(todoItems);
        sp.edit().putString("todoItems", itemsJson).apply();
    }


}
