package views;

import viewmodels.BaseViewModel;

public abstract class BaseController<T extends BaseViewModel> {

    protected ViewFactory factory;
    protected T viewModel;

    public void setup(ViewFactory factory, T viewModel) {
        this.factory = factory;
        this.viewModel = viewModel;
        init();
    }

    public abstract void init();

}
