package com.example.mypokemonapplication.model.pokemon.Ability;

import com.example.mypokemonapplication.model.utility.common_models.Name;
import com.example.mypokemonapplication.model.utility.common_models.NamedAPIResource;
import com.example.mypokemonapplication.model.utility.languages.Language;
import com.google.gson.annotations.SerializedName;

public class AbilityFlavorText {
    @SerializedName("flavor_text")
    private String flavorText;

    private NamedAPIResource languages;

    @SerializedName("version_group")
    private NamedAPIResource versionGroup;

    public AbilityFlavorText(String flavorText, NamedAPIResource languages, NamedAPIResource versionGroup) {
        this.flavorText = flavorText;
        this.languages = languages;
        this.versionGroup = versionGroup;
    }

    public AbilityFlavorText() {
    }

    public String getFlavorText() {
        return flavorText;
    }

    public void setFlavorText(String flavorText) {
        this.flavorText = flavorText;
    }

    public NamedAPIResource getLanguages() {
        return languages;
    }

    public void setLanguages(NamedAPIResource languages) {
        this.languages = languages;
    }

    public NamedAPIResource getVersionGroup() {
        return versionGroup;
    }

    public void setVersionGroup(NamedAPIResource versionGroup) {
        this.versionGroup = versionGroup;
    }
}
