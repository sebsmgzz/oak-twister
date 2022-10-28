package com.oaktwister.services.resources;

public interface ViewResources {

    interface Controls {
        String ACCOUNT_PANE = "/com/oaktwister/views/controls/accounts/AccountPane.fxml";
        String PLATFORM_PANE = "/com/oaktwister/views/controls/platforms/PlatformPane.fxml";
        String IDENTITY_PANE = "/com/oaktwister/views/controls/identities/IdentityPane.fxml";
        String IMAGE_BUTTON_BOX = "/com/oaktwister/views/controls/buttons/ImageButtonBox.fxml";
        String PAGE_PANE = "/com/oaktwister/views/controls/pages/PagePane.fxml";
    }

    interface Dialogs {
        String EDIT_PLATFORM = "/com/oaktwister/views/dialogs/platforms/EditPlatformDialogPane.fxml";
    }

    interface Windows {
        String LANDING = "/com/oaktwister/views/windows/landings/LandingView.fxml";
        String MAIN = "/com/oaktwister/views/windows/main/MainView.fxml";
    }

}
