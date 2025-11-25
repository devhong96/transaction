package com.scheduler.transaction.Tx02.original;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Service
public class Child extends Parent {

    @Override
    @Transactional
    public void cook() {
        System.out.println("== [자식] 트랜잭션 시작됨? "
                + TransactionSynchronizationManager.isActualTransactionActive());

        // 부모의 기능을 그대로 씁니다.
        super.cook();

        System.out.println("== [자식] 요리 끝 (커밋 예정) ==");
    }
}
