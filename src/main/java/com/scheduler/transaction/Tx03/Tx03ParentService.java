package com.scheduler.transaction.Tx03;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class Tx03ParentService {

    // 문제
    // 아래 getParent를 호출할 시 RuntimeException이 예외로 발생하면
    // getParent의 트랜잭션이 롤백 될까요?
    @Transactional
    public void getParent() {
        try {
            System.out.println("Before getChild");
            parent();
            System.out.println("After getChild");
        } catch (Exception e) {
            //error ignore
        }

    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void parent() {
        System.out.println("Before Exception");
        throw new RuntimeException("UncheckedException : RuntimeException");
    }
}
