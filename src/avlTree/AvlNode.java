/**
 * 
 */
package avlTree;

/**
 * @author grahamw0
 *
 */
public class AvlNode {
  private AvlNode left, right, parent;
  private Comparable data;
  private int balance;
  
  public AvlNode(Comparable c) {
    parent = left = right = null;
    balance = 0;
    data = c;
  }

  /**
   * @return the left
   */
  public AvlNode getLeft() {
    return left;
  }

  /**
   * @param left the left to set
   */
  public void setLeft(AvlNode left) {
    this.left = left;
  }

  /**
   * @return the right
   */
  public AvlNode getRight() {
    return right;
  }

  /**
   * @param right the right to set
   */
  public void setRight(AvlNode right) {
    this.right = right;
  }

  /**
   * @return the parent
   */
  public AvlNode getParent() {
    return parent;
  }

  /**
   * @param parent the parent to set
   */
  public void setParent(AvlNode parent) {
    this.parent = parent;
  }

  /**
   * @return the data
   */
  public Comparable getData() {
    return data;
  }

  /**
   * @param data the data to set
   */
  public void setData(Comparable data) {
    this.data = data;
  }

  /**
   * @return the balance
   */
  public int getBalance() {
    return balance;
  }

  /**
   * @param balance the balance to set
   */
  public void setBalance(int balance) {
    this.balance = balance;
  }
  
  public String toString() {
    return data.toString();
  }
  
}
