package com.oaktwister.core;

import com.oaktwister.views.roots.LandingViewController;
import com.oaktwister.views.roots.MainViewController;
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
        } else {
            return null;
        }
    }

}
