package algorithms;

import java.io.Serializable;

public interface MinPriorityQueue<K extends Comparable<? super K>, V> extends Serializable {
    // Returns true iff the priority queue contains no entries.
    boolean isEmpty();

    // Returns the number of entries in the priority queue.
    int size();

    // Returns an entry with the smallest key in the priority queue.
    Entry<K, V> minEntry() throws EmptyQueueException;

    // Inserts the entry (key, value) in the priority queue.
    void insert(K key, V value);

    // Removes an entry with the smallest key from the priority queue
    // and returns that entry.
    Entry<K, V> removeMin() throws EmptyQueueException;
}