package com.scheduler.transaction.Tx01;

public class ParentServiceProxy extends SelfInvocationParentService {

    SelfInvocationParentService target;

    @Override
    public boolean getParent() {
        return target.getParent();
    }

    @Override
    public boolean parent() {
        // txManager.begin(); // 트랜잭션 시작
        boolean result = target.parent();
        // txManager.commit(); // 트랜잭션 종료
        return result;
    }
}
