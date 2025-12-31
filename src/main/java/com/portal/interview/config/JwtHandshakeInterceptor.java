/**
 * @author Krishna Alisetty
 * @date 26-12-2025
 */

package com.portal.interview.config;

import com.portal.interview.service.JwtService;
import io.jsonwebtoken.Claims;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Arrays;
import java.util.Map;

@Component
public class JwtHandshakeInterceptor implements HandshakeInterceptor {

    private final JwtService jwtService;

    public JwtHandshakeInterceptor(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        String query = request.getURI().getQuery();

        if(query ==null || !query.contains("token=")){
            return false;
        }

        String token = Arrays.stream(query.split("&"))
                .filter(q -> q.startsWith("token="))
                .map(q -> q.substring(6))
                .findFirst()
                .orElse(null);

        if (token == null) {
            return false;
        }

        Claims claims = jwtService.parseToken(token);
        String userId = claims.getSubject();
        String role = claims.get("role", String.class);
        String room = claims.get("room", String.class);

        if (userId == null || role == null || room == null) {
            return false;
        }

        attributes.put("userId", userId);
        attributes.put("role", role);
        attributes.put("room", room);

        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {

    }
}
