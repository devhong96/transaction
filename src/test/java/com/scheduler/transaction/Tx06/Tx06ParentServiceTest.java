package com.scheduler.transaction.Tx06;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
class Tx06ParentServiceTest {

    @Autowired
    private Tx06ParentService parentService;

    @Test
    @DisplayName("REQUIRES_NEW로 인해 트랜잭션 이름이 서로 다른지 눈으로 확인하고, 롤백을 검증한다")
    void 트랜잭션_분리_및_롤백_확인() {

        assertThatThrownBy(() -> parentService.getParent())
                .isInstanceOf(RuntimeException.class);
    }

}