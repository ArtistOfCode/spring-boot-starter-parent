package com.codeartist.component.core.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * 时间操作类
 *
 * @author 艾江南
 * @date 2020/10/9
 * @see LocalDateTime
 * @see LocalDate
 * @see LocalTime
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DateUtil {

    public static final String[] DAY_OF_WEEK = {"", "一", "二", "三", "四", "五", "六", "日"};

    public static final DateTimeFormatter LONG_DATETIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static final DateTimeFormatter LONG_DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static final DateTimeFormatter LONG_TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");
}
