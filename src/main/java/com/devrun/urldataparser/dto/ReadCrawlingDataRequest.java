package com.devrun.urldataparser.dto;

import com.devrun.urldataparser.OutputType;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReadCrawlingDataRequest {

    @ApiParam(hidden = true)
    public final String REGEX_URL_PATTERN = "[-a-zA-Z0-9@:%_\\+.~#?&//=]{2,256}\\.[a-z]{2,4}\\b(\\/[-a-zA-Z0-9@:%_\\+.~#?&//=]*)?";

    @ApiModelProperty(required = true, value = "URL", dataType = "string", example = "https://www.naver.com/")
    @Pattern(
        regexp = REGEX_URL_PATTERN,
        message = "url must be url pattern"
    )
    private String url;

    @ApiModelProperty(required = true, value = "출력유형", dataType = "string", example = "ALL", allowableValues = "ALL, EXCEPT_TAG")
    @NotBlank(message = "outputType must be not blank")
    private String outputType;

    @ApiModelProperty(required = true, value = "출력단위", dataType = "int", example = "1")
    @Min(value = 1, message = "outputUnit must be bigger than 1")
    private int outputUnit;

    @ApiParam(hidden = true)
    @AssertTrue(message = "invalid outputType")
    public boolean isOutputTypeValid() {
        return OutputType.isExist(outputType);
    }

}
