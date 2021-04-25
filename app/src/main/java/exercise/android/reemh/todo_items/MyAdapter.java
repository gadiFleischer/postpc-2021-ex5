package exercise.android.reemh.todo_items;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class MyViewHolder extends RecyclerView.ViewHolder {
    CheckBox checkBox;
    TextView creationTime;
    TextView description;
    //TODO delete button

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        checkBox= itemView.findViewById(R.id.checkBox);
        creationTime = itemView.findViewById(R.id.creationTime);
        description = itemView.findViewById(R.id.description);
    }
}



public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
    TodoItemsHolder itemsHolder;
    LayoutInflater inflater;

    public MyAdapter(Context context, TodoItemsHolder holder){
        this.itemsHolder = holder;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.row_todo_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        TodoItem curItem = this.itemsHolder.getCurrentItems().get(position);
        holder.description.setText(curItem.description);
        holder.creationTime.setText(curItem.createdTime.toString());
        holder.checkBox.setChecked(curItem.isDone);
        if(curItem.isDone){
            holder.description.setPaintFlags(holder.description.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }else{
            holder.description.setPaintFlags(0);
        }
        holder.checkBox.setOnClickListener(view -> {
            if (curItem.isDone) {
                itemsHolder.markItemInProgress(curItem);
            } else {
                itemsHolder.markItemDone(curItem);
            }
            notifyDataSetChanged();
        });
    }

    @Override
    public int getItemCount() {
        return itemsHolder.getCurrentItems().size();
    }
}
