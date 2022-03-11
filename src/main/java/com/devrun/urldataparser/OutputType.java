package com.devrun.urldataparser;

import java.util.Arrays;

public enum OutputType {
    ALL {
        @Override
        public String getFilteredCrawlingData(String source) {
            return source;
        }
    }, EXCEPT_TAG {
        @Override
        public String getFilteredCrawlingData(String source) {
            return source.replaceAll(REGEX_HTML_TAG, "");
        }
    };

    public final String REGEX_HTML_TAG = "<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>";

    public static boolean isExist(String outputType) {
        return Arrays.stream(values())
            .anyMatch(type -> type.name().equals(outputType));
    }

    abstract public String getFilteredCrawlingData(String source);
}
