package com.vigilanteaf.shortesturl.shorturl;

import java.util.List;

public record ShortestUrlsResponse(
    List<ShortestUrlResponse> shortestUrls,
    long totalCount
) {
}
