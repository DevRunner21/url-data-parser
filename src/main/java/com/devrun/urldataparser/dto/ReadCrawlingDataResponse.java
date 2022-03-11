package com.devrun.urldataparser.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ReadCrawlingDataResponse {

    private final String quotientText;

    private final String reminderText;

    @Builder
    public ReadCrawlingDataResponse(String quotientText, String reminderText) {
        this.quotientText = quotientText;
        this.reminderText = reminderText;
    }

}
