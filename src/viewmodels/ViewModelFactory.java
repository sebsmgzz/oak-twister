package viewmodels;

import management.Manager;

public class ViewModelFactory {

    private final Manager manager;

    private HomeViewModel homeViewModel;
    private LateralPaneViewModel lateralPaneViewModel;
    private PlatformsViewModel platformsViewModel;

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

    public PlatformsViewModel getPlatformsViewModel() {
        if(platformsViewModel == null) {
            platformsViewModel = new PlatformsViewModel();
        }
        return platformsViewModel;
    }

    public PaneViewModel getPaneViewModel() {
        return new PaneViewModel();
    }

    public ViewModelFactory(Manager manager) {
        this.manager = manager;
    }

}
