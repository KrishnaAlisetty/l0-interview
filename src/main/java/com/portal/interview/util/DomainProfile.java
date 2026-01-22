/**
 * @author Krishna Alisetty
 * @date 04-01-2026
 */

package com.portal.interview.util;

import java.util.Set;

public record DomainProfile(
        Set<String> core,
        Set<String> supporting,
        Set<String> generic
) {
}
