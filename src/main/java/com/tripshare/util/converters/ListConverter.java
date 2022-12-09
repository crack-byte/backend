package com.tripshare.util.converters;

import org.springframework.lang.Nullable;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Converter(
    autoApply = true
)
public class ListConverter implements AttributeConverter<List<String>, String> {

    public ListConverter() {
    }

    @Nullable
    public String convertToDatabaseColumn(List<String> list) {
        return list == null || list.isEmpty() ? null : String.join(",", list);
    }

    @Nullable
    public List<String> convertToEntityAttribute(String data) {
        return data == null ? new ArrayList<>(0) : Arrays.asList(data.split(","));
    }

}