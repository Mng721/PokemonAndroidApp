package com.example.mypokemonapplication.viewmodels;

import androidx.lifecycle.ViewModel;

import com.example.mypokemonapplication.model.pokemon.Ability.Ability;

public class AbilityDetailViewModel extends ViewModel {

    private Ability ability;

    public Ability getAbility() {
        return ability;
    }

    public void setAbility(Ability ability) {
        this.ability = ability;
    }
}

