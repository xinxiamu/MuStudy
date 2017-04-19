package co.jufeng.string;

class IntHashMap {

    private transient Entry table[];

    private transient int count;

    private int threshold;

    private final float loadFactor;

    private static class Entry {
        final int hash;
        @SuppressWarnings("unused")
		final int key; // TODO not read; seems to be always same as hash
        Object value;
        Entry next;

        protected Entry(int hash, int key, Object value, Entry next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    public IntHashMap() {
        this(20, 0.75f);
    }

    public IntHashMap(int initialCapacity) {
        this(initialCapacity, 0.75f);
    }

    public IntHashMap(int initialCapacity, float loadFactor) {
        super();
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
        }
        if (loadFactor <= 0) {
            throw new IllegalArgumentException("Illegal Load: " + loadFactor);
        }
        if (initialCapacity == 0) {
            initialCapacity = 1;
        }

        this.loadFactor = loadFactor;
        table = new Entry[initialCapacity];
        threshold = (int) (initialCapacity * loadFactor);
    }

    public int size() {
        return count;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public boolean contains(Object value) {
        if (value == null) {
            throw new NullPointerException();
        }

        Entry tab[] = table;
        for (int i = tab.length; i-- > 0;) {
            for (Entry e = tab[i]; e != null; e = e.next) {
                if (e.value.equals(value)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean containsValue(Object value) {
        return contains(value);
    }

    public boolean containsKey(int key) {
        Entry tab[] = table;
        int hash = key;
        int index = (hash & 0x7FFFFFFF) % tab.length;
        for (Entry e = tab[index]; e != null; e = e.next) {
            if (e.hash == hash) {
                return true;
            }
        }
        return false;
    }

    public Object get(int key) {
        Entry tab[] = table;
        int hash = key;
        int index = (hash & 0x7FFFFFFF) % tab.length;
        for (Entry e = tab[index]; e != null; e = e.next) {
            if (e.hash == hash) {
                return e.value;
            }
        }
        return null;
    }

    protected void rehash() {
        int oldCapacity = table.length;
        Entry oldMap[] = table;

        int newCapacity = oldCapacity * 2 + 1;
        Entry newMap[] = new Entry[newCapacity];

        threshold = (int) (newCapacity * loadFactor);
        table = newMap;

        for (int i = oldCapacity; i-- > 0;) {
            for (Entry old = oldMap[i]; old != null;) {
                Entry e = old;
                old = old.next;

                int index = (e.hash & 0x7FFFFFFF) % newCapacity;
                e.next = newMap[index];
                newMap[index] = e;
            }
        }
    }

    public Object put(int key, Object value) {
        // Makes sure the key is not already in the hashtable.
        Entry tab[] = table;
        int hash = key;
        int index = (hash & 0x7FFFFFFF) % tab.length;
        for (Entry e = tab[index]; e != null; e = e.next) {
            if (e.hash == hash) {
                Object old = e.value;
                e.value = value;
                return old;
            }
        }

        if (count >= threshold) {
            // Rehash the table if the threshold is exceeded
            rehash();

            tab = table;
            index = (hash & 0x7FFFFFFF) % tab.length;
        }

        // Creates the new entry.
        Entry e = new Entry(hash, key, value, tab[index]);
        tab[index] = e;
        count++;
        return null;
    }

    public Object remove(int key) {
        Entry tab[] = table;
        int hash = key;
        int index = (hash & 0x7FFFFFFF) % tab.length;
        for (Entry e = tab[index], prev = null; e != null; prev = e, e = e.next) {
            if (e.hash == hash) {
                if (prev != null) {
                    prev.next = e.next;
                } else {
                    tab[index] = e.next;
                }
                count--;
                Object oldValue = e.value;
                e.value = null;
                return oldValue;
            }
        }
        return null;
    }

    public synchronized void clear() {
        Entry tab[] = table;
        for (int index = tab.length; --index >= 0;) {
            tab[index] = null;
        }
        count = 0;
    }
    
}
