import java.util.ArrayList;
import java.util.Iterator;

/**
 * A doubly-linked list implementation for managing freeze-thaw records of Lake Mendota. Implements
 * ListADT and Iterable, providing operations for adding, removing, merging, and analyzing freeze
 * data.
 */
public class FreezeTracker implements ListADT<LakeRecord>, Iterable<LakeRecord> {
  /**
   * Pointer to head of the linked list.
   */
  private LinkedNode head;
  /**
   * Pointer to tail of the linked list.
   */
  private LinkedNode tail;
  /**
   * Number of elements in the list.
   */
  private int size;
  /**
   * Whether to traverse the list is reverse-chronological order.
   */
  private boolean reversed;

  /**
   * Constructs an empty FreezeTracker.
   */
  public FreezeTracker() {
    head = null;
    tail = null;
    size = 0;
    reversed = false;
  }

  /**
   * Constructs a FreezeTracker and initializes it with an ArrayList of LakeRecords. This
   * constructor processes the provided dataset. After this process, the linked list will contain
   * exactly one cleaned and merged record per winter.
   * 
   * @param records The list of LakeRecord objects read from FreezeData.csv. This list may contain
   *                missing or duplicate entries, which are handled during initialization.
   */
  public FreezeTracker(ArrayList<LakeRecord> records) {
    // TODO
    // Initialize the linked list
    head = null;
    tail = null;
    size = 0 ;
    reversed = false;
    // Cleaning
    // Iterate through the records and add them to the linked list
    for (LakeRecord record : records) {
      // Check if the record is valid (not null)
      if (record != null) {
        // Add the record to the linked list
        add(record);
      }
    }
    this.removeIncompleteRecords();
    this.updateDurations();
    this.mergeWinters();
  }

  /**
   * Returns the number of records in the list.
   * 
   * @return The size of the list.
   */
  @Override
  public int size() {
    return size;
  }

  /**
   * Checks if the list is empty.
   * 
   * @return True if the list is empty, false otherwise.
   */
  @Override
  public boolean isEmpty() {
    return size == 0;
  }

  /**
   * Clears all records from the list.
   */
  @Override
  public void clear() {
    head = null;
    tail = null;
    size = 0;
  }

  /**
   * Specifies which direction the list should be traversed in the future
   * @param reversed whether to traverse the list backwards
   */
  public void setReversed(boolean reversed) {
    this.reversed = reversed;
  }

  /**
   * Getter method for head
   * @return head of the linked list
   */
  public LinkedNode getHead() {
    return head;
  }

  /**
   * Getter method for tail
   * @return tail of the linked list
   */
  public LinkedNode getTail() {
    return tail;
  }



  /**
   * Appends a new freeze record to the end of the linked list in O(1) time.
   *
   * <br><br> Note: This method is closely related to the learning objectives of the assignment, and so we'll
   * pay special attention to it during manual grading. Be sure to leave comments explaining
   * each algorithmic step you use!
   *
   * @param record The record to add.
   */
  @Override
  public void add(LakeRecord record) {
    // Create a new LinkedNode with the given record
    LinkedNode newNode = new LinkedNode(record, tail, null);
    // If the list is empty, set head to the new node
    if (head == null) {
      head = newNode;
    } else {
      return;
    }
    // If the list is not empty, set the next pointer of the current tail to the new node
    LinkedNode temp = head;
    //finds tail
    while (temp.getNext() != null) {
      temp = temp.getNext();
    }
    //sets tails new next to new node
    temp.setNext(newNode);
    //sets new nodes previous to temp
    newNode.setPrev(temp);
    //increments size
    size++;
  }

  /**
   * Removes the given node from the linked list in O(1) time. Note: this method does not
   * verify that the given node is a member of the list, and should only be used as a helper
   * function inside the FreezeTracker class.
   *
   * <br><br> Note: This method is closely related to the learning objectives of the assignment, and so we'll
   * pay special attention to it during manual grading. Be sure to leave comments explaining
   * each algorithmic step you use!
   *
   * @param node the node to be removed
   * @throws IllegalArgumentException if node is null
   */
  private void removeNode(LinkedNode node) {
    LinkedNode current = node;
    // Check if the node is null
    if (current == null) {
      throw new IllegalArgumentException("Node cannot be null");
    }
    // If node is head, update head
    if (current == head) {
      head = current.getNext();
    }
    //update tail if node is tail
    if (current == tail) {
      tail = current.getPrev();
    }
    // If node is in the middle, update pointers
    if (current.getNext() != null) {
      current.getNext().setPrev(current.getPrev());
    }
    if (current.getPrev() != null) {
      current.getPrev().setNext(current.getNext());
    }
    // Decrement size
    size--;
  }

  /**
   * Removes the first node in the list that contains the given record.
   *
   * <br><br> Note: This method is closely related to the learning objectives of the assignment, and so we'll
   * pay special attention to it during manual grading. Be sure to leave comments explaining
   * each algorithmic step you use!
   *
   * @param record the record to be removed
   * @return boolean indicating whether the record was found in the list
   */
  @Override
  public boolean remove(LakeRecord record) {
    // Finds the node containing the record and sets it to current
    LinkedNode current = find(record);
    // if null = record not found
    if (current == null) {
      return false; // Record not found
    }
    // If the record is found, remove the node
    removeNode(current);
    return true;
  }

  /**
   * Finds the given record in the list
   * @param record the LakeRecord to search for
   * @return The first LinkedNode containing the given record, or null if none exists
   */
  public LinkedNode find(LakeRecord record) {
    LinkedNode current = head;
    // Traverse the list to find the record
    while(current != null && !current.getLakeRecord().equals(record)) {
      current = current.getNext();
    }
    if (current == null) {
      return null; // Record not found
    }
    return current;
  }

  /**
   * Returns the LakeRecord at index i in the list, using zero-indexing.
   * @param i a non-negative integer
   * @return The LakeRecord at the given index
   * @throws IndexOutOfBoundsException if i is negative or greater than size()-1
   */
  public LakeRecord get(int i) {
    if (i < 0 || i > size() - 1)  {
      throw new IndexOutOfBoundsException("Index out of bounds");
    }
    LinkedNode current = head;
    int count = 0;
    // Traverse the list to find the record at index i
    while (current != null && count < i) {
      // If the current index matches i, return the record
      if (count == i) {
        return current.getLakeRecord();
      }
      // Move to the next node
      current = current.getNext();
      count++;
    }
    return null; 
  }

  /**
   * Provides an iterator for traversal. The direction of traversal is head-to-tail
   * if this.reversed is false, and tail-to-head otherwise.
   *
   * @return An iterator traversing the list.
   */
  @Override
  public Iterator<LakeRecord> iterator() {
    if (reversed) {
      return new IteratorBwd(tail);
    } else {
      return new IteratorFwd(head);
    }
  }
  /**
   * Removes all nodes with missing freeze or thaw dates
   */
  public void removeIncompleteRecords() {
    Iterator <LakeRecord> iter = this.iterator();
    while (iter.hasNext()) {
      if (!iter.next().hasCompleteData()) {
        iter.remove();
      }
    }
  }
  /**
   * Fixes all LakeRecords contained in this list with incorrect durations
   * (Hint: LakeRecord already has a method for this!)
   */
  public void updateDurations() {
    Iterator <LakeRecord> iter = this.iterator();
    while (iter.hasNext()) {
      iter.next().updateDuration();
    }
  }

  /**
   * Merges consecutive nodes containing records from the same winter. The merged node contains
   * a single record with the earliest freeze date, the latest thaw date, and the total number of
   * days of ice cover. Note that merging discards the middle thaw and freeze dates, and so this
   * method should only be used after calling updateDurations().
   *
   * <br><br> Note: This method is closely related to the learning objectives of the assignment, and so we'll
   * pay special attention to it during manual grading. Be sure to leave comments explaining
   * each algorithmic step you use!
   */
  public void mergeWinters() {
    Iterator<LakeRecord> iter = this.iterator();
    LakeRecord currentRecord = null;
    LakeRecord nextRecord = null;
    while (iter.hasNext()) {
      currentRecord = iter.next();
      nextRecord = iter.hasNext() ? iter.next() : null;
      if (nextRecord != null && currentRecord.getWinter().equals(nextRecord.getWinter())) {
        currentRecord.mergeWith(nextRecord);
        iter.remove();
      }
    }
    // Reset the iterator to the beginning of the list
    iter = this.iterator();
    // Update the tail pointer to the last node
    while (iter.hasNext()) {
      nextRecord = iter.next();
    }
    tail = find(nextRecord);
    // Update the size of the list
    size = 0;
    while (iter.hasNext()) {
      iter.next();
      size++;
    }
  }

  /**
   * Returns a new linked list containing all the records falling between year1 and
   * year 2, inclusive. The returned list should not contain any references to nodes
   * or records from the original list, and the relative ordering of nodes should not change.
   *
   * @param year1 minimum allowable year for the new list
   * @param year2 maximum allowable year for the new list
   * @return a new, filtered linked list covering the given range of years.
   */
  public FreezeTracker filterByYear(int year1, int year2) {
    return null; // TODO
  }

  /**
   * Returns a new linked list containing all of the records from the given year. The returned
   * list should not contain any references to nodes or records from the original list, and the
   * relative ordering of nodes should not change.
   *
   * @param year the single year covered by the new list
   * @return a new linked list containing only nodes from the given year
   */
  public FreezeTracker filterByYear(int year) {
    return null; // TODO
  }

  /**
   * Returns a new linked list containing all of the records whose total days of ice cover are
   * between low and high, inclusive. The returned list should not contain any references to
   * nodes or records from the original list, and the relative ordering of nodes should not change.
   *
   * @param low The minimum allowed duration for the new list
   * @param high The maximum allowed duration for the new list
   * @return a new list containing only records with duration in the given range
   */
  public FreezeTracker filterByDuration(int low, int high) {
    return null; // TODO
  }


  /**
   * Finds the latest date at which the lake thawed.
   * 
   * @return The date of the latest thaw, e.g. "April 15"
   */
  public String getLatestThaw() {
    return null; // TODO
  }

  /**
   * Finds the earliest date at which the lake froze.
   *
   * @return The day of the earliest freeze, e.g. "December 2"
   */
  public String getEarliestFreeze() {
    return null; // TODO
  }

  /**
   * Finds the average (arithmetic mean) number of days of ice cover across the entire list
   *
   * @return The average number of days of ice cover across all nodes, or 0 if list is empty.
   */
  public float getAverageFreezeDuration() {
    return 0; // TODO
  }

  /**
   * Finds the maximum number of days of ice cover across the entire list
   * 
   * @return The maximum number of days of ice cover across all nodes, or 0 if the list is empty.
   */ 
  public int getMaxFreezeDuration() {
    return 0; // TODO
  }

  /**
   * Finds the minimum number of days of ice cover across the entire list
   * 
   * @return The minimum number of days of ice cover across all nodes, or 0 if the list is empty.
   */ 
  public int getMinFreezeDuration() {
    return 0; // TODO 
  }

  /**
   * Creates a string representation of the tracker with each node on a new line.
   * The order of the nodes depends on whether the string is currently reversed.
   *
   * @return a String representation of the list
   */
  public String toString() {
    return null; // TODO
  }
}
