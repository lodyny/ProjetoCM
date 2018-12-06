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

import java.util.ArrayList;


public class ExerciseFragment extends Fragment {
    private ExerciseAdapter mExerciseAdapter;
    private View v;
    private ArrayList<Exercise> exercises;
    private RecyclerView recyclerView;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_exercise,null);
        exercises =  new ArrayList<>();
        Bundle bundle = this.getArguments();
        if(bundle!=null){
            if (bundle.getBoolean("isAbsChecked")){
                exercises.add(new Exercise(getString(R.string.exercise_hipopressive_abdominal),"20-30x",getResources().getDrawable(R.drawable.hipopressiveabs)));
                exercises.add(new Exercise(getString(R.string.exercise_frontal_board),"30-60 "+getString(R.string.seconds),getResources().getDrawable(R.drawable.frontalboard)));
                exercises.add(new Exercise(getString(R.string.exercise_side_board),"30-60 "+getString(R.string.seconds),getResources().getDrawable(R.drawable.sideboard)));
                exercises.add(new Exercise(getString(R.string.exercise_modified_bicycle),"20-30x",getResources().getDrawable(R.drawable.modifiedbicycle)));
                exercises.add(new Exercise(getString(R.string.exercise_inverted_abdominal),"12-20x",getResources().getDrawable(R.drawable.abdominalinvertido)));
            }
            if (bundle.getBoolean("isBackChecked")){
                exercises.add(new Exercise(getString(R.string.exercise_back_deadlift),"10-15x",getResources().getDrawable(R.drawable.deadlift)));
                exercises.add(new Exercise(getString(R.string.exercise_back_chinup),"20x",getResources().getDrawable(R.drawable.elevacaoespegasamplas)));
                exercises.add(new Exercise(getString(R.string.exercise_back_remada_inclinada),"30-60x",getResources().getDrawable(R.drawable.remadainclinadabarra)));
                exercises.add(new Exercise(getString(R.string.exercise_back_pulldown),"15-20x",getResources().getDrawable(R.drawable.pulldown)));
            }
//            if(bundle.getBoolean("isBicepsChecked")){
//                exercises.add(new Exercise(getString(R.string.exercise_back_deadlift),"10-15x",getResources().getDrawable(R.drawable.deadlift)));
//                exercises.add(new Exercise(getString(R.string.exercise_back_chinup),"20x",getResources().getDrawable(R.drawable.elevacaoespegasamplas)));
//                exercises.add(new Exercise(getString(R.string.exercise_back_remada_inclinada),"30-60x",getResources().getDrawable(R.drawable.remadainclinadabarra)));
//                exercises.add(new Exercise(getString(R.string.exercise_back_pulldown),"15-20x",getResources().getDrawable(R.drawable.pulldown)));
//            }
        }
        mExerciseAdapter = new ExerciseAdapter(getContext(),exercises);
        recyclerView =  (RecyclerView)v.findViewById(R.id.recyclerViewExercise);
        recyclerView.setClickable(true);
        recyclerView.setAdapter(mExerciseAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(false);
        return v;
    }
}
