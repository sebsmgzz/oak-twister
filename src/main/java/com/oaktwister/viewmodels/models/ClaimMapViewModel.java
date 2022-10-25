package com.oaktwister.viewmodels.models;

import com.oaktwister.models.claims.Claim;
import com.oaktwister.models.claims.ClaimMap;
import com.oaktwister.utils.listeners.ListItemChangeListener;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.ReadOnlyListProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import java.util.Collection;

public class ClaimMapViewModel {

    private ClaimMap claimMap;

    private final SimpleIntegerProperty claimCountProperty;
    private final SimpleListProperty<Claim> claimsProperty;

    public ClaimMapViewModel() {
        claimCountProperty = new SimpleIntegerProperty();
        claimsProperty = new SimpleListProperty<>(FXCollections.observableArrayList());
    }

    public void bind(ClaimMap claimMap) {
        if(this.claimMap != null) {
            throw new RuntimeException("ClaimMap has already been set");
        }
        this.claimMap = claimMap;

        int claimCount = claimMap.size();
        claimCountProperty.set(claimCount);

        Collection<Claim> claims = claimMap.claims();
        claimsProperty.setAll(claims);
        claimsProperty.addListener(new ListItemChangeListener<>(this.claimMap::add, this.claimMap::remove));
        claimsProperty.addListener((ListChangeListener<Claim>) change -> {
            claimCountProperty.set(claimsProperty.size());
        });
    }

    public ReadOnlyIntegerProperty claimCountProperty() {
        return claimCountProperty;
    }

    public ReadOnlyListProperty<Claim> claimsProperty() {
        return claimsProperty;
    }

}
