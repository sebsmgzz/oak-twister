package com.oaktwister.app.views.widgets.dialogs;

import java.util.Objects;

public final class DialogResult {

    public final static DialogResult CANCELED = new DialogResult("Cancel");
    public final static DialogResult OKAY = new DialogResult("Okay");
    public final static DialogResult SAVED = new DialogResult("Save");
    public final static DialogResult YES = new DialogResult("Yes");
    public final static DialogResult NO = new DialogResult("No");

    private final String action;

    private DialogResult(String action) {
        this.action = action;
    }

    public String getAction() {
        return action;
    }

    @Override
    public boolean equals(Object object) {
        if(object instanceof DialogResult other) {
            return Objects.equals(action, other.getAction());
        }
        return false;
    }

}
