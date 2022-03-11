package com.devrun.urldataparser.common.crawler;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import com.devrun.urldataparser.common.exception.BusinessException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("BasicCrawler 단위테스트")
class BasicCrawlerTest {

    private final String VALID_URL = "https://www.naver.com/";
    private final String INVALID_URL = "sfdfsdsdf";

    private Crawler crawler;

    @BeforeEach
    void setUp() {
        crawler = new BasicCrawler();
    }

    @Test
    @DisplayName("잘못된 URL을 입력할 경우 Exception을 던집니다.")
    void test_getCrawlingDataByUrl_invalid_url_exception() {
        assertThatThrownBy(() -> crawler.getCrawlingDataByUrl(INVALID_URL))
            .isInstanceOf(BusinessException.class);
    }

    @Test
    @DisplayName("올바른 URL을 입력할 경우 크롤링한 HTML 데이터를 반환합니다.")
    void test_getCrawlingDataByUrl_success() {
        String crawlingDataByUrl = crawler.getCrawlingDataByUrl("https://www.naver.com/");
        assertThat(crawlingDataByUrl).isNotNull();
    }

}
