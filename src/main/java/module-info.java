module oaktwister.main {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.jetbrains.annotations;
    requires org.json;
    requires java.desktop;

    exports com.oaktwister.app.utils;
    opens com.oaktwister.app.utils to javafx.graphics;

    exports com.oaktwister.app.core;
    opens com.oaktwister.app.core to javafx.graphics;

    exports com.oaktwister.app.views;
    opens com.oaktwister.app.views to javafx.fxml;

    exports com.oaktwister.app.views.login;
    opens com.oaktwister.app.views.login to javafx.fxml;

    exports com.oaktwister.app.views.widgets;
    opens com.oaktwister.app.views.widgets to javafx.fxml;

    exports com.oaktwister.app.views.main;
    opens com.oaktwister.app.views.main to javafx.fxml;

    exports com.oaktwister.app.views.platforms;
    opens com.oaktwister.app.views.platforms to javafx.fxml;

    exports com.oaktwister.app.views.accounts;
    opens com.oaktwister.app.views.accounts to javafx.fxml;

    exports com.oaktwister.app.views.identities;
    opens com.oaktwister.app.views.identities to javafx.fxml;

    exports com.oaktwister.app.utils.tables;
    opens com.oaktwister.app.utils.tables to javafx.fxml;

    exports com.oaktwister.app.events;
    opens com.oaktwister.app.events to javafx.fxml;

    exports com.oaktwister.app.views.widgets.crud;
    opens com.oaktwister.app.views.widgets.crud to javafx.fxml;

    exports com.oaktwister.app.views.widgets.dialogs;
    opens com.oaktwister.app.views.widgets.dialogs to javafx.fxml;
    exports com.oaktwister.app.views.claims;
    opens com.oaktwister.app.views.claims to javafx.fxml;

}
