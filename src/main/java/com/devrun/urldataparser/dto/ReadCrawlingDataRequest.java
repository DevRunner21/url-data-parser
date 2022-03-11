package com.devrun.urldataparser.dto;

import com.devrun.urldataparser.OutputType;
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

    public final String REGEX_URL_PATTERN = "[-a-zA-Z0-9@:%_\\+.~#?&//=]{2,256}\\.[a-z]{2,4}\\b(\\/[-a-zA-Z0-9@:%_\\+.~#?&//=]*)?";

    @Pattern(
        regexp = REGEX_URL_PATTERN,
        message = "url must be url pattern"
    )
    private String url;

    @NotBlank(message = "outputType must be not blank")
    private String outputType;

    @Min(value = 1, message = "outputUnit must be bigger than 1")
    private int outputUnit;

    @AssertTrue(message = "invalid outputType")
    public boolean isOutputTypeValid() {
        return OutputType.isExist(outputType);
    }

}
