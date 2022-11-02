package com.oaktwister.services.resources;

public interface ViewResources {

    interface Accounts {
        String PANE = "/com/oaktwister/views/accounts/AccountPane.fxml";
    }

    interface Identities {
        String PANE = "/com/oaktwister/views/identities/IdentityPane.fxml";
    }

    interface Login {
        String FAILED_ALERT = "/com/oaktwister/views/login/LoginFailedAlert.fxml";
        String LAYOUT = "/com/oaktwister/views/login/LoginLayout.fxml";
    }

    interface Main {
        String LAYOUT = "/com/oaktwister/views/main/MainLayout.fxml";
    }

    interface Platforms {
        String PANE = "/com/oaktwister/views/platforms/PlatformPane.fxml";
        String EDIT_DIALOG = "/com/oaktwister/views/platforms/EditPlatformDialog.fxml";
    }

    interface Widgets {
        String GROUP_BOX = "/com/oaktwister/views/widgets/GroupBox.fxml";
        String IMAGE_BUTTON_BOX = "/com/oaktwister/views/widgets/ImageButtonBox.fxml";
        String PAGE_PANE = "/com/oaktwister/views/widgets/PagePane.fxml";
    }

}
