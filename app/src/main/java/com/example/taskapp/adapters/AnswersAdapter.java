package com.example.taskapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;

import com.example.taskapp.R;
import com.example.taskapp.classes.AnswerModel;
import com.example.taskapp.classes.QuestionModel;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AnswersAdapter extends RecyclerView.Adapter<AnswersAdapter.MyViewHolder> {

    private List<AnswerModel> itemsList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView answer;

        public MyViewHolder(View view) {
            super(view);
            answer= view.findViewById(R.id.answer);
        }
    }

    public AnswersAdapter(List<AnswerModel> itemsList) {
        this.itemsList = itemsList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.answers, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        AnswerModel itemaya = itemsList.get(position);

           holder.answer.setText(itemaya.getAnswer());


    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }
}
