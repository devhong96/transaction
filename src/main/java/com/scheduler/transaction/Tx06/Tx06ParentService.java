package com.scheduler.transaction.Tx06;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Service
public class Tx06ParentService {

    // 문제
    // @Transactional(propagation = Propagation.REQUIRES_NEW)가 적용된 아래의 경우,
    // 각각의 트랜잭션은 어떻게 생성되며, 어떤 스레드에서 실행될까요?
    private final Tx06ChildService childService;

    Tx06ParentService(Tx06ChildService childService) {
        this.childService = childService;
    }

    //자식에서 문제가 발생했을때, 부모까지 롤백되지 않게 하려면 try catch로 예외를 잡아야함.
    @Transactional
    public void getParent() {

        // 1. 부모 트랜잭션 이름 확인
        String parentTx = TransactionSynchronizationManager.getCurrentTransactionName();
        System.out.println("====== [Parent Tx] : " + parentTx + " ======");
        childService.getChild();
    }

}

@Service
class Tx06ChildService {

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void getChild() {

        // 2. 자식 트랜잭션 이름 확인 (예외 던지기 전에 확인해야 함!)
        String childTx = TransactionSynchronizationManager.getCurrentTransactionName();
        System.out.println("====== [Child Tx (New)] : " + childTx + " ======");

        throw new RuntimeException("Unchecked Exception : RuntimeException");
    }
}

   /*
        @Transactional
        public void getParent() {

             try {
                // 1. 부모 가져오기 (성공해야 함) 여기서 try문 밖에 있어도 똑같이 커밋 됨.
                // 트랜잭션 전파가 REQUIRED(기본값)라면, 부모(getParent) 트랜잭션에 '참여'합니다.
                // 만약 둘 다 실패되게 하려면 실패하는것 위주로 주

                childService.getChild();
                // 2. 자식가져오기 (실패함, REQUIRES_NEW)
                // 얘는 혼자 딴 살림(새 커넥션) 차려서 나갔다가 망해서 돌아옵니다.
                childService.getChild();
             } catch (Exception e) {
                // 예외를 잡아서 복구했습니다.
                // 이때 부모 트랜잭션(Tx A)에는 아무런 타격(롤백 마킹)이 없습니다.
                System.out.println("자식 가져오기는 실패했지만, 부모 찾기는 계속 진행합니다.");
             }
                // 3. getParent 정상 종료 -> Tx A 커밋
                // 결과: getChild 데이터는 DB에 저장됨!
         }
     */
