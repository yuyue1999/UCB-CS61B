import javax.swing.event.MouseInputAdapter;

/**
 * @author YY
 * @create 2022-05-20 8:12 PM
 */
public class LinkedListDeque<T> {
    private IntNode Sentinel;
    private int size;
    private class IntNode{
        public IntNode prev;
        public T item;
        public IntNode next;
        public IntNode(T item, IntNode prev,IntNode next){
            this.item=item;
            this.prev=prev;
            this.next=next;
        }
    }
    public LinkedListDeque(){
        size=0;
        this.Sentinel=new IntNode(null,null,null);
        this.Sentinel.next=this.Sentinel;
        this.Sentinel.prev=this.Sentinel;
    }
//    public LinkedListDeque(T i){
//        size=1;
//        this.Sentinel=new IntNode(null,null,null);
//        this.Sentinel.next=new IntNode(i,Sentinel,Sentinel);
//        this.Sentinel.prev=this.Sentinel.next;
//    }
    //add and remove operations must not involve any looping or recursion.
    //A single such operation must take “constant time”, i.e. execution time should not depend on the size of the deque.
    public void addFirst(T item){
        if(size>=1){
        Sentinel.next=new IntNode(item,Sentinel,Sentinel.next);
        Sentinel.next.next.prev=Sentinel.next;
        size++;
        }else if(size==0){
            Sentinel.next=new IntNode(item,Sentinel,Sentinel);
            Sentinel.prev=this.Sentinel.next;
            size++;
        }
    }
    public void addLast(T item){
        if(size>=1){
            Sentinel.prev=new IntNode(item,Sentinel.prev,Sentinel);
            Sentinel.prev.prev.next=Sentinel.prev;
            size++;
        }else if(size==0){
            Sentinel.next=new IntNode(item,Sentinel,Sentinel);
            Sentinel.prev=this.Sentinel.next;
            size++;
        }

    }
    public boolean isEmpty(){
        if(size==0){
            return true;
        }
        return false;
    }
    public int size(){
        return this.size;
    }
    public void printDeque(){
        IntNode p=Sentinel;
        for(int i=0;i<size;i++){
            p=p.next;
            System.out.print(p.item+" ");
        }
    }
    public T removeFirst(){
        if(size==0){
            return null;
        }
        if(size>=2){
            IntNode remove=Sentinel.next;
            T result=remove.item;
            IntNode new_next=Sentinel.next.next;
            Sentinel.next=new_next;
            new_next.prev=Sentinel;
            remove=null;
            size--;
            return result;
        }

        IntNode remove=Sentinel.next;
        T result=remove.item;
        Sentinel.next=Sentinel;
        Sentinel.prev=Sentinel;
        remove=null;
        size--;
        return result;


    }
    public T removeLast(){
        if(size==0){
            return null;
        }
        if(size==1){
            IntNode remove=Sentinel.next;
            T result=remove.item;
            Sentinel.next=Sentinel;
            Sentinel.prev=Sentinel;
            remove=null;
            size--;
            return result;
        }
        IntNode remove=Sentinel.prev;
        T result=remove.item;
        IntNode LastSecond_newlast=Sentinel.prev.prev;
        Sentinel.prev=LastSecond_newlast;
        LastSecond_newlast.next=Sentinel;
        remove=null;
        size--;
        return result;
    }
    //must use iteration, not recursion.
    public T get(int index){
        IntNode p=Sentinel;
        for(int i=0;i<=index;i++){
            p=p.next;
        }
        return p.item;
    }
    //Same as get, but uses recursion.
    private IntNode helper_getRecursive(IntNode p,int index){
        if(index==0){
            return p;
        }
        return helper_getRecursive(p.next,index-1);
    }

    public T getRecursive(int index){
        IntNode p=Sentinel.next;
        IntNode result=helper_getRecursive(p,index);
        return result.item;

    }

//    public static void main(String[] args) {
//        LinkedListDeque<Integer> D=new LinkedListDeque<>();
//        D.addFirst(10);
//        D.addFirst(5);
//        D.addLast(15);
//
//        LinkedListDeque<String> S=new LinkedListDeque<>();
//        S.addFirst("tom2");
//        S.addFirst("tom1");
//        S.addLast("tom3");
//        S.printDeque();
//
//
//        //D.printDeque();
//
//    }
}
