package com.oaktwister.viewmodels.models;

import com.oaktwister.core.ViewModelFactory;
import com.oaktwister.exceptions.UnknownGrantTypeException;
import com.oaktwister.models.claims.Claim;
import com.oaktwister.models.claims.ClaimMap;
import com.oaktwister.models.grants.Grant;
import com.oaktwister.services.parsers.GrantTypeParser;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;

import java.util.Collection;

public class ClaimMapViewModel {

    private final ViewModelFactory viewModelFactory;
    private final GrantTypeParser grantTypeParser;

    private final SimpleListProperty<ClaimViewModel> claimsProperty;

    public ClaimMapViewModel(ViewModelFactory viewModelFactory, GrantTypeParser grantTypeParser) {
        this.viewModelFactory = viewModelFactory;
        this.grantTypeParser = grantTypeParser;
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

    public ClaimMap getClaimMap() throws UnknownGrantTypeException {
        ClaimMap claimMap = new ClaimMap();
        for(ClaimViewModel viewModel : claimsProperty.get()) {
            String name = viewModel.nameProperty().get();
            String grantTypeName = viewModel.grantTypeNameProperty().get();
            Class<? extends Grant<?>> grantType = grantTypeParser.getGrantType(grantTypeName);
            boolean isOptional = viewModel.isOptionalProperty().get();
            Claim claim = new Claim(name, grantType, isOptional);
            claimMap.add(claim);
        }
        return claimMap;
    }

    public ListProperty<ClaimViewModel> claimsProperty() {
        return claimsProperty;
    }

    public ReadOnlyIntegerProperty sizeProperty() {
        return claimsProperty().sizeProperty();
    }

}
