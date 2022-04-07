package Interface;

public interface TreeInterface<T> {
    /**
     * @return Returns data of root
     */
    T getRootData();

    /**
     * @return Returns the height of tree
     */
    int getHeight();

    /**
     * @return returns the amount of nodes
     */
    int getNumberOfNodes();

    /**
     * @return returns true of tree is empty
     */
    boolean isEmpty();

    /** Clears the tree
     */
    void clear();
}
