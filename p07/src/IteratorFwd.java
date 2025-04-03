//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Iterator Forward
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
//                  - lecture slides describing what the iterator interface is
// Online Sources:  W3 Schools and Oracle
//                  - https://www.w3schools.com/java/java_iterator.asp
//                  - https://docs.oracle.com/javase/7/docs/api/java/util/Iterator.html
//
///////////////////////////////////////////////////////////////////////////////
// imports
import java.util.Iterator;
import java.util.NoSuchElementException;
/**
 * IteratorFwd class implements the Iterator interface for traversing a linked list of LakeRecords.
 * It provides methods for accessing next item and checking if there is another to access.
 */
public class IteratorFwd implements Iterator<LakeRecord> {
    private LinkedNode current;
    /**
     * Constructor to initialize the iterator with the starting node.
     * 
     * @param start The starting node of the linked list.
     */
    public IteratorFwd(LinkedNode start) {
        current = start;
    }
    /**
     * Checks if there is a next element in the iterator.
     * 
     * @return true if there is a next element, false otherwise.
     */
    public boolean hasNext() {
        boolean hasNext = true;
        try{
            hasNext = (current.getNext() != null);
        } catch (NullPointerException e) {
            hasNext = false;
        }
        return hasNext;
    }
    /**
     * Returns the next element in the iterator.
     * 
     * @return the next LakeRecord in the iterator.
     * @throws NoSuchElementException if there are no more elements to iterate over.
     */
    public LakeRecord next() {
        if (current == null) {
            throw new NoSuchElementException("No more elements in the iterator.");
        }
        LakeRecord data = current.getLakeRecord();
        current = current.getNext();
        return data;
    }
}