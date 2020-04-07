package com.totaloil.simpletest.unitTest

import org.junit.Assert
import org.junit.Test


class ExampleFunctionTest {

    // @Before ส่วนที่ต้องทำก่อน test เช่น mock-up server
    // @Test ตัว test case ซึ่งมีมากกว่า 1 อันแน่ๆ เช่น ถ้า response เป็น 200 หรือ 404
    // @After ส่วนที่ต้องทำหลัง test เช่น shutdown server ที่เรา mock-up ไป

    @Test
    fun testFunctionPlusNumber() {
        Assert.assertEquals(4,plusNumber(3,1))
    }

    fun plusNumber(a: Int, b: Int): Int {
        return a + b
    }
}