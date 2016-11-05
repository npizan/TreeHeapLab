/**
 * 
 */
package avlTree;

/**
 * @author grahamw0
 *
 */
public class Node implements Comparable<Node> {
  private Node parent;
  private Node leftChild;
  private Node rightChild;
  private Comparable data;



  /**
   * @param parent
   * @param leftChild
   * @param rightChild
   * @param data
   */
  public Node(Node parent, Node leftChild, Node rightChild, Comparable data) {
    this.parent = parent;
    this.leftChild = leftChild;
    this.rightChild = rightChild;
    this.data = data;
  }

  /**
   * @param data
   */
  public Node(Comparable data) {
    this.data = data;
  }

  /**
   * 
   */
  public Node() {
  }

  /**
   * @return the parent
   */
  public Node getParent() {
    return parent;
  }

  /**
   * @param parent the parent to set
   */
  public void setParent(Node parent) {
    this.parent = parent;
  }

  /**
   * @return the leftChild
   */
  public Node getLeftChild() {
    return leftChild;
  }

  /**
   * @param leftChild the leftChild to set
   */
  public void setLeftChild(Node leftChild) {
    this.leftChild = leftChild;
  }

  /**
   * @return the rightChild
   */
  public Node getRightChild() {
    return rightChild;
  }

  /**
   * @param rightChild the rightChild to set
   */
  public void setRightChild(Node rightChild) {
    this.rightChild = rightChild;
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

  @Override
  public int compareTo(Node o) {
    return data.compareTo(o.getData());
  }

}
