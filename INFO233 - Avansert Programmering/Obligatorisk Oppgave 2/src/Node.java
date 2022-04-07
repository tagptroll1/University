public class Node<E>{
    private E data;
    private Node<E> next;

    public Node(){
        this(null, null);
    }

    public Node(E info){
        this(info, null);
    }

    public Node(E info, Node<E> next){
        this.data = info;
        this.next = next;
    }

    public void changeData(E info){
        this.data = info;
    }

    public E getData(){
        return this.data;
    }

    public void changeNext(Node<E> next){
        this.next = next;
    }

    public Node<E> getNext(){
        return this.next;
    }

    public boolean hasNext(){
        if (this.next == null){
            return false;
        } else {
            return true;
        }
    }

}
