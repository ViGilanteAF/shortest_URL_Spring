package com.vigilanteaf.shortesturl.shorturl;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import java.io.IOException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/shortest-urls")
public class ShortestUrlController {

    private final ShortestUrlService shortestUrlService;

    public ShortestUrlController(ShortestUrlService shortestUrlService) {
        this.shortestUrlService = shortestUrlService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateShortestUrlResponse createShortestUrl(
        @Valid @RequestBody CreateShortestUrlRequest request
    ) {
        return shortestUrlService.create(request.originalUrl());
    }

    @GetMapping("/{shortestUrlKey}")
    public void redirectOriginalUrl(
        @PathVariable @Size(min = 7, max = 7, message = "7자리 문자열입니다.") String shortestUrlKey,
        HttpServletResponse response
    ) throws IOException {
        String originalUrl = shortestUrlService.getOriginalUrlAndIncreaseVisitCount(shortestUrlKey);
        response.sendRedirect(originalUrl);
    }

    @GetMapping
    public ShortestUrlsResponse getShortestUrls(
        @RequestParam(defaultValue = "1") @Min(1) int pageNumber,
        @RequestParam(defaultValue = "10") @Min(1) @Max(100) int pageSize
    ) {
        return shortestUrlService.findAll(pageNumber, pageSize);
    }
}
