package com.scheduler.transaction.Tx01;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Service
public class SelfInvocationParentService {

    // 문제
    // getParent 메소드 내의 parent가 호출되면
    // 새로운 트랜잭션이 열릴까요?
    public boolean getParent() {
        return this.parent();
    }

    @Transactional
    public boolean parent() {
        boolean isActive = TransactionSynchronizationManager.isActualTransactionActive();
        System.out.println("Current Transaction Active: " + isActive);
        return isActive;
    }

}
