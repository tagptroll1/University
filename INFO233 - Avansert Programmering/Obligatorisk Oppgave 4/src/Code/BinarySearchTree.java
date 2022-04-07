package Code;

import Interface.BinaryTreeInterface;
import Interface.Copyable;
import Interface.SearchBinaryTreeInterface;

public class BinarySearchTree<T extends Comparable<? super T> & Copyable>
        extends BinaryTree<T>
        implements SearchBinaryTreeInterface<T> {

    public BinarySearchTree(){
        super();
    }

    public BinarySearchTree(T root){
        super();
        setRootNode(new BinaryNode<>(root));
    }

    public void setTree(T root){
        throw new UnsupportedOperationException();
    }

    public void setTree(T root, BinaryTreeInterface<T> left,
                                BinaryTreeInterface<T> right){
        throw new UnsupportedOperationException();
    }


    /**
     * @param anEntry Entry to find
     * @return returns true if entry is in tree
     */
    @Override
    public boolean contains(T anEntry) {
        return false;
    }

    /**
     * @param anEntry entry to find
     * @return returns the given entry, or null if it doesn't exist
     */
    @Override
    public T getEntry(T anEntry) {
        return null;
    }

    /**
     * @param anEntry Entry to add
     * @return returns old entry if an override happened.
     */
    @Override
    public T add(T anEntry) {
        return null;
    }

    /**
     * @param anEntry Entry to remove
     * @return returns the removed entry, or null
     */
    @Override
    public T remove(T anEntry) {
        return null;
    }
}
