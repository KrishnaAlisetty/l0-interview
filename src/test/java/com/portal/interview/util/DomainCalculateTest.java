package com.portal.interview.util;

import com.portal.interview.components.DomainDictionaryProperties;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(properties =
        "spring.config.import=classpath:domain-dictionary.yml"
)
class DomainCalculateTest {

    @Autowired
    DomainDictionaryProperties props;

    @Test
    void shouldLoadDomains() {
        assertTrue(props.getDomains().size()>0);
    }
}