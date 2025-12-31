/**
 * @author Krishna Alisetty
 * @date 30-12-2025
 */

package com.portal.interview.constants.domain.tech;

import java.util.Map;
import java.util.Set;

public class TechnicalDomainSkills {

    private TechnicalDomainSkills() {

    }

    private final static Map<TechnicalDomain, Set<String>> DOMAIN_SKILLS = Map.of(
            TechnicalDomain.JAVA_BACKEND, Set.of(
                    "java", "spring boot", "spring", "hibernate", "j2ee", "maven",
                    "jpa", "microservices", "rest", "kafka", "sql"
            ),
            TechnicalDomain.PYTHON_BACKEND, Set.of(
                    "python", "django"
            ),
            TechnicalDomain.FRONTEND, Set.of(
                    "javascript", "typescript", "react",
                    "angular", "vue", "html" ,"css", "ajax", "jquery", "bootstrap"
            ),
            TechnicalDomain.DEVOPS, Set.of(
                    "docker", "kubernetes", "jenkins",
                    "ci/cd", "terraform", "helm", "gitlab"
            ),
            TechnicalDomain.DATA_ENGINEERING, Set.of(
                    "spark", "hadoop", "airflow",
                    "etl", "kafka", "bigquery"
            ),
            TechnicalDomain.CLOUD, Set.of(
                    "aws", "azure", "gcp",
                    "ec2", "s3", "lambda"
            ),
            TechnicalDomain.QA_AUTOMATION, Set.of(
                    "selenium", "cypress", "playwright",
                    "junit", "testng"
            )
    );

    public static Map<TechnicalDomain, Set<String>> getSkills() {
        return DOMAIN_SKILLS;
    }

}
