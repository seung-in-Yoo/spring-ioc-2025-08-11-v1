package com.ll.framework.ioc;

import com.ll.domain.testPost.testPost.repository.TestPostRepository;
import com.ll.domain.testPost.testPost.service.TestFacadePostService;
import com.ll.domain.testPost.testPost.service.TestPostService;

public class ApplicationContext {
    TestPostRepository testPostRepository;
    TestPostService testPostService;
    TestFacadePostService testFacadePostService;

    public <T> T genBean(String beanName) {
        if (testFacadePostService == null) {
            testPostRepository = new TestPostRepository();
            testPostService = new TestPostService(testPostRepository);
            testFacadePostService = new TestFacadePostService(testPostService, testPostRepository);
        }

        return (T) switch (beanName) {
            case "testPostService" -> testPostService;
            case "testPostRepository" -> testPostRepository;
            case "testFacadePostService" -> testFacadePostService;
            default -> null;
        };
    }
}
