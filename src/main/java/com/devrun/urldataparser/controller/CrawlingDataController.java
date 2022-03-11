package com.devrun.urldataparser.controller;

import com.devrun.urldataparser.OutputType;
import com.devrun.urldataparser.common.dto.ApiResponse;
import com.devrun.urldataparser.dto.ReadCrawlingDataRequest;
import com.devrun.urldataparser.dto.ReadCrawlingDataResponse;
import com.devrun.urldataparser.service.CrawlingDataService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
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

    @ApiOperation(
        value = "URL 별 가공된 데이터 조회",
        notes = "주어진 URL의 페이지를 크롤링 한 후 가공한 데이터를 조회합니다.",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ApiImplicitParams({
        @ApiImplicitParam(name = "url", value = "URL", required = true, dataType = "string", paramType = "query"),
        @ApiImplicitParam(name = "outputType", value = "출력유형", required = true, dataType = "string", paramType = "query"),
        @ApiImplicitParam(name = "outputUnit", value = "출력단위", required = true, dataType = "int", paramType = "query")
    })
    @ApiResponses({
        @io.swagger.annotations.ApiResponse(code = 200, response = ReadCrawlingDataResponse.class, message = "성공"),
        @io.swagger.annotations.ApiResponse(code = 400, response = ReadCrawlingDataResponse.class,
            message = "실패 \n\t 1. [INVALID_URL] URL에 해당하는 데이터를 가져 올 수 없습니다.\n\t 2. [UNIT_OVERFLOW] 출력단위가 너무 큽니다.")
    })
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
