package com.chubby.notsochubby;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.chubby.notsochubby.models.adapters.ExerciseAdapter;
import com.chubby.notsochubby.models.Exercise;

import java.util.ArrayList;
import java.util.Random;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


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
        setHasOptionsMenu(true);
        Bundle bundle = this.getArguments();
        if(bundle!=null) {
            ArrayList<MuscleType> muscleTypesSelected = populateList(bundle);
            ArrayList<Exercise> randomExercises = randomPopulateList(muscleTypesSelected);
            mExerciseAdapter = new ExerciseAdapter(getContext(), randomExercises);
            recyclerView = (RecyclerView) v.findViewById(R.id.recyclerViewExercise);
            recyclerView.setClickable(true);
            recyclerView.setAdapter(mExerciseAdapter);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setHasFixedSize(false);
        }
        return v;
    }

    private ArrayList<MuscleType> populateList(Bundle bundle){
        ArrayList<MuscleType> muscleTypesAvaliable = new ArrayList<>();
        if (bundle.getBoolean("isAbsChecked")){
            muscleTypesAvaliable.add(MuscleType.ABDOMENS);
            exercises.add(new Exercise(getString(R.string.exercise_hipopressive_abdominal),MuscleType.ABDOMENS,"20-30x",getResources().getDrawable(R.drawable.hipopressiveabs)));
            exercises.add(new Exercise(getString(R.string.exercise_frontal_board),MuscleType.ABDOMENS,"30-60 "+getString(R.string.seconds),getResources().getDrawable(R.drawable.frontalboard)));
            exercises.add(new Exercise(getString(R.string.exercise_side_board),MuscleType.ABDOMENS,"30-60 "+getString(R.string.seconds),getResources().getDrawable(R.drawable.sideboard)));
            exercises.add(new Exercise(getString(R.string.exercise_modified_bicycle),MuscleType.ABDOMENS,"20-30x",getResources().getDrawable(R.drawable.modifiedbicycle)));
            exercises.add(new Exercise(getString(R.string.exercise_inverted_abdominal),MuscleType.ABDOMENS,"12-20x",getResources().getDrawable(R.drawable.abdominalinvertido)));
            exercises.add(new Exercise(getString(R.string.exercise_abs_hanging_leg_raise),MuscleType.ABDOMENS,"12-20x",getResources().getDrawable(R.drawable.hanging_leg_raise)));
            exercises.add(new Exercise(getString(R.string.exercise_abs_kneeling_cable_crunch),MuscleType.ABDOMENS,"12-20x",getResources().getDrawable(R.drawable.kneeling_cable)));
        }
        if (bundle.getBoolean("isBackChecked")){
            muscleTypesAvaliable.add(MuscleType.BACK);
            exercises.add(new Exercise(getString(R.string.exercise_back_deadlift),MuscleType.BACK,"10-15x",getResources().getDrawable(R.drawable.deadlift)));
            exercises.add(new Exercise(getString(R.string.exercise_back_chinup),MuscleType.BACK,"20x",getResources().getDrawable(R.drawable.elevacaoespegasamplas)));
            exercises.add(new Exercise(getString(R.string.exercise_back_bent_over_barbell_deadlift),MuscleType.BACK,"30-60x",getResources().getDrawable(R.drawable.remadainclinadabarra)));
            exercises.add(new Exercise(getString(R.string.exercise_back_pulldown),MuscleType.BACK,"15-20x",getResources().getDrawable(R.drawable.pulldown)));
            exercises.add(new Exercise(getString(R.string.exercise_back_wide_grip_pull_up),MuscleType.BACK,"20x",getResources().getDrawable(R.drawable.wide_grip_pull_ups)));
            exercises.add(new Exercise(getString(R.string.exercise_back_wide_grip_seated_cable_row),MuscleType.BACK,"20x",getResources().getDrawable(R.drawable.wide_grip_seated_cable_row)));
            exercises.add(new Exercise(getString(R.string.exercise_back_standing_t_bar_row),MuscleType.BACK,"13x",getResources().getDrawable(R.drawable.standing_t_bar_row)));
        }
        if(bundle.getBoolean("isBicepsChecked")){
            muscleTypesAvaliable.add(MuscleType.BICEPS);
            exercises.add(new Exercise(getString(R.string.exercise_biceps_dumbell_biceps_curl),MuscleType.BICEPS,"10-15x",getResources().getDrawable(R.drawable.dumbell_curl)));
            exercises.add(new Exercise(getString(R.string.exercise_biceps_overhead_cable_curl),MuscleType.BICEPS,"20x",getResources().getDrawable(R.drawable.overheadcablecurl)));
            exercises.add(new Exercise(getString(R.string.exercise_biceps_wide_grip_standing_curl),MuscleType.BICEPS,"15x",getResources().getDrawable(R.drawable.widegripcurl)));
            exercises.add(new Exercise(getString(R.string.exercise_biceps_zottman_curl),MuscleType.BICEPS,"13x",getResources().getDrawable(R.drawable.zottmancurl)));
            exercises.add(new Exercise(getString(R.string.exercise_biceps_incline_dumbbell_hammer_curl),MuscleType.BICEPS,"20x",getResources().getDrawable(R.drawable.incline_dumbbell_hammer_curl)));
            exercises.add(new Exercise(getString(R.string.exercise_biceps_incline_inner_biceps_curl),MuscleType.BICEPS,"20x",getResources().getDrawable(R.drawable.incline_inner_biceps_curl)));
            exercises.add(new Exercise(getString(R.string.exercise_biceps_regular_grip_barbell_curl),MuscleType.BICEPS,"13x",getResources().getDrawable(R.drawable.regular_grip_barbell_curl)));
        }
        if (bundle.getBoolean("isChestChecked")){
            muscleTypesAvaliable.add(MuscleType.CHEST);
            exercises.add(new Exercise(getString(R.string.exercise_chest_bench_press_dumbell),MuscleType.CHEST,"15x",getResources().getDrawable(R.drawable.bench_press_drumbell)));
            exercises.add(new Exercise(getString(R.string.exercise_chest_bench_press),MuscleType.CHEST,"15x",getResources().getDrawable(R.drawable.bench_press)));
            exercises.add(new Exercise(getString(R.string.exercise_chest_reverse_grip_bench_press),MuscleType.CHEST,"13x",getResources().getDrawable(R.drawable.reverse_grip_bench_press)));
            exercises.add(new Exercise(getString(R.string.exercise_chest_weighted_push_ups),MuscleType.CHEST,"20x",getResources().getDrawable(R.drawable.weighted_push_up)));
            exercises.add(new Exercise(getString(R.string.exercise_chest_barbell_bench_press),MuscleType.CHEST,"13x",getResources().getDrawable(R.drawable.barbel_bench_press)));
            exercises.add(new Exercise(getString(R.string.exercise_chest_flat_bench_dumbbell_press),MuscleType.CHEST,"20x",getResources().getDrawable(R.drawable.flat_bench_dumbbell_press)));
            exercises.add(new Exercise(getString(R.string.exercise_chest_low_incline_barbell_bench_press),MuscleType.CHEST,"20x",getResources().getDrawable(R.drawable.low_incline_barbell_bench_press)));
        }
        if (bundle.getBoolean("isShoulderChecked")){
            muscleTypesAvaliable.add(MuscleType.SHOULDER);
            exercises.add(new Exercise(getString(R.string.exercise_shoulder_cable_reverse_flye),MuscleType.SHOULDER,"15x",getResources().getDrawable(R.drawable.cablereverseflye)));
            exercises.add(new Exercise(getString(R.string.exercise_shoulder_bent_over_over_dumbbell_lateral_raise),MuscleType.SHOULDER,"20x",getResources().getDrawable(R.drawable.bent_over_dumbbell_lateral_raise)));
            exercises.add(new Exercise(getString(R.string.exercise_shoulder_one_arm_cable_lateral_raise),MuscleType.SHOULDER,"20x",getResources().getDrawable(R.drawable.one_arm_cable_lateral_raise)));
            exercises.add(new Exercise(getString(R.string.exercise_shoulder_cable_front_raise),MuscleType.SHOULDER,"20x",getResources().getDrawable(R.drawable.cable_front_raise)));
            exercises.add(new Exercise(getString(R.string.exercise_shoulder_push_press),MuscleType.SHOULDER,"20x",getResources().getDrawable(R.drawable.push_press)));
            exercises.add(new Exercise(getString(R.string.exercise_shoulder_wide_grip_smith_machine_upright_row),MuscleType.SHOULDER,"20x",getResources().getDrawable(R.drawable.wide_grip_smith_machine_up_right_row)));
            exercises.add(new Exercise(getString(R.string.exercise_shoulder_face_pull),MuscleType.SHOULDER,"20x",getResources().getDrawable(R.drawable.face_pull)));
            exercises.add(new Exercise(getString(R.string.exercise_shoulder_dumbbell_lateral_raise),MuscleType.SHOULDER,"20x",getResources().getDrawable(R.drawable.dumbell_lateral_raise)));
            exercises.add(new Exercise(getString(R.string.exercise_shoulder_seated_barbell_shoulder_press),MuscleType.SHOULDER,"20x",getResources().getDrawable(R.drawable.seatedbarbell_shoulder_press)));
            exercises.add(new Exercise(getString(R.string.exercise_shoulder_seated_dumbbell_shoulder_press),MuscleType.SHOULDER,"20x",getResources().getDrawable(R.drawable.seateddumbbell_shoulder_press)));
        }
        if (bundle.getBoolean("isTricepsChecked")){
            muscleTypesAvaliable.add(MuscleType.TRICEPS);
            exercises.add(new Exercise(getString(R.string.exercise_triceps_triangle_pushup),MuscleType.TRICEPS,"15x",getResources().getDrawable(R.drawable.triangle_pushups)));
            exercises.add(new Exercise(getString(R.string.exercise_triceps_kickbacks),MuscleType.TRICEPS,"15x",getResources().getDrawable(R.drawable.triceps_kickbacks)));
            exercises.add(new Exercise(getString(R.string.exercise_triceps_dips),MuscleType.TRICEPS,"15x",getResources().getDrawable(R.drawable.bench_dips)));
            exercises.add(new Exercise(getString(R.string.exercise_triceps_overhead_triceps_extension),MuscleType.TRICEPS,"15x",getResources().getDrawable(R.drawable.triceps_extension)));
            exercises.add(new Exercise(getString(R.string.exercise_triceps_rope_pushdown),MuscleType.TRICEPS,"15x",getResources().getDrawable(R.drawable.rope_pushdowns)));
            exercises.add(new Exercise(getString(R.string.exercise_triceps_bar_pushdown),MuscleType.TRICEPS,"15x",getResources().getDrawable(R.drawable.triceps_pushdowns)));
            exercises.add(new Exercise(getString(R.string.exercise_triceps_lying_barbell_triceps_extension),MuscleType.TRICEPS,"15x",getResources().getDrawable(R.drawable.skullcrusher)));
            exercises.add(new Exercise(getString(R.string.exercise_triceps_close_grip_press),MuscleType.TRICEPS,"15x",getResources().getDrawable(R.drawable.close_grip_bench_press)));
        }
        return muscleTypesAvaliable;
    }

    private ArrayList<Exercise> randomPopulateList(ArrayList<MuscleType> muscleTypesSelected){
        ArrayList<Exercise> randomGeneratedList = new ArrayList<>();
        ArrayList<MuscleType> visitedMuscleTypes = new ArrayList<>();
        ArrayList<Exercise> exOfType;
        int checkSameNumber;
        Random r = new Random();
        int index;
        for (Exercise ex : exercises) {
            if (!visitedMuscleTypes.contains(ex.getType())){
                exOfType = pickType(ex.getType());
                index = r.nextInt(exOfType.size());
                checkSameNumber = index;
                randomGeneratedList.add(exercises.get(index));
                index = r.nextInt(exOfType.size());
                while (index == checkSameNumber) {
                    index = r.nextInt(exOfType.size());
                }
                randomGeneratedList.add(exercises.get(index));
                visitedMuscleTypes.add(ex.getType());
            }
        }
        return randomGeneratedList;
    }

    private ArrayList<Exercise> pickType(MuscleType type){
        ArrayList<Exercise> mExercisesOfType = new ArrayList<>();
        for (Exercise ex : exercises){
            if (ex.getType()==type)
                mExercisesOfType.add(ex);
        }
        return mExercisesOfType;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_new_exercise, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.newExercise){
            Fragment fragment = new GestorExercicioFragment();
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container,fragment);
            fragmentTransaction.commit();
            return true;
        }
        return false;
    }
}
