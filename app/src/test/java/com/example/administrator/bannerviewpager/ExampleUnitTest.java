package com.example.administrator.bannerviewpager;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void TestUnit(){
        ArrayList<Integer> arrayList=new ArrayList<>();
        for(int i=0;i<10;i++){
            arrayList.add(i);
        }
        arrayList.add(0,10);
        for (int i:arrayList){
            System.out.println(i);
        }
    }
}