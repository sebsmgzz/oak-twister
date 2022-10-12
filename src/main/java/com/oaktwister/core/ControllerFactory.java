package com.oaktwister.core;

import com.oaktwister.views.landing.LandingViewController;
import com.oaktwister.views.main.AccountsViewController;
import com.oaktwister.views.main.IdentitiesViewController;
import com.oaktwister.views.main.MainViewController;
import com.oaktwister.views.main.PlatformsViewController;
import javafx.util.Callback;

public class ControllerFactory implements Callback<Class<?>, Object> {

    private final ViewFactory viewFactory;
    private final ViewModelFactory viewModelFactory;

    public ControllerFactory(ViewFactory viewFactory, ViewModelFactory viewModelFactory) {
        this.viewFactory = viewFactory;
        this.viewModelFactory = viewModelFactory;
    }

    @Override
    public Object call(Class<?> aClass) {
        if(aClass.equals(LandingViewController.class)) {
            return new LandingViewController(viewFactory, viewModelFactory.getLandingViewModel());
        } else if (aClass.equals(MainViewController.class)) {
            return new MainViewController(viewFactory, viewModelFactory.getMainViewModel());
        } else if (aClass.equals(IdentitiesViewController.class)) {
            return new IdentitiesViewController(viewFactory, viewModelFactory.getIdentitiesViewModel());
        } else if (aClass.equals(PlatformsViewController.class)) {
            return new PlatformsViewController(viewFactory, viewModelFactory.getPlatformsViewModel());
        } else if (aClass.equals(AccountsViewController.class)) {
            return new AccountsViewController(viewFactory, viewModelFactory.getAccountsViewModel());
        } else {
            return null;
        }
    }

}
