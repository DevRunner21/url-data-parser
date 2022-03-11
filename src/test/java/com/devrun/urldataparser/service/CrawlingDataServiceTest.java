package com.devrun.urldataparser.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;

import com.devrun.urldataparser.OutputType;
import com.devrun.urldataparser.common.crawler.Crawler;
import com.devrun.urldataparser.common.exception.BusinessException;
import com.devrun.urldataparser.dto.ReadCrawlingDataResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@DisplayName("CrawlingDataService 인수 테스트")
@ExtendWith(MockitoExtension.class)
class CrawlingDataServiceTest {

    private final String VALID_URL = "https://www.naver.com/";
    private final String TEST_CRAWLING_RESULT_DATA = "<div>abcdef</div><div>12345</div>";
    public final String EXPECTED_QUOTIENT_TEXT = "a1b2c3d4d5dddefiiiivv";
    public final String EXPECTED_REMINDER_TEXT = "vv";

    public final int OVERFLOW_OUTPUT_UNIT = TEST_CRAWLING_RESULT_DATA.length() + 10;

    private CrawlingDataService dataService;

    @Mock
    private Crawler crawler;

    @BeforeEach
    void setUp() {
        given(crawler.getCrawlingDataByUrl(VALID_URL)).willReturn(TEST_CRAWLING_RESULT_DATA);
        dataService = new CrawlingDataService(crawler);
    }

    @Test
    @DisplayName("주어진 URL의 페이지 데이터를 크롤링 해서 OutputType에 맞게 가공된 데이터를 반환합니다.")
    void test_getQuotientAndReminderAfterCrawling_success() {
        ReadCrawlingDataResponse response =
            dataService.getQuotientAndReminderAfterCrawling(VALID_URL, OutputType.ALL, 3);

        assertThat(response.getQuotientText()).isEqualTo(EXPECTED_QUOTIENT_TEXT);
        assertThat(response.getReminderText()).isEqualTo(EXPECTED_REMINDER_TEXT);
        assertThat(response).isInstanceOf(ReadCrawlingDataResponse.class);
    }

    @Test
    @DisplayName("문자열의 길이보다 큰 outputUnit을 입력한 경우 Exception을 던집니다.")
    void test_getQuotientAndReminderAfterCrawling_unit_overflow_exception() {
        assertThatThrownBy(() -> dataService.getQuotientAndReminderAfterCrawling(VALID_URL, OutputType.ALL, OVERFLOW_OUTPUT_UNIT))
            .isInstanceOf(BusinessException.class);
    }

}
