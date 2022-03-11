package com.devrun.urldataparser.common.crawler;

import com.devrun.urldataparser.common.exception.BusinessException;
import com.devrun.urldataparser.common.exception.ErrorInfo;
import java.io.IOException;
import org.jsoup.Jsoup;

public class BasicCrawler implements Crawler{

    @Override
    public String getCrawlingDataByUrl(String url) {
        String data = null;

        try {
            data = Jsoup.connect(url).get().toString();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            throw new BusinessException(ErrorInfo.INVALID_URL);
        }

        return data;
    }

}
