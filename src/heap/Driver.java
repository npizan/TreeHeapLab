package heap;

import java.util.Random;

public class Driver {

  public static void main(String[] args) {
    Random rand = new Random();
    
    Heap heap = new Heap();
    heap.makeHeap(rand.nextInt(200));
    Heap heap2 = new Heap();
    heap2.makeHeap(rand.nextInt(200));
    for(int i = 0; i < 9; i++) {
      heap.insert(rand.nextInt(200));
      heap2.insert(rand.nextInt(200));
    }
    heap.print();
    System.out.println();
    heap2.print();
    System.out.println();
    
    heap.union(heap2);
    heap.print();
    
  }

}
