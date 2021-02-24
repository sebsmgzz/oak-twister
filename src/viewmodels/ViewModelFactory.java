package viewmodels;

import models.ModelFactory;

public class ViewModelFactory {

    private final ModelFactory modelFactory;
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

    public ViewModelFactory(ModelFactory modelFactory) {
        this.modelFactory = modelFactory;
    }

}
