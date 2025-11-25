package com.scheduler.transaction.Tx02.composition;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DeluxeRecipeTest {

    @Autowired
    private DeluxeRecipe deluxeRecipe; // 래퍼(Wrapper)를 주입받음

    @Test
    @DisplayName("조합(Composition)을 사용하면, 트랜잭션 안에서 원본 객체가 실행된다")
    void testComposition() {
        // When
        deluxeRecipe.cook();

        // Console Output 예상:
        // == [Original] 요리 중... (트랜잭션 활성 상태: true) ==
    }

}