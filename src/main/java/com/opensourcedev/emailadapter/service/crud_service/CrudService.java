package com.opensourcedev.emailadapter.service.crud_service;



import java.util.Optional;
import java.util.Set;


public interface CrudService<T, ID>{

    Set<Optional<T>> findAll();

    Optional<T> findById(ID id);

    Optional<T> save(T object);

    void deleteById(ID id);

}
