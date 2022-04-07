package Deque.Tests;

public interface ArrayDequesTestInterface {
    /**
     * Tests adding a,b,c,d to the top of the deque
     */
    void testAddingTopElements(); // Top decrements index values as it grows

    /**
     * Tests adding 1,2,3,4 to the bottom of the deque
     */
    void testAddingBottomElements(); // Bot increases index values as it grows

    /**
     * Peek at the top of deque
     */
    void peekAtTop();

    /**
     * Peek at the bot of the deque
     */
    void peekAtBot();

    /**
     * Pull the top element
     */
    void pullFromTop();

    /**
     * Pull the bottom element
     */
    void pullFromBot();

    /**
     * Clear the entire deque
     */
    void clearEntireDeque();

    /**
     * Display the entire array in string format
     */
    void displayEntireArray();

    /**
     * Print numberofelements
     */
    void displaySize();

    /**
     * boolean checker if deque is full
     */
    void isDequeFull();

    /**
     * Boolean checker if deque is empty
     */
    void isDequeEmpty();

    /**
     * Boolean check if an element is in the deque (this case the head element, so always true)
     * @param element the element to check, in our case head of the deque so always true unless empty
     */
    void doesItContain(String element);

}
