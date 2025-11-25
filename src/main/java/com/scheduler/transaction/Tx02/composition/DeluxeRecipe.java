package com.scheduler.transaction.Tx02.composition;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DeluxeRecipe {

    private final OriginalRecipe originalRecipe;

    public DeluxeRecipe(OriginalRecipe originalRecipe) {
        this.originalRecipe = originalRecipe;
    }

    @Transactional
    public void cook() {
        // 트랜잭션 시작
        originalRecipe.cook();  //위임
        // 트랜잭션 종료
    }
}
