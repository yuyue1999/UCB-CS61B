package synthesizer;// TODO: Make sure to make this class a part of the synthesizer package
// package <package name>;
import synthesizer.AbstractBoundedQueue;

import java.util.Iterator;

//TODO: Make sure to make this class and all of its methods public
//finished
//TODO: Make sure to make this class extend AbstractBoundedQueue<t>
//finished
public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;            // index for the next dequeue or peek
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        // TODO: Create new array with capacity elements.
        //       first, last, and fillCount should all be set to 0.
        //       this.capacity should be set appropriately. Note that the local variable
        //       here shadows the field we inherit from AbstractBoundedQueue, so
        //       you'll need to use this.capacity to set the capacity.
        //finished
        this.fillCount=0;
        this.first=0;
        this.last=0;
        this.rb= (T[])new Object[capacity];
        this.capacity=capacity;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
    public void enqueue(T x) {
        // TODO: Enqueue the item. Don't forget to increase fillCount and update last.
        //finished
        if(isFull()){
            throw new RuntimeException("Ring buffer overflow");
        }
        rb[last]=x;
        last=(last+1)%capacity;
        fillCount++;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    public T dequeue() {
        // TODO: Dequeue the first item. Don't forget to decrease fillCount and update
        //finished
        if(isEmpty()){
            throw new RuntimeException("Ring buffer underflow");
        }
        T result=rb[first];
        rb[first] = null;
        first=(first+1)%capacity;
        fillCount--;
        return result;
    }
    /**
     * Return oldest item, but don't remove it.
     */
    public T peek() {
        // TODO: Return the first item. None of your instance variables should change.
        //finished
        return rb[first];
    }

    @Override
    public Iterator<T> iterator() {
        return new myIterator();
    }
    private class myIterator implements Iterator<T>{
        private int position;
        private T[] new_rb;
        public myIterator(){
            position=first;
            new_rb=(T[])new Object[fillCount];
            for(int i=0;i<fillCount;i++){
                new_rb[i]=rb[(first+i)%capacity];
            }
        }
        @Override
        public boolean hasNext() {
            return position<fillCount;
        }

        @Override
        public T next() {
            T result=rb[position];
            position++;
            return result;
        }
    }

//    public static void main(String[] args) {
//        ArrayRingBuffer<Integer> A=new ArrayRingBuffer<>(7);
//        for(int i=0;i< A.capacity();i++){
//            A.enqueue(i);
//        }
//        Iterator<Integer> I=A.iterator();
//        while (I.hasNext()){
//            System.out.println(I.next());
//        }
//    }
    // TODO: When you get to part 5, implement the needed code to support iteration.
}
