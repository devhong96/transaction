

# Spring Transaction

Part 1: 진입 단계 (Proxy Mechanism)

핵심 질문: "트랜잭션 인터셉터가 호출되는가?"

내용: 프록시 패턴의 한계, 내부 호출 문제 (Case 1~3)

Part 2: 실행 단계 (Propagation & Policy)

핵심 질문: "호출된 인터셉터가 트랜잭션을 어떻게 다루는가?"

내용: 전파 속성, 물리,논리 트랜잭션의 커밋,롤백 판단 (Tx 03~05)

## 1\. 프록시와 내부 호출 (Self-Invocation)

Spring AOP는 기본적으로 **프록시(Proxy)** 패턴을 사용하여 트랜잭션을 처리합니다. 이로 인해 발생하는 대표적인 문제와 해결책을 다룹니다.

### Case 1: 내부 호출 문제 (`SelfInvocationPizzaService`)

### Case 2: 해결책 - 외부 빈 주입 (`OriginalRecipe` -\> `DeluxeRecipe`)

### Case 3: 상속과 트랜잭션 (`Child` extends `Parent`)

-----

## 2\. 트랜잭션 전파와 롤백 규칙

트랜잭션 전파 속성(`REQUIRED`, `REQUIRES_NEW`)과 예외 발생(`RuntimeException`) 시 부모/자식 트랜잭션의 운명을 다룹니다.


### Tx03: REQUIRES\_NEW와 내부 예외 처리

### Tx04: 기본 전파(REQUIRED)와 참여

### Tx05: 트랜잭션 없는 자식 호출

### Tx06: 스레드와 트랜잭션 분리

### Tx07: REQUIRES\_NEW와 외부 클래스 분리 (격리)

### Tx08: REQUIRES\_NEW 성공 후 부모 예외

-----

## 3\. 핵심 요약

1.  **Self-Invocation:** 같은 클래스 내의 메서드 호출(`this.method()`)은 `@Transactional`이 무시된다. (Aspect가 적용되지 않음)
2.  **Rollback Only:** `REQUIRED` 전파 속성에서 자식 메서드가 예외를 던지면, 부모가 `try-catch`를 해도 트랜잭션은 `rollback-only` 마크 때문에 결국 롤백된다.
3.  **Requires New:** 물리적으로 완전히 독립된 트랜잭션을 연다. 자식의 롤백이 부모에게 영향을 주지 않으려면 부모에서 예외 처리를 해야 한다.
4.  **Thread:** 트랜잭션 전파 속성이 바뀌어도 기본적으로는 **동일한 스레드**에서 실행된다. (비동기 처리가 아님)

-----

### 🛠 Tech Stack

* Java 17+
* Spring Boot 3.x
* JPA / Hibernate