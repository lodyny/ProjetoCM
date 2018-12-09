package com.chubby.notsochubby;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class GestorExercicioFragment extends Fragment {
    private Button button;
    private CheckBox checkAbs;
    private CheckBox checkBack;
    private CheckBox checkBiceps;
    private CheckBox checkChest;
    private CheckBox checkShoulder;
    private CheckBox checkTricep;
    private View v;
    private Fragment fragment;

    private View.OnClickListener buttonExerciseClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Bundle bundle = new Bundle();
            bundle.putBoolean("isAbsChecked",checkAbs.isChecked());
            bundle.putBoolean("isBackChecked",checkBack.isChecked());
            bundle.putBoolean("isBicepsChecked",checkBiceps.isChecked());
            bundle.putBoolean("isChestChecked",checkChest.isChecked());
            bundle.putBoolean("isShoulderChecked", checkShoulder.isChecked());
            bundle.putBoolean("isTricepsChecked",checkTricep.isChecked());
            fragment = new ExerciseFragment();
            fragment.setArguments(bundle);
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container,fragment);
            fragmentTransaction.commit();
        }
    };
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_gestor_exercicio,null);
        button = (Button)v.findViewById(R.id.buttonExercise);
        checkAbs = (CheckBox)v.findViewById(R.id.checkBoxAbs);
        checkBack = (CheckBox)v.findViewById(R.id.checkBoxBack);
        checkBiceps = (CheckBox)v.findViewById(R.id.checkBoxBiceps);
        checkChest = (CheckBox)v.findViewById(R.id.checkBoxChest);
        checkShoulder = (CheckBox)v.findViewById(R.id.checkBoxShoulder);
        checkTricep = (CheckBox)v.findViewById(R.id.checkBoxTriceps);
        button.setOnClickListener(buttonExerciseClick);
        return v;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
