module oaktwister.main {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.jetbrains.annotations;
    requires org.json;

    opens com.oaktwister.core to javafx.graphics;
    exports com.oaktwister.core;

    opens com.oaktwister.views.controllers to javafx.fxml;
    exports com.oaktwister.views.controllers;

    opens com.oaktwister.views.controls to javafx.fxml;
    exports com.oaktwister.views.controls;

}
