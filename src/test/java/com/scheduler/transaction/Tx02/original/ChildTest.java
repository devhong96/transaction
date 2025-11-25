package com.scheduler.transaction.Tx02.original;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ChildTest {

    @Autowired
    private Child child;
    // 2. Parent가 아닌, @Service가 붙은 Child을 주입받습니다.
    // 이때 주입되는 객체는 트랜잭션 기능이 입혀진 '프록시(Proxy)'입니다.

    @Test
    @DisplayName("상속받은 메서드에 @Transactional을 붙이면 부모 로직도 트랜잭션 안에서 실행된다")
    void testInheritanceTransaction() {

        // When
        System.out.println("====== 테스트 시작 ======");
        child.getChild();
        System.out.println("====== 테스트 종료 ======");

        // Then
        // 콘솔 로그에서 "트랜잭션 시작됨? true"를 확인하면 성공입니다.
    }

}