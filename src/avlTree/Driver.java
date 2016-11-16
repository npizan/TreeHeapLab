/**
 * 
 */
package avlTree;

/**
 * @author grahamw0
 *
 */
public class Driver {

  /**
   * @param args
   */
  public static void main(String[] args) {
    
    AvlTree tree = new AvlTree();
    AvlTree.insert(tree, 666);
    AvlTree.insert(tree, 5);
    AvlTree.insert(tree, 240);
    
    AvlTree.delete(tree, 666);
    
    for(Comparable c : AvlTree.inOrder(tree)) {
      System.out.println(c);
    }
    
  }

}
