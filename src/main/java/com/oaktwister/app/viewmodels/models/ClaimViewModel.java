package com.oaktwister.app.viewmodels.models;

import com.oaktwister.app.exceptions.UnknownGrantTypeException;
import com.oaktwister.domain.models.claims.Claim;
import com.oaktwister.domain.models.grants.Grant;
import com.oaktwister.app.services.parsers.GrantTypeParser;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ClaimViewModel {

    private final GrantTypeParser grantTypeParser;

    private final SimpleStringProperty nameProperty;
    private final SimpleStringProperty grantTypeNameProperty;
    private final SimpleBooleanProperty isOptionalProperty;

    public ClaimViewModel(GrantTypeParser grantTypeParser) {
        this.grantTypeParser = grantTypeParser;
        nameProperty = new SimpleStringProperty();
        grantTypeNameProperty = new SimpleStringProperty();
        isOptionalProperty = new SimpleBooleanProperty();
    }

    public void setClaim(Claim claim) {
        String name = claim.getName();
        nameProperty.set(name);
        Class<? extends Grant<?>> claimType = claim.getGrantType();
        String grantTypeName = grantTypeParser.getGrantTypeName(claimType);
        grantTypeNameProperty.set(grantTypeName);
        boolean isOptional = claim.getIsOptional();
        isOptionalProperty.set(isOptional);
    }

    public Claim getClaim() throws UnknownGrantTypeException {
        String name = nameProperty.get();
        String claimTypeName = grantTypeNameProperty.get();
        Class<? extends Grant<?>> claimType = grantTypeParser.getGrantType(claimTypeName);
        boolean isOptional = isOptionalProperty.get();
        return new Claim(name, claimType, isOptional);
    }

    public StringProperty nameProperty() {
        return nameProperty;
    }
    public String getName() {
        return nameProperty().get();
    }
    public void setName(String value) {
        nameProperty().set(value);
    }

    public StringProperty grantTypeNameProperty() {
        return grantTypeNameProperty;
    }
    public String getGrantTypeName() {
        return grantTypeNameProperty().get();
    }
    public void setGrantTypeName(String value) {
        grantTypeNameProperty().set(value);
    }

    public BooleanProperty isOptionalProperty() {
        return isOptionalProperty;
    }
    public Boolean getIsOptional() {
        return isOptionalProperty().get();
    }
    public void setIsOptional(Boolean value) {
        isOptionalProperty().set(value);
    }

}
