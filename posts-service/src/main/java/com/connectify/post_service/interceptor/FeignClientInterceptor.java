package com.connectify.post_service.interceptor;

import com.connectify.post_service.context.UserContextHolder;
import com.connectify.post_service.dto.UserDto;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;

@Component
public class FeignClientInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        UserDto user = UserContextHolder.getCurrentUser();
        if(user!=null) {
            requestTemplate.header("X-User-Id", user.getId().toString());
        }
    }
}
