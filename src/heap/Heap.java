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

  /*
   * (non-Javadoc)
   * 
   * @see heap.MyHeap#makeHeap(java.lang.Comparable)
   */
  @Override
  public Node makeHeap(Comparable value) {
    size = 1;
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
      Node parent = getInsertParent();
      if (parent.getLeftChild() == null) {
        parent.setLeftChild(newNode);
      } else {
        parent.setRightChild(newNode);
      }
      newNode.setParent(parent);
      siftUp(newNode);
    }
    size++;
    return true;
  }

  /*
   * (non-Javadoc)
   * 
   * @see heap.MyHeap#deleteMin()
   */
  @Override
  public boolean deleteMin() {
    if (root == null) {
      return false;
    }
    Node last = getLast();
    swapData(root, last);

    // Delete the last child which now holds the old min value
    if (size % 2 == 0) { // If size is even, the last added child is a left child. Right if odd.
      last.getParent().setLeftChild(null); // Use the children to kill themselves
    } else {
      last.getParent().setRightChild(null);
    }

    siftDown(root);

    size--;
    return true;
  }

  /*
   * (non-Javadoc)
   * 
   * @see heap.MyHeap#decreaseKey(heap.Node, java.lang.Comparable)
   */
  @Override
  public boolean decreaseKey(Node key, Comparable updateValue) {
    if(key.getData().compareTo(updateValue) > 0) {
      key.setData(updateValue);
      siftUp(key);
      return true;
    }
    else {
      return false;
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see heap.MyHeap#delete(heap.Node)
   */
  @Override
  public boolean delete(Node del) {
    if (root == null)
      return false;

    Node last = getLast();
    swapData(del, last);
    if (size % 2 == 0) { // If size is even, the last added child is a left child. Right if odd.
      last.getParent().setLeftChild(null); // Use the children to kill themselves
    } else {
      last.getParent().setRightChild(null);
    }

    siftDown(del);
    size--;
    return true;
  }

  /*
   * (non-Javadoc)
   * 
   * @see heap.MyHeap#union(heap.MyHeap)
   */
  @Override
  public boolean union(MyHeap heap) {
    while (!heap.isEmpty()) {
      insert(heap.findMin());
      heap.deleteMin();
    }
    return true;
    
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

  /**
   * Returns parent of open space to add/remove from (remove?)
   * 
   * @return
   */
  private Node getInsertParent() {
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

  private Node getLast() {
    String bin = Integer.toBinaryString(size);
    bin = bin.substring(1);

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
      swapData(current, current.getParent());
      current = current.getParent();
    }
  }

  private void siftDown(Node n) {
    Node current = n;
    boolean continueSift = true;
    while (continueSift) {
      if (current.getLeftChild() == null && current.getRightChild() == null) { // No children
        continueSift = false;
      } else if (current.getLeftChild() != null && current.getRightChild() != null) { // Two child
        Node child;
        if (current.getLeftChild().getData().compareTo(current.getLeftChild().getData()) <= 0)
          child = current.getLeftChild();
        else
          child = current.getRightChild();

        if (current.getData().compareTo(child.getData()) > 0) {
          swapData(current, child);
          current = child;
        } else {
          continueSift = false;
        }


      } else { // One child
        Node child;
        if (current.getLeftChild() != null)
          child = current.getLeftChild();
        else
          child = current.getRightChild();

        if (current.getData().compareTo(child.getData()) > 0) {
          swapData(current, child);
          current = child;
        } else {
          continueSift = false;
        }
      }
    }

  }

  /**
   * Swaps the data of two Nodes.
   * 
   * @param a first node to swap
   * @param b second node to swap
   */
  private void swapData(Node a, Node b) {
    Comparable temp = a.getData();
    a.setData(b.getData());
    b.setData(temp);
  }

}
