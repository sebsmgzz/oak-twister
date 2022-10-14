package com.oaktwister.viewmodels.main;

import com.oaktwister.views.util.Section;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class MainViewModel {

    private final ObjectProperty<Section> section;

    public MainViewModel() {
        section = new SimpleObjectProperty<Section>(Section.IDENTITIES);
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
