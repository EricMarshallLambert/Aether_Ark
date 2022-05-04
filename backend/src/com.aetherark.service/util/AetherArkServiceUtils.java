package com.aetherark.service.util;


import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

public class AetherArkServiceUtils {
    private static final Pattern INVALID_CHARACTER_PATTERN = Pattern.compile("[\"\'\\\\]");

    static final int ENTITY_ID_LENGTH = 5;


    private AetherArkServiceUtils() {

    }

    public static boolean isValidString(final String stringToValidate) {
        if(StringUtils.isBlank(stringToValidate)) {
            return false;
        }

        return !INVALID_CHARACTER_PATTERN.matcher(stringToValidate).find();
    }

    public static String generateId() {
        return RandomStringUtils.randomAlphabetic(ENTITY_ID_LENGTH);

    }


}
