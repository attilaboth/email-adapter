package com.opensourcedev.emailadapter.service.date_time_utilities;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface DateTimeConverter {

    LocalDateTime convertDateTime(String dateTime);
    LocalDateTime convertDateTimeWithSeconds(String dateTime);
    LocalDate convertDate(String date);

    LocalDateTime customDateTimeConverter(String dateTime, String pattern);
    LocalDate customDateConverter(String date, String pattern);

    long dateTimeToEpochTime(LocalDateTime dateTime);
    long dateToEpochTime(LocalDate date);
}
