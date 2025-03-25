package deque;

import org.junit.Assert;
import org.junit.Test;

import java.util.Comparator;
import java.util.Optional;

public class MaxArrayDequeTest {
    @Test
    public void maxtest(){
        MaxArrayDeque<Integer> a=new MaxArrayDeque<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if (o1==o2) return 0;
                return o1>o2?1:-1;
            }
        });
        a.addLast(1);
        a.addLast(2);
        a.addLast(3);
        a.addLast(4);
        a.addLast(5);
        a.addLast(6);

        System.out.println(a.max());
        Assert.assertEquals(Optional.of(6),Optional.of(a.max()));

    }
}
