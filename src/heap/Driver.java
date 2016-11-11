package heap;

public class Driver {

  public static void main(String[] args) {
    Heap heap1 = new Heap();
    heap1.makeHeap(6);
    heap1.insert(12);
    heap1.insert(7);
    heap1.insert(8);
    heap1.insert(10);
    heap1.insert(666);
    //heap1.print();
    /*heap1.insert(2389);
    heap1.insert(74);
    heap1.insert(23);
    heap1.print();*/
    
    Heap heap2 = new Heap();
    heap2.makeHeap(2389);
    heap2.insert(74);
    heap2.insert(23);
    //heap2.print();
    
    heap1.union(heap2);
    heap1.print();
  }

}
