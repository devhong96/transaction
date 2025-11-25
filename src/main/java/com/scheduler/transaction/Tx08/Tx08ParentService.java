package com.scheduler.transaction.Tx08;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class Tx08ParentService {

    // 문제
    // 아래 Tx08ChildService의 getChild를 호출할 시
    // RuntimeException이 예외로 발생하면 getParent의 트랜잭션이 롤백 될까요?
    private final Tx08ChildService childService;

    Tx08ParentService(Tx08ChildService childService) {
        this.childService = childService;
    }

    @Transactional
    public void getParent() {
        childService.getChild();
        throw new RuntimeException("Unchecked Exception : RuntimeException");
    }
}

@Service
class Tx08ChildService {

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void getChild() {
        System.out.println("child");
    }
}

