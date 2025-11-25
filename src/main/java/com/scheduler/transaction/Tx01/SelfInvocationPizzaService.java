package com.scheduler.transaction.Tx01;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Service
public class SelfInvocationPizzaService {

    //문제
    // eatPizza 메소드 내의 pizza가 호출되면
    // 새로운 트랜잭션이 열릴까요?
    public boolean eatPizza() {
        return this.pizza();
    }

    @Transactional
    public boolean pizza() {
        boolean isActive = TransactionSynchronizationManager.isActualTransactionActive();
        System.out.println("Current Transaction Active: " + isActive);
        return isActive;
    }

}
