package heap;

public class Driver {

  public static void main(String[] args) {
    Heap heap = new Heap();
    heap.makeHeap(6);
    heap.insert(12);
    heap.insert(7);
    heap.insert(8);
    heap.insert(10);
    heap.insert(666);
    heap.print();

  }

}
