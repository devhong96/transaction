package com.scheduler.transaction.Tx03;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThatCode;

@SpringBootTest
class Tx03ParentServiceTest {

    @Autowired
    private Tx03ParentService parentService;

    @Test
    @DisplayName("내부 호출 시 프록시가 적용되지 않아 " +
            "REQUIRES_NEW가 무시되고, " +
            "예외를 catch하면 정상 커밋된다"
    )
    void 내부호출_정상커밋_검증() {

        // 1. 내부 호출(parent())이라 프록시가 동작하지 않음 (REQUIRES_NEW 무시)
        // 2. 같은 트랜잭션이지만 프록시가 개입하지 못해 '롤백 마킹'도 없음
        // 3. catch로 예외를 잡았으므로, 부모 트랜잭션은 '성공'으로 판단하고 커밋함
        // 같은 서비스 내에서 @Transactional 어노테이션을 호출했기 때문에 프록시 객체를 거치지 않아 새로운 트랜잭션은 열리지 않습니다.
        assertThatCode(() -> parentService.getParent())
                .doesNotThrowAnyException();
    }
}