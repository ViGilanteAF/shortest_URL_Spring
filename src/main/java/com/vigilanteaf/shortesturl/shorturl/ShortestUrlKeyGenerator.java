package com.vigilanteaf.shortesturl.shorturl;

import org.springframework.stereotype.Component;

@Component
public class ShortestUrlKeyGenerator {

    private static final char[] BASE64_URL_CHARS =
        "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_".toCharArray();

    private final CountAllocator countAllocator;

    public ShortestUrlKeyGenerator(CountAllocator countAllocator) {
        this.countAllocator = countAllocator;
    }

    public String generate() {
        long number = countAllocator.next();
        char[] result = new char[7];

        for (int i = result.length - 1; i >= 0; i--) {
            int index = (int) (number % BASE64_URL_CHARS.length);
            result[i] = BASE64_URL_CHARS[index];
            number = number / BASE64_URL_CHARS.length;
        }

        return new String(result);
    }
}
