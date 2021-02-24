package views;

import viewmodels.*;
import views.home.HomeView;
import views.lateralpane.LateralPaneView;
import views.pane.PaneView;

import java.io.IOException;

public class ViewFactory {

    private final ViewModelFactory viewModelFactory;
    private HomeView homeView;
    private LateralPaneView lateralPane;

    public HomeView getHomeView() throws IOException {
        if(homeView == null) {
            homeView = new HomeView(viewModelFactory.getHomeViewModel());
        }
        return homeView;
    }

    public LateralPaneView getLateralPaneView() throws IOException {
        if(lateralPane == null) {
            lateralPane = new LateralPaneView(viewModelFactory.getLateralPaneViewModel());
        }
        return lateralPane;
    }

    public PaneView getPaneView() throws IOException {
        return new PaneView(viewModelFactory.getPaneViewModel());
    }

    public ViewFactory(ViewModelFactory viewModelFactory) {
        this.viewModelFactory = viewModelFactory;
    }

}
