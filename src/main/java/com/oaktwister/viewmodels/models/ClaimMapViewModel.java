package com.oaktwister.viewmodels.models;

import com.oaktwister.models.aggregators.claims.Claim;
import com.oaktwister.models.aggregators.claims.ClaimMap;
import com.oaktwister.viewmodels.util.DualChangeListener;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.ReadOnlyListProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;

public class ClaimMapViewModel {

    private ClaimMap claimMap;
    private final SimpleListProperty<Claim> claimsProperty;
    private final SimpleIntegerProperty claimCountProperty;

    public ClaimMapViewModel() {
        this.claimsProperty = new SimpleListProperty<>(FXCollections.observableArrayList());
        this.claimCountProperty = new SimpleIntegerProperty(-1);
    }

    public void bind(ClaimMap claimMap) {
        this.claimMap = claimMap;

        claimsProperty.set(FXCollections.observableArrayList(this.claimMap.claims()));
        claimsProperty.addListener(new DualChangeListener<>(this.claimMap::add, this.claimMap::remove));

        claimCountProperty.set(this.claimMap.size());
        claimsProperty.addListener((ListChangeListener<Claim>) change ->
                claimCountProperty.set(claimsProperty.size()));
    }

    public ReadOnlyListProperty<Claim> claimsProperty() {
        return claimsProperty;
    }

    public ReadOnlyIntegerProperty claimCountProperty() {
        return claimCountProperty;
    }

}