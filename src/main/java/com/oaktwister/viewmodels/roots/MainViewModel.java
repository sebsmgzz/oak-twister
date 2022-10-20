package com.oaktwister.viewmodels.roots;

import com.oaktwister.services.logging.Logger;
import com.oaktwister.views.util.Section;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class MainViewModel {

    private final ObjectProperty<Section> section;

    private final Logger logger;

    public MainViewModel(Logger logger) {
        section = new SimpleObjectProperty<Section>(Section.ACCOUNTS);
        this.logger = logger;
    }

    public ObjectProperty<Section> sectionProperty() {
        return section;
    }

    public Section getSection() {
        return sectionProperty().get();
    }

    public void setSection(Section value) {
        sectionProperty().set(value);
    }

}
