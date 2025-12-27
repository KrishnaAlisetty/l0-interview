/**
 * @author Krishna Alisetty
 * @date 26-12-2025
 */

package com.portal.interview.service;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RoomService {

    public String createRoomId() {
        return UUID.randomUUID().toString();
    }
}
