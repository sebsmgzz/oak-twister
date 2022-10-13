module oaktwister.main {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.jetbrains.annotations;
    requires org.json;

    opens com.oaktwister.core to javafx.graphics;
    exports com.oaktwister.core;

    opens com.oaktwister.views.landing to javafx.fxml;
    exports com.oaktwister.views.landing;

    opens com.oaktwister.views.main to javafx.fxml;
    exports com.oaktwister.views.main;

}
