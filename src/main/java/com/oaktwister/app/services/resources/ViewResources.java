package com.oaktwister.app.services.resources;

public interface ViewResources {

    String BASE_URL = "/com/oaktwister/app/views";

    interface Accounts {
        String URL = BASE_URL + "/accounts";
        String PANE = URL + "/AccountPane.fxml";
        String PAGE = URL + "/AccountsPage.fxml";
    }

    interface Identities {
        String URL = BASE_URL + "/identities";
        String PANE = URL + "/IdentityPane.fxml";
        String PAGE = URL + "/IdentitiesPage.fxml";
        String TABLE = URL + "/IdentitiesTable.fxml";
    }

    interface Login {
        String URL = BASE_URL + "/login";
        String FAILED_ALERT = URL + "/LoginFailedAlert.fxml";
        String LAYOUT = URL + "/LoginLayout.fxml";
    }

    interface Main {
        String URL = BASE_URL + "/main";
        String LAYOUT = URL + "/MainLayout.fxml";
    }

    interface Platforms {
        String URL = BASE_URL + "/platforms";
        String PANE = URL + "/PlatformPane.fxml";
        String EDIT_DIALOG = URL + "/EditPlatformDialog.fxml";
        String PAGE = URL + "/PlatformsPage.fxml";
    }

    interface Widgets {
        String URL = BASE_URL + "/widgets";
        String GROUP_BOX = URL + "/GroupBox.fxml";
        String IMAGE_BUTTON_BOX = URL + "/ImageButtonBox.fxml";
        String CRUD_FRAME = URL + "/crud/CrudFrame.fxml";
        String CRUD_PANE = URL + "/crud/CrudPane.fxml";
        String CRUD_PAGE = URL + "/crud/CrudPage.fxml";
    }

}
