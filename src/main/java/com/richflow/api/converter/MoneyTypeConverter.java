package com.richflow.api.converter;

import com.richflow.api.domain.enumType.MoneyType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class MoneyTypeConverter implements AttributeConverter<MoneyType, String> {

    @Override
    public String convertToDatabaseColumn(MoneyType attribute) {
        String dbValue = (attribute == null) ? null : attribute.name().toLowerCase();
        System.out.println("Converting to DB column: " + dbValue);
        return dbValue;
    }
    @Override
    public MoneyType convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        return MoneyType.valueOf(dbData.toUpperCase());
    }
}