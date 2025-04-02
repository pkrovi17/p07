import java.util.Iterator;
import java.util.NoSuchElementException;
public class IteratorBwd implements Iterator<LakeRecord> {
    private LinkedNode current;
    public IteratorBwd(LinkedNode start) {
        current = start;
    }
    public boolean hasNext() {
        return current.getPrev() != null;
    }
    public LakeRecord next() {
        if (!hasNext()) {
            throw new NoSuchElementException("No more elements in the iterator.");
        }
        LakeRecord data = current.getLakeRecord();
        current = current.getPrev();
        return data;
    }
}
