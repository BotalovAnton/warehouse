package ru.mfua.botalov.warehouse.service.utils;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
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

    /**
     * Возвращает дату с добавлением указанного количества дней.
     *
     * <p>Метод корректирует месяцы и годы при необходимости.</p>
     *
     * @param date  Date дата
     * @param count количество дней. Отрицательные значения принимаются.
     * @return Date новый объект даты
     */
    public Date addDay(Date date, int count) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, count);
        return calendar.getTime();
    }

    public Date convertToDate(OffsetDateTime offsetDateTime) {
        if (offsetDateTime != null) {
            long date = offsetDateTime.toInstant().toEpochMilli();
            return new Date(date);
        } else {
            return null;
        }
    }

    public OffsetDateTime convertToOffsetDateTime(String date) {
        if (date != null) {
            return LocalDate.parse(date, DateTimeFormatter.ofPattern(DATE_PATTERN))
                    .atStartOfDay()
                    .atZone(ZoneId.systemDefault())
                    .toInstant()
                    .atOffset(OffsetDateTime.now().getOffset());
        } else {
            return null;
        }
    }

    public Date createDate(int year, int month, int day) {
        var calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DAY_OF_MONTH, day);

        return calendar.getTime();
    }
}
