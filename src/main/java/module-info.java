module com.oaktwister.oaktwister {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.jetbrains.annotations;

    exports com.oaktwister;
    opens com.oaktwister to javafx.fxml;

    exports com.oaktwister.core;
    opens com.oaktwister.core to javafx.fxml;

    exports com.oaktwister.views.landing;
    opens com.oaktwister.views.landing to javafx.fxml;

    exports com.oaktwister.views.main;
    opens com.oaktwister.views.main to javafx.fxml;

    exports com.oaktwister.viewmodels.landing;
    opens com.oaktwister.viewmodels.landing to javafx.fxml;

    exports com.oaktwister.viewmodels.main;
    opens com.oaktwister.viewmodels.main to javafx.fxml;

}