package com.chubby.notsochubby.Models.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chubby.notsochubby.Models.Exercise;
import com.chubby.notsochubby.R;

import java.util.ArrayList;

public class ExerciseAdapter extends RecyclerView.Adapter<ExerciseAdapter.ExerciseViewHolder> {
    private ArrayList<Exercise> mExercises;
    private Context mContext;
        public ExerciseAdapter(Context context,ArrayList<Exercise> exercises) {
            this.mExercises = exercises;
            this.mContext = context;
        }

    @Override
    public ExerciseAdapter.ExerciseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.layout_exercise_list_template,parent,false);
        return new ExerciseViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ExerciseViewHolder holder, int position) {
        Exercise exercise = mExercises.get(position);
        holder.mTextViewDesc.setText(exercise.getDescription());
        holder.mTextViewRepeat.setText(exercise.getRepeats());
        Glide.with(mContext)
                .load(exercise.getImage())
                .into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return mExercises.size();
    }

    public static class ExerciseViewHolder extends RecyclerView.ViewHolder {
        private TextView mTextViewDesc;
        private TextView mTextViewRepeat;
        private ImageView mImageView;
        public ExerciseViewHolder(View v) {
            super(v);
            mTextViewDesc = v.findViewById(R.id.textViewDesc);
            mTextViewRepeat = v.findViewById(R.id.textViewTimeToRepeat);
            mImageView = v.findViewById(R.id.imageView);
        }
    }
}

