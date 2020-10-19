package com.medicto.medicto.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.medicto.medicto.R;

public class Main_Fragment_Instruction_trans extends Fragment {
    public static Main_Fragment_Instruction newInstance(int translateCheck){
        Main_Fragment_Instruction main_fragment_instruction = new Main_Fragment_Instruction();
        Bundle args = new Bundle();

        return main_fragment_instruction;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_instruction_finish,container,false);


        return view;
    }
}
