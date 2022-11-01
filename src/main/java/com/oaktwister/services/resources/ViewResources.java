package com.oaktwister.services.resources;

public interface ViewResources {

    interface Controls {
        String ACCOUNT_PANE = "/com/oaktwister/views/controls/AccountPane.fxml";
        String PLATFORM_PANE = "/com/oaktwister/views/controls/PlatformPane.fxml";
        String IDENTITY_PANE = "/com/oaktwister/views/controls/IdentityPane.fxml";
        String IMAGE_BUTTON_BOX = "/com/oaktwister/views/controls/ImageButtonBox.fxml";
        String PAGE_PANE = "/com/oaktwister/views/controls/PagePane.fxml";
    }

    interface Dialogs {
        String EDIT_PLATFORM = "/com/oaktwister/views/dialogs/EditPlatformDialog.fxml";
        String FAILED_LOGIN = "/com/oaktwister/views/dialogs/LoginFailedAlert.fxml";
    }

    interface Windows {
        String MAIN = "/com/oaktwister/views/layouts/MainLayout.fxml";
        String LOGIN = "/com/oaktwister/views/layouts/LoginLayout.fxml";
    }

}
