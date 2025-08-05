package ru.otus.jdbc.mapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class EntityClassMetaDataImpl implements EntityClassMetaData {
    private static final Logger log = LoggerFactory.getLogger(EntityClassMetaDataImpl.class);

    private final Class<?> clazz;
    private final Constructor<?> constructor;
    private final Field idField;
    private final List<Field> fieldList;
    private final List<Field> fieldListWithoutId;

    public EntityClassMetaDataImpl(Class<?> clazz) {
        this.clazz=clazz;
        try {
            this.constructor = clazz.getConstructor();
            this.idField = Arrays.stream(clazz.getDeclaredFields())
                    .filter(it -> it.isAnnotationPresent(Id.class))
                    .findFirst().orElseThrow(RuntimeException::new);
            this.fieldList = Arrays.stream(clazz.getDeclaredFields())
                    .sorted(Comparator.comparing(Field::getName))
                    .collect(Collectors.toList());
            this.fieldListWithoutId = Arrays.stream(clazz.getDeclaredFields())
                    .filter(it -> !it.isAnnotationPresent(Id.class))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getName() {
        return clazz.getSimpleName();
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> Constructor<T> getConstructor() {
        return (Constructor<T>) constructor;
    }

    @Override
    public Field getIdField() {
        return idField;
    }

    @Override
    public List<Field> getAllFields() {
        return fieldList;
    }

    @Override
    public List<Field> getFieldsWithoutId() {
        return fieldListWithoutId;
    }
}
