package avlTree;

import java.util.ArrayList;

/**
 * Implementation of a reference based AVL Tree.
 * 
 * @author Ryan Godfrey, Will Graham
 */
public class AvlTree {
  private AvlNode root;

  /**
   * Constructor for the empty tree.
   */
  public AvlTree() {
    root = null;
  }

  /**
   * Method to begin the recursive process of inserting.
   * 
   * @param data The data to be inserted into the tree
   */
  public void insert(Comparable data) {
    AvlNode node = new AvlNode(data);
    insertAVL(root, node);
  }

  /**
   * Recursive insert method. Compares a node in the tree to the new node to either set the new
   * node as a child of the current, or to recurse and move within the tree. The balancing method is
   * then called.
   * 
   * @param current The node currently being compared (should start w/ root)
   * @param insertNode The node to be inserted
   */
  private void insertAVL(AvlNode current, AvlNode insertNode) {
    if (current == null) {
      root = insertNode;
    } else {
      if (insertNode.getData().compareTo(current.getData()) < 0) {
        if (current.getLeft() == null) {
          current.setLeft(insertNode);
          insertNode.setParent(current);
          balance(current);
        } else {
          insertAVL(current.getLeft(), insertNode);
        }
      } else if (insertNode.getData().compareTo(current.getData()) > 0) {
        if (current.getRight() == null) {
          current.setRight(insertNode);
          insertNode.setParent(current);
          balance(current);
        } else {
          insertAVL(current.getRight(), insertNode);
        }
      }
    }
  }

  /**
   * User friendly method to begin the process of removing a data value from the tree. Since the
   * tree has no direct access to nodes, removing must initially take in a data value. Before trying
   * to delete, the tree is searched to ensure the data exists in the tree.
   * 
   * @param data The data to delete from the tree
   */
  public void remove(Comparable data) {
    AvlNode toRemove = search(root, data);
    if (toRemove != null)
      removeNode(toRemove);
  }

  /**
   * Recursive method to search for a data value in the tree. Initial call should supply the root as
   * the node.
   * 
   * @param node The current node in the tree against which the data is being compared
   * @param data The data to search for within the tree
   * @return The node in the tree containing the data, or null if the data is not in the tree
   */
  private AvlNode search(AvlNode node, Comparable data) {
    if (node == null) {
      return null;
    } else if (data.compareTo(node.getData()) < 0) {
      return search(node.getLeft(), data);
    } else if (data.compareTo(node.getData()) > 0) {
      return search(node.getRight(), data);
    } else {
      return node;
    }

  }

  /**
   * Removes the given node from the tree, then calls the balancing method. Does no searching, the
   * searched node should be passed in.
   * 
   * @param node The node to remove from the tree
   */
  private void removeNode(AvlNode node) {
    AvlNode node1;

    if (node.getLeft() == null || node.getRight() == null) {
      if (node.getParent() == null) { // Root is being deleted
        root = null;
        node = null;
        return;
      }
      node1 = node;
    } else {
      node1 = successor(node);
      node.setData(node1.getData());
    }

    AvlNode node2;
    if (node1.getLeft() != null) {
      node2 = node1.getLeft();
    } else {
      node2 = node1.getRight();
    }

    if (node2 != null) {
      node2.setParent(node1.getParent());
    }

    if (node1.getParent() == null) {
      root = node2;
    } else {
      if (node1 == node1.getParent().getLeft())
        node1.getParent().setLeft(node2);
      else
        node1.getParent().setRight(node2);
      balance(node1.getParent());
    }
    node1 = null;
  }

  /**
   * Recursively balances the tree, starting with the initial passed node.
   * 
   * @param node Current node being checked for balance issues
   */
  private void balance(AvlNode node) {
    setBalance(node);
    int balance = node.getBalance();

    if (balance == -2) {
      if (findNodeHeight(node.getLeft().getLeft()) >= findNodeHeight(node.getLeft().getRight())) {
        node = rotateRight(node);
      } else {
        node = rotateLeftRight(node);
      }
    } else if (balance == 2) {
      if (findNodeHeight(node.getRight().getRight()) >= findNodeHeight(node.getRight().getLeft())) {
        node = rotateLeft(node);
      } else {
        node = rotateRightLeft(node);
      }
    }

    if (node.getParent() != null) {
      balance(node.getParent());
    } else {
      this.root = node;
    }
  }

  /**
   * Rotates the given node left; part of maintaining balance.
   * 
   * @param node Node to rotate
   * @return The rotated node, used for recursive balancing
   */
  private AvlNode rotateLeft(AvlNode node) {
    AvlNode v = node.getRight();
    v.setParent(node.getParent());
    node.setRight(v.getLeft());

    if (node.getRight() != null)
      node.getRight().setParent(node);

    v.setLeft(node);
    node.setParent(v);

    if (v.getParent() != null) {
      if (v.getParent().getRight() == node) {
        v.getParent().setRight(v);
      } else if (v.getParent().getLeft() == node) {
        v.getParent().setLeft(v);
      }
    }
    setBalance(node);
    setBalance(v);

    return v;
  }

  /**
   * Rotates the given node right; part of maintaining balance.
   * 
   * @param node Node to rotate
   * @return The rotated node, used for recursive balancing
   */
  private AvlNode rotateRight(AvlNode node) {
    AvlNode v = node.getLeft();
    v.setParent(node.getParent());
    node.setLeft(v.getRight());

    if (node.getLeft() != null)
      node.getLeft().setParent(node);

    v.setRight(node);
    node.setParent(v);

    if (v.getParent() != null) {
      if (v.getParent().getRight() == node) {
        v.getParent().setRight(v);
      } else if (v.getParent().getLeft() == node) {
        v.getParent().setLeft(v);
      }
    }
    setBalance(node);
    setBalance(v);

    return v;
  }

  /**
   * Rotates node left then right; part of maintaining balance.
   * 
   * @param node Node to rotate
   * @return The rotated node, used for recursive balancing
   */
  private AvlNode rotateLeftRight(AvlNode node) {
    node.setLeft(rotateLeft(node.getLeft()));
    return rotateRight(node);
  }

  /**
   * Rotates node Right then left; part of maintaining balance.
   * 
   * @param node Node to rotate
   * @return The rotated node, used for recursive balancing
   */
  private AvlNode rotateRightLeft(AvlNode node) {
    node.setRight(rotateRight(node.getRight()));
    return rotateLeft(node);
  }

  /**
   * Find the successor node used for removal.
   * 
   * @param node The node whose successor is to be found
   * @return The successor of passed node
   */
  private AvlNode successor(AvlNode node) {
    if (node.getRight() != null) {
      AvlNode node1 = node.getRight();
      while (node1.getLeft() != null) {
        node1 = node1.getLeft();
      }
      return node1;
    } else {
      AvlNode node2 = node.getParent();
      while (node2 != null && node == node2.getRight()) {
        node = node2;
        node2 = node.getParent();
      }
      return node2;
    }
  }

  /**
   * Recursive method to find the height of a given node, used for determining balance.
   * 
   * @param node Initially, the node to find height for
   * @return The height of the current node
   */
  private int findNodeHeight(AvlNode node) {
    if (node == null)
      return -1;
    if (node.getLeft() == null && node.getRight() == null)
      return 0;
    if (node.getLeft() == null)
      return 1 + findNodeHeight(node.getRight());
    if (node.getRight() == null)
      return 1 + findNodeHeight(node.getLeft());
    return 1 + Math.max(findNodeHeight(node.getLeft()), findNodeHeight(node.getRight()));
  }

  /**
   * Sets the balance factor for the specified node.
   * 
   * @param node Node to set balance for
   */
  private void setBalance(AvlNode node) {
    node.setBalance(findNodeHeight(node.getRight()) - findNodeHeight(node.getLeft()));
  }

  /**
   * Prints the data contained within the tree via In-Order traversal.
   */
  public void inOrderPrint() {
    inOrderPrintRecur(root);
  }

  /**
   * Recursive component of the inOrderPrint() method.
   * 
   * @param node Current node (initial call should be on root)
   */
  private void inOrderPrintRecur(AvlNode node) {
    if (node == null)
      return;
    inOrderPrintRecur(node.getLeft());
    System.out.println(node);
    inOrderPrintRecur(node.getRight());
  }

  /**
   * Getter for the root.
   * 
   * @return The tree's root
   */
  public AvlNode getRoot() {
    return root;
  }

  /** NUMBERS 2-6 ON LAB INSTRUCTION SHEET **/

  /**
   * Searches the passed tree for the passed data, returning whether the data exists in the tree or
   * not.
   * 
   * @param tree The tree to search
   * @param data The data to search for
   * @return Whether the data exists in the tree or not
   */
  public static boolean search(AvlTree tree, Comparable data) {
    return tree.search(tree.root, data) != null;
  }

  /**
   * Inserts the passed data into the passed AVL Tree.
   * 
   * @param tree The tree to insert into
   * @param data The data to insert
   * @return The tree with the data inserted
   */
  public static AvlTree insert(AvlTree tree, Comparable data) {
    if (tree.search(tree.root, data) != null) // Tree already contains data
      return tree;
    tree.insert(data);
    return tree;
  }

  /**
   * Deletes the passed data from the passed AVL Tree.
   * 
   * @param tree The tree to delete from
   * @param data The data to delete
   * @return The tree with data deleted (if it exists in the tree- otherwise the tree remains
   *         unchanged)
   */
  public static AvlTree delete(AvlTree tree, Comparable data) {
    tree.remove(data);
    return tree;
  }

  /**
   * Parses the passed tree into an ArrayList of all contained values, via In-Order traversal.
   * 
   * @param tree The tree to convert into a list
   * @return The list with all values in In-Order format
   */
  public static ArrayList<Comparable> inOrder(AvlTree tree) {
    ArrayList<Comparable> list = new ArrayList<>();
    AvlTree.inOrderRecurs(list, tree.root);
    return list;
  }

  /**
   * Recursive component of converting the tree into a list.
   * 
   * @param list The list data are to be added to
   * @param node The current node being looked at (initial call should be on root)
   */
  private static void inOrderRecurs(ArrayList<Comparable> list, AvlNode node) {
    if (node == null)
      return;
    inOrderRecurs(list, node.getLeft());
    list.add(node.getData());
    inOrderRecurs(list, node.getRight());
  }

  /**
   * Finds the amount of values in the passed tree that are between x0 and x1 (inclusive).
   * 
   * @param tree Tree to look within
   * @param x0 The lower bound of values
   * @param x1 The upper bound of values
   * @return The number of values in the tree in range of x0 to x1 (inclusive)
   */
  public static int count(AvlTree tree, Comparable x0, Comparable x1) {
    if (x0.compareTo(x1) > 0) // If lower bound is larger than upper
      return 0;
    return countRecur(tree.root, x0, x1);
  }

  /**
   * Recursive component of count().
   * 
   * @param node Current node being looked at (initial call should be on root)
   * @param x0 Lower bound
   * @param x1 Upper bound
   * @return Running total of values between x0 and x1 (inclusive)
   */
  private static int countRecur(AvlNode node, Comparable x0, Comparable x1) {
    if (node == null)
      return 0;
    if ((node.getData().compareTo(x0) > 0 && node.getData().compareTo(x1) < 0)
        || node.getData().compareTo(x0) == 0 || node.getData().compareTo(x1) == 0) {
      return 1 + countRecur(node.getLeft(), x0, x1) + countRecur(node.getRight(), x0, x1);
    } else if (node.getData().compareTo(x0) < 0) {
      return countRecur(node.getRight(), x0, x1);
    } else {
      return countRecur(node.getLeft(), x0, x1);
    }
  }
}
