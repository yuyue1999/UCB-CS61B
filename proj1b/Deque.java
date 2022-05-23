/**
 * @author YY
 * @create 2022-05-23 4:17 PM
 */
public interface Deque<T> {
    public void addFirst(T item);
    public void addLast(T item);
    public boolean isEmpty();
    public int size();
    public void printDeque();
    public T removeFirst();
    public T removeLast();
    public T get(int index);
}
