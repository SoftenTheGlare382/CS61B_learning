package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE
    @Test
    public void testThreeAddThreeRemove() {
        BuggyAList<Integer> b = new BuggyAList<>();
        assertEquals(0, b.size());
        b.addLast(1);
        assertEquals(1, b.size());
        b.addLast(2);
        assertEquals(2, b.size());
        b.addLast(3);
        assertEquals(3, b.size());

        assertEquals(3, b.removeLast().intValue());
        assertEquals(2, b.removeLast().intValue());
        assertEquals(1, b.removeLast().intValue());
        assertEquals(0, b.size());
    }

}
