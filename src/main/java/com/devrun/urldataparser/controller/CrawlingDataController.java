package com.devrun.urldataparser.controller;

import com.devrun.urldataparser.OutputType;
import com.devrun.urldataparser.common.dto.ApiResponse;
import com.devrun.urldataparser.dto.ReadCrawlingDataRequest;
import com.devrun.urldataparser.dto.ReadCrawlingDataResponse;
import com.devrun.urldataparser.service.CrawlingDataService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/crawling_data")
@RequiredArgsConstructor
public class CrawlingDataController {

    private final CrawlingDataService crawlingDataService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<ReadCrawlingDataResponse> ReadCrawlingData(@Valid ReadCrawlingDataRequest request) {
        ReadCrawlingDataResponse response = crawlingDataService.getQuotientAndReminderAfterCrawling(
            request.getUrl(),
            OutputType.valueOf(request.getOutputType()),
            request.getOutputUnit()
        );

        return ApiResponse.ok(response);
    }

}
