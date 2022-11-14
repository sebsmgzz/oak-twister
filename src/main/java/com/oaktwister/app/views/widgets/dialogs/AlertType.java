package com.oaktwister.app.views.widgets.dialogs;

public final class AlertType {

    public static final AlertType CONFIRM = new AlertType("Confirm");
    public static final AlertType INFO = new AlertType("Information");
    public static final AlertType WARNING = new AlertType("Warning");
    public static final AlertType ERROR = new AlertType("Error");

    private final String name;

    private AlertType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
