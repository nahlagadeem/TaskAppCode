package com.example.taskapp.adapters;


import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;

import com.example.taskapp.AnswersActivity;
import com.example.taskapp.MainActivity;
import com.example.taskapp.R;
import com.example.taskapp.classes.QuestionModel;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import java.util.List;

import static com.example.taskapp.MainActivity.que_id;

public class QuestionsAdapter extends RecyclerView.Adapter<QuestionsAdapter.MyViewHolder> {

    private List<QuestionModel> itemsList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title,username;
        public ImageView img;

        public MyViewHolder(View view) {
            super(view);
            username= view.findViewById(R.id.username);
            img= view.findViewById(R.id.image);
            title= view.findViewById(R.id.title);
        }
    }

    public QuestionsAdapter(List<QuestionModel> itemsList) {
        this.itemsList = itemsList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.questions, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final QuestionModel itemaya = itemsList.get(position);

        holder.title.setText(itemaya.getTitle());
        holder.username.setText(itemaya.getUsername());
        Picasso.get().load(itemaya.getImage()).memoryPolicy(MemoryPolicy.NO_CACHE).into(holder.img);

        holder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.context.startActivity(new Intent(MainActivity.context, AnswersActivity.class).putExtra("question", itemaya.getTitle()).putExtra("que_id",que_id));
            }
        });



    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }
}