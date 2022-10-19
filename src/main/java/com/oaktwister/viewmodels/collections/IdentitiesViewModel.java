package com.oaktwister.viewmodels.collections;

import com.oaktwister.models.aggregators.Identity;
import com.oaktwister.services.logging.Logger;
import com.oaktwister.services.repos.IdentitiesRepo;
import com.oaktwister.viewmodels.models.IdentityViewModel;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;

import java.util.ArrayList;

public class IdentitiesViewModel {

    private final IdentitiesRepo identitiesRepo;
    private final Logger logger;

    private final SimpleListProperty<IdentityViewModel> identities;

    public IdentitiesViewModel(IdentitiesRepo identitiesRepo, Logger logger) {
        this.identitiesRepo = identitiesRepo;
        this.logger = logger;
        this.identities = new SimpleListProperty<>(FXCollections.observableArrayList());
    }

    public void loadIdentities() {
        ArrayList<Identity> identities = identitiesRepo.findAll();
        for(Identity identity : identities) {
            this.identities.add(new IdentityViewModel(identity));
        }
    }

}
