package com.richflow.api.converter;

import com.richflow.api.domain.enumType.AcMoneyType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class MoneyTypeConverter implements AttributeConverter<AcMoneyType, String> {

    @Override
    public String convertToDatabaseColumn(AcMoneyType attribute) {
        String dbValue = (attribute == null) ? null : attribute.name().toLowerCase();
        System.out.println("Converting to DB column: " + dbValue);
        return dbValue;
    }
    @Override
    public AcMoneyType convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        return AcMoneyType.valueOf(dbData.toUpperCase());
    }
}