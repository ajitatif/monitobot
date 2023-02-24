package com.monitobot.common;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * @author Gökalp Gürbüzer (gokalp.gurbuzer@gmail.com)
 */
public final class DateHelper {

    private DateHelper() { }

    public static LocalDateTime currentLocalTimeInUtc() {
        return Instant.now().atZone(ZoneId.of("UTC")).toLocalDateTime();
    }
}
