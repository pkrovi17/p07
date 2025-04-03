//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Freeze Tracking System and Analyzer Testing Methods
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
import java.util.Iterator;

/**
 * Tester class for FreezeTracker functionality.
 */
public class FreezeTrackerTester {

  /**
   * Tests adding records to an empty FreezeTracker and the end of a non-empty FreezeTracker.
   * 
   * Ensure that size has been updated correctly, that the first and last winters are correct,
   * and that all added records are present in the correct locations in the list.
   * 
   * @return true if all cases pass, false otherwise.
   */
  public static boolean testAdd() {
    // Scenario 1: Add to an empty list
    {
    // Create a new FreezeTracker
    FreezeTracker tracker = new FreezeTracker();
    // Create some LakeRecord objects
    LakeRecord record1 = new LakeRecord("2019-20", "December 31", "February 11", 45);
    LakeRecord record2 = new LakeRecord("2020-21", "February 10", "April 12", 50);
    LakeRecord record3 = new LakeRecord("2017-18", "March 17", "April 10", 20);
    // Add records to the tracker
    tracker.add(record1);
    tracker.add(record2);
    tracker.add(record3);
    // Check size
    if (tracker.size() != 3) {
      return false;
    }
    // check first winter
    if (!tracker.get(0).getWinter().equals("2019-20")) {
      return false;
    }
    // check last winter
    if (!tracker.get(tracker.size() - 1).getWinter().equals("2017-18")) {
      return false;
    }
  }
  // Scenario 2: Add to a non-empty list
  {
    // Create a new FreezeTracker
    FreezeTracker tracker = new FreezeTracker();
    // Create some LakeRecord objects
    LakeRecord record1 = new LakeRecord("2019-20", "December 31", "February 11", 45);
    LakeRecord record2 = new LakeRecord("2020-21", "February 10", "April 12", 50);
    LakeRecord record3 = new LakeRecord("2017-18", "March 17", "April 10", 20);
    // Add records to the tracker
    tracker.add(record1);
    tracker.add(record2);
    tracker.add(record3);
    // Check size
    if (tracker.size() != 3) {
      return false;
    }
    LakeRecord record4 = new LakeRecord("2016-17", "March 15", "April 5", 20);
    tracker.add(record4);
    // Check size
    if (tracker.size() != 4) {
      return false;
    }
    // check first winter
    if (!tracker.get(0).getWinter().equals("2019-20")) {
      return false;
    }
    // check last winter
    if (!tracker.get(tracker.size() - 1).getWinter().equals("2016-17")) {
      return false;
    }
  }
    return true;
  }

  /**
   * Tests removing records from different positions (beginning, middle, end). Your initial
   * FreezeTracker list should contain AT LEAST five records; none of these tests will clear
   * out the list (that's a different test, below).
   * 
   * Verify both return values and the list state.
   * 
   * @return true if all cases pass, false otherwise.
   */
  public static boolean testRemove() {
    {
      // Create a new FreezeTracker
      FreezeTracker tracker = new FreezeTracker();
      // Create some LakeRecord objects
      LakeRecord record1 = new LakeRecord("2019-20", "December 31", "February 11", 45);
      LakeRecord record2 = new LakeRecord("2020-21", "February 10", "April 12", 50);
      LakeRecord record3 = new LakeRecord("2017-18", "March 17", "April 10", 20);
      LakeRecord record4 = new LakeRecord("2016-17", "March 15", "April 5", 20);
      LakeRecord record5 = new LakeRecord("2018-19", "March 17", "April 10", 20);
      // Add records to the tracker
      tracker.add(record1);
      tracker.add(record2);
      tracker.add(record3);
      tracker.add(record4);
      tracker.add(record5);
      // Check size
      if (tracker.size() != 5) {
        return false;
      }
      // Remove the first record
      if (!tracker.remove(record1)) {
        return false;
      }
      // Remove the middle record
      if (!tracker.remove(record3)) {
        return false;
      }
      // Remove the last record
      if (!tracker.remove(record5)) {
        return false;
      }
      // Check size
      if (tracker.size() != 2) {
        return false;
      }
    }
    return true; 
  }

  /**
   * Tests removing the ONLY value from a FreezeTracker.
   * 
   * Ensure all accessor methods behave correctly after removing it.
   * 
   * @return true if all cases pass, false otherwise.
   */
  public static boolean testRemoveOnly() {
    {
      // Create a new FreezeTracker
      FreezeTracker tracker = new FreezeTracker();
      // Create some LakeRecord objects
      LakeRecord record1 = new LakeRecord("2019-20", "December 31", "February 11", 45);
      // Add records to the tracker
      tracker.add(record1);
      // Remove the only record
      if (!tracker.remove(record1)) {
        return false;
      }
      // Check size
      if (tracker.size() != 0) {
        return false;
      }
    }
    return true;
  }

  /**
   * Tests removing a record from FreezeTracker which is not present in the list.
   * 
   * Verify both the return value and the list state.
   * 
   * @return true if all cases pass, false otherwise.
   */
  public static boolean testRemoveDoesNotExist() {
    {
      // Create a new FreezeTracker
      FreezeTracker tracker = new FreezeTracker();
      // Create some LakeRecord objects
      LakeRecord record1 = new LakeRecord("2019-20", "December 31", "February 11", 45);
      LakeRecord record2 = new LakeRecord("2020-21", "February 10", "April 12", 50);
      // Add records to the tracker
      tracker.add(record1);
      // Remove a record that doesn't exist
      if (tracker.remove(record2)) {
        return false;
      }
      // Check size
      if (tracker.size() != 1) {
        return false;
      }
    }
    return true;
  }

  /**
   * Tests iterators (forward and backward). For full credit, this test MUST contain at 
   * least one enhanced for loop with each type of iterator.
   * 
   * @return true if all cases pass, false otherwise.
   */
  public static boolean testIterators() {
    {
      // Create a new FreezeTracker
      FreezeTracker tracker = new FreezeTracker();
      // Create some LakeRecord objects
      LakeRecord record1 = new LakeRecord("2019-20", "December 31", "February 11", 45);
      LakeRecord record2 = new LakeRecord("2020-21", "February 10", "April 12", 50);
      LakeRecord record3 = new LakeRecord("2017-18", "March 17", "April 10", 20);
      // Add records to the tracker
      tracker.add(record1);
      tracker.add(record2);
      tracker.add(record3);
      // Iterate forward using the iterator
      Iterator<LakeRecord> fwdIterator = tracker.iterator();
      boolean f = false;
      for (LakeRecord record : tracker) {
        if (!fwdIterator.hasNext()) {
          f = true;
        }
        LakeRecord fwdRecord = fwdIterator.next();
        // Check if the forward iterator is correct
        if (!fwdRecord.equals(record)) {
          return false;
        }
        if (f) {
          break;
        }
      }
    }
    {
      // Create a new FreezeTracker
      FreezeTracker tracker = new FreezeTracker();
      // Create some LakeRecord objects
      LakeRecord record1 = new LakeRecord("2019-20", "December 31", "February 11", 45);
      LakeRecord record2 = new LakeRecord("2020-21", "February 10", "April 12", 50);
      LakeRecord record3 = new LakeRecord("2017-18", "March 17", "April 10", 20);
      // Add records to the tracker
      tracker.add(record1);
      tracker.add(record2);
      tracker.add(record3);
      // Iterate backward using the iterator
      tracker.setReversed(true);
      Iterator<LakeRecord> bwdIterator = tracker.iterator();
      boolean b = false;
      for (LakeRecord record : tracker) {
        if (!bwdIterator.hasNext()) {
          b = true;
        }
        LakeRecord bwdRecord = bwdIterator.next();
        // Check if the backward iterator is correct
        if (!bwdRecord.equals(record)) {
          return false;
        }
        if (b) {
          break;
        }
      }
    }
    return true; 
  }

  /**
   * Tests merging multiple freeze records (provided!) for the same winter.
   * 
   * Add these records to a FreezeTracker and verify that merging them results in a list 
   * with a single record with the correct values.
   * 
   * @return true if all cases pass, false otherwise.
   */
  public static boolean testMergeWinters() {
    LakeRecord freeze1 = new LakeRecord("2019-20", "December 1", "January 15", 45);
    LakeRecord freeze2 = new LakeRecord("2019-20", "January 20", "March 10", 50);
    LakeRecord freeze3 = new LakeRecord("2019-20", "March 15", "April 5", 20);
    {
      // Create a new FreezeTracker
      FreezeTracker tracker = new FreezeTracker();
      // Add records to the tracker
      tracker.add(freeze1);
      tracker.add(freeze2);
      tracker.add(freeze3);
      tracker.mergeWinters();
      // Check size
      if (tracker.size() != 1) {
        System.out.println(tracker.size());
        return false;
      }
      // Check that the merged record is in correct winter
      if (!tracker.get(0).getWinter().equals("2019-20")) {
        System.out.println(tracker.get(0).getWinter());
        return false;
      }
      // Check that the merged record has correct freeze date
      if (!tracker.get(0).getFreezeDate().equals("December 1")) {
        System.out.println(tracker.get(0).getFreezeDate());
        return false;
      }
      // Check that the merged record has correct thaw date
      if (!tracker.get(0).getThawDate().equals("April 5")) {
        System.out.println(tracker.get(0).getThawDate());
        return false;
      }
    }
    return true; 
  }

  /**
   * Tests cleaning the dataset (removing incomplete records). Create a FreezeTracker with
   * some valid and invalid records, and verify that all of the invalid records are removed
   * (and none of the valid ones).
   * 
   * @return true if all cases pass, false otherwise.
   */
  public static boolean testCleanData() {
    // remove incomplete records
    {
      // Create a new FreezeTracker
      FreezeTracker tracker = new FreezeTracker();
      // Create some LakeRecord objects
      LakeRecord record1 = new LakeRecord("2019-20", "December 31", "February 11", 45);
      LakeRecord record2 = new LakeRecord("2020-21", "February 10", null, 50);
      LakeRecord record3 = new LakeRecord("2017-18", "March 17", "April 10", 20);
      // Add records to the tracker
      tracker.add(record1);
      tracker.add(record2);
      tracker.add(record3);
      tracker.removeIncompleteRecords();
      // Check size
      if (tracker.size() != 2) {
        System.out.println(tracker.size());
        return false;
      }
    }
    {
      // Create a new FreezeTracker
      FreezeTracker tracker = new FreezeTracker();
      // Create some LakeRecord objects
      LakeRecord record1 = new LakeRecord("2019-20", "December 31", "February 11", LakeRecord.MISSING);
      LakeRecord record2 = new LakeRecord("2020-21", "February 10", "April 12", LakeRecord.MISSING);
      LakeRecord record3 = new LakeRecord("2017-18", "March 17", "April 10", LakeRecord.MISSING);
      // Add records to the tracker
      tracker.add(record1);
      tracker.add(record2);
      tracker.add(record3);
      tracker.updateDurations();
      // Check size
      if (tracker.size() != 3) {
        return false;
      }
      // Check that none of the records have missing data
      if (tracker.get(0).getDaysOfIceCover() == LakeRecord.MISSING) {
        return false;
      }
      if (tracker.get(1).getDaysOfIceCover() == LakeRecord.MISSING) {
        return false;
      }
      if (tracker.get(2).getDaysOfIceCover() == LakeRecord.MISSING) {
        return false;
      }
    }
    return true;
  }

  /**
   * Tests computing the average freeze duration.
   * 
   * @return true if all cases pass, false otherwise.
   */
  public static boolean testAverageFreezeDuration() {
    // Create a new FreezeTracker
    FreezeTracker tracker = new FreezeTracker();
    // Create some LakeRecord objects
    LakeRecord record1 = new LakeRecord("2019-20", "December 31", "February 11", 45);
    LakeRecord record2 = new LakeRecord("2020-21", "February 10", "April 12", 50);
    LakeRecord record3 = new LakeRecord("2017-18", "March 17", "April 10", 20);
    // Add records to the tracker
    tracker.add(record1);
    tracker.add(record2);
    tracker.add(record3);
    float expected = (float)record1.getDaysOfIceCover() + record2.getDaysOfIceCover() + record3.getDaysOfIceCover() / (float)3.0;
    // Check average
    if (!(tracker.getAverageFreezeDuration() > expected + 0.1 || tracker.getAverageFreezeDuration() < expected - 0.1)) {
      System.out.println(tracker.getAverageFreezeDuration());
      return false;
    }
    if (tracker.getAverageFreezeDuration() % 1 == 0) {
      return false;
    }
    return true;
  }

  /**
   * Tests finding the maximum number of days of ice cover in a single winter.
   * 
   * @return true if all cases pass, false otherwise.
   */
  public static boolean testMaxFreezeDuration() {
    // Create a new FreezeTracker
    FreezeTracker tracker = new FreezeTracker();
    // Create some LakeRecord objects
    LakeRecord record1 = new LakeRecord("2019-20", "December 31", "February 11", 45);
    LakeRecord record2 = new LakeRecord("2020-21", "February 10", "April 12", 40);
    LakeRecord record3 = new LakeRecord("2017-18", "March 17", "April 10", 50);
    // Add records to the tracker
    tracker.add(record1);
    tracker.add(record2);
    tracker.add(record3);
    // Check max
    if (tracker.getMaxFreezeDuration() != 50) {
      return false;
    }
    return true;
  }

  /**
   * Tests finding the minimum number of days of ice cover in a single winter.
   * 
   * @return true if all cases pass, false otherwise.
   */
  public static boolean testMinFreezeDuration() {
    // Create a new FreezeTracker
    FreezeTracker tracker = new FreezeTracker();
    // Create some LakeRecord objects
    LakeRecord record1 = new LakeRecord("2019-20", "December 31", "February 11", 45);
    LakeRecord record2 = new LakeRecord("2020-21", "February 10", "April 12", 40);
    LakeRecord record3 = new LakeRecord("2017-18", "March 17", "April 10", 50);
    // Add records to the tracker
    tracker.add(record1);
    tracker.add(record2);
    tracker.add(record3);
    // Check min
    if (tracker.getMinFreezeDuration() != 40) {
      return false;
    }
    return true;
  }

  /**
   * Tests finding the earliest freeze.
   * 
   * @return true if all cases pass, false otherwise.
   */
  public static boolean testGetEarliestFreeze() {
    // Create a new FreezeTracker
    FreezeTracker tracker = new FreezeTracker();
    // Create some LakeRecord objects
    LakeRecord record1 = new LakeRecord("2019-20", "December 31", "February 11", 45);
    LakeRecord record2 = new LakeRecord("2020-21", "February 10", "April 12", 40);
    LakeRecord record3 = new LakeRecord("2017-18", "March 17", "April 10", 50);
    // Add records to the tracker
    tracker.add(record1);
    tracker.add(record2);
    tracker.add(record3);
    // Check earliest freeze
    if (!tracker.getEarliestFreeze().equals("December 31")) {
      return false;
    }
    return true;
  }

  /**
   * Tests finding the latest thaw.
   * 
   * @return true if all cases pass, false otherwise.
   */
  public static boolean testGetLatestThaw() {
    // Create a new FreezeTracker
    FreezeTracker tracker = new FreezeTracker();
    // Create some LakeRecord objects
    LakeRecord record1 = new LakeRecord("2019-20", "December 31", "February 11", 45);
    LakeRecord record2 = new LakeRecord("2020-21", "February 10", "April 12", 40);
    LakeRecord record3 = new LakeRecord("2017-18", "March 17", "April 10", 50);
    // Add records to the tracker 
    tracker.add(record1);
    tracker.add(record2);
    tracker.add(record3);
    // Check latest thaw
    if (!tracker.getLatestThaw().equals("April 12")) {
      return false;
    }
    return true;
  }

  /**
   * Tests filtering freeze records by a range of years.
   * 
   * Ensure that only records between the specified years (inclusive) are present in the result.
   * 
   * @return true if all cases pass, false otherwise.
   */
  public static boolean testFilterByYear() {
    // Create a new FreezeTracker
    FreezeTracker tracker = new FreezeTracker();
    // Create some LakeRecord objects
    LakeRecord record1 = new LakeRecord("2019-20", "December 31", "February 11", 45);
    LakeRecord record2 = new LakeRecord("2020-21", "February 10", "April 12", 40);
    LakeRecord record3 = new LakeRecord("2017-18", "March 17", "April 10", 50);
    // Add records to the tracker
    tracker.add(record1);
    tracker.add(record2);
    tracker.add(record3);
    // Check filtered records
    FreezeTracker filtered = tracker.filterByYear(2019, 2020);
    if (filtered.size() != 2) {
      return false;
    }
    // Check that the filtered records are correct
    if (!filtered.get(0).getWinter().equals("2019-20")) {
      return false;
    }
    if (!filtered.get(1).getWinter().equals("2020-21")) {
      return false;
    }
    return true;
  }

  /**
   * Tests filtering freeze records by a range of ice cover duration values.
   * 
   * Ensure that only records within the duration range are included in the filtered list.
   * 
   * @return true if all cases pass, false otherwise.
   */
  public static boolean testFilterByDuration() {
    // Create a new FreezeTracker
    FreezeTracker tracker = new FreezeTracker();
    // Create some LakeRecord objects
    LakeRecord record1 = new LakeRecord("2019-20", "December 31", "February 11", 45);
    LakeRecord record2 = new LakeRecord("2020-21", "February 10", "April 12", 40);
    LakeRecord record3 = new LakeRecord("2017-18", "March 17", "April 10", 60);
    // Add records to the tracker
    tracker.add(record1);
    tracker.add(record2);
    tracker.add(record3);
    // Check filtered records
    FreezeTracker filtered = tracker.filterByDuration(40, 50);
    if (filtered.size() != 2) {
      return false;
    }
    // Check that the filtered records are correct
    if (!filtered.get(0).getWinter().equals("2019-20")) {
      return false;
    }
    if (!filtered.get(1).getWinter().equals("2020-21")) {
      return false;
    }
    return true;
  }

  /**
   * Main Method to Launch the tester methods.
   * 
   * @param args list of inputs if any.
   */
  public static void main(String[] args) {
    System.out.println("Running tests:");
    System.out.println("testAdd(): " + (testAdd() ? "PASSED" : "FAILED"));
    System.out.println("testRemove(): " + (testRemove() ? "PASSED" : "FAILED"));
    System.out.println("testRemoveOnly(): " + (testRemoveOnly() ? "PASSED" : "FAILED"));
    System.out.println("testRemoveDoesNotExist(): " + (testRemoveDoesNotExist() ? "PASSED" : "FAILED"));
    System.out.println("testIterators(): " + (testIterators() ? "PASSED" : "FAILED"));
    System.out.println("testMergeWinters(): " + (testMergeWinters() ? "PASSED" : "FAILED"));
    System.out.println("testCleanData(): " + (testCleanData() ? "PASSED" : "FAILED"));
    System.out.println("testAverageFreezeDuration(): " + (testAverageFreezeDuration() ? "PASSED" : "FAILED"));
    System.out.println("testMaxFreezeDuration(): " + (testMaxFreezeDuration() ? "PASSED" : "FAILED"));
    System.out.println("testMinFreezeDuration(): " + (testMinFreezeDuration() ? "PASSED" : "FAILED"));
    System.out.println("testGetEarliestFreeze(): " + (testGetEarliestFreeze() ? "PASSED" : "FAILED"));
    System.out.println("testGetLatestThaw(): " + (testGetLatestThaw() ? "PASSED" : "FAILED"));
    System.out.println("testFilterByYear(): " + (testFilterByYear() ? "PASSED" : "FAILED"));
    System.out.println("testFilterByDuration(): " + (testFilterByDuration() ? "PASSED" : "FAILED"));

    boolean allTestsPassed = testAdd() && testRemove() && testRemoveOnly()
        && testRemoveDoesNotExist() && testIterators() && testMergeWinters() && testCleanData()
        && testAverageFreezeDuration() && testMaxFreezeDuration() && testMinFreezeDuration()
        && testGetEarliestFreeze() && testGetLatestThaw()
        && testFilterByYear() && testFilterByDuration();
    System.out.println("ALL TESTS: " + (allTestsPassed ? "PASSED" : "FAILED"));
  }
}
