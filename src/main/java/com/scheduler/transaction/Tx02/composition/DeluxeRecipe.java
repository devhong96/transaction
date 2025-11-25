package com.scheduler.transaction.Tx02.composition;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Service
public class DeluxeRecipe {

    private final OriginalRecipe originalRecipe;

    DeluxeRecipe(OriginalRecipe originalRecipe) {
        this.originalRecipe = originalRecipe;
    }

    @Transactional
    public void cook() {
        // 트랜잭션 시작
        originalRecipe.cook();  //위임
        // 트랜잭션 종료
    }
}

@Service
class OriginalRecipe {

    public void cook() {
        boolean isActive = TransactionSynchronizationManager.isActualTransactionActive();
        System.out.println("== [Original] 요리 중... (트랜잭션 활성 상태: " + isActive + ") ==");
    }
}
