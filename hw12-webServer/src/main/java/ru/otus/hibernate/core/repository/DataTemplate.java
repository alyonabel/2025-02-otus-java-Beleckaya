package ru.otus.hibernate.core.repository;

import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

public interface DataTemplate<T> {
    Optional<T> findById(Session session, long id);

    List<T> findAll(Session session);

    List<T> findAllByParameters(Session session, String... parameters);

    void insert(Session session, T object);

    void update(Session session, T object);
}
