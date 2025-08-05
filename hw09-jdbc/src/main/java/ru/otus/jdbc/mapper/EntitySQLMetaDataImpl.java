package ru.otus.jdbc.mapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.Collectors;

public class EntitySQLMetaDataImpl implements EntitySQLMetaData {

    private final EntityClassMetaData entityClassMetaData;

    public EntitySQLMetaDataImpl(EntityClassMetaData entityClassMetaData) {
        this.entityClassMetaData = entityClassMetaData;
    }

    @Override
    public String getSelectAllSql() {
        return String.format("SELECT * FROM %s", entityClassMetaData.getName().toLowerCase());
    }

    @Override
    public String getSelectByIdSql() {
        var selectedFields = entityClassMetaData.getAllFields().stream()
                .map(it -> it.getName().toLowerCase()).collect(Collectors.joining(","));
        return String.format("SELECT %s FROM %s WHERE %s = ?", selectedFields,
                entityClassMetaData.getName().toLowerCase(),
                entityClassMetaData.getIdField().getName().toLowerCase());
    }

    @Override
    public String getInsertSql() {
        var withoutIdFields = entityClassMetaData.getFieldsWithoutId();
        var insertFields = withoutIdFields.stream()
                .map(it -> it.getName().toLowerCase()).collect(Collectors.joining(","));

        if(insertFields.isEmpty()){
            throw new RuntimeException("The class must contain at least one field");
        }


        return String.format("INSERT INTO %s (%s) VALUES (%s)",
                entityClassMetaData.getName().toLowerCase(),
                insertFields
               );
    }

    @Override
    public String getUpdateSql() {
        var updateFields = entityClassMetaData.getFieldsWithoutId().stream()
                .map(it -> it.getName().toLowerCase() + " = ?")
                .collect(Collectors.joining(", "));
        return String.format("UPDATE %s SET %s WHERE %s = ?", entityClassMetaData.getName().toLowerCase(),
                updateFields,entityClassMetaData.getIdField().getName().toLowerCase());
    }
}
