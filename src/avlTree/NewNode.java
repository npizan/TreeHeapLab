package avlTree;

/**
 * @author grahamw0
 *
 */
public class NewNode implements Comparable<NewNode>{
  private NewNode left, right;
  Comparable data;
  int height;
  
  public NewNode() {
    left = right = null;
    data = null;
    height = 0;
  }
  
  public NewNode(Comparable data) {
    left = right = null;
    this.data = data;
    height = 0;
  }
  
  

  /**
   * @return the left
   */
  public NewNode getLeft() {
    return left;
  }

  /**
   * @param left the left to set
   */
  public void setLeft(NewNode left) {
    this.left = left;
  }

  /**
   * @return the right
   */
  public NewNode getRight() {
    return right;
  }

  /**
   * @param right the right to set
   */
  public void setRight(NewNode right) {
    this.right = right;
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
   * @return the height
   */
  public int getHeight() {
    return height;
  }

  /**
   * @param height the height to set
   */
  public void setHeight(int height) {
    this.height = height;
  }

  /* (non-Javadoc)
   * @see java.lang.Comparable#compareTo(java.lang.Object)
   */
  @Override
  public int compareTo(NewNode o) {
    return data.compareTo(o.getData());
  }
  
  
  
}
