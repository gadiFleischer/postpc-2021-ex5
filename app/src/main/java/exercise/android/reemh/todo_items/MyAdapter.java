package exercise.android.reemh.todo_items;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

class MyViewHolder extends RecyclerView.ViewHolder {
    CheckBox checkBox;
    TextView description;
    ImageButton removeButton;
    FloatingActionButton editButton;


    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        checkBox= itemView.findViewById(R.id.checkBox);
        description = itemView.findViewById(R.id.description);
        removeButton = itemView.findViewById(R.id.removeButton);
        editButton =  itemView.findViewById(R.id.editButton);
    }
}

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
    public TodoItemsHolder itemsHolder;
    LayoutInflater inflater;
    Context context;

    public MyAdapter(Context context, TodoItemsHolder holder){
        this.itemsHolder = holder;
        this.inflater = LayoutInflater.from(context);
        this.context=context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.row_todo_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        TodoItem curItem = itemsHolder.getCurrentItems().get(position);
        holder.description.setText(curItem.description);
        holder.checkBox.setChecked(curItem.isDone);

        this.addLineToMarked(holder, curItem);

        holder.checkBox.setOnClickListener(view -> {
            if (curItem.isDone) {
                itemsHolder.markItemInProgress(curItem);
            } else {
                itemsHolder.markItemDone(curItem);
            }
            notifyDataSetChanged();
        });

        holder.removeButton.setOnClickListener(view -> {
            itemsHolder.deleteItem(curItem);
            notifyDataSetChanged();
        });

        holder.editButton.setOnClickListener(view -> {
            Intent editIntent = new Intent(this.context, EditActivity.class);
            editIntent.putExtra("rowItem", curItem);
            this.context.startActivity(editIntent);
        });
    }


    private void addLineToMarked(@NonNull MyViewHolder holder, TodoItem curItem) {
        if(curItem.isDone){
            holder.description.setPaintFlags(holder.description.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }else{
            holder.description.setPaintFlags(0);
        }
    }

    @Override
    public int getItemCount() {
        return itemsHolder.getCurrentItems().size();
    }
}
