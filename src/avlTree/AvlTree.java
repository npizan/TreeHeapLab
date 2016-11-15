/**
 * 
 */
package avlTree;

import java.util.ArrayList;

/**
 * @author grahamw0
 *
 */
public class AvlTree {
  private AvlNode root;

  public AvlTree() {
    root = null;
  }

  /**
   * Starts the recursive insert method
   * 
   * @param data
   */
  public void insert(Comparable data) {
    AvlNode node = new AvlNode(data);
    insertAVL(root, node);
  }

  /**
   * Recursive insert method FINISH THIS AND REMOVE
   * 
   * @param node1 The node currently being compared (should start w/ root)
   * @param node2 The node to be inserted
   */
  private void insertAVL(AvlNode node1, AvlNode node2) {
    if (node1 == null) {
      root = node2;
    } else {
      if (node2.getData().compareTo(node1.getData()) < 0) {
        if (node1.getLeft() == null) {
          node1.setLeft(node2);
          node2.setParent(node1);
          recursiveBalance(node1);
        } else {
          insertAVL(node1.getLeft(), node2);
        }
      } else if (node2.getData().compareTo(node1.getData()) > 0) {
        if (node1.getRight() == null) {
          node1.setRight(node2);
          node2.setParent(node1);
          recursiveBalance(node1);
        } else {
          insertAVL(node1.getRight(), node2);
        }
      }
    }
  }

  public void remove(Comparable data) {
    AvlNode toRemove = search(root, data);
    if (toRemove != null)
      removeNode(toRemove);
  }

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

  private void removeNode(AvlNode node) {
    AvlNode r;

    if (node.getLeft() == null || node.getRight() == null) {
      if (node.getParent() == null) { // Root is being deleted
        root = null;
        node = null;
        return;
      }
      r = node;
    } else {
      r = successor(node);
      node.setData(r.getData());
    }

    AvlNode p;
    if (r.getLeft() != null) {
      p = r.getLeft();
    } else {
      p = r.getRight();
    }

    if (p != null) {
      p.setParent(r.getParent());
    }

    if (r.getParent() == null) {
      root = p;
    } else {
      if (r == r.getParent().getLeft())
        r.getParent().setLeft(p);
      else
        r.getParent().setRight(p);
      recursiveBalance(r.getParent());
    }
    r = null;
  }

  private void recursiveBalance(AvlNode node) {
    setBalance(node);
    int balance = node.getBalance();

    if (balance == -2) {
      if (height(node.getLeft().getLeft()) >= height(node.getLeft().getRight())) {
        node = rotateRight(node);
      } else {
        node = doubleRotateLeftRight(node);
      }
    } else if (balance == 2) {
      if (height(node.getRight().getRight()) >= height(node.getRight().getLeft())) {
        node = rotateLeft(node);
      } else {
        node = doubleRotateRightLeft(node);
      }
    }

    if (node.getParent() != null) {
      recursiveBalance(node.getParent());
    } else {
      this.root = node;
      // System.out.println("Balance Finish"); // TODO: Remove test statement
    }
  }

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

  private AvlNode doubleRotateLeftRight(AvlNode node) {
    node.setLeft(rotateLeft(node.getLeft()));
    return rotateRight(node);
  }

  private AvlNode doubleRotateRightLeft(AvlNode node) {
    node.setRight(rotateRight(node.getRight()));
    return rotateLeft(node);
  }



  /****** Helper Functions ***********/
  private AvlNode successor(AvlNode q) {
    if (q.getRight() != null) {
      AvlNode r = q.getRight();
      while (r.getLeft() != null) {
        r = r.getLeft();
      }
      return r;
    } else {
      AvlNode p = q.getParent();
      while (p != null && q == p.getRight()) {
        q = p;
        p = q.getParent();
      }
      return p;
    }
  }

  private int height(AvlNode node) {
    if (node == null)
      return -1;
    if (node.getLeft() == null && node.getRight() == null)
      return 0;
    if (node.getLeft() == null)
      return 1 + height(node.getRight());
    if (node.getRight() == null)
      return 1 + height(node.getLeft());
    return 1 + Math.max(height(node.getLeft()), height(node.getRight()));
  }

  private void setBalance(AvlNode node) {
    node.setBalance(height(node.getRight()) - height(node.getLeft()));
  }

  public void inOrderPrint() {
    inOrderPrintRecur(root);
  }

  private void inOrderPrintRecur(AvlNode node) {
    if (node == null)
      return;
    inOrderPrintRecur(node.getLeft());
    System.out.println(node);
    inOrderPrintRecur(node.getRight());
  }

  public AvlNode getRoot() {
    return root;
  }

  /** WEIRD FUNCTIONS FROM INSTRUCTIONS **/
  public boolean search(AvlTree tree, Comparable data) {
    return tree.search(tree.root, data) != null;
  }

  public AvlTree insert(AvlTree tree, Comparable data) {
    if (tree.search(tree.root, data) != null) // Tree already contains data
      return tree;
    tree.insert(data);
    return tree;
  }

  public AvlTree delete(AvlTree tree, Comparable data) {
    tree.remove(data);
    return tree;
  }

  public ArrayList<Comparable> inOrder(AvlTree tree) {
    ArrayList<Comparable> list = new ArrayList<>();
    inOrderRecurs(list, tree.root);
    return list;
  }

  private void inOrderRecurs(ArrayList<Comparable> list, AvlNode node) {
    if (node == null)
      return;
    inOrderRecurs(list, node.getLeft());
    list.add(node.getData());
    inOrderRecurs(list, node.getRight());
  }

  /*
   * public int count(AvlTree tree, Comparable x0, Comparable x1) { int count = 0;
   * ArrayList<Comparable> list = tree.inOrder(tree); for(Comparable c : list) { if(c.compareTo(x0)
   * > 0 && c.compareTo(x1) < 0) count++; }
   * 
   * return count; }
   */

  public int count(AvlTree tree, Comparable x0, Comparable x1) {
    return countRecur(tree.root, x0, x1);
  }

  private int countRecur(AvlNode node, Comparable x0, Comparable x1) {
    if (node == null)
      return 0;
    if (node.getData().compareTo(x0) > 0 && node.getData().compareTo(x1) < 0) {
      return 1 + countRecur(node.getLeft(), x0, x1) + countRecur(node.getRight(), x0, x1);
    } else if (node.getData().compareTo(x0) < 0) {
      return countRecur(node.getRight(), x0, x1);
    } else {
      return countRecur(node.getLeft(), x0, x1);
    }
  }

}
