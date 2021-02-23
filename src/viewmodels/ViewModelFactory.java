package viewmodels;

import models.ModelFactory;

public class ViewModelFactory {

    private ModelFactory modelFactory;
    private HomeViewModel homeViewModel;

    public HomeViewModel getHomeViewModel() {
        if(homeViewModel == null) {
            homeViewModel = new HomeViewModel();
        }
        return homeViewModel;
    }

    public ViewModelFactory(ModelFactory modelFactory) {
        this.modelFactory = modelFactory;
    }

}
