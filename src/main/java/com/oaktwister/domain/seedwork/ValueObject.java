package com.oaktwister.domain.seedwork;

import java.util.List;
import java.util.Objects;

public abstract class ValueObject {

    protected abstract List<Object> getComponents();

    public boolean equals(ValueObject valueObject) {
        List<Object> thisComponents = getComponents();
        List<Object> otherComponents = valueObject.getComponents();
        if(otherComponents.size() != thisComponents.size()) {
            return false;
        }
        for (int i = 0; i < thisComponents.size(); i++) {
            Object thisComponent = thisComponents.get(i);
            Object otherComponent = otherComponents.get(i);
            if(thisComponent != otherComponent) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean equals(Object object) {
        if(object instanceof ValueObject valueObject) {
            return equals(valueObject);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getComponents());
    }

    @Override
    public String toString() {
        Iterable<String> componentsStrings = getComponents().stream().map(Object::toString).toList();
        String componentsString = String.join(",", componentsStrings);
        String className = getClass().getSimpleName();
        return String.format("{%s: %s}", className, componentsString);
    }
    
}
