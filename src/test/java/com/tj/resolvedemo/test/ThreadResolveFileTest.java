package com.tj.resolvedemo.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @program: resolve-demo
 * @Date: 2021/3/18 10:54
 * @Author: Mr.SU
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ThreadResolveFileTest {

    @Test
    public void testFourThread() {
        int num = 10;
        int start = num / 4;
        System.out.println(start);

    }
}