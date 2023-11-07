package assn05;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MaxBinHeapER  <V, P extends Comparable<P>> implements BinaryHeap<V, P> {

    private List<Prioritized<V, P>> _heap;

    /**
     * Constructor that creates an empty heap of hospital.Prioritized objects.
     */
    public MaxBinHeapER() {
        _heap = new ArrayList<>();
    }

    /**
     * Constructor that builds a heap given an initial array of hospital.Prioritized objects.
     */
    // TODO (Part 3): overloaded constructor
    public MaxBinHeapER(Prioritized<V, P>[] initialEntries ) {
        _heap = new ArrayList<>();
        for (Prioritized<V, P> entry : initialEntries)
            _heap.add(entry);
        
        for (int i = size() / 2 - 1; i >= 0; i--)
            heapDown(i);
    }

    @Override
    public int size() {
        return _heap.size();
    }

    // TODO: enqueue
    @Override
    public void enqueue(V value, P priority) {
        Patient patient = new Patient<V, P>(value, priority);
        _heap.add(patient);
        heapUp(size() - 1);
    }

    // TODO: enqueue
    public void enqueue(V value) {
        Patient patient = new Patient<V, P>(value);
        _heap.add(patient);
        heapUp(size() - 1);
    }

    // TODO: dequeue
    @Override
    public V dequeue() {
        if (size() == 0)
            return null;

        
        Prioritized<V, P> max = _heap.get(0);
        _heap.set(0, _heap.get(size() - 1));
        _heap.remove(size() - 1);
        
        if (size() > 1)
            heapDown(0);
        
        return max.getValue();

    }

    // TODO: getMax
    @Override
    public V getMax() {
        if (size() == 0)
            return null;

        return _heap.get(0).getValue();
    }

    @Override
    public Prioritized<V, P>[] getAsArray() {
        Prioritized<V,P>[] result = (Prioritized<V, P>[]) Array.newInstance(Prioritized.class, size());
        return _heap.toArray(result);
    }

    private void heapUp(int index) {
        if (index == 0)
            return;

        int parentIndex = (index - 1) / 2;
        if (_heap.get(index).compareTo(_heap.get(parentIndex)) > 0) {
            swap(index, parentIndex);
            heapUp(parentIndex);
        }
    }
    
    private void heapDown(int index) {
        int left = 2 * index + 1;
        int right = 2 * index + 2;
        int maxIndex = index;
        
        if (left < size() && _heap.get(left).compareTo(_heap.get(maxIndex)) > 0)
            maxIndex = left;
        
        if (right < size() && _heap.get(right).compareTo(_heap.get(maxIndex)) > 0)
            maxIndex = right;
        
        if (maxIndex != index) {
            swap(index, maxIndex);
            heapDown(maxIndex);
        }
    }

    private void swap(int index1, int index2) {
        Prioritized<V, P> temp = _heap.get(index1);
        _heap.set(index1, _heap.get(index2));
        _heap.set(index2, temp);
    }
}
