package com.vigilanteaf.shortesturl.shorturl;

import java.util.List;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ShortestUrlService {

    private final ShortestUrlRepository shortestUrlRepository;
    private final ShortestUrlKeyGenerator keyGenerator;
    private final ShortestUrlCache cache;
    private final VisitCountProducer visitCountProducer;

    public ShortestUrlService(
        ShortestUrlRepository shortestUrlRepository,
        ShortestUrlKeyGenerator keyGenerator,
        ShortestUrlCache cache,
        VisitCountProducer visitCountProducer
    ) {
        this.shortestUrlRepository = shortestUrlRepository;
        this.keyGenerator = keyGenerator;
        this.cache = cache;
        this.visitCountProducer = visitCountProducer;
    }

    public CreateShortestUrlResponse create(String originalUrl) {
        String key = keyGenerator.generate();
        ShortestUrlDocument document = shortestUrlRepository.save(new ShortestUrlDocument(key, originalUrl));
        cache.saveOriginalUrl(document.getKey(), document.getOriginalUrl());
        return new CreateShortestUrlResponse(document.getKey());
    }

    public String getOriginalUrlAndIncreaseVisitCount(String key) {
        String originalUrl = cache.findOriginalUrl(key)
            .orElseGet(() -> loadOriginalUrlFromDatabase(key));

        visitCountProducer.increaseVisitCount(key);
        return originalUrl;
    }

    public ShortestUrlsResponse findAll(int pageNumber, int pageSize) {
        int zeroBasedPage = Math.max(pageNumber - 1, 0);
        PageRequest pageRequest = PageRequest.of(
            zeroBasedPage,
            pageSize,
            Sort.by(Sort.Direction.DESC, "createdAt")
        );

        List<ShortestUrlResponse> shortestUrls = shortestUrlRepository.findAll(pageRequest)
            .stream()
            .map(ShortestUrlResponse::from)
            .toList();

        return new ShortestUrlsResponse(shortestUrls, shortestUrlRepository.count());
    }

    private String loadOriginalUrlFromDatabase(String key) {
        ShortestUrlDocument document = shortestUrlRepository.findByKey(key)
            .orElseThrow(() -> new ShortestUrlNotFoundException(key));
        cache.saveOriginalUrl(document.getKey(), document.getOriginalUrl());
        return document.getOriginalUrl();
    }
}
