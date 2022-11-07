package com.oaktwister.app.services.resources;

public interface ViewResources {
    String BASE_URL = "/com/oaktwister/app/views";

    interface Accounts {
        String PANE = BASE_URL + "/accounts/AccountPane.fxml";
        String PAGE = BASE_URL + "/accounts/AccountsPage.fxml";
    }

    interface Identities {
        String PANE = BASE_URL + "/identities/IdentityPane.fxml";
        String PAGE = BASE_URL + "/identities/IdentitiesPage.fxml";
        String TABLE = BASE_URL + "/identities/IdentitiesTable.fxml";
    }

    interface Login {
        String FAILED_ALERT = BASE_URL + "/login/LoginFailedAlert.fxml";
        String LAYOUT = BASE_URL + "/login/LoginLayout.fxml";
    }

    interface Main {
        String LAYOUT = BASE_URL + "/main/MainLayout.fxml";
        String HEADER = BASE_URL + "/main/Header.fxml";
    }

    interface Platforms {
        String PANE = BASE_URL + "/platforms/PlatformPane.fxml";
        String EDIT_DIALOG = BASE_URL + "/platforms/EditPlatformDialog.fxml";
        String PAGE = BASE_URL + "/platforms/PlatformsPage.fxml";
    }

    interface Widgets {
        String GROUP_BOX = BASE_URL + "/widgets/GroupBox.fxml";
        String IMAGE_BUTTON_BOX = BASE_URL + "/widgets/ImageButtonBox.fxml";
        String PAGE_PANE = BASE_URL + "/widgets/PagePane.fxml";
        String FLOW_PANE = BASE_URL + "/widgets/FlowPage.fxml";
        String IMAGE_FRAME = BASE_URL + "/widgets/DeleteFrame.fxml";
        String CRUD_PAGE = BASE_URL + "/widgets/CrudPage.fxml";
    }

}
