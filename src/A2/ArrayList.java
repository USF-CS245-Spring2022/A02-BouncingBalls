package A2;

class ArrayList<T> implements List<T> {

    Object[] arr;
    int size;

    /**
     * Constructor
     */
    public ArrayList() {
        arr = new Object[10];
        size = 0;
    }


    /**
     * @return the number of items in the ArrayList
     */
    @Override
    public int size() {
        return size;
    }


    /**
     * Doubles the length of the array; called by add methods when the array is full
     */
    private void growArray() {
        Object[] newArr = new Object[size * 2];
        for (int i = 0; i < size; i++)
            newArr[i] = arr[i];
        arr = newArr;
    }


    /**
     * Adds a new element to the end of the ArrayList
     * @param item new element
     * @return true if add was successful
     */
    @Override
    public boolean add(T item) {
        if (size == arr.length)
            growArray();
        arr[size++] = item;
        return true;
    }


    /**
     * Adds a new element to a specified position in the ArrayList
     * @param pos index of the new element
     * @param item new element
     */
    @Override
    public void add(int pos, T item) {
        if (pos < 0 || pos > size)
            throw new IndexOutOfBoundsException();
        if (size == arr.length)
            growArray();
        for (int i = size++; i > pos; i--)
            arr[i] = arr[i - 1];
        arr[pos] = item;
    }


    /**
     * Returns true if an array contains an item, false otherwise
     * @param item to be found
     * @return true if contains, false otherwise
     */
    public boolean contains(T item) {
        for (int i = 0; i < size; i++) {
            if (arr[i].equals(item)) {
                return true;
            }
        }
        return false;
    }


    /**
     * Gets an element specified by position
     * @param pos index of the desired element
     * @return element
     */
    @Override
    public Object get(int pos) {
        if (pos < 0 || pos >= size)
            throw new IndexOutOfBoundsException();
        return arr[pos];
    }


    /**
     * Removes the last element in the list
     * @return item
     */
    public Object removeLast() {
        Object item = arr[size - 1];
        size--;
        return item;
    }


    /**
     * Removes an element specified by position
     * @param item to be removed
     * @return removed element
     */
    @Override
    public boolean remove(T item) {
        int idxFound = -1;
        for (int i = 0; i < size; i++) {
            if (arr[i].equals(item)) { //remove
                idxFound = i;
                break;
            }
        }
        if (idxFound != -1) {
            for (int i = idxFound; i < size - 1; i++)
                arr[i] = arr[i + 1];
            size--;
            return true;
        }
        return false;
    }


    /**
     * Removes an item from the end of a list with specific positions from the end
     * @param pos number of positions from the end an item should be removed
     * @return
     */
    public Object removeFromEnd(int pos) {

        if (!(pos >= 0 && pos < size)) {
            throw new IndexOutOfBoundsException();
        }

        if (pos == 0) { //removing "last" pos item, but nonexistent
            return null;
        }

        int position  = size - pos;

        Object item = arr[position];

        for (int i = position; i < size - 1; i++)
            arr[i] = arr[i + 1];
        size--;

        return item;
    }
}
