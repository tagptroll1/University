package Interface;

public interface BinaryTreeInterface<T> extends TreeInterface<T> {
    void setTree(T root);
    void setTree(T root, BinaryTreeInterface<T> left,
                         BinaryTreeInterface<T> right);
}
