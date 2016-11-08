package avlTree;

public class RyansAvlTree {

  Node root;

  public RyansAvlTree() {
    root = null;
  }

  public void insertNode(Comparable value) {
    Node newNode = new Node(value);
    if (root == null) {
      root = newNode;
    } else {
      Node focusNode = root;
      Node parent;
      while (true) {
        parent = focusNode;
        if (value.compareTo(focusNode.getData()) < 0) {
          focusNode = focusNode.leftChild;
          if (focusNode == null) {
            parent.leftChild = newNode;
            return;
          }
        } else {
          focusNode = focusNode.rightChild;
          if (focusNode == null) {
            parent.rightChild = newNode;
            return;
          }
        }
      }
    }
  }

  public void inOrderTraverseTree(Node focusNode){
    if(focusNode != null){
      inOrderTraverseTree(focusNode.leftChild);
      System.out.println(focusNode);
      inOrderTraverseTree(focusNode.rightChild);
      
    }
  }
}
