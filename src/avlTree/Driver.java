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
    // TODO Auto-generated method stub

    RyansAvlTree avl = new RyansAvlTree();
    
    avl.insertNode(10);
    avl.insertNode(38);
    avl.insertNode(55);
    avl.insertNode(666);
    avl.insertNode(2);
    avl.insertNode(12);
    
    avl.inOrderTraverseTree(avl.root);
  }

}
