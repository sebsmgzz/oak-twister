package views;

import javafx.util.Callback;
import viewmodels.ViewModelFactory;
import views.home.HomeController;
import views.lateralpane.LateralPaneController;
import views.platforms.PlatformsController;

public final class ControllerFactory implements Callback<Class<?>, Object> {

    private final ViewModelFactory viewModelFactory;

    private HomeController homeController;
    private LateralPaneController lateralPaneController;
    private PlatformsController platformsController;

    public ControllerFactory(ViewModelFactory viewModelFactory) {
        this.viewModelFactory= viewModelFactory;
    }

    public HomeController getHomeController() {
        if(homeController == null) {
            homeController = new HomeController(viewModelFactory.getHomeViewModel());
        }
        return homeController;
    }

    public LateralPaneController getLateralPaneController() {
        if(lateralPaneController == null) {
            lateralPaneController = new LateralPaneController(viewModelFactory.getLateralPaneViewModel());
        }
        return lateralPaneController;
    }

    public PlatformsController getPlatformsController() {
        if(platformsController == null) {
            platformsController = new PlatformsController(viewModelFactory.getPlatformsViewModel());
        }
        return platformsController;
    }

    @Override
    public Object call(Class<?> aClass) {
        if(aClass.equals(HomeController.class)) {
            return getHomeController();
        } else if(aClass.equals(LateralPaneController.class)) {
            return getLateralPaneController();
        } else if(aClass.equals(PlatformsController.class)) {
            return getPlatformsController();
        }
        return null;
    }

}
