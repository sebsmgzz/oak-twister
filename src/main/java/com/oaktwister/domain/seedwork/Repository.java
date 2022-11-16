package com.oaktwister.domain.seedwork;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public interface Repository<T extends Entity> {

    Optional<T> findById(UUID id);

    Collection<T> findAll();

    void add(T model);

    void remove(T model);

    boolean save();

}
