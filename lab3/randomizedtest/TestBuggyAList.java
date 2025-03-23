package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Assert;
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
        AListNoResizing<Integer> ANobug = new AListNoResizing<>();
        BuggyAList<Integer> Bhasbug = new BuggyAList<>();
        ANobug.addLast(4);
        Bhasbug.addLast(4);
        ANobug.addLast(5);
        Bhasbug.addLast(5);
        ANobug.addLast(6);
        Bhasbug.addLast(6);

        Assert.assertEquals(ANobug.size(),Bhasbug.size());
        Assert.assertEquals(ANobug.removeLast(),Bhasbug.removeLast());
        Assert.assertEquals(ANobug.removeLast(),Bhasbug.removeLast());
    }

}
