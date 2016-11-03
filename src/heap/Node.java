package heap;


public class Node {
  private Node left_child;
  private Node right_child;
  private Node parent;
  private Comparable data;


  public Node() {
    left_child = null;
    right_child = null;
    data = null;
    parent = null;
  }

  public Node(Comparable data) {
    this.data = data;
    left_child = null;
    right_child = null;
    parent = null;
  }

  public Node(Comparable data, Node left_child, Node right_child, Node parent) {
    this.data = data;
    this.left_child = left_child;
    this.right_child = right_child;
    this.parent = parent;
  }

  public Node getLeftChild() {
    return left_child;
  }

  public Node getRightChild() {
    return right_child;
  }

  public Comparable getData() {
    return data;
  }

  public Node getParent() {
    return parent;
  }

  public void setLeftChild(Node left_child) {
    this.left_child = left_child;
  }

  public void setRightChild(Node right_child) {
    this.right_child = right_child;
  }

  public void setData(Comparable data) {
    this.data = data;
  }

  public void setParent(Node parent) {
    this.parent = parent;
  }
}
