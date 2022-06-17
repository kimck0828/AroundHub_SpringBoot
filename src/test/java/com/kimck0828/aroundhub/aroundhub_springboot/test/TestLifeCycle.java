package com.kimck0828.aroundhub.aroundhub_springboot.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;

@Slf4j
public class TestLifeCycle {
    
    @BeforeAll
    static void beforeAll() {
        log.info("**beforeAll");
    }
    @AfterAll
    static void afterAll(){
        log.info("****afterAll");
    }
    
    @BeforeEach
    void beforeEach(){
        log.info("**beforeEach");
    }
    @AfterEach
    void afterEach(){
        log.info("**afterEach");
    }
    
    @Test
    void test1(){
        log.info("*test1");
    }
    @Test
    @DisplayName("test2メソッド")
    void test2() {
        log.info("*test2");
    }
    @Test
    @Disabled
    void test3() {
        log.info("*test3");
    }
}
