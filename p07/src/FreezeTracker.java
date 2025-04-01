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
    // TODO
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
  }

  /**
   * Returns the number of records in the list.
   * 
   * @return The size of the list.
   */
  @Override
  public int size() {
    return 0; // TODO
  }

  /**
   * Checks if the list is empty.
   * 
   * @return True if the list is empty, false otherwise.
   */
  @Override
  public boolean isEmpty() {
    return false; // TODO
  }

  /**
   * Clears all records from the list.
   */
  @Override
  public void clear() {
    // TODO
  }

  /**
   * Specifies which direction the list should be traversed in the future
   * @param reversed whether to traverse the list backwards
   */
  public void setReversed(boolean reversed) {
    // TODO
  }

  /**
   * Getter method for head
   * @return head of the linked list
   */
  public LinkedNode getHead() {
    return null; // TODO
  }

  /**
   * Getter method for tail
   * @return tail of the linked list
   */
  public LinkedNode getTail() {
    return null; // TODO
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
    // TODO
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
    // TODO
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
    return false; // TODO
  }

  /**
   * Finds the given record in the list
   * @param record the LakeRecord to search for
   * @return The first LinkedNode containing the given record, or null if none exists
   */
  public LinkedNode find(LakeRecord record) {
    return null; // TODO
  }

  /**
   * Returns the LakeRecord at index i in the list, using zero-indexing.
   * @param i a non-negative integer
   * @return The LakeRecord at the given index
   * @throws IndexOutOfBoundsException if i is negative or greater than size()-1
   */
  public LakeRecord get(int i) {
    return null; // TODO
  }

  /**
   * Provides an iterator for traversal. The direction of traversal is head-to-tail
   * if this.reversed is false, and tail-to-head otherwise.
   *
   * @return An iterator traversing the list.
   */
  @Override
  public Iterator<LakeRecord> iterator() {
    return null; // TODO
  }


  /**
   * Removes all nodes with missing freeze or thaw dates
   */
  public void removeIncompleteRecords() {
    // TODO
  }

  /**
   * Fixes all LakeRecords contained in this list with incorrect durations
   * (Hint: LakeRecord already has a method for this!)
   */
  public void updateDurations() {
    // TODO
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
    // TODO
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
