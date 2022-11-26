package com.oaktwister.app.viewmodels.models.claims;

import com.oaktwister.app.core.ViewModelFactory;
import com.oaktwister.domain.models.claims.Claim;
import com.oaktwister.domain.models.claims.ClaimMap;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Collection;

public class ClaimMapViewModel {

    private final ViewModelFactory viewModelFactory;

    private final SimpleListProperty<ClaimViewModel> claimsProperty;

    public ClaimMapViewModel(ViewModelFactory viewModelFactory) {
        this.viewModelFactory = viewModelFactory;
        claimsProperty = new SimpleListProperty<>(FXCollections.observableArrayList());
    }

    public void setClaimMap(ClaimMap claimMap) {
        Collection<Claim> claims = claimMap.claims();
        for(Claim claim : claims) {
            ClaimViewModel claimViewModel = viewModelFactory.claim();
            claimViewModel.setClaim(claim);
            claimsProperty.add(claimViewModel);
        }
    }

    public ClaimMap getClaimMap() {
        ClaimMap claimMap = new ClaimMap();
        for(ClaimViewModel viewModel : claimsProperty.get()) {
            Claim claim = viewModel.getClaim();
            claimMap.add(claim);
        }
        return claimMap;
    }

    public ListProperty<ClaimViewModel> claimsProperty() {
        return claimsProperty;
    }
    public ObservableList<ClaimViewModel> getClaims() {
        return claimsProperty().get();
    }
    public boolean addClaim(ClaimViewModel claim) {
        return getClaims().add(claim);
    }
    public boolean addClaims(ClaimViewModel... claims) {
        return getClaims().addAll(claims);
    }
    public boolean removeClaim(ClaimViewModel claim) {
        return getClaims().remove(claim);
    }
    public boolean removeClaims(ClaimViewModel... claims) {
        return getClaims().removeAll(claims);
    }
    public void clearClaims() {
        getClaims().clear();
    }

    public ReadOnlyIntegerProperty sizeProperty() {
        return claimsProperty().sizeProperty();
    }

}
