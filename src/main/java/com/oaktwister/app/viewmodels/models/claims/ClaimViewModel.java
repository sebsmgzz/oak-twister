package com.oaktwister.app.viewmodels.models.claims;

import com.oaktwister.domain.models.claims.Claim;
import com.oaktwister.domain.models.claims.MetaGrant;
import javafx.beans.property.*;

public class ClaimViewModel {

    private final SimpleStringProperty nameProperty;
    private final SimpleStringProperty metaGrantNameProperty;
    private final SimpleBooleanProperty isOptionalProperty;

    public ClaimViewModel() {
        nameProperty = new SimpleStringProperty();
        metaGrantNameProperty = new SimpleStringProperty();
        isOptionalProperty = new SimpleBooleanProperty();
    }

    public void setClaim(Claim claim) {
        nameProperty.set(claim.getName());
        metaGrantNameProperty.set(claim.getMetaGrant().getName());
        isOptionalProperty.set(claim.getIsOptional());
    }

    public Claim getClaim() {
        String name = nameProperty.get();
        MetaGrant metaGrant = MetaGrant.tryParse(metaGrantNameProperty.get());
        boolean isOptional = isOptionalProperty.get();
        return new Claim(name, metaGrant, isOptional);
    }

    public void copy(ClaimViewModel source) {
        nameProperty.set(source.getName());
        metaGrantNameProperty.set(source.getMetaGrantName());
        isOptionalProperty.set(source.getIsOptional());
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

    public StringProperty metaGrantNameProperty() {
        return metaGrantNameProperty;
    }
    public String getMetaGrantName() {
        return metaGrantNameProperty().get();
    }
    public void setMetaGrantName(String value) {
        metaGrantNameProperty().set(value);
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
