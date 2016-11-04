/**
 * 
 */
package heap;

import java.util.Stack;

/**
 * @author grahamw0
 *
 */
public class Heap implements MyHeap {
  private int size;
  private Node root;
  private Node tail;
  private int height;

  /*
   * (non-Javadoc)
   * 
   * @see heap.MyHeap#makeHeap(java.lang.Comparable)
   */
  @Override
  public Node makeHeap(Comparable value) {
    size = 1;
    height = 0;
    root = new Node(value);
    return root;
  }

  /*
   * (non-Javadoc)
   * 
   * @see heap.MyHeap#isEmpty()
   */
  @Override
  public boolean isEmpty() {
    return size == 0;
  }

  /*
   * (non-Javadoc)
   * 
   * @see heap.MyHeap#insert(java.lang.Comparable)
   */
  @Override
  public boolean insert(Comparable value) {
    Node newNode = new Node(value);
    if (root == null) {
      root = newNode;
    } else {

      Node parent = getTail();
      if (parent.getLeftChild() == null) {
        parent.setLeftChild(newNode);
      } else {
        parent.setRightChild(newNode);
      }
      siftUp(newNode);
    }


    return true;
  }

  /*
   * (non-Javadoc)
   * 
   * @see heap.MyHeap#deleteMin()
   */
  @Override
  public boolean deleteMin() {
    // TODO Auto-generated method stub
    return false;
  }

  /*
   * (non-Javadoc)
   * 
   * @see heap.MyHeap#decreaseKey(heap.Node, java.lang.Comparable)
   */
  @Override
  public boolean decreaseKey(Node key, Comparable updateValue) {
    // TODO Auto-generated method stub
    return false;
  }

  /*
   * (non-Javadoc)
   * 
   * @see heap.MyHeap#delete(heap.Node)
   */
  @Override
  public boolean delete(Node del) {
    // TODO Auto-generated method stub
    return false;
  }

  /*
   * (non-Javadoc)
   * 
   * @see heap.MyHeap#union(heap.MyHeap)
   */
  @Override
  public boolean union(MyHeap heap) {
    // TODO Auto-generated method stub
    return false;
  }

  /*
   * (non-Javadoc)
   * 
   * @see heap.MyHeap#findMin()
   */
  @Override
  public Comparable findMin() {
    if (root != null)
      return root.getData();
    else
      return null;
  }

  private boolean lastLevelFull() {
    return size == Math.pow(2, height);
  }

  /**
   * Returns parent of open space to add/remove from (remove?)
   * 
   * @return
   */
  private Node getTail() {
    String bin = Integer.toBinaryString(size + 1);
    bin = bin.substring(1, bin.length() - 1);

    Node current = root;
    for (int i = bin.length() - 1; i >= 0; i--) {
      if (bin.charAt(i) == '0') {
        current = current.getLeftChild();
      } else {
        current = current.getRightChild();
      }
    }
    return current;

  }

  private void siftUp(Node n) {
    Node current = n;
    while (current != root && current.getData().compareTo(current.getParent().getData()) <= 0) {
      Comparable temp = current.getData();
      current.setData(current.getParent().getData());
      current.getParent().setData(temp);
    }
  }

}
