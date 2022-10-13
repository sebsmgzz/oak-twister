package com.oaktwister.models.claims;

public interface Claim<T> {

    String getName();

    T getValue();

}
