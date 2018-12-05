package com.chubby.notsochubby;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.security.PublicKey;

public class ExerciseAdapter extends RecyclerView.Adapter<ExerciseAdapter.ExerciseViewHolder> {
    public static class ExerciseViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mTextViewDesc;
        public TextView mTextViewRepeat;
        public CheckBox mcheckBox;
        public ImageView mImageView;
        public ExerciseViewHolder(View v) {
            super(v);
            mTextViewDesc = v.findViewById(R.id.textViewDescription);

        }
}

