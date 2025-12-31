/**
 * @author Krishna Alisetty
 * @date 31-12-2025
 */

package com.portal.interview.util;

import java.util.List;
import java.util.Map;

public class LanguageSynonyms {

    private static final Map<List<String>, String> LANG_SYNONYMS = Map.of(
            List.of("springboot", "spring boot"), "spring boot",
            List.of("js", "javascript", "vanila javascript"), "javascript",
            List.of("reactjs", "react native"), "react",
            List.of("angularjs", "angular"), "angular",
            List.of("html", "html5"), "html",
            List.of("css", "css3", "bootstrap"), "css",
            List.of("java", "java8", "java17", "java21"), "java",
            List.of("microservice", "microservices"), "microservice",
            List.of( "rest", "restservice", "reset services", "restservice"), "rest",
            List.of("maven", "maven3", "mvn", "mvn3"), "maven"
    );

    public static String normalizeSkill(final String skill) {
        for (List<String> k : LANG_SYNONYMS.keySet()) {
            if(k.contains(skill) ) {
                return LANG_SYNONYMS.get(k);
            }
        }

        return skill;
    }
}
