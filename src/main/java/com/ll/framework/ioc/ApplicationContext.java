package com.ll.framework.ioc;

import com.ll.domain.testPost.testPost.repository.TestPostRepository;
import com.ll.domain.testPost.testPost.service.TestFacadePostService;
import com.ll.domain.testPost.testPost.service.TestPostService;

public class ApplicationContext {
    private TestPostRepository testPostRepository;
    private TestPostService testPostService;
    private TestFacadePostService testFacadePostService;

    public ApplicationContext() {
    }

    @SuppressWarnings("unchecked")
    public <T> T genBean(String beanName) {

        if ("testPostRepository".equals(beanName)) {
            if (testPostRepository == null) {
                testPostRepository = new TestPostRepository();
            }
            return (T) testPostRepository;
        }

        else if ("testPostService".equals(beanName)) {
            if (testPostRepository == null) {
                testPostRepository = new TestPostRepository();
            }
            if (testPostService == null) {
                testPostService = new TestPostService(testPostRepository);
            }
            return (T) testPostService;
        }

        else if ("testFacadePostService".equals(beanName)) {
            if (testPostRepository == null) {
                testPostRepository = new TestPostRepository();
            }
            if (testPostService == null) {
                testPostService = new TestPostService(testPostRepository);
            }
            if (testFacadePostService == null) {
                testFacadePostService = new TestFacadePostService(
                        testPostService,
                        testPostRepository
                );
            }
            return (T) testFacadePostService;
        }

        else {
            throw new IllegalArgumentException("등록되지 않은 빈 이름입니다: " + beanName);
        }
    }
}
