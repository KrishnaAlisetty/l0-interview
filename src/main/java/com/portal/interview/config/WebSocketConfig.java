package com.portal.interview.config;

import com.portal.interview.components.SignalingHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * @author Krishna Alisetty
 * @date 23-12-2025
 */

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
    private final JwtHandshakeInterceptor jwtHandshakeInterceptor;
    private final SignalingHandler signalingHandler;
    public WebSocketConfig(SignalingHandler signalingHandler, JwtHandshakeInterceptor jwtHandshakeInterceptor) {
        this.signalingHandler = signalingHandler;
        this.jwtHandshakeInterceptor = jwtHandshakeInterceptor;
    }
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(signalingHandler, "/signal")
                .addInterceptors(jwtHandshakeInterceptor)
                .setAllowedOrigins("*");
    }
}
