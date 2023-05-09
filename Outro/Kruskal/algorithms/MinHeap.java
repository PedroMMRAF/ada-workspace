package algorithms;

import java.util.ArrayList;
import java.util.List;

public class MinHeap<K extends Comparable<? super K>, V> implements MinPriorityQueue<K, V> {
    private List<Entry<K, V>> entries;

    public MinHeap() {
        entries = new ArrayList<>();
    }

    @Override
    public boolean isEmpty() {
        return entries.isEmpty();
    }

    @Override
    public int size() {
        return entries.size();
    }

    @Override
    public Entry<K, V> minEntry() throws EmptyQueueException {
        if (isEmpty()) {
            throw new EmptyQueueException();
        }
        return entries.get(0);
    }

    @Override
    public void insert(K key, V value) {
        entries.add(new Entry<>(key, value));
        upHeap(entries.size() - 1);
    }

    @Override
    public Entry<K, V> removeMin() throws EmptyQueueException {
        if (isEmpty()) {
            throw new EmptyQueueException();
        }

        Entry<K, V> minEntry = entries.get(0);
        Entry<K, V> lastEntry = entries.remove(entries.size() - 1);

        if (!isEmpty()) {
            entries.set(0, lastEntry);
            downHeap(0);
        }

        return minEntry;
    }

    private void upHeap(int i) {
        Entry<K, V> node = entries.get(i);

        while (i > 0) {
            int parentIndex = (i - 1) / 2;
            Entry<K, V> parent = entries.get(parentIndex);

            if (node.compareTo(parent) >= 0) {
                break;
            }

            entries.set(i, parent);
            i = parentIndex;
        }

        entries.set(i, node);
    }

    private void downHeap(int i) {
        Entry<K, V> node = entries.get(i);
        int size = entries.size();

        while (i < size / 2) {
            int leftChildIndex = 2 * i + 1;
            int rightChildIndex = 2 * i + 2;

            Entry<K, V> minChild = entries.get(leftChildIndex);

            if (rightChildIndex < size) {
                Entry<K, V> rightChild = entries.get(rightChildIndex);

                if (rightChild.compareTo(minChild) < 0) {
                    leftChildIndex = rightChildIndex;
                    minChild = rightChild;
                }
            }

            if (minChild.compareTo(node) >= 0) {
                break;
            }

            entries.set(i, minChild);
            i = leftChildIndex;
        }

        entries.set(i, node);
    }
}
