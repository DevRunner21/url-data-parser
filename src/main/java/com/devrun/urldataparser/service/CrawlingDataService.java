package com.devrun.urldataparser.service;

import com.devrun.urldataparser.OutputType;
import com.devrun.urldataparser.common.crawler.Crawler;
import com.devrun.urldataparser.common.exception.BusinessException;
import com.devrun.urldataparser.common.exception.ErrorInfo;
import com.devrun.urldataparser.dto.ReadCrawlingDataResponse;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CrawlingDataService {

    public final String REGEX_NOT_NUMBER = "[\\D]";

    public final String REGEX_NOT_ALPHABET = "[^a-zA-Z]";

    private final Crawler crawler;

    public ReadCrawlingDataResponse getQuotientAndReminderAfterCrawling(String url, OutputType type, int outputUnit) {
        String crawlingResult = type.getFilteredCrawlingData(
            crawler.getCrawlingDataByUrl(url)
        );

        // 영어/숫자만 출력
        String onlyNumberStr = crawlingResult.replaceAll(REGEX_NOT_NUMBER, "");
        String onlyAlphabetStr = crawlingResult.replaceAll(REGEX_NOT_ALPHABET, "");

        // 영숫영숫 교차출력
        String crossUnionStr = makeCrossUnionString(onlyNumberStr, onlyAlphabetStr);

        // 출력 묶음단위로 결과 값 나누기
        int crossUnionStrLength = crossUnionStr.length();

        // 단위가 더크다면 에러 발생
        if (outputUnit > crossUnionStrLength) {
            throw new BusinessException(ErrorInfo.UNIT_OVERFLOW);
        }

        // 몫과 나머지로 파싱
        int quotient = crossUnionStrLength - (crossUnionStrLength % outputUnit);
        String quotientText = crossUnionStr.substring(0, quotient);
        String reminderText = crossUnionStr.substring(quotient);

        return ReadCrawlingDataResponse.builder()
            .quotientText(quotientText)
            .reminderText(reminderText)
            .build();
    }

    private String makeCrossUnionString(String onlyNumberStr, String onlyAlphabetStr) {
        Queue<String> numberQueue = convertStringToQueueAndSorting(onlyNumberStr);
        Queue<String> stringQueue = convertStringToQueueAndSorting(onlyAlphabetStr);

        int biggerLength = Math.max(numberQueue.size(), stringQueue.size());

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < biggerLength; i++) {
            if (stringQueue.peek() != null) {
                stringBuilder.append(stringQueue.poll());
            }
            if (numberQueue.peek() != null) {
                stringBuilder.append(numberQueue.poll());
            }
        }

        return stringBuilder.toString();
    }

    private LinkedList<String> convertStringToQueueAndSorting(String str) {
        return Arrays.stream(str.split(""))
            .sorted()
            .collect(Collectors.toCollection(LinkedList::new));
    }

}
