package com.scheduler.transaction.Tx01;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class SelfInvocationParentServiceTest {

    @Autowired
    private SelfInvocationParentService parentService;
    // ^ 여기 주입되는 객체는 'PizzaServiceProxy' (CGLIB 프록시)입니다.

    @Test
    @DisplayName("외부에서 getParent 호출 -> 내부에서 parent 호출 (Self-Invocation) -> 트랜잭션 적용 X")
    void testGetChild_InternalCall() {

        // 프록시의 getParent()를 호출하지만, 내부에서 this.parent()를 부르므로 트랜잭션 코드를 건너뜀
        boolean isTxActive = parentService.getParent();

        // 기대값: false (트랜잭션이 활성화 X)
        assertThat(isTxActive).isFalse();
        System.out.println("getParent 결과: 트랜잭션 없음");
    }

    @Test
    @DisplayName("외부에서 parent 직접 호출 -> 프록시가 가로챔 -> 트랜잭션 적용 O")
    void testParent_DirectCall() {

        // 프록시의 parent()를 직접 호출하므로 txManager.begin()이 실행됨
        boolean isTxActive = parentService.parent();

        // 기대값: true (트랜잭션이 활성화 O)
        assertThat(isTxActive).isTrue();
        System.out.println("parent 결과: 트랜잭션 있음");
    }

    /*
        @Transactional 어노테이션은 Spring AOP 기반으로 동작하는데
        동일한 클래스 내에서 @Transactional 이 적용된 내부 메서드를 호출하는 경우,
        호출되는 메서드는 프록시 객체를 거치지 않고 직접 호출되기 때문에 프록시(TransactionInterceptor)가 동작하지 않는다.
     */

}