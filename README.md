# url-data-parser

```
    특정 URL의 데이터를 크롤링하고 가공하여 반환하는 간단한 어플리케이션을 만들어본다.
```

## 프로젝트 실행 방법

---

```
    1. 프로젝트를 pull 받은 후 별다른 설정 없이 그대로 실행
    2. "http://localhost:8080/swagger-ui/index.html"에서 API 명세 확인
    3. Swagger의 API 테스트 기능을 통해 API 테스트를 진행 할 수 있습니다.
```

## 협업을 위한 방법

---

1. 구현사항을 Git Issue로 등록하여 관리함
2. Git Convention을 지키면서 커밋을 진행함

   - feat : 새로운 기능 추가
   - fix : 버그 수정
   - docs : 문서 관련
   - style : 스타일 변경 (포매팅 수정, 들여쓰기 추가, …)
   - refactor : 코드 리팩토링
   - test : 테스트 관련 코드
   - build : 빌드 관련 파일 수정
   - chore : 그 외 자잘한 수정

3. 커밋 메시지 컨벤션
   ```
       [Feat] [이슈번호] 내용
   ```

## 구현방법

---

1. URL을 통해서 html 데이터를 읽어오기 위해 별도의 크롤러 라이브러리를 사용함

   - Jsoup vs Seleninum -> Jsoup 선택

2. 별도의 출력유형 요구사항 추가에 대응하기 위해 "상태패턴"을 사용하여 출력유형별 필터링 기능을 구현함.

   ```java
       public enum OutputType {
           ALL {
               @Override
               public String getFilteredCrawlingData(String source) {
                   return source;
               }
           }, EXCEPT_TAG {
               @Override
               public String getFilteredCrawlingData(String source) {
                   return source.replaceAll(REGEX_HTML_TAG, "");
               }
           };

           ...

           abstract public String getFilteredCrawlingData(String source);
       }
   ```

3. 필요한 문자열만 필터링 하기 위해 정규식을 적극 활용함
