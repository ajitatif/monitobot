insert into tracks ( id, public_id, created_on_utc, last_run_on_utc, search_criteria )
values (
    nextval('sq_tracks_id'),
    'existing-track',
    '2023-01-01T00:00:00',
    '2023-02-05T00:00:00',
    '{ "query": "kahramanmaras" }'
);

insert into search_results ( id, track_id, search_engine, created_on_utc, status_code, raw_data)
values (
    nextval('sq_search_results_id'),
    1,
    'google',
    '2023-02-06T00:00:00',
    502,
    '{ "error": "some error, don''t know what" }'
),
(
    nextval('sq_search_results_id'),
    1,
    'google',
    '2023-02-06T00:00:00',
    200,
    '{
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
      "htmlTitle": "\u003cb\u003eKahramanmaraş\u003c/b\u003e - Wikipedia",
      "link": "https://en.wikipedia.org/wiki/Kahramanmara%C5%9F",
      "displayLink": "en.wikipedia.org",
      "snippet": "Maraş, officially Kahramanmaraş and historically Germanicea (Greek: Γερμανίκεια), is a city in the Mediterranean region of Turkey and the administrative ...",
      "htmlSnippet": "Maraş, officially \u003cb\u003eKahramanmaraş\u003c/b\u003e and historically Germanicea (Greek: Γερμανίκεια), is a city in the Mediterranean region of Turkey and the administrative&nbsp;...",
      "cacheId": "IIj8IYqUTj8J",
      "formattedUrl": "https://en.wikipedia.org/wiki/Kahramanmaraş",
      "htmlFormattedUrl": "https://en.wikipedia.org/wiki/\u003cb\u003eKahramanmaraş\u003c/b\u003e",
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
    }
   ]
  }'
);