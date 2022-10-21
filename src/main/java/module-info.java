module oaktwister.main {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.jetbrains.annotations;
    requires org.json;

    opens com.oaktwister.core to javafx.graphics;
    exports com.oaktwister.core;

    opens com.oaktwister.views.landing to javafx.fxml;
    exports com.oaktwister.views.landing;

    opens com.oaktwister.views.laterals to javafx.fxml;
    exports com.oaktwister.views.laterals;
    exports com.oaktwister.views.main;
    opens com.oaktwister.views.main to javafx.fxml;
    exports com.oaktwister.views.accounts;
    opens com.oaktwister.views.accounts to javafx.fxml;
    exports com.oaktwister.views.identities;
    opens com.oaktwister.views.identities to javafx.fxml;
    exports com.oaktwister.views.platforms;
    opens com.oaktwister.views.platforms to javafx.fxml;

}
