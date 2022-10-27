module oaktwister.main {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.jetbrains.annotations;
    requires org.json;

    opens com.oaktwister.core to javafx.graphics;
    exports com.oaktwister.core;

    opens com.oaktwister.views.windows.landings to javafx.fxml;
    exports com.oaktwister.views.windows.landings;

    opens com.oaktwister.views.controls.laterals to javafx.fxml;
    exports com.oaktwister.views.controls.laterals;

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

}
