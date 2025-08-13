package com.ll.framework.ioc;

import com.ll.domain.testPost.testPost.repository.TestPostRepository;
import com.ll.domain.testPost.testPost.service.TestPostService;

public class ApplicationContext {
    public ApplicationContext() { }

    @SuppressWarnings("unchecked")
    public <T> T genBean(String beanName) {
        switch (beanName) {
            case "testPostService":
                return (T) new TestPostService(new TestPostRepository());
            case "testPostRepository":
                return (T) new TestPostRepository();
            default:
                throw new IllegalArgumentException("빈이 존재하지 않습니다: " + beanName);
        }
    }

    @Override
    public String toString() {
        return "ApplicationContext{}";
    }
}