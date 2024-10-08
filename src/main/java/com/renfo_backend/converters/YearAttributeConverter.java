package com.renfo_backend.converters;

import java.time.Year;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class YearAttributeConverter
        implements AttributeConverter<Year, Short> {

    @Override
    public Short convertToDatabaseColumn(
            Year attribute) {
        if (attribute != null) {
            return (short) attribute.getValue();
        }
        return null;
    }

    @Override
    public Year convertToEntityAttribute(
            Short dbData) {
        if (dbData != null) {
            return Year.of(dbData);
        }
        return null;
    }
}