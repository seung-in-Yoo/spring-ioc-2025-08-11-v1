package com.ll.framework.ioc;

import com.ll.domain.testPost.testPost.repository.TestPostRepository;
import com.ll.domain.testPost.testPost.service.TestFacadePostService;
import com.ll.domain.testPost.testPost.service.TestPostService;

public class ApplicationContext {
    TestPostService testPostService;
    TestPostRepository testPostRepository;
    TestFacadePostService testFacadePostService;

    public ApplicationContext() {
        testPostRepository = new TestPostRepository();
        testPostService = new TestPostService(testPostRepository);
        testFacadePostService = new TestFacadePostService(testPostService, testPostRepository);
    }

    public <T> T genBean(String beanName) {
        switch (beanName) {
            case "testPostService" -> {return (T)testPostService;}
            case "testPostRepository" -> {return (T)testPostRepository;}
            case "testFacadePostService" -> {return (T)testFacadePostService;}
            default -> {return (T)null;}
        }
    };
}

