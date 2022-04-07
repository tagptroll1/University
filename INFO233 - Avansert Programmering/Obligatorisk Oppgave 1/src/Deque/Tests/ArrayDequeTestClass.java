package Deque.Tests;
import Deque.ArrayDeque;
import Deque.Exceptions.DequeEmptyException;
import Deque.ResizeableArrayDeque;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class ArrayDequeTestClass {
    private ArrayDeque<Integer> deque;
    private ResizeableArrayDeque<Integer> rDeque;


    @BeforeEach
    void setup() {
        deque = new ArrayDeque<>(10);
        rDeque = new ResizeableArrayDeque<>(11);

        deque.addFirst(3);
        deque.addFirst(2);
        deque.addLast(4);
        deque.addLast(5);
        deque.addFirst(1);


        rDeque.addFirst(1);
        rDeque.addFirst(2);
        rDeque.addLast(3);
        rDeque.addFirst(4);
        rDeque.addLast(5);


    }

    @Test
    void testEmptyDeque(){
        ArrayDeque<String> testDeque = new ArrayDeque<>();
        ResizeableArrayDeque<String> testRDeque = new ResizeableArrayDeque<>();

        assertTrue(testDeque.isArrayEmpty());
        assertTrue(testRDeque.isArrayEmpty());
        assertFalse(testDeque.isArrayFull());
        assertFalse(testRDeque.isArrayFull());
    }

    @Test
    void testAddFirst() {
        deque.addFirst(0);
        assertEquals(Integer.valueOf(0), deque.peekFirst());

        rDeque.addFirst(0);
        assertEquals(Integer.valueOf(0), rDeque.peekFirst());
    }


    @Test
    void testAddLast() {
        deque.addLast(0);
        assertEquals(Integer.valueOf(0), deque.peekLast());

        rDeque.addLast(0);
        assertEquals(Integer.valueOf(0), rDeque.peekLast());
    }

    @Test
    void peekFirst() {
        assertEquals(Integer.valueOf(1), deque.peekFirst());
        assertEquals(Integer.valueOf(4), rDeque.peekFirst());
    }

    @Test
    void peekLast() {
        assertEquals(Integer.valueOf(5), deque.peekLast());
        assertEquals(Integer.valueOf(5), rDeque.peekLast());
    }

    @Test
    void pullTop() {
        assertEquals(5, deque.size());
        assertEquals(Integer.valueOf(1), deque.pullFirst());
        assertEquals(4, deque.size());
        assertEquals(Integer.valueOf(2), deque.peekFirst());

        assertEquals(5, rDeque.size());
        assertEquals(Integer.valueOf(4), rDeque.pullFirst());
        assertEquals(4, rDeque.size());
        assertEquals(Integer.valueOf(2), rDeque.peekFirst());
    }

    @Test
    void pullBot() {
        assertEquals(5, deque.size());
        assertEquals(Integer.valueOf(5), deque.pullLast());
        assertEquals(4, deque.size());
        assertEquals(Integer.valueOf(4), deque.peekLast());

        assertEquals(5, rDeque.size());
        assertEquals(Integer.valueOf(5), rDeque.pullLast());
        assertEquals(4, rDeque.size());
        assertEquals(Integer.valueOf(3), rDeque.peekLast());
    }

    @Test
    void testClear() {
        deque.clear();
        rDeque.clear();

        assertEquals(0, deque.size());
        assertEquals(0, rDeque.size());
        assertThrows(DequeEmptyException.class, () -> deque.peekFirst());
        assertThrows(DequeEmptyException.class, () -> deque.peekLast());
        assertThrows(DequeEmptyException.class, () -> rDeque.peekFirst());
        assertThrows(DequeEmptyException.class, () -> rDeque.peekLast());
    }

    @Test
    void testToArray() {
        Integer[] testArray = {4, 5, null, null, null, null, null, 1, 2, 3};
        assertArrayEquals(testArray, deque.toArray(new Integer[0]));

        Integer[] testArray2 = {3, 5, null, null, null, null, null, null, 4, 2, 1};
        assertArrayEquals(testArray2, rDeque.toArray(new Integer[0]));
    }
}
