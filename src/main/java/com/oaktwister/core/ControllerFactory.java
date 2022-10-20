package com.oaktwister.core;

import com.oaktwister.views.controllers.LandingViewController;
import com.oaktwister.views.controllers.MainViewController;
import javafx.util.Callback;

import java.io.IOException;

public class ControllerFactory implements Callback<Class<?>, Object> {

    private final ViewHandler viewHandler;
    private final ViewModelFactory viewModelFactory;

    public ControllerFactory(ViewHandler viewHandler, ViewModelFactory viewModelFactory) {
        this.viewHandler = viewHandler;
        this.viewModelFactory = viewModelFactory;
    }

    @Override
    public Object call(Class<?> aClass) {
        try {
            if(aClass.equals(LandingViewController.class)) {
                return new LandingViewController(viewHandler, viewModelFactory.getLandingViewModel());
            } else if (aClass.equals(MainViewController.class)) {
                return new MainViewController(viewHandler, viewModelFactory.getMainViewModel());
            } else {
                return null;
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

}
