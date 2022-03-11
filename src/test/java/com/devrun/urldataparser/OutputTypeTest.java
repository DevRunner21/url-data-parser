package com.devrun.urldataparser;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("OutputType 단위테스트")
class OutputTypeTest {

    private final String TEST_CRAWLING_RESULT_DATA = "<div>abcdef</div><div>12345</div>";

    @Test
    @DisplayName("ALL은 모든 데이터를 반환합니다.")
    void getFilteredCrawlingData_ALL() {
        String expected = TEST_CRAWLING_RESULT_DATA;

        String result = OutputType.ALL.getFilteredCrawlingData(TEST_CRAWLING_RESULT_DATA);
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("EXCEPT_TAG는 HTML 태그를 제외한 나머지 데이터를 반환합니다.")
    void getFilteredCrawlingData_EXCEPT_TAG() {
        String expected = "abcdef12345";

        String result = OutputType.EXCEPT_TAG.getFilteredCrawlingData(TEST_CRAWLING_RESULT_DATA);
        assertThat(result).isEqualTo(expected);
    }

}
