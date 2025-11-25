package com.scheduler.transaction.Tx02.original;

public class Parent {

    public void getChild() {
        // DB 작업이 있다고 가정 (하지만 트랜잭션 관리는 안 되고 있음)
        System.out.println("== [부모] 부르기 (트랜잭션 없음, 순수 로직) ==");
    }
}
