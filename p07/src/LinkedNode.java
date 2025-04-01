/**
 * Instantiable class representing a single node in a doubly-linked list of LakeRecords.
 */
public class LinkedNode {

  /**
   * The LakeRecord stored in this node
   */
  private LakeRecord data;

  /**
   * A reference to the previous node in this linked list
   */
  private LinkedNode prev;

  /**
   * A reference to the next node in this linked list
   */
  private LinkedNode next;

  /**
   * Creates a new LinkedNode with the given data, previous and next nodes
   * 
   * @param data the LakeRecord to be contained in this node
   * @param prev a reference to the previous node in this list (may be null)
   * @param next a reference to the next node in this list (may be null)
   */
  public LinkedNode(LakeRecord data, LinkedNode prev, LinkedNode next) {
    // TODO
  }

  /**
   * Creates a new LinkedNode with the given data which is not currently linked to any other nodes
   * 
   * @param data the data to be contained in this node
   */
  public LinkedNode(LakeRecord data) {
    // TODO
  }

  /**
   * Accesses the LakeRecord stored in this node
   * 
   * @return the LakeRecord stored in this node
   */
  public LakeRecord getLakeRecord() {
    return null; // TODO
  }

  /**
   * Accesses the previous node in the list
   * 
   * @return a reference to the previous node in this list (may be null)
   */
  public LinkedNode getPrev() {
    return null; // TODO
  }

  /**
   * Updates the previous node for this node
   * 
   * @param newPrev the new previous node in this list (may be null)
   */
  public void setPrev(LinkedNode newPrev) {
    // TODO
  }

  /**
   * Accesses the next node in the list
   * 
   * @return a reference to the next node in this list (may be null)
   */
  public LinkedNode getNext() {
    return null; // TODO
  }

  /**
   * Updates the next node for this node
   * 
   * @param newNext the new next node in this list (may be null)
   */
  public void setNext(LinkedNode newNext) {
    // TODO
  }
}
