package com.scheduler.transaction.Tx07;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class Tx07ParentService {

    // 문제
    // 아래 Tx07ChildService의 getChild을 호출할 시
    // RuntimeException이 예외로 발생하면 getParent의 트랜잭션이 롤백 될까요?
    private final Tx07ChildService childService;

    Tx07ParentService(Tx07ChildService childService) {
        this.childService = childService;
    }

    @Transactional
    public void getParent() {
        try {
            System.out.println("Before getChild");
            childService.getChild();
            System.out.println("After getChild");
        } catch (Exception e) {
            // error ignore
        }
    }
}

@Service
class Tx07ChildService {

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void getChild() { // 어떤 트랜잭션과 스레드에서 동작할까?
        System.out.println("Before Exception");
        throw new RuntimeException("Unchecked Exception : RuntimeException");
    }
}

