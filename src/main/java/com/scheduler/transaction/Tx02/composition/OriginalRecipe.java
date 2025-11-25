package com.scheduler.transaction.Tx02.composition;

import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Service
public class OriginalRecipe {

    public void cook() {
        boolean isActive = TransactionSynchronizationManager.isActualTransactionActive();
        System.out.println("== [Original] 요리 중... (트랜잭션 활성 상태: " + isActive + ") ==");
    }
}