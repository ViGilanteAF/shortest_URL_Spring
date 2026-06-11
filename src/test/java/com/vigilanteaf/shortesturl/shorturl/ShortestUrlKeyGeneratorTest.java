package com.vigilanteaf.shortesturl.shorturl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class ShortestUrlKeyGeneratorTest {

    @Test
    void generatesSevenCharacterBase64UrlKey() {
        CountAllocator countAllocator = Mockito.mock(CountAllocator.class);
        when(countAllocator.next()).thenReturn(1L);

        ShortestUrlKeyGenerator generator = new ShortestUrlKeyGenerator(countAllocator);

        assertThat(generator.generate()).isEqualTo("AAAAAAB");
    }

    @Test
    void propertiesKeepNestJsCacheTtlUnitAsSeconds() {
        ShortestUrlProperties properties =
            new ShortestUrlProperties(60, 10_000, "increaseVisitCountByKey");

        assertThat(properties.cacheTtlSeconds()).isEqualTo(60);
    }
}
