import java.util.Iterator;
import java.util.NoSuchElementException;
public class IteratorFwd implements Iterator<LakeRecord> {
    private LinkedNode current;
    public IteratorFwd(LinkedNode start) {
        current = start;
    }
    public boolean hasNext() {
        boolean hasNext = true;
        try{
            hasNext = (current.getNext() != null);
        } catch (NullPointerException e) {
            hasNext = false;
        }
        return hasNext;
    }
    public LakeRecord next() {
        if (current == null) {
            throw new NoSuchElementException("No more elements in the iterator.");
        }
        LakeRecord data = current.getLakeRecord();
        current = current.getNext();
        return data;
    }
}