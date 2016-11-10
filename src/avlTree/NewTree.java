package avlTree;

/**
 * @author grahamw0
 *
 */
public class NewTree {
  private NewNode root;

  public NewTree() {
    root = null;
  }

  public boolean isEmpty() {
    return root == null;
  }

  private int height(NewNode node) {
    if (node == null)
      return -1;
    else
      return node.getHeight();
  }

  private int max(int left, int right) {
    if (left > right)
      return left;
    else
      return right;
  }

  /**
   * Starter method for the recursive version of the insert function.
   * 
   * @param data
   */
  public void insert(Comparable data) {
    root = insert(data, root);
  }

  private NewNode insert(Comparable data, NewNode node) {
    if (node == null)
      node = new NewNode(data);
    else if (data.compareTo(node.getData()) < 0) {
      node.setLeft(insert(data, node.getLeft()));
      if (height(node.getLeft()) - height(node.getRight()) == 2) {
        if (data.compareTo(node.getLeft().getData()) < 0)
          node = rotateWithLeftChild(node);
        else
          node = doubleWithLeftChild(node);
      }
    } else if (data.compareTo(node.getData()) > 0) {
      node.setRight(insert(data, node.getRight()));
      if (height(node.getRight()) - height(node.getLeft()) == 2) {
        if (data.compareTo(node.getRight().getData()) > 0)
          node = rotateWithRightChild(node);
        else
          node = doubleWithRightChild(node);
      }
    }
    node.setHeight(max(height(node.getLeft()), height(node.getRight())) + 1);
    return node;
  }

  private NewNode rotateWithLeftChild(NewNode root) {
    NewNode newNode = root.getLeft();
    root.setLeft(newNode.getRight());
    newNode.setRight(root);
    root.setHeight(max(height(root.getLeft()), height(root.getRight())) + 1);
    newNode.setHeight(max(height(newNode.getLeft()), root.getHeight()) + 1);
    return newNode;
  }

  private NewNode rotateWithRightChild(NewNode root) {
    NewNode newNode = root.getRight();
    root.setRight(newNode.getLeft());
    newNode.setLeft(root);
    root.setHeight(max(height(root.getLeft()), height(root.getRight())) + 1);
    newNode.setHeight(max(height(newNode.getRight()), root.getHeight()) + 1);
    return newNode;
  }

  private NewNode doubleWithLeftChild(NewNode node) {
    node.setLeft(rotateWithRightChild(node.getLeft()));
    return rotateWithLeftChild(node);
  }

  private NewNode doubleWithRightChild(NewNode node) {
    node.setRight(rotateWithLeftChild(node.getRight()));
    return rotateWithRightChild(node);
  }
  
  public boolean search(Comparable data) {
    NewNode current = root;
    boolean found = false;
    while(current != null && !found) {
      if(data.compareTo(current.getData()) < 0) {
        current = current.getLeft();
      }
      else if (data.compareTo(current.getData()) > 0) {
        current = current.getRight();
      }
      else {
        found = true;
      }
    }
    
    return found;
  }
  
  public void printInOrder(NewNode node) {
    if(node != null) {
      printInOrder(node.getLeft());
      System.out.print(node.getData() + " ");
      printInOrder(node.getRight());
    }
  }
  
  public NewNode getRoot() {
    return root;
  }

}
