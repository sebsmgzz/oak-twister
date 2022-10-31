module oaktwister.main {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.jetbrains.annotations;
    requires org.json;
    requires java.desktop;

    opens com.oaktwister.core to javafx.graphics;
    exports com.oaktwister.core;

    exports com.oaktwister.controllers.dialogs;
    opens com.oaktwister.controllers.dialogs to javafx.fxml;

    exports com.oaktwister.controllers.layouts;
    opens com.oaktwister.controllers.layouts to javafx.fxml;

    exports com.oaktwister.controllers.controls;
    opens com.oaktwister.controllers.controls to javafx.fxml;

    exports com.oaktwister.views.layouts;
    opens com.oaktwister.views.layouts to javafx.fxml;

    exports com.oaktwister.views.controls;
    opens com.oaktwister.views.controls to javafx.fxml;

    exports com.oaktwister.views.dialogs;
    opens com.oaktwister.views.dialogs to javafx.fxml;

}
