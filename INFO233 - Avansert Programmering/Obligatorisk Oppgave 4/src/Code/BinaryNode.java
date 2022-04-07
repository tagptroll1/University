package Code;

import Interface.Copyable;

public class BinaryNode<T extends Copyable> implements Cloneable{
    private T data;
    private BinaryNode<T> leftChild;
    private BinaryNode<T> rightChild;

    public BinaryNode(){
        this(null);
    }

    public BinaryNode(T data){
        this(data, null, null);
    }

    public BinaryNode(T data, BinaryNode<T> leftChild,
                      BinaryNode<T> rightChild){
        this.data = data;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    public T getData(){
        return this.data;
    }

    public void setData(T data){
        this.data = data;
    }

    public BinaryNode<T> getLeft(){
        return this.leftChild;
    }

    public BinaryNode<T> getRight(){
        return this.rightChild;
    }

    public void setLeft(BinaryNode<T> leftChild){
        this.leftChild = leftChild;
    }

    public boolean hasLeft(){
        return this.leftChild != null;
    }

    public void setRight(BinaryNode<T> rightChild){
        this.rightChild = rightChild;
    }

    public boolean hasRight(){
        return this.rightChild != null;
    }

    public boolean isLeaf(){
        return (this.leftChild == null) && (this.rightChild == null);
    }

    public int getNumberOfNodes(){
        int leftNum = 0;
        int rightNum = 0;

        if (leftChild != null){
            leftNum = leftChild.getNumberOfNodes();
        }
        if (rightChild != null){
            rightNum = rightChild.getNumberOfNodes();
        }

        return 1 + leftNum + rightNum;
    }

    public int getHeight(){
        return getHeight(this);
    }

    private int getHeight(BinaryNode<T> node){
        int height = 0;

        if (node!=null){
            height = 1 + Math.max(getHeight(node.getLeft()),
                                  getHeight(node.getRight()));
        }
        return height;
    }

    public BinaryNode<T> copy(){
        BinaryNode<T> newRoot = new BinaryNode<>(this.data);
        if (this.leftChild != null){
            newRoot.setLeft(this.leftChild.copy());
        }
        if (this.rightChild != null){
            newRoot.setRight(this.rightChild.copy());
        }
        return newRoot;
    }

    @SuppressWarnings("unchecked")
    public Object clone(){
        BinaryNode<T> theCopy;
        try{
            theCopy = (BinaryNode<T>)super.clone();
        } catch (CloneNotSupportedException e){
            throw new Error("Clone not supported.."+e.toString());
        }
        theCopy.data = (T) data.clone();

        if(leftChild != null){
            theCopy.setLeft((BinaryNode<T>) leftChild.clone());
        }
        if (rightChild != null){
            theCopy.setRight((BinaryNode<T>) rightChild.clone());
        }

        return theCopy;
    }



}
