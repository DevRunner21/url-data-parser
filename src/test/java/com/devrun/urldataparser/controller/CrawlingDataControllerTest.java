package com.devrun.urldataparser.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("CrawlingDataController 통합 테스트")
class CrawlingDataControllerTest {

    private final String VALID_URL = "https://www.naver.com/";
    public final String VALID_OUTPUT_TYPE = "ALL";
    public final String VALID_OUTPUT_UNIT = "3";
    public final String INVALID_URL = "sdfsdfsdfsd";
    public final String INVALID_OUTPUT_TYPE = "Invalid OutputType";
    public final String INVALID_OUTPUT_UNIT = "0";

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("URL 별 데이터 가공 API")
    void test_readCrawlingData_success() throws Exception {
        mockMvc.perform(get("/api/v1/crawling_data")
            .param("url", VALID_URL)
            .param("outputType", VALID_OUTPUT_TYPE)
            .param("outputUnit", VALID_OUTPUT_UNIT)
        ).andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("URL 별 데이터 가공 API는 잘못된 URL을 넘길경우 실패합니다.")
    void test_readCrawlingData_fail_invalid_url() throws Exception {
        mockMvc.perform(get("/api/v1/crawling_data")
            .param("url", INVALID_URL)
            .param("outputType", VALID_OUTPUT_TYPE)
            .param("outputUnit", VALID_OUTPUT_UNIT)
        ).andExpect(status().isBadRequest())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("URL 별 데이터 가공 API는 잘못된 OutputType을 넘길 경우 실패합니다.")
    void test_readCrawlingData_fail_invalid_output_type() throws Exception {
        mockMvc.perform(get("/api/v1/crawling_data")
            .param("url", INVALID_URL)
            .param("outputType", INVALID_OUTPUT_TYPE)
            .param("outputUnit", VALID_OUTPUT_UNIT)
        ).andExpect(status().isBadRequest())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("URL 별 데이터 가공 API는 1보다 작은 outputUnit을 넘길 경우 실패합니다.")
    void test_readCrawlingData_fail_invalid_output_unit() throws Exception {
        mockMvc.perform(get("/api/v1/crawling_data")
            .param("url", INVALID_URL)
            .param("outputType", VALID_OUTPUT_TYPE)
            .param("outputUnit", INVALID_OUTPUT_UNIT)
        ).andExpect(status().isBadRequest())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

}
