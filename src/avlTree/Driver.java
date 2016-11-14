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

    /*RyansAvlTree avl = new RyansAvlTree();
    
    Node root = null;
    root = avl.insertNode(root, 10);
    root = avl.insertNode(root, 666);
    root = avl.insertNode(root, 55);
    root = avl.insertNode(root, 38);
    
    System.out.println("This is the first tree. ");
    System.out.println();
    avl.inOrderTraverseTree(root);
    System.out.println("The new root of the tree is " + root.getData() + 
        " + with a height of " + root.height);
    
    root = avl.insertNode(root, 2);
    root = avl.insertNode(root, 420);
    root = avl.insertNode(root, 32);
    root = avl.insertNode(root, 18);
    root = avl.insertNode(root, 4);
    root = avl.insertNode(root, 22);
    System.out.println();
    System.out.println("This is the updated tree. ");
    avl.inOrderTraverseTree(root);
    System.out.println("The new root of the tree is " + root.getData() + 
        " + with a height of " + root.height);
    //System.out.println(root.leftChild.rightChild.rightChild.getData());
    
    System.out.println();
    System.out.println("");
    
    System.out.println(avl.search(root, 420));*/
    
    /*NewTree test = new NewTree();
    test.insert(666);
    test.insert(4);
    test.insert(10);
    test.insert(3);
    test.insert(2);
    test.insert(667);
    test.insert(20);
    test.insert(100);
    test.insert(101);
    
    //test.printInOrder();
    System.out.println(test.search(20).getData());
    //test.printInOrder(test.getRoot());
    //System.out.println(test.search(-1));*/
    
    AvlTree test = new AvlTree();
    test.insert(666);
    test.insert(4);
    test.insert(10);
    test.insert(3);
    test.insert(2);
    test.insert(667);
    test.insert(20);
    test.insert(100);
    test.insert(101);
    test.remove(20);
    test.inOrderPrint();
    
  }

}
