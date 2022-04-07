package Deque;

import Deque.Exceptions.DequeEmptyException;
import Deque.Exceptions.DequeFullException;
import java.util.Arrays;

public class ArrayDeque<E> implements IDeque<E> {
    protected E[] deque;
    int numberOfEntries;
    int topIndex; // Head of Deque
    int botIndex; // Tail of Deque
    private static final int DEFAULT_CAPACITY = 10;
    static final int MAX_CAPACITY = 10000;

    /**
     * Default constructor for Arraydeque, sets capacity to DEFAULT_CAPACITY / 10
     */
    public ArrayDeque(){
        this(DEFAULT_CAPACITY);
    }

    /**
     * Constructor for Arraydeque, sets capacity based on param, checks if its below
     * MAX_CAPACITY.
     * Creates a new array of the object type specified (E), resets all indexes and number of entries
     * @param capacity the Max capacity of the deque / array inside
     */
    public ArrayDeque(int capacity){
        if (capacity >= MAX_CAPACITY) {
            capacity = MAX_CAPACITY;
        }

        @SuppressWarnings("unchecked")
        E[] tempDeque = (E[]) new Object[capacity];
        deque = tempDeque;
        numberOfEntries = 0;
        topIndex = capacity;
        botIndex = 0;
    }


    /**
     * Custom modulus method for incrementing deque index
     * @param i current index in deque
     * @param modulus the number to modulus through the array with, length of array
     * @return returns the incremented number, wraps around to start if passes array length
     */
    static int inc(int i, int modulus) {
        if (++i >= modulus) {
            i = 0;
        }
        return i;
    }

    /**
     * Custom modulus method for decrementing deque index
     * @param i current index in deque
     * @param modulus modulus to wrap by, length of array
     * @return returns the decremented number, wrapped to end of array if bellow 0
     */
    static int dec(int i, int modulus) {
        if (--i < 0) {
            i = modulus - 1;
        }
        return i;
    }

    /**
     * Debug function to check position of tails index
     * @return Tails index
     */
    public int getBotIndex() {
        return botIndex;
    }

    /**
     * Debug function to check position of heads index
     * @return Heads index
     */
    public int getTopIndex(){
        return topIndex;
    }

    /**
     * Function to see if the deque is full
     * @return true if the deque is full, else false
     */
    public boolean isArrayFull(){
        return (topIndex == botIndex && numberOfEntries > 0);
    }

    /**
     * function to see if deque if empty
     * @return true if empty, else false
     */
    public boolean isArrayEmpty(){
        return (numberOfEntries <= 0);
    }

    @Override
    public int size() {
        return numberOfEntries;
    }

    @Override
    public void addFirst(E elem) throws DequeFullException {
        if (isArrayFull()){
            throw new DequeFullException("addFirst couldn't add anymore elements to the deque, it's" +
                    "either full or having index issues.");
        }
        deque[topIndex = dec(topIndex, deque.length)] = elem;
        numberOfEntries ++;
    }

    @Override
    public E pullFirst() throws DequeEmptyException {
        if (isArrayEmpty()){
            throw new DequeEmptyException("Deque doesn't contain any entries");
        }
        E temp = deque[topIndex];
        deque[topIndex] = null;
        topIndex = inc(topIndex, deque.length);
        numberOfEntries--;
        return temp;
    }

    @Override
    public E peekFirst() throws DequeEmptyException {
        if (isArrayEmpty()){
            throw new DequeEmptyException("Deque doesn't contain any entries");
        }
        return deque[topIndex];
    }

    @Override
    public void addLast(E elem) throws DequeFullException {
        if (isArrayFull()){
            throw new DequeFullException("addLast couldn't add element to deque, " +
                    "it's either full or head index is the same as tails");
        }

        deque[botIndex] = elem;
        numberOfEntries ++;
        botIndex = inc(botIndex, deque.length);
    }

    @Override
    public E pullLast() throws DequeEmptyException {
        if (isArrayEmpty()){
            throw new DequeEmptyException("Deque doesn't contain any entries");
        }
        E temp = deque[dec(botIndex, deque.length)]; // stuff
        deque[botIndex = dec(botIndex, deque.length)] = null;
        numberOfEntries--;
        return temp;
    }

    @Override
    public E peekLast() throws DequeEmptyException {
        if (isArrayEmpty()) {
            throw new DequeEmptyException("Deque doesn't contain any entries");
        }
        return deque[dec(botIndex, deque.length)];
    }

    @Override
    public boolean contains(Object inputElem) {
        if (isArrayEmpty()) {
            return false;
        }

        for(E ele : deque){
            if (inputElem.equals(ele)){
                return true;
            }
        }
        return false;
    }

    /**
     * Alternative to toArray(T[] a) that doesn't require any parameters
     *
     * @return returns an Object[] array
     */
    public Object[] toArray() {
        @SuppressWarnings("unchecked")
        E[]result = (E[])new Object[deque.length];
        System.arraycopy(deque, 0, result,0,deque.length);
        return result;
    }

    @Override
    public <T> T[] toArray(T[] a){
        @SuppressWarnings("unchecked")
        T[]result = (T[]) Arrays.copyOf(deque, deque.length, a.getClass());
        return result;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void clear() {
        deque = (E[]) new Object[deque.length];
        numberOfEntries = 0;
        topIndex = 0;
        botIndex = 0;
    }
}
