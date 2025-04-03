//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Freeze Tracking System and Analyzer
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
// Online Sources:  W3 Schools and tutorials point
//                  - https://www.w3schools.com/dsa/dsa_data_linkedlists_types.php
//                  - https://www.tutorialspoint.com/data_structures_algorithms/linked_list_algorithms.htm
//
///////////////////////////////////////////////////////////////////////////////
//imports
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
    // Initialize the linked list with empty values
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
    // Initialize the linked list
    head = null;
    tail = null;
    size = 0 ;
    reversed = false;
    // Iterate through the records and add them to the linked list
    for (LakeRecord record : records) {
      // Check if the record is valid (not null)
      if (record != null) {
        // Add the record to the linked list
        add(record);
      }
    }
    // cleaning the list
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
    // Return the size of the list
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
    LinkedNode newNode = new LinkedNode(record, null, null);
    // If the list is empty set head to the new node
    if (head == null) {
      // Head and Tail are the same
      head = newNode;
      tail = newNode;
      // increment size
      size++;
      return;
    }
    // If the list is not empty, set the next pointer of the current tail to the new node
    LinkedNode temp = head;
    // Finds tail
    while (temp.getNext() != null) {
      temp = temp.getNext();
    }
    // Temp is our tail in this case
    //sets tails new next to new node
    temp.setNext(newNode);
    //sets new nodes previous to temp
    newNode.setPrev(temp);
    // tail is now new node
    tail = newNode;
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
      // Throw an exception if the node is null
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
    // If node is in the middle, update pointers on either side
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
    // If the record is found, remove the node using removeNode
    removeNode(current);
    return true; // record found
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
    // Check if the index is valid
    if (i < 0 || i > size() - 1)  {
      throw new IndexOutOfBoundsException("Index out of bounds");
    }
    // starter node
    LinkedNode current = head;
    int count = 0;
    // Traverse the list to find the record at index i
    while (current != null && count <= i) {
      // If the current index matches i, return the record
      if (count == i) {
        return current.getLakeRecord();
      }
      // move to the next node
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
    // If reversed, return the backwards iterator
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
    LinkedNode current = head;
    // Traverse the list to find records with missing data
    while (current != null) {
      // Check if the record is incomplete
      if (!current.getLakeRecord().hasCompleteData()) {
        // Remove the node
        LinkedNode toRemove = current;
        current = current.getNext();
        this.removeNode(toRemove);
        //System.out.println("remove" + toRemove.getLakeRecord());
      } else {
        // Move to the next node
        current = current.getNext();
      }
    }
  }
  /**
   * Fixes all LakeRecords contained in this list with incorrect durations
   * (Hint: LakeRecord already has a method for this!)
   */
  public void updateDurations() {
    // Traverse through all nodes in the list
    LinkedNode current = head;
    while (current != null) {
      // Get the current record
      LakeRecord record = current.getLakeRecord();
      // Create a new record with MISSING duration
      LakeRecord newRecord = new LakeRecord(record.getWinter(), record.getFreezeDate(), record.getThawDate(), LakeRecord.MISSING);
      // Update the duration
      newRecord.updateDuration();
      // Create a new node with the updated record
      LinkedNode newNode = new LinkedNode(newRecord, current.getPrev(), current.getNext());
      // Update the links
      if (current.getPrev() != null) {
        current.getPrev().setNext(newNode);
      } else {
        head = newNode;
      }
      if (current.getNext() != null) {
        current.getNext().setPrev(newNode);
      } else {
        tail = newNode;
      }
      // Move 
      current = current.getNext();
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
    //iterator for the list
    Iterator<LakeRecord> iter = this.iterator();
    // initialize the current and next records
    // and the merged record
    LakeRecord currentRecord = null;
    LakeRecord nextRecord = null;
    //LakeRecord mergedRecord = null;
    // for each record
    while (iter.hasNext()) {
      currentRecord = iter.next();
      //System.out.println( "Current Record: " + currentRecord);
      if (iter.hasNext()) {
        nextRecord = iter.next();
        //System.out.println( "Next Record: " + nextRecord);
      } else {
        nextRecord = this.get(size() - 1);
        //System.out.println( "Next Record: " + nextRecord);
      }
      // end condition of nulls are detected
      if (currentRecord == null || nextRecord == null) {
        break;
      }
      //check if the two records are from the same winter
      if (currentRecord.getWinter().equals(nextRecord.getWinter())) {
        // merge the two
        currentRecord.mergeWith(nextRecord);
        //mergedRecord = currentRecord;
        // discard both records
        //this.remove(currentRecord);
        this.remove(nextRecord);
        //System.out.println(currentRecord);
        //this.add(mergedRecord);
        // reset the iterator to the beginning
        iter = this.iterator();
        // reset the current and next records
        currentRecord = null;
        nextRecord = null;
        //mergedRecord = null;
      } else {
      continue;
      }
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
    // new FreezeTracker 
    FreezeTracker filteredList = new FreezeTracker();
    // Iterate through the current list
    LinkedNode current = head;
    while (current != null) {
      LakeRecord record = current.getLakeRecord();
      int recordYear = record.getYear();
      // Check if the record year is within the specified range
      if (recordYear >= year1 && recordYear <= year2) {
        // deep copy of the record
        LakeRecord deepCopy = new LakeRecord(
          record.getWinter(),
          record.getFreezeDate(),
          record.getThawDate(),
          record.getDaysOfIceCover()
        );
        // Add the deep copy to the new list
        filteredList.add(deepCopy);
      }
      current = current.getNext();
    }
    // return the new list
    return filteredList;
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
    // new FreezeTracker 
    FreezeTracker filteredList = new FreezeTracker();
    // Iterate through the current list
    LinkedNode current = head;
    while (current != null) {
      LakeRecord record = current.getLakeRecord();
      int recordYear = record.getYear();
      // Check if the record year matches the specified year
      if (recordYear == year) {
        // deep copy of the record
        LakeRecord deepCopy = new LakeRecord(
          record.getWinter(),
          record.getFreezeDate(),
          record.getThawDate(),
          record.getDaysOfIceCover()
        );
        // Add the deep copy to the new list
        filteredList.add(deepCopy);
      }
      current = current.getNext();
    }
    return filteredList;
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
    //  new FreezeTracker 
    FreezeTracker filteredList = new FreezeTracker();
    // Iterate through the current list
    LinkedNode current = head;
    while (current != null) {
      LakeRecord record = current.getLakeRecord();
      int duration = record.getDaysOfIceCover();
      // Check if the record duration is within the specified range
      if (duration >= low && duration <= high) {
        // deep copy of the record
        LakeRecord deepCopy = new LakeRecord(
          record.getWinter(),
          record.getFreezeDate(),
          record.getThawDate(),
          record.getDaysOfIceCover()
        );
        // Add the deep copy to the new list
        filteredList.add(deepCopy);
      }
      current = current.getNext();
    }
    return filteredList;
  }


  /**
   * Finds the latest date at which the lake thawed.
   * 
   * @return The date of the latest thaw, e.g. "April 15"
   */
  public String getLatestThaw() {
    // If list is empty, return null
    if (isEmpty()) {
      return null;
    }
    // Initialize latest thaw with first record's thaw date
    Iterator<LakeRecord> iter = this.iterator();
    LakeRecord firstRecord = iter.next();
    String latestThaw = firstRecord.getThawDate();
    
    // Use the iterator to find latest thaw date
    while (iter.hasNext()) {
      LakeRecord record = iter.next();
      String currentThaw = record.getThawDate();
      // comparison logic
      if (currentThaw != null && (latestThaw == null || Date.compareDates(currentThaw, latestThaw) > 0)) {
        latestThaw = currentThaw;
      }
    }
    // check last record
    LakeRecord record = iter.next();
    String currentThaw = record.getThawDate();
    if (currentThaw != null && (latestThaw == null || Date.compareDates(currentThaw, latestThaw) > 0)) {
      latestThaw = currentThaw;
    }
    return latestThaw;
  }

  /**
   * Finds the earliest date at which the lake froze.
   *
   * @return The day of the earliest freeze, e.g. "December 2"
   */
  public String getEarliestFreeze() {
    // If list is empty, return null
    if (isEmpty()) {
      return null;
    }
    // Initialize earliest freeze with first record's freeze date
    Iterator<LakeRecord> iter = this.iterator();
    LakeRecord firstRecord = iter.next();
    String earliestFreeze = firstRecord.getFreezeDate();
    // Use the iterator to find earliest freeze date
    while (iter.hasNext()) {
      LakeRecord record = iter.next();
      String currentFreeze = record.getFreezeDate();
      // comparison logic
      if (currentFreeze != null && (earliestFreeze == null || Date.compareDates(currentFreeze, earliestFreeze) < 0)) {
        earliestFreeze = currentFreeze;
      }
    }
    // check last record
    LakeRecord record = iter.next();
    String currentFreeze = record.getFreezeDate();
    if (currentFreeze != null && (earliestFreeze == null || Date.compareDates(currentFreeze, earliestFreeze) < 0)) {
      earliestFreeze = currentFreeze;
    }
    return earliestFreeze;
  }

  /**
   * Finds the average (arithmetic mean) number of days of ice cover across the entire list
   *
   * @return The average number of days of ice cover across all nodes, or 0 if list is empty.
   */
  public float getAverageFreezeDuration() {
    // If list is empty, return 0
    if (isEmpty()) {
      return 0;
    }
    // Init
    int totalDuration = 0;
    int count = 0;
    // Use the iterator to traverse 
    Iterator<LakeRecord> iter = this.iterator();
    while (iter.hasNext()) {
      LakeRecord record = iter.next();
      totalDuration += record.getDaysOfIceCover();
      count++;
    }
    // check last record
    LakeRecord record =iter.next();
    totalDuration += record.getDaysOfIceCover();
    count++;
    // Calculate logic
    return (float) totalDuration / count;
  }

  /**
   * Finds the maximum number of days of ice cover across the entire list
   * 
   * @return The maximum number of days of ice cover across all nodes, or 0 if the list is empty.
   */ 
  public int getMaxFreezeDuration() {
    // If list is empty, return 0
    if (isEmpty()) {
      return 0;
    }
    // Init
    Iterator<LakeRecord> iter = this.iterator();
    LakeRecord firstRecord = iter.next();
    int maxDuration = firstRecord.getDaysOfIceCover();
    // Use the iterator 
    while (iter.hasNext()) {
      LakeRecord record = iter.next();
      int currentDuration = record.getDaysOfIceCover();
      if (currentDuration > maxDuration) {
        maxDuration = currentDuration;
      }
    }
    // check last record
    LakeRecord record = iter.next();
    int currentDuration = record.getDaysOfIceCover();
    if (currentDuration > maxDuration) {
      maxDuration = currentDuration;
    }
    return maxDuration;
  }

  /**
   * Finds the minimum number of days of ice cover across the entire list
   * 
   * @return The minimum number of days of ice cover across all nodes, or 0 if the list is empty.
   */ 
  public int getMinFreezeDuration() {
    // If list is empty, return 0
    if (isEmpty()) {
      return 0;
    }
    
    // Init
    Iterator<LakeRecord> iter = this.iterator();
    LakeRecord firstRecord = iter.next();
    int minDuration = firstRecord.getDaysOfIceCover();
    // Use the iterator
    while (iter.hasNext()) {
      LakeRecord record = iter.next();
      int currentDuration = record.getDaysOfIceCover();
      if (currentDuration < minDuration) {
        minDuration = currentDuration;
      }
    }
    // check last record
    LakeRecord record = iter.next();
    int currentDuration = record.getDaysOfIceCover();
    if (currentDuration < minDuration) {
      minDuration = currentDuration;
    }
    return minDuration;
  }

  /**
   * Creates a string representation of the tracker with each node on a new line.
   * The order of the nodes depends on whether the string is currently reversed.
   *
   * @return a String representation of the list
   */
  public String toString() {
    if (reversed) {
      // If reversed, traverse from tail to head
      StringBuilder sb = new StringBuilder();
      LinkedNode current = tail;
      while (current != null) {
        // Append the record to the string
        // and move to the previous node
        sb.append(current.getLakeRecord().toString());
        sb.append("\n");
        current = current.getPrev();
      }
      return sb.toString();
    } else {
      // If not reversed, traverse from head to tail
      StringBuilder sb = new StringBuilder();
      LinkedNode current = head;
      while (current != null) {
        // Append the record to the string
        // and move to the next node
        sb.append(current.getLakeRecord().toString());
        sb.append("\n");
        current = current.getNext();
      }
      return sb.toString();
    }
    
  }
}
