package synthesizer;

/**
 * @author YY
 * @create 2022-05-28 3:11 PM
 */
public abstract class AbstractBoundedQueue<T> implements BoundedQueue<T> {
    protected int fillCount;
    protected int capacity;
    public int capacity(){
        return capacity;
    };
    public int fillCount(){
        return fillCount;
    };
    //public abstract boolean isEmpty();
    //public abstract boolean isFull();
    //public abstract T peek();
    //public abstract T dequeue();
    //public abstract void enqueue(T x);
}
