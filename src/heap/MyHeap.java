package heap;


public interface MyHeap {
    public Node makeHeap(Comparable value);
    public boolean isEmpty();
    public boolean insert(Comparable value);
    public boolean deleteMin();
    public boolean decreaseKey(Node key, Comparable updateValue);
    public boolean delete(Node del);
    public boolean union(MyHeap heap);
    public Comparable findMin();
}