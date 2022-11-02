module oaktwister.main {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.jetbrains.annotations;
    requires org.json;
    requires java.desktop;

    exports com.oaktwister.utils;
    opens com.oaktwister.utils to javafx.graphics;

    exports com.oaktwister.core;
    opens com.oaktwister.core to javafx.graphics;

    exports com.oaktwister.controllers.dialogs;
    opens com.oaktwister.controllers.dialogs to javafx.fxml;

    exports com.oaktwister.controllers.layouts;
    opens com.oaktwister.controllers.layouts to javafx.fxml;

    exports com.oaktwister.controllers.controls;
    opens com.oaktwister.controllers.controls to javafx.fxml;

    exports com.oaktwister.views;
    opens com.oaktwister.views to javafx.fxml;

    exports com.oaktwister.views.login;
    opens com.oaktwister.views.login to javafx.fxml;

    exports com.oaktwister.views.widgets;
    opens com.oaktwister.views.widgets to javafx.fxml;

    exports com.oaktwister.views.main;
    opens com.oaktwister.views.main to javafx.fxml;

    exports com.oaktwister.views.platforms;
    opens com.oaktwister.views.platforms to javafx.fxml;

    exports com.oaktwister.views.accounts;
    opens com.oaktwister.views.accounts to javafx.fxml;

    exports com.oaktwister.views.identities;
    opens com.oaktwister.views.identities to javafx.fxml;

}
