package ru.mfua.botalov.warehouse.service.utils;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.springframework.stereotype.Component;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class Utils {

    public static final String DATE_TIME_MS_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSS";
    public static final String DATE_PATTERN = "MM/dd/yyyy";

    public void merge(Object destination, Object source) {
        List<Field> fields = Arrays.stream(FieldUtils.getAllFields(source.getClass()))
                .filter(f -> !"serialVersionUID".equals(f.getName()))
                .collect(Collectors.toList());
        try {
            for (Field field : fields) {
                field.setAccessible(true);
                Object destValue = field.get(destination);
                Object sourceValue = field.get(source);
                if (sourceValue != null) {
                    if (destValue == null) {
                        field.set(destination, sourceValue);
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
