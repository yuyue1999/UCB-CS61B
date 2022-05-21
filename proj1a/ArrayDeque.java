/**
 * @author YY
 * @create 2022-05-21 9:37 AM
 */
public class ArrayDeque<T> {
    //The starting size of your array should be 8.
    //For arrays of length 16 or more, your usage factor should always be at least 25%.
    // For smaller arrays, your usage factor can be arbitrarily low.
    private int size;
    private T[] items;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque(){
        items=(T[]) new Object[8];
        size=0;
        this.nextFirst=0;
        this.nextLast=1;
    }
//    public ArrayDeque(int nextFirst){
//        items=(T[])new Object[8];
//        size=0;
//        this.nextFirst=nextFirst;
//        if(nextFirst==7){
//            this.nextLast=0;
//        }else{
//            this.nextLast=nextFirst+1;
//        }
//    }
    private void resize(int new_size){
        T[] newarray=(T[])new Object[new_size];
        for(int i=0;i<size;i++){
            newarray[i]=this.get(i);
        }
        items=newarray;
        this.nextFirst=new_size-1;
        this.nextLast=size;
    }

    //add and remove must take constant time, except during resizing operations.
    public void addFirst(T item){
        if(this.size==items.length){
            resize(size*2);
        }
        items[nextFirst]=item;
        size++;
        this.nextFirst=(nextFirst+items.length-1)%items.length;
    }

    public void addLast(T item){
        if(this.size==items.length){
            resize(size*2);
        }
        items[nextLast]=item;
        size++;
        this.nextLast=(nextLast+1+items.length)%items.length;
    }
    public boolean isEmpty(){
        if(size==0){
            return true;
        }
        return false;
    }
    //get and size must take constant time.
    public int size(){
        return this.size;
    }
    public void printDeque(){
        for(int i=0;i<size;i++){
            System.out.print(get(i)+" ");
        }
    }
    public T removeFirst(){
        if(size==0){
            return null;
        }
        T remove =items[(nextFirst+1)%items.length];
        items[(nextFirst+1)%items.length]=null;
        size--;
        this.nextFirst=(nextFirst+1)%items.length;
        if(items.length>=16 && (size/items.length)<0.25){
            resize(size*2);
        }
        return remove;
    }
    public T removeLast(){
        if(size==0){
            return null;
        }
        T remove=items[(nextLast-1)%items.length];
        items[(nextLast-1)%items.length]=null;
        size--;
        this.nextLast=(nextLast-1)%items.length;
        if(items.length>=16 && (size/items.length)<0.25){
            resize(size*2);
        }
        return remove;
    }
    public T get(int index){
        if(index<0 || index>size-1){
            return null;
        }
        return items[(nextFirst+index+1)%items.length];
    }

}
