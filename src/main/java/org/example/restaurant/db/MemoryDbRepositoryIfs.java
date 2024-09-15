package org.example.restaurant.db;

import java.util.List;
import java.util.Optional;

public interface MemoryDbRepositoryIfs<T> {

    Optional<T> findById(int index);
    T save(T t);
    void deleteById(int index);
    List<T> findAll();


}
