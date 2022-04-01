package A2;

public interface List<T> {
    public int size();
    public boolean add(T item);
    public void add(int pos, T item);
    public boolean contains(T item);
    public Object get(int pos);
    public Object removeLast();
    public boolean remove(T item);
    public Object removeFromEnd(int pos);
}
