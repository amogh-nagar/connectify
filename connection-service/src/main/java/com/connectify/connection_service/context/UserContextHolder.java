package com.connectify.connection_service.context;

import com.connectify.connection_service.dto.UserDto;

public class UserContextHolder {

    private static final ThreadLocal<UserDto> currentUser = new ThreadLocal<>();

    public static UserDto getCurrentUser() {
        return currentUser.get();
    }

    public static void setCurrentUser(UserDto user) {
        currentUser.set(user);
    }

    public static void clear() {
        currentUser.remove();
    }

}
