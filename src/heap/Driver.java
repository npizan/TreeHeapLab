package heap;

import java.util.Random;

/**
 * Testing for the Heap Implementation. Unable to test any method that requires a Node as a
 * parameter, since the Heap has no direct access.  
 * 
 * @author Ryan Godfrey, Will Graham
 *
 */
public class Driver {

  public static void main(String[] args) {
    Random rand = new Random(120);

    Heap heap = new Heap();
    heap.makeHeap(rand.nextInt(200));
    Heap heap2 = new Heap();
    heap2.makeHeap(rand.nextInt(200));
    for (int i = 0; i < 9; i++) {
      heap.insert(rand.nextInt(200));
      heap2.insert(rand.nextInt(200));
    }
    heap.deleteMin();

    heap.union(heap2);
    heap.print();

  }

}
