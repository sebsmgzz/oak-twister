package com.oaktwister.services.resources;

public interface ViewResources {

    interface Controls {
        String ACCOUNT_PANE = "/com/oaktwister/views/controls/accounts/account-pane.fxml";
        String PLATFORM_PANE = "/com/oaktwister/views/controls/platforms/platform-pane.fxml";
        String IDENTITY_PANE = "/com/oaktwister/views/controls/identities/identity-pane.fxml";
        String IMAGE_BUTTON_BOX = "/com/oaktwister/views/controls/imagebuttonboxes/image-button-box.fxml";
        String PAGE_PANE = "/com/oaktwister/views/controls/pagepanes/page-pane.fxml";
    }

    interface Dialogs {
        String EDIT_PLATFORM = "/com/oaktwister/views/dialogs/platforms/edit-platform-dialog-pane.fxml";
    }

    interface Windows {
        String LANDING = "/com/oaktwister/views/windows/landings/landing.fxml";
        String MAIN = "/com/oaktwister/views/windows/main/main.fxml";
    }

}
