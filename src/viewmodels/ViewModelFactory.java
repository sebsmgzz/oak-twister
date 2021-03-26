package viewmodels;

import management.Manager;

public class ViewModelFactory {

    private final Manager manager;

    private HomeViewModel homeViewModel;
    private LateralPaneViewModel lateralPaneViewModel;

    public HomeViewModel getHomeViewModel() {
        if(homeViewModel == null) {
            homeViewModel = new HomeViewModel();
        }
        return homeViewModel;
    }

    public LateralPaneViewModel getLateralPaneViewModel() {
        if(lateralPaneViewModel == null) {
            lateralPaneViewModel = new LateralPaneViewModel();
        }
        return lateralPaneViewModel;
    }

    public PaneViewModel getPaneViewModel() {
        return new PaneViewModel();
    }

    public ViewModelFactory(Manager manager) {
        this.manager = manager;
    }

}
