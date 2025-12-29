package com.portal.interview;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = { "spring.ai.openai.api-key=dummy" })
class InterviewPortalApplicationTests {

    @Test
    void contextLoads() {}
}
