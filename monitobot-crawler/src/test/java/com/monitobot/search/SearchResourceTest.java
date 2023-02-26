package com.monitobot.search;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.monitobot.http.HttpRequestHandler;
import com.monitobot.search.google.GoogleSearchResult;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import javax.inject.Inject;
import java.time.LocalDateTime;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;

/**
 * @author Gökalp Gürbüzer (gokalp.gurbuzer@gmail.com)
 */
@QuarkusTest
class SearchResourceTest {

    @InjectMock
    HttpRequestHandler httpRequestHandler;

    @Inject
    ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        Mockito.when(httpRequestHandler.handleSearchRequest(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any()))
                .then(invocation -> new InternetSearchResult(
                        invocation.getArgument(1),
                        LocalDateTime.now(),
                        200,
                        googleSearchResult,
                        objectMapper.readValue(googleSearchResult, GoogleSearchResult.class)
                ));
    }

    @Test
    void shouldReturnAllResultsWhenNewTrack() {

        given()
                .when().get("/v1/search/tracks/new-track/new-results")
                .then()
                .statusCode(200)
                .body(containsString("items"));
    }

    @Test
    void shouldReturnNewResultsWhenExistingTrack() {

        given()
                .when().get("v1/search/tracks/existing-track/new-results")
                .then()
                .statusCode(200)
                .extract().response().peek().then()
                .body("results.google.result.items[0].title", equalTo("M7.8 and M7.5 Kahramanmaraş Earthquake Sequence near ..."));
    }

    private static final String googleSearchResult = """
                                {
                                  "kind": "customsearch#search",
                                  "url": {
                                    "type": "application/json",
                                    "template": "https://www.googleapis.com/customsearch/v1?q={searchTerms}&num={count?}&start={startIndex?}&lr={language?}&safe={safe?}&cx={cx?}&sort={sort?}&filter={filter?}&gl={gl?}&cr={cr?}&googlehost={googleHost?}&c2coff={disableCnTwTranslation?}&hq={hq?}&hl={hl?}&siteSearch={siteSearch?}&siteSearchFilter={siteSearchFilter?}&exactTerms={exactTerms?}&excludeTerms={excludeTerms?}&linkSite={linkSite?}&orTerms={orTerms?}&relatedSite={relatedSite?}&dateRestrict={dateRestrict?}&lowRange={lowRange?}&highRange={highRange?}&searchType={searchType}&fileType={fileType?}&rights={rights?}&imgSize={imgSize?}&imgType={imgType?}&imgColorType={imgColorType?}&imgDominantColor={imgDominantColor?}&alt=json"
                                  },
                                  "queries": {
                                    "request": [
                                      {
                                        "title": "Google Custom Search - kahramanmaras",
                                        "totalResults": "141000000",
                                        "searchTerms": "kahramanmaras",
                                        "count": 10,
                                        "startIndex": 1,
                                        "inputEncoding": "utf8",
                                        "outputEncoding": "utf8",
                                        "safe": "off",
                                        "cx": "c1bc1d7ac0422409b"
                                      }
                                    ],
                                    "nextPage": [
                                      {
                                        "title": "Google Custom Search - kahramanmaras",
                                        "totalResults": "141000000",
                                        "searchTerms": "kahramanmaras",
                                        "count": 10,
                                        "startIndex": 11,
                                        "inputEncoding": "utf8",
                                        "outputEncoding": "utf8",
                                        "safe": "off",
                                        "cx": "c1bc1d7ac0422409b"
                                      }
                                    ]
                                  },
                                  "context": {
                                    "title": "Monitobot Search Engine"
                                  },
                                  "searchInformation": {
                                    "searchTime": 0.458934,
                                    "formattedSearchTime": "0.46",
                                    "totalResults": "141000000",
                                    "formattedTotalResults": "141,000,000"
                                  },
                                  "items": [
                                    {
                                      "kind": "customsearch#result",
                                      "title": "Kahramanmaraş - Wikipedia",
                                      "htmlTitle": "\\u003cb\\u003eKahramanmaraş\\u003c/b\\u003e - Wikipedia",
                                      "link": "https://en.wikipedia.org/wiki/Kahramanmara%C5%9F",
                                      "displayLink": "en.wikipedia.org",
                                      "snippet": "Maraş, officially Kahramanmaraş and historically Germanicea (Greek: Γερμανίκεια), is a city in the Mediterranean region of Turkey and the administrative ...",
                                      "htmlSnippet": "Maraş, officially \\u003cb\\u003eKahramanmaraş\\u003c/b\\u003e and historically Germanicea (Greek: Γερμανίκεια), is a city in the Mediterranean region of Turkey and the administrative&nbsp;...",
                                      "cacheId": "IIj8IYqUTj8J",
                                      "formattedUrl": "https://en.wikipedia.org/wiki/Kahramanmaraş",
                                      "htmlFormattedUrl": "https://en.wikipedia.org/wiki/\\u003cb\\u003eKahramanmaraş\\u003c/b\\u003e",
                                      "pagemap": {
                                        "hcard": [
                                          {
                                            "fn": "Kahramanmaraş",
                                            "category": "Metropolitan municipality"
                                          }
                                        ],
                                        "metatags": [
                                          {
                                            "referrer": "origin",
                                            "og:image": "https://upload.wikimedia.org/wikipedia/commons/thumb/1/1d/Kahramanmara%C5%9F_City.JPG/1200px-Kahramanmara%C5%9F_City.JPG",
                                            "theme-color": "#eaecf0",
                                            "og:image:width": "1200",
                                            "og:type": "website",
                                            "viewport": "width=device-width, initial-scale=1.0, user-scalable=yes, minimum-scale=0.25, maximum-scale=5.0",
                                            "og:title": "Kahramanmaraş - Wikipedia",
                                            "og:image:height": "797",
                                            "format-detection": "telephone=no"
                                          }
                                        ]
                                      }
                                    },
                                    {
                                      "kind": "customsearch#result",
                                      "title": "M7.8 and M7.5 Kahramanmaraş Earthquake Sequence near ...",
                                      "htmlTitle": "M7.8 and M7.5 \\u003cb\\u003eKahramanmaraş\\u003c/b\\u003e Earthquake Sequence near ...",
                                      "link": "https://www.usgs.gov/news/featured-story/m78-and-m75-kahramanmaras-earthquake-sequence-near-nurdagi-turkey-turkiye",
                                      "displayLink": "www.usgs.gov",
                                      "snippet": "Feb 6, 2023 ... M7.8 and M7.5 Kahramanmaraş Earthquake Sequence near Nurdağı, Turkey (Türkiye). Earthquake details about the February 6, 2023 quakes.",
                                      "htmlSnippet": "Feb 6, 2023 \\u003cb\\u003e...\\u003c/b\\u003e M7.8 and M7.5 \\u003cb\\u003eKahramanmaraş\\u003c/b\\u003e Earthquake Sequence near Nurdağı, Turkey (Türkiye). Earthquake details about the February 6, 2023 quakes.",
                                      "cacheId": "CRMpeTKzpvUJ",
                                      "formattedUrl": "https://www.usgs.gov/.../m78-and-m75-kahramanmaras-earthquake-sequence -near-nurdagi-turkey-turkiye",
                                      "htmlFormattedUrl": "https://www.usgs.gov/.../m78-and-m75-\\u003cb\\u003ekahramanmaras\\u003c/b\\u003e-earthquake-sequence -near-nurdagi-turkey-turkiye",
                                      "pagemap": {
                                        "cse_thumbnail": [
                                          {
                                            "src": "https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcS6QZZn2cQtyEMpkAhaHJc3me1cTGFh7dt8ObViqT-BCU0bkPI53zYu1Fg",
                                            "width": "218",
                                            "height": "231"
                                          }
                                        ],
                                        "metatags": [
                                          {
                                            "handheldfriendly": "true",
                                            "viewport": "width=device-width, initial-scale=1.0",
                                            "mobileoptimized": "width"
                                          }
                                        ],
                                        "cse_image": [
                                          {
                                            "src": "https://d9-wret.s3.us-west-2.amazonaws.com/assets/palladium/production/s3fs-public/styles/half_width/public/media/images/FofeZ1RWYAAfGf5.jpg?itok=PrlJtWL-"
                                          }
                                        ]
                                      }
                                    }
                                  ]
                                },
                                """;
}