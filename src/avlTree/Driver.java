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
    
    Node root = null;
    root = avl.insertNode(root, 10);
    root = avl.insertNode(root, 666);
    root = avl.insertNode(root, 55);
    root = avl.insertNode(root, 38);
    
    System.out.println("This is the first tree. ");
    System.out.println();
    avl.inOrderTraverseTree(root);
    
    root = avl.insertNode(root, 2);
    root = avl.insertNode(root, 420);
    root = avl.insertNode(root, 32);
    root = avl.insertNode(root, 18);
    root = avl.insertNode(root, 4);
    root = avl.insertNode(root, 22);
    System.out.println();
    System.out.println("This is the updated tree. ");
    avl.inOrderTraverseTree(root);
    
    System.out.println();
    System.out.println("");
    
    System.out.println(avl.search(root, 1));
    
  }

}
