import java.util.Iterator;
import java.util.NoSuchElementException;
public class IteratorFwd implements Iterator<LakeRecord> {
    private LinkedNode current;
    public IteratorFwd(LinkedNode start) {
        current = start;
    }
    public boolean hasNext() {
        return current.getNext() != null;
    }
    public LakeRecord next() {
        if (!hasNext()) {
            throw new NoSuchElementException("No more elements in the iterator.");
        }
        LakeRecord data = current.getLakeRecord();
        current = current.getNext();
        return data;
    }
}