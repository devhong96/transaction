package com.scheduler.transaction.Tx05;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThatCode;

@SpringBootTest
class Tx05ParentServiceTest {

    @Autowired
    private Tx05ParentService parentService;

    @Test
    @DisplayName("자식에 @Transactional이 없으면, 롤백 마킹 없이 예외만 전파된다")
    void 자식_트랜잭션이_없을때_예외가_발생하면_롤백되지_않는다() {


        // 예외가 발생했지만 내부에서 잡혔고(catch),
        // 롤백 마킹도 없었으므로 아무런 예외 없이 통과해야 합니다.
        assertThatCode(() -> parentService.getParent())
                .doesNotThrowAnyException();
    }


}