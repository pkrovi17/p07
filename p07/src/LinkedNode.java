//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Linked Node helper for linked list
// Course:   CS 300 Spring 2025
//
// Author:   Pranav Krovi
// Email:    pkrovi@wisc.edu
// Lecturer: Mouna Kacem
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
// 
// Partner Name:    none
// Partner Email:   none
// Partner Lecturer's Name: none
// 
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   x Write-up states that pair programming is allowed for this assignment.
//   x We have both read and understand the course Pair Programming Policy.
//   x We have registered our team prior to the team registration deadline.
//
//////////////////////// ASSISTANCE/HELP CITATIONS ////////////////////////////
//
// Persons:         Mouna Kacem 
//                  - lecture slides showcasing the use of linked lists
//                  - in class exercises using linked lists
//                  - Also showed us the LinkedNode class in the lecture
//                  - provided the LinkedNode class as a reference
// Online Sources:  Programiz
//                  - https://www.programiz.com/dsa/linked-list
//
///////////////////////////////////////////////////////////////////////////////

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
    // Initialize the data, previous and next references
    // to the given values
    this.data = data;
    this.prev = prev;
    this.next = next;
  }

  /**
   * Creates a new LinkedNode with the given data which is not currently linked to any other nodes
   * 
   * @param data the data to be contained in this node
   */
  public LinkedNode(LakeRecord data) {
    // Initialize the data to the given value
    // and the previous and next references to null
    this.data = data;
    this.prev = null;
    this.next = null;
  }

  /**
   * Accesses the LakeRecord stored in this node
   * 
   * @return the LakeRecord stored in this node
   */
  public LakeRecord getLakeRecord() {
    return data;
  }

  /**
   * Accesses the previous node in the list
   * 
   * @return a reference to the previous node in this list (may be null)
   */
  public LinkedNode getPrev() {
    return prev;
  }

  /**
   * Updates the previous node for this node
   * 
   * @param newPrev the new previous node in this list (may be null)
   */
  public void setPrev(LinkedNode newPrev) {
    prev = newPrev;
  }

  /**
   * Accesses the next node in the list
   * 
   * @return a reference to the next node in this list (may be null)
   */
  public LinkedNode getNext() {
    return next;
  }

  /**
   * Updates the next node for this node
   * 
   * @param newNext the new next node in this list (may be null)
   */
  public void setNext(LinkedNode newNext) {
    next = newNext;
  }
}
