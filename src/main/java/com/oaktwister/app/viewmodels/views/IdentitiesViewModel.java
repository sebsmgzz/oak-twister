package com.oaktwister.app.viewmodels.views;

import com.oaktwister.app.core.ViewModelFactory;
import com.oaktwister.app.viewmodels.ErrorViewModel;
import com.oaktwister.domain.exceptions.InvalidSessionPropertyException;
import com.oaktwister.domain.models.identities.Identity;
import com.oaktwister.app.services.logging.Logger;
import com.oaktwister.infrastructure.repos.IdentitiesRepo;
import com.oaktwister.app.viewmodels.models.IdentityViewModel;
import javafx.beans.property.ReadOnlyListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;

import java.io.IOException;
import java.util.ArrayList;

public class IdentitiesViewModel extends ErrorViewModel {

    private final ViewModelFactory viewModelFactory;
    private final IdentitiesRepo identitiesRepo;
    private final Logger logger;

    private final SimpleListProperty<IdentityViewModel> identitiesProperty;

    public IdentitiesViewModel(ViewModelFactory viewModelFactory, IdentitiesRepo identitiesRepo, Logger logger) {
        this.viewModelFactory = viewModelFactory;
        this.identitiesRepo = identitiesRepo;
        this.logger = logger;
        identitiesProperty = new SimpleListProperty<>(FXCollections.observableArrayList());
    }

    public ReadOnlyListProperty<IdentityViewModel> identitiesProperty() {
        return identitiesProperty;
    }

    public void load() {
        try {
            logger.debug("Loading identities");
            ArrayList<Identity> identities = identitiesRepo.findAll();
            for(Identity identity : identities) {
                IdentityViewModel identityViewModel = viewModelFactory.identity();
                identitiesProperty.add(identityViewModel);
                identityViewModel.setIdentity(identity);
            }
            logger.debug("Loaded %s identities", identities.size());
        } catch (IOException | InvalidSessionPropertyException ex) {
            logger.error(ex);
            setError(ex);
        }
    }

    public void clear() {
        identitiesProperty.clear();
    }

}
