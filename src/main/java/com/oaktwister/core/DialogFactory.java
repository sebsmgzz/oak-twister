package com.oaktwister.core;

import com.oaktwister.viewmodels.models.PlatformViewModel;
import com.oaktwister.views.platforms.EditPlatformDialogPane;

public class DialogFactory {

    private final ViewMediator viewMediator;
    private final ViewModelFactory viewModelFactory;

    public DialogFactory(ViewMediator viewMediator, ViewModelFactory viewModelFactory) {
        this.viewMediator = viewMediator;
        this.viewModelFactory = viewModelFactory;
    }

    public EditPlatformDialogPane getEditPlatformDialogPane(PlatformViewModel viewModel) {
        EditPlatformDialogPane dialogPane = new EditPlatformDialogPane();
        viewMediator.loadViewControl(dialogPane);
        dialogPane.setViewModel(viewModel);
        return dialogPane;
    }

    public EditPlatformDialogPane getEditPlatformDialogPane() {
        PlatformViewModel viewModel = viewModelFactory.getPlatformViewModel();
        return getEditPlatformDialogPane(viewModel);
    }

}
