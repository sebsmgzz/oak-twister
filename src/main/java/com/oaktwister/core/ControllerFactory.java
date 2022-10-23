package com.oaktwister.core;

import com.oaktwister.views.landings.LandingViewController;
import com.oaktwister.views.main.MainViewController;
import javafx.util.Callback;

import java.io.IOException;

public class ControllerFactory implements Callback<Class<?>, Object> {

    private final ViewMediator viewMediator;
    private final ViewModelFactory viewModelFactory;

    public ControllerFactory(ViewMediator viewMediator, ViewModelFactory viewModelFactory) {
        this.viewMediator = viewMediator;
        this.viewModelFactory = viewModelFactory;
    }

    @Override
    public Object call(Class<?> aClass) {
        try {
            if(aClass.equals(LandingViewController.class)) {
                return new LandingViewController(viewMediator, viewModelFactory.getLandingViewModel());
            } else if (aClass.equals(MainViewController.class)) {
                return new MainViewController(viewMediator, viewModelFactory.getMainViewModel());
            } else {
                return null;
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

}
