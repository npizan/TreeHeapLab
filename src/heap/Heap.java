package heap;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Implementation of a min-heap.
 * 
 * @author Ryan Godfrey, Will Graham
 *
 */
public class Heap implements MyHeap {
  private int size;
  private Node root;

  /**
   * Method that stands in for a constructor.
   * 
   * @param value The data to be held by the first node in the new heap
   * @return The root of the new heap
   */
  @Override
  public Node makeHeap(Comparable value) {
    size = 1;
    root = new Node(value);
    return root;
  }

  /**
   * Checks if heap is empty (no nodes).
   * 
   * @return Whether the heap is empty or not
   */
  @Override
  public boolean isEmpty() {
    return size == 0;
  }

  /**
   * Inserts a node into the heap, then sifts up from the insert position.
   * 
   * @param value The data to be inserted into the heap
   * @return Whether the insertion is successful or not (it will always be)
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

  /**
   * Removes the minimum value (root) of the heap.
   * 
   * @return Whether the delete is successful or not (only fails if heap is empty)
   */
  @Override
  public boolean deleteMin() {
    if (root == null) {
      return false;
    }
    if (size == 1) {
      root = null;
      size--;
      return true;
    }
    Node last = getLast();
    swapData(root, last);

    // Delete the last child which now holds the old min value
    if (size % 2 == 0) { // If size is even, the last added child is a left child. Right if odd.
      last.getParent().setLeftChild(null); // Use the children to kill themselves
    } else {
      last.getParent().setRightChild(null);
    }
    size--;

    siftDown(root);

    // size--;
    return true;
  }

  /**
   * Decreases a given node's value to supplied value, if and only if that value is smaller than the
   * node's existing value.
   * 
   * @param key The node to be updated
   * @param updateValue The new value for the node
   * @return Whether the node's value is decreased or not
   */
  @Override
  public boolean decreaseKey(Node key, Comparable updateValue) {
    if (key.getData().compareTo(updateValue) > 0) {
      key.setData(updateValue);
      siftUp(key);
      return true;
    } else {
      return false;
    }
  }

  /**
   * Removes a given node from the heap.
   * 
   * @param del The node to be deleted
   * @return Whether the node was found and deleted or not
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

  /**
   * Unions (merges) the supplied heap into the current heap via repeated insertion.
   * 
   * @param heap The heap to union into the current heap
   * @return Whether the union was successful (always true)
   */
  @Override
  public boolean union(MyHeap heap) {
    while (!heap.isEmpty()) {
      insert(heap.findMin());
      heap.deleteMin();
    }
    return true;

  }

  /**
   * Returns the minimum value (root) of the heap, or null if empty.
   * 
   * @return The most minimum value in the heap, or null if the heap is empty
   */
  @Override
  public Comparable findMin() {
    if (root != null)
      return root.getData();
    else
      return null;
  }

  /**
   * Finds the parent of the next space available to insert. Path found via analyzing the binary
   * representation of the heap size.
   * 
   * @return The node which will be the parent of the next insert
   */
  private Node getInsertParent() {
    String bin = Integer.toBinaryString(size + 1);
    bin = bin.substring(1, bin.length() - 1);

    Node current = root;
    for (int i = 0; i < bin.length(); i++) {
      if (bin.charAt(i) == '0') {
        current = current.getLeftChild();
      } else {
        current = current.getRightChild();
      }
    }
    return current;

  }

  /**
   * Finds the last insert position; the right-most child of the last level. Used for swapping in
   * removal methods.
   * 
   * @return The node in the "last inserted" position
   */
  public Node getLast() {
    String bin = Integer.toBinaryString(size);
    bin = bin.substring(1);

    Node current = root;
    for (int i = 0; i < bin.length(); i++) {
      if (bin.charAt(i) == '0') {
        current = current.getLeftChild();
      } else {
        current = current.getRightChild();
      }
    }
    return current;
  }

  /**
   * Maintains the heap property in an upwards direction, starting at the passed node.
   * 
   * @param n The node at which to start the sifting up
   */
  private void siftUp(Node n) {
    Node current = n;
    while (current != root && current.getData().compareTo(current.getParent().getData()) <= 0) {
      swapData(current, current.getParent());
      current = current.getParent();
    }
  }

  /**
   * Moves a given node's value down tree to appropriate position so that the heap property is
   * maintained. Used in delete operations.
   * 
   * @param n Node at which to start the sifting
   */
  private void siftDown(Node n) {
    Node current = n;
    boolean continueSift = true;
    while (continueSift) {
      if (current.getLeftChild() == null && current.getRightChild() == null) { // No children
        continueSift = false;
      } else if (current.getLeftChild() != null && current.getRightChild() != null) { // Two child
        Node child;
        if (current.getLeftChild().getData().compareTo(current.getRightChild().getData()) <= 0)
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

  public void print() {
    Queue<Node> queue = new LinkedList<Node>();
    if (root == null)
      return;
    queue.clear();
    queue.add(root);
    while (!queue.isEmpty()) {
      Node node = queue.remove();
      System.out.print(node.getData() + " ");
      if (node.getLeftChild() != null)
        queue.add(node.getLeftChild());
      if (node.getRightChild() != null)
        queue.add(node.getRightChild());
    }
    System.out.println();

  }

}
