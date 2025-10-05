package algorithms;

import metrics.PerformanceTracker;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Random;
import java.util.Arrays;
public class HeapSortTest {
    @Test
    public void testEmpty() {
        int[] a = new int[0];
        PerformanceTracker t = new PerformanceTracker();
        HeapSort.sort(a, t);
        assertEquals(0, a.length);
    }
    @Test
    public void testSingleElement() {
        int[] a = {42};
        PerformanceTracker t = new PerformanceTracker();
        HeapSort.sort(a, t);
        assertArrayEquals(new int[]{42}, a);
    }
    @Test
    public void testDuplicates() {
        int[] a = {5,5,5,5,5};
        HeapSort.sort(a, null);
        assertArrayEquals(new int[]{5,5,5,5,5}, a);
    }
    @Test
    public void testRandomSmall() {
        Random rnd = new Random(123);
        for (int it = 0; it < 50; it++) {
            int n = rnd.nextInt(50);
            int[] a = new int[n];
            for (int i = 0; i < n; i++) a[i] = rnd.nextInt(1000) - 500;
            int[] copy = Arrays.copyOf(a, a.length);
            HeapSort.sort(a, null);
            Arrays.sort(copy);
            assertArrayEquals(copy, a);
        }
    }
    @Test
    public void testStabilityAgainstArraysSort() {
        Random rnd = new Random(999);
        int[] a = new int[1000];
        for (int i = 0; i < a.length; i++) a[i] = rnd.nextInt();
        int[] copy = Arrays.copyOf(a, a.length);
        HeapSort.sort(a, new PerformanceTracker());
        Arrays.sort(copy);
        assertArrayEquals(copy, a);
    }

    @Test
    public void testReverseSorted() {
        int[] a = {5,4,3,2,1};
        HeapSort.sort(a, new PerformanceTracker());
        assertArrayEquals(new int[]{1,2,3,4,5}, a);
    }
    @Test
    public void testAlreadySorted() {
        int[] a = {1,2,3,4,5};
        HeapSort.sort(a, new PerformanceTracker());
        assertArrayEquals(new int[]{1,2,3,4,5}, a);
    }
}
