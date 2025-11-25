package com.scheduler.transaction.Tx04;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.UnexpectedRollbackException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
class Tx04ParentServiceTest {

    @Autowired
    private Tx04ParentService parentService;

    @Test
    @DisplayName("내부 트랜잭션(Pickle)에서 롤백 마킹을 하면, 외부에서 예외를 잡아도 결국 롤백된다")
    void 외부에서_catch해도_UnexpectedRollbackException이_발생한다() {

        // When & Then
        // 기대하는 동작:
        // 1. getChild()에서 예외 발생 -> 트랜잭션에 '롤백 전용(rollback-only)' 마킹
        // 2. getParent()에서 catch로 예외를 삼킴 -> 정상 종료되는 것처럼 보임
        // 3. 하지만 스프링이 커밋하려는 순간 '롤백 마킹'을 확인하고 UnexpectedRollbackException을 던짐
        assertThatThrownBy(() -> parentService.getParent())
                .isInstanceOf(UnexpectedRollbackException.class);
    }
}