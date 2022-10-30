module oaktwister.main {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.jetbrains.annotations;
    requires org.json;
    requires java.desktop;

    opens com.oaktwister.core to javafx.graphics;
    exports com.oaktwister.core;

    opens com.oaktwister.views.windows.login to javafx.fxml;
    exports com.oaktwister.views.windows.login;

    opens com.oaktwister.views.controls.buttons to javafx.fxml;
    exports com.oaktwister.views.controls.buttons;

    exports com.oaktwister.views.windows.main;
    opens com.oaktwister.views.windows.main to javafx.fxml;

    exports com.oaktwister.views.controls.accounts;
    opens com.oaktwister.views.controls.accounts to javafx.fxml;

    exports com.oaktwister.views.controls.identities;
    opens com.oaktwister.views.controls.identities to javafx.fxml;

    exports com.oaktwister.views.controls.platforms;
    opens com.oaktwister.views.controls.platforms to javafx.fxml;

    exports com.oaktwister.views.dialogs.platforms;
    opens com.oaktwister.views.dialogs.platforms to javafx.fxml;

    exports com.oaktwister.views.controls.pages;
    opens com.oaktwister.views.controls.pages to javafx.fxml;

}
