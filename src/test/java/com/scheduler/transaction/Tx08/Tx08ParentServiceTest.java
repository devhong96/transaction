package com.scheduler.transaction.Tx08;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
class Tx08ParentServiceTest {

    @Autowired
    Tx08ParentService tx08ParentService;

    @Test
    @DisplayName("자식(REQUIRES_NEW)은 먼저 성공해서 커밋되고, 부모는 나중에 예외가 발생하여 롤백된다")
    void 자식은_커밋되고_부모는_롤백된다() {

        // 1. getChild() 실행 완료 -> 자식 트랜잭션(Tx B) 즉시 커밋! (DB 저장 완료)
        // 2. getParent() 로직 계속 진행
        // 3. throw RuntimeException 발생 -> 부모 트랜잭션(Tx A) 롤백
        // 결과: 전체 로직은 실패로 끝나지만, pickle 데이터는 남아있습니다.
        assertThatThrownBy(() -> tx08ParentService.getParent())
                .isInstanceOf(RuntimeException.class);
    }

}