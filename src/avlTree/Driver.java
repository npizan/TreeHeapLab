package avlTree;

import java.util.Random;

/**
 * Driver for AVL Tree.
 * 
 * @author Ryan Godfrey, William Graham
 *
 */
public class Driver {

  /**
   * @param args
   */
  public static void main(String[] args) {
    Random rand = new Random(666);
    
    AvlTree tree = new AvlTree();
    for(int i = 0; i < 10; i++) {
      tree.insert(rand.nextInt(100));
    }
    
    for(Comparable c : AvlTree.inOrder(tree)) {
      System.out.println(c);
    }
    
  }

}
