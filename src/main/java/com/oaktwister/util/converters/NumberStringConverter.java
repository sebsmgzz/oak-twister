package com.oaktwister.util.converters;

import javafx.util.StringConverter;

public class NumberStringConverter extends StringConverter<Number> {

    private final Class<?> numberType;

    public NumberStringConverter(Class<? extends Number> numberType) {
        this.numberType = numberType;
    }

    @Override
    public String toString(Number number) {
        return number.toString();
    }

    @Override
    public Number fromString(String string) {
        if(numberType.equals(Integer.class)) {
            return Integer.parseInt(string);
        } else if (numberType.equals(Double.class)) {
            return Double.parseDouble(string);
        } else if (numberType.equals(Long.class)) {
            return Long.parseLong(string);
        } else {
            throw new UnsupportedOperationException(String.format(
                    "Class %s is not yet supported.", numberType.getName()));
        }
    }

}
