package com.oaktwister.services.resources;

public interface ViewResources {

    interface Accounts {
        String PANE = "/com/oaktwister/views/accounts/AccountPane.fxml";
        String PAGE = "/com/oaktwister/views/accounts/AccountsPage.fxml";
    }

    interface Identities {
        String PANE = "/com/oaktwister/views/identities/IdentityPane.fxml";
        String PAGE = "/com/oaktwister/views/identities/IdentitiesPage.fxml";;
    }

    interface Login {
        String FAILED_ALERT = "/com/oaktwister/views/login/LoginFailedAlert.fxml";
        String LAYOUT = "/com/oaktwister/views/login/LoginLayout.fxml";
    }

    interface Main {
        String LAYOUT = "/com/oaktwister/views/main/MainLayout.fxml";
        String HEADER = "/com/oaktwister/views/main/Header.fxml";
    }

    interface Platforms {
        String PANE = "/com/oaktwister/views/platforms/PlatformPane.fxml";
        String EDIT_DIALOG = "/com/oaktwister/views/platforms/EditPlatformDialog.fxml";
        String PAGE = "/com/oaktwister/views/platforms/PlatformsPage.fxml";
    }

    interface Widgets {
        String GROUP_BOX = "/com/oaktwister/views/widgets/GroupBox.fxml";
        String IMAGE_BUTTON_BOX = "/com/oaktwister/views/widgets/ImageButtonBox.fxml";
        String PAGE_PANE = "/com/oaktwister/views/widgets/PagePane.fxml";
        String FLOW_PANE = "/com/oaktwister/views/widgets/FlowPage.fxml";
        String IMAGE_FRAME = "/com/oaktwister/views/widgets/DeleteFrame.fxml";
        String CRUD_PAGE = "/com/oaktwister/views/widgets/CrudPage.fxml";
    }

}
