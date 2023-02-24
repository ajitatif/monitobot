package com.monitobot.common;

import java.util.Objects;
import java.util.Optional;

/**
 * @author Gökalp Gürbüzer (gokalp.gurbuzer@gmail.com)
 */
public final class StringHelper {
    private StringHelper() { }

    public static boolean isEmptyString(String s) {
        return Objects.isNull(s) || s.isBlank();
    }

    public static Optional<String> getTrimmedString(String s) {
        if (!isEmptyString(s)) {
            String trimmed = s.trim();
            return Optional.ofNullable(isEmptyString(trimmed) ? null : trimmed);
        }
        return Optional.empty();
    }
}
