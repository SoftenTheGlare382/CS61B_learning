package tester;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Assert;
import org.junit.Test;
import student.StudentArrayDeque;

public class TestArrayDequeEC {
    @Test
    public void testArrayDequeEC() {
        StudentArrayDeque<Integer> sad1 = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> ads2 = new ArrayDequeSolution<>();
        String message = "";

        /**
         * 测试 addFirst()
         * 测试 addLast()
         * 测试 removeFirst()
         * 测试 removeLast()
         */
        Integer N=500;
        for (Integer i = 0; i < N; i += 1) {
            int randomNumber = StdRandom.uniform(0, 4);
            if (randomNumber == 0) {
                sad1.addFirst(i);
                ads2.addFirst(i);
                message = message+ "addFirst("+i.toString()+")\\n";
                Assert.assertEquals(message,ads2.size(),sad1.size());
            }else if (randomNumber == 1){
                sad1.addLast(i);
                ads2.addLast(i);
                message = message + "addLast("+i.toString()+")\\n";
                Assert.assertEquals(message,ads2.size(),sad1.size());
            }else if (randomNumber == 2){
                if(sad1.isEmpty()|| ads2.isEmpty()){
                    continue;
                }
                Integer sad1_removeFirst = sad1.removeFirst();
                Integer ads2_removeFirst = ads2.removeFirst();
                message = message + "removeFirst()\\n";
                Assert.assertEquals(message,sad1_removeFirst, ads2_removeFirst);
            }else{
                if(sad1.isEmpty()|| ads2.isEmpty()){
                    continue;
                }
                Integer sad1_removeLast = sad1.removeLast();
                Integer ads2_removeLast = ads2.removeLast();
                message = message + "removeLast()\\n";
                Assert.assertEquals(message,sad1_removeLast, ads2_removeLast);
            }
        }

    }
    @Test
    public void testRemoveLast() {
        StudentArrayDeque<Integer> sad2 = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> ads2 = new ArrayDequeSolution<>();
        Integer N = 100;
        for (int i = 0; i < N; i++) {
            ads2.addFirst(i);
            sad2.addFirst(i);
        }
        for (int i = 0; i < N; i++) {
            Integer adsRemoveFirst = ads2.removeFirst();
            Integer sadRemoveFirst = sad2.removeFirst();
            Assert.assertEquals(adsRemoveFirst, sadRemoveFirst);
        }

    }
}
