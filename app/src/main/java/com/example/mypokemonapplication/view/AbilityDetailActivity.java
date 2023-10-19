package com.example.mypokemonapplication.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.example.mypokemonapplication.R;
import com.example.mypokemonapplication.databinding.ActivityAbilityDetailBinding;
import com.example.mypokemonapplication.viewmodels.AbilityDetailViewModel;

public class AbilityDetailActivity extends AppCompatActivity {

    AbilityDetailViewModel abilityDetailViewModel;

    ActivityAbilityDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        abilityDetailViewModel = new ViewModelProvider(this).get(AbilityDetailViewModel.class);
        binding = DataBindingUtil.setContentView(AbilityDetailActivity.this, R.layout.activity_ability_detail);
        binding.setLifecycleOwner(this);
        binding.setAbilityDetailViewModel(abilityDetailViewModel);
    }
}