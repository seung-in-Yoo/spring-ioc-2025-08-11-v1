package com.ll.framework.ioc;

import com.ll.domain.testPost.testPost.repository.TestPostRepository;
import com.ll.domain.testPost.testPost.service.TestPostService;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ApplicationContext {
    private final Map<String, Object> singletons = new ConcurrentHashMap<>(); // 싱글톤 캐시 관련
    public ApplicationContext() { }

    @SuppressWarnings("unchecked") // warning 없애기 위해서 사용 (없어도 되긴함)
    public <T> T genBean(String beanName) {
        return (T) singletons.computeIfAbsent(beanName, this::createBeanByName); // 싱글톤 보장하여 호출
    }

    private Object createBeanByName(String name) {
        switch (name) {
            case "testPostRepository":
                return new TestPostRepository();

            case "testPostService":
                TestPostRepository repo = genBean("testPostRepository"); // DI 사용
                return new TestPostService(repo);

            default:
                throw new IllegalArgumentException(
                        String.format("이름이 '%s'인 빈이 정의되어 있지 않습니다. 등록된 빈: %s", name, singletons.keySet())
                );
        }
    }


    @Override
    public String toString() {
        return "ApplicationContext{singletons=" + singletons.keySet() + "}";
    }
}