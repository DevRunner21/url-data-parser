package com.devrun.urldataparser.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ReadCrawlingDataResponse {

    @ApiModelProperty(value = "몫", dataType = "string", example = "A0a1B2")
    private final String quotientText;

    @ApiModelProperty(value = "나머지", dataType = "string", example = "b3")
    private final String reminderText;

    @Builder
    public ReadCrawlingDataResponse(String quotientText, String reminderText) {
        this.quotientText = quotientText;
        this.reminderText = reminderText;
    }

}
