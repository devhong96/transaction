package com.scheduler.transaction.Tx07;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThatCode;

@SpringBootTest
class Tx07ParentServiceTest {

    @Autowired
    Tx07ParentService tx07ParentService;

    @Test
    @DisplayName("REQUIRES_NEW인 자식이 롤백되어도, 부모가 예외를 catch하면 부모는 정상 커밋된다")
    void 자식은_롤백되지만_부모는_커밋된다() {

        // 1. getChild()은 예외가 발생하여 롤백됨 (Tx B Rollback)
        // 2. getParent()는 예외를 catch하여 정상 흐름으로 돌아옴
        // 3. Tx A(부모)와 Tx B(자식)는 서로 다른 커넥션이므로, Tx B의 롤백이 Tx A에 영향을 주지 않음
        assertThatCode(() -> tx07ParentService.getParent())
                .doesNotThrowAnyException();
    }

}