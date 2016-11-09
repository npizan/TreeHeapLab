package avlTree;

public class RyansAvlTree {
  
  public RyansAvlTree() {

  }

  public Node insertNode(Node root, Comparable value) {
    if (root == null) { // If the tree is empty
      Node newNode = new Node(value);
      root = newNode;
      return root;
    } 
    
    // if the root is less than the new value return a -1 and move right
    if (root.getData().compareTo(value) < 0) { 
      root.rightChild = insertNode(root.rightChild, value); // go right 
    } else {
      root.leftChild = insertNode(root.leftChild, value); // go left
    }
    
    // Check the balance of the tree
    int balance = balance(root.leftChild, root.rightChild);
    if(balance > 1){ // if the tree is unbalanced
      if(height(root.leftChild.leftChild) >= height(root.leftChild.rightChild)){ // This is the LL
        root = rightRotate(root);
      } else {
        root.leftChild = leftRotate(root.leftChild); // This is the LR
        root = rightRotate(root);
      }
    } else if(balance < -1) {
      if(height(root.rightChild.rightChild) >= height(root.rightChild.leftChild)){ //This is the RR
        root = leftRotate(root);
      } else {
        root.rightChild = rightRotate(root.rightChild); // This is the RL
        root = leftRotate(root);
      }
    }
    else { // keep track of the height and size
      root.height = setHeight(root); 
      root.size = setSize(root);
    }
    return root;
  }
  
  /**
   * 
   * @param focusNode
   */
  private Node leftRotate(Node root){
    Node newRoot = root.rightChild;
    root.rightChild = root.rightChild.leftChild;
    newRoot.leftChild = root;
    root.height = setHeight(root);
    root.size = setSize(root);
    newRoot.height = setHeight(newRoot);
    newRoot.size = setSize(newRoot);
    return newRoot;
  }
  
  /**
   * 
   * @param focusNode
   */
  private Node rightRotate(Node root){
    Node newRoot = root.leftChild;
    root.leftChild = root.leftChild.rightChild;
    newRoot.rightChild = root;
    root.height = setHeight(root);
    root.size = setSize(root);
    newRoot.height = setHeight(newRoot);
    newRoot.size = setSize(newRoot);
    return newRoot;
  }
  
  /**
   * 
   */
  public boolean search(Node root, Comparable value) {
    Node currentNode = root;
    boolean found = false;
    while(currentNode != null && !found){
      if(value.compareTo(currentNode.getData()) < 0){
        currentNode = currentNode.leftChild;
      } else if(value.compareTo(currentNode.getData()) > 0){
        currentNode = currentNode.rightChild;
      } else {
        found = true;
      }
    }
    return found;
  }
  
  
  /**
   * 
   * @param currentNode
   */
  public void inOrderTraverseTree(Node currentNode) {
    if (currentNode != null) {
      inOrderTraverseTree(currentNode.leftChild);
      System.out.println(currentNode);
      inOrderTraverseTree(currentNode.rightChild);
    }
  }
  
  /**
   * 
   * @param rootLeft
   * @param rootRight
   * @return
   */
  private int balance(Node rootLeft, Node rootRight){
    return height(rootLeft) - height(rootRight);
  }
  
  /**
   * 
   * @param root
   * @return
   */
  private int setHeight(Node root){
    if(root == null){
      return 0;
    }
    return 1 + Math.max((root.leftChild != null ? root.leftChild.size : 0), (root.rightChild != 
        null ? root.rightChild.size : 0));
  }
  
  /**
   * 
   * @param root
   * @return
   */
  private int setSize(Node root){
    if(root == null){
      return 0;
    }
    return 1 + Math.max((root.leftChild != null ? root.leftChild.size : 0), (root.rightChild != 
        null ? root.rightChild.size : 0));
  }
  
  /**
   * 
   * @param root
   * @return
   */
  private int height(Node root){
    if(root == null){
      return 0;
    } else {
      return root.getHeight();
    }
  }
  
  
  
}
