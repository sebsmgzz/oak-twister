package viewmodels;

public class ViewModelFactory {

    private final DataModelFactory dataModelFactory;
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

    public ViewModelFactory(DataModelFactory dataModelFactory) {
        this.dataModelFactory = dataModelFactory;
    }

}
