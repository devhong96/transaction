package com.scheduler.transaction.Tx05;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class Tx05ParentService {

    // 문제
    // 아래 Tx05ChildService의 getChild를 호출할 시
    // RuntimeException이 예외로 발생하면 getParent의 트랜잭션이 롤백 될까요?
    // 이번엔 Tx05ChildService에 @Transaction이 없음.
    private final Tx05ChildService childService;

    Tx05ParentService(Tx05ChildService childService) {
        this.childService = childService;
    }

    @Transactional
    public void getParent() {

        parent();
        try {
            System.out.println("Before getChild");
            childService.getChild();
            System.out.println("After getChild");
        } catch (Exception e) {
            // error ignore
        }
    }

    public void parent() {
        System.out.println("Out Of try-catch block");
    }

}

@Service
class Tx05ChildService {
    public void getChild() {
        System.out.println("Before Exception");
        throw new RuntimeException("Unchecked Exception : RuntimeException");
    }
}
