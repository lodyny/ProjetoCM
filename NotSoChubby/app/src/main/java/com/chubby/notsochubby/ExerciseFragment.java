package com.chubby.notsochubby;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.nio.BufferUnderflowException;
import java.util.HashMap;


public class ExerciseFragment extends Fragment {
    private View v;
    HashMap<String,Boolean> map;
    RecyclerView recyclerView;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_exercise,null);
        Bundle bundle = this.getArguments();
        recyclerView =  (RecyclerView)v.findViewById(R.id.recyclerViewExercise);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        if(bundle!=null){

        }
        return v;
    }

    private void getAllTrue(Bundle bundle,HashMap<String,Boolean> list){
        if (bundle.getBoolean("isAbsChecked"))
            list.put("Abs",bundle.getBoolean("isAbsChecked"));
        if (bundle.getBoolean("isBackChecked"))
            list.put("Back",bundle.getBoolean("isBackChecked"));
        if (bundle.getBoolean("isBicepsChecked"))
            list.put("Biceps",bundle.getBoolean("isBicepsChecked"));
        if (bundle.getBoolean("isChestChecked"))
            list.put("Chest",bundle.getBoolean("isChestChecked"));
        if (bundle.getBoolean("isShoulderChecked"))
            list.put("Shoulder",bundle.getBoolean("isShoulderChecked"));
        if (bundle.getBoolean("isTricepsChecked"))
            list.put("Triceps",bundle.getBoolean("isTricepsChecked"));
    }
}
