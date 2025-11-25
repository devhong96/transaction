package com.scheduler.transaction.Tx01;

public class PizzaServiceProxy extends SelfInvocationPizzaService{

    SelfInvocationPizzaService target;

    @Override
    public boolean eatPizza() {
        return target.eatPizza();
    }

    @Override
    public boolean pizza() {
        // txManager.begin(); // 트랜잭션 시작
        boolean result = target.pizza();
        // txManager.commit(); // 트랜잭션 종료
        return result;
    }
}
