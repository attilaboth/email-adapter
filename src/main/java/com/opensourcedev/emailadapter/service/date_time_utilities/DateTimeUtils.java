package com.opensourcedev.emailadapter.service.date_time_utilities;

import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Service
public class DateTimeUtils implements DateTimeConverter {

    private final String patternWithSeconds = "yyyy-MM-dd HH:mm:ss";
    private final String patternWithoutSeconds = "yyyy-MM-dd HH:mm";
    private final String patternWithOnlyDate = "yyyy-MM-dd";
    private LocalDateTime dt;
    private LocalDate localDate;
    private long epochTime = 0;

    // Predefined date and time converters

    @Override
    public LocalDateTime convertDateTime(String dateTime) {
        dt = LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern(patternWithoutSeconds, Locale.ENGLISH));
        System.out.println("[+] convertDateTime() value is: " + dt.toString());
        return dt;
    }

    @Override
    public LocalDateTime convertDateTimeWithSeconds(String dateTime) {
        dt = LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern(patternWithSeconds, Locale.ENGLISH));
        System.out.println("[+] convertDateTimeWithSeconds() value is: " + dt.toString());
        return dt;
    }

    @Override
    public LocalDate convertDate(String date) {
        localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern(patternWithOnlyDate, Locale.ENGLISH));
        System.out.println("[+] convertDate() value is: " + localDate.toString());
        return localDate;
    }


    // Custom date and time converters where user specifies the string date and string pattern

    @Override
    public LocalDateTime customDateTimeConverter(String dateTime, String pattern) {
        dt = LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern(pattern, Locale.ENGLISH));

        System.out.println("[+] customDateTimeConverter() value is: " + dt.toString());
        return dt;    }

    @Override
    public LocalDate customDateConverter(String date, String pattern) {
        localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern(pattern, Locale.ENGLISH));

        System.out.println("[+] customDateConverter() value is: " + localDate.toString());
        return localDate;
    }


    // Methods to convert object of LocalDateTime and LocalDate to epoch time in seconds

    @Override
    public long dateTimeToEpochTime(LocalDateTime dateTime) {
        Instant dateConverted = dateTime.atZone(ZoneId.of("Europe/Bratislava")).toInstant();
        epochTime = dateConverted.toEpochMilli() / 1000L;

        System.out.println("[+] Passed original dateTime value: " + dateTime.toString());
        System.out.println("[+] Value of converted dateTime to epoch time seconds is: " + epochTime);
        return epochTime;
    }

    @Override
    public long dateToEpochTime(LocalDate date) {
        Instant instant = date.atStartOfDay(ZoneId.of("Europe/Bratislava")).toInstant();
        epochTime = instant.toEpochMilli() / 1000L;

        System.out.println("[+] Passed original date value: " + date.toString());
        System.out.println("[+] Value of converted date to epoch time seconds is: " + epochTime);
        return epochTime;    }
}
