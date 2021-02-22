package com.karandap.springboot.test_task;

import com.karandap.springboot.test_task.controller.Controller;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class TestTaskApplicationTests {

    @Autowired
    private Controller controller;

    @Test
    void contextLoads() {

    }

}
