package com.asifahmedsohan.quizapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class QuizListAdapter extends RecyclerView.Adapter<QuizListAdapter.QuizListViewHolder> {

    private List<QuizListModel> quizListModels;

    @NonNull
    @Override
    public QuizListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_list_item, parent, false);
        return new QuizListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuizListViewHolder holder, int position) {
        holder.listTitle.setText(quizListModels.get(position).getName());
    }

    @Override
    public int getItemCount() {
        if (quizListModels == null){
            return 0;
        } else {
            return quizListModels.size();
        }
    }

    public void setQuizListModels(List<QuizListModel> quizListModels) {
        this.quizListModels = quizListModels;
    }

    public class QuizListViewHolder extends RecyclerView.ViewHolder {

        private ImageView listImage;
        private TextView listTitle;
        private TextView listDesc;
        private TextView listLevel;
        private Button listBtn;

        public QuizListViewHolder(@NonNull View itemView) {
            super(itemView);

            listImage = itemView.findViewById(R.id.list_image);
            listTitle = itemView.findViewById(R.id.list_title);
            listDesc = itemView.findViewById(R.id.list_desc);
            listLevel = itemView.findViewById(R.id.list_difficulty);
            listBtn = itemView.findViewById(R.id.list_btn);
        }
    }
}
