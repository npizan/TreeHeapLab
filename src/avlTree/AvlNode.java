package avlTree;

/**
 * Node class for the AVL Tree. Tracks children, parent, and balance.
 * 
 * @author Ryan Godfrey, William Graham
 *
 */
public class AvlNode {
  private AvlNode left, right, parent;
  private Comparable data;
  private int balance;

  /**
   * Constructor.
   * 
   * @param c The data for the first node to hold
   */
  public AvlNode(Comparable c) {
    parent = left = right = null;
    balance = 0;
    data = c;
  }

  /**
   * Getter for the left child.
   * 
   * @return the left child
   */
  public AvlNode getLeft() {
    return left;
  }

  /**
   * Setter for the left child.
   * 
   * @param left the left child to set
   */
  public void setLeft(AvlNode left) {
    this.left = left;
  }

  /**
   * Getter for the right child.
   * 
   * @return the right child
   */
  public AvlNode getRight() {
    return right;
  }

  /**
   * Setter for the right child.
   * 
   * @param right the right child to set
   */
  public void setRight(AvlNode right) {
    this.right = right;
  }

  /**
   * Getter for the parent.
   * 
   * @return the parent
   */
  public AvlNode getParent() {
    return parent;
  }

  /**
   * Setter for the parent.
   * 
   * @param parent the parent to set
   */
  public void setParent(AvlNode parent) {
    this.parent = parent;
  }

  /**
   * Getter for the data contained in the node.
   * 
   * @return the data
   */
  public Comparable getData() {
    return data;
  }

  /**
   * Setter for the node's data.
   * 
   * @param data the data to set
   */
  public void setData(Comparable data) {
    this.data = data;
  }

  /**
   * Getter for the balance of the node's subtree.
   * 
   * @return the balance of the node (its subtree)
   */
  public int getBalance() {
    return balance;
  }

  /**
   * Setter for the node's balance
   * 
   * @param balance the balance to set
   */
  public void setBalance(int balance) {
    this.balance = balance;
  }

  /**
   * Formats the node as a String of its data.
   * 
   * @return String representation of the node's data
   */
  public String toString() {
    return data.toString();
  }

}
