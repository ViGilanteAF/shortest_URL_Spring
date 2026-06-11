package com.vigilanteaf.shortesturl.shorturl;

public class ShortestUrlNotFoundException extends RuntimeException {

    public ShortestUrlNotFoundException(String key) {
        super("등록되어있는 단축 URL이 아닙니다: " + key);
    }
}
