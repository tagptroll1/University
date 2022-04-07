package Interface;

import java.util.Iterator;

public interface SearchBinaryTreeInterface<T extends Comparable<? super T>>
        extends TreeInterface<T> {
    /**
     * @param anEntry Entry to find
     * @return returns true if entry is in tree
     */
    boolean contains(T anEntry);

    /**
     * @param anEntry entry to find
     * @return returns the given entry, or null if it doesn't exist
     */
    T getEntry(T anEntry);

    /**
     * @param anEntry Entry to add
     * @return returns old entry if an override happened.
     */
    T add(T anEntry);

    /**
     * @param anEntry Entry to remove
     * @return returns the removed entry, or null
     */
    T remove(T anEntry);

    /** Inorder traversal - Left tree -> Root -> Right tree
     * @return returns an iterator that traverses the tree inorder
     */
    Iterator<T> getInorderIterator();

}
