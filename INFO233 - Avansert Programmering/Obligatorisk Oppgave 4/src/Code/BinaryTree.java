package Code;

import Exception.EmptyTreeException;
import Interface.Copyable;
import Interface.StackInterface;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class BinaryTree<T extends Copyable> {
    private BinaryNode<T> root;

    public BinaryTree(){
        this.root = null;
    }

    public BinaryTree(T root){
        this.root = new BinaryNode<>(root);
    }

    public BinaryTree(T data, BinaryTree<T> leftTree, BinaryTree<T> rightTree){
        privateSetTree(data, leftTree, rightTree);
    }

    public void setTree(T data){
        root = new BinaryNode<>(data);
    }

    public void setTree(T data, BinaryTree<T> leftTree, BinaryTree<T> rightTree){
        privateSetTree(data, leftTree, rightTree);
    }

    private void privateSetTree(T data, BinaryTree<T> leftTree, BinaryTree<T> rightTree){
        root = new BinaryNode<>(data);

        if ((leftTree != null) && !leftTree.isEmpty()){
            root.setLeft(leftTree.root);
        }
        if ((rightTree != null) && !rightTree.isEmpty()){
            if (rightTree != leftTree){
                root.setRight(rightTree.root);
            } else {
                root.setRight(rightTree.root.copy());
            }
        }
        if ((leftTree != null) && (leftTree != this)){
            leftTree.clear();
        }
        if ((rightTree != null) && (rightTree != this)){
            rightTree.clear();
        }
    }

    public T getRootData(){
        if (isEmpty()){
            throw new EmptyTreeException();
        } else {
            return root.getData();
        }
    }

    public BinaryNode<T> getRootNode(){
        return this.root;
    }

    public void setRoot(T data){
        this.root.setData(data);
    }

    public void setRootNode(BinaryNode<T> node){
        this.root = node;
    }

    public int getHeight(){
        return this.root.getHeight();
    }

    public int getNumberOfNodes(){
        return this.root.getNumberOfNodes();
    }

    public boolean isEmpty(){
        return root == null;
    }

    public void clear(){
        this.root = null;
    }

    public Iterator<T> getInorderIterator(){
        return new InorderIterator();
    }

    private class InorderIterator implements Iterator<T> {
        private StackInterface<BinaryNode<T>> nodeStack;
        private BinaryNode<T> currentNode;

        public InorderIterator(){
            nodeStack = new LinkedStack<>();
            currentNode = root;
        }
        /**
         * Returns {@code true} if the iteration has more elements.
         * (In other words, returns {@code true} if {@link #next} would
         * return an element rather than throwing an exception.)
         *
         * @return {@code true} if the iteration has more elements
         */
        @Override
        public boolean hasNext() {
            return !nodeStack.isEmpty() || (currentNode!= null);
        }

        /**
         * Returns the next element in the iteration.
         *
         * @return the next element in the iteration
         * @throws NoSuchElementException if the iteration has no more elements
         */
        @Override
        public T next() {
            BinaryNode<T> nextNode = null;

            while(currentNode != null){
                nodeStack.push(currentNode);
                currentNode = currentNode.getLeft();
            }

            if (!nodeStack.isEmpty()){
                nextNode = nodeStack.pop();
                assert nextNode != null;

                currentNode = nextNode.getRight();
            } else {
                throw new NoSuchElementException();
            }

            return nextNode.getData();
        }

        /**
         * Removes from the underlying collection the last element returned
         * by this iterator (optional operation).  This method can be called
         * only once per call to {@link #next}.
         * <p>
         * The behavior of an iterator is unspecified if the underlying collection
         * is modified while the iteration is in progress in any way other than by
         * calling this method, unless an overriding class has specified a
         * concurrent modification policy.
         * <p>
         * The behavior of an iterator is unspecified if this method is called
         * after a call to the {@link #forEachRemaining forEachRemaining} method.
         *
         * @throws UnsupportedOperationException if the {@code remove}
         *                                       operation is not supported by this iterator
         * @throws IllegalStateException         if the {@code next} method has not
         *                                       yet been called, or the {@code remove} method has already
         *                                       been called after the last call to the {@code next}
         *                                       method
         * @implSpec The default implementation throws an instance of
         * {@link UnsupportedOperationException} and performs no other action.
         */
        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
