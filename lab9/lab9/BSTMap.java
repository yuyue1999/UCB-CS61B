package lab9;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Implementation of interface Map61B with BST as core data structure.
 *
 * @author Your name here
 */
public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private class Node {
        /* (K, V) pair stored in this Node. */
        private K key;
        private V value;

        /* Children of this Node. */
        private Node left;
        private Node right;

        private Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    private Node root;  /* Root node of the tree. */
    private int size; /* The number of key-value pairs in the tree */

    /* Creates an empty BSTMap. */
    public BSTMap() {
        this.clear();
    }

    /* Removes all of the mappings from this map. */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    /** Returns the value mapped to by KEY in the subtree rooted in P.
     *  or null if this map contains no mapping for the key.
     */
    private V getHelper(K key, Node p) {
        if(p==null){
            return null;
        }
        if(p.key.compareTo(key)==0){
            return p.value;
        }
        if(p.key.compareTo(key)<0){
            return getHelper(key,p.right);
        }else{
            return getHelper(key,p.left);
        }
    }

    /** Returns the value to which the specified key is mapped, or null if this
     *  map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        return getHelper(key,root);
    }

    /** Returns a BSTMap rooted in p with (KEY, VALUE) added as a key-value mapping.
      * Or if p is null, it returns a one node BSTMap containing (KEY, VALUE).
     */
    private Node putHelper(K key, V value, Node p) {
        if(p==null){
            return new Node(key,value);
        }
        if(p.key.compareTo(key)<0){
            p.right=putHelper(key,value,p.right);
        }else{
            p.left=putHelper(key,value,p.left);
        }
        return p;
    }

    /** Inserts the key KEY
     *  If it is already present, updates value to be VALUE.
     */
    @Override
    public void put(K key, V value) {
        Node temp=root;
//        if(root==null){
//            root=new Node(key,value);
//            return;
//        }
        if(get(key)!=null){
            while(temp.key!=key){
                if(temp.key.compareTo(key)<0){
                    temp=temp.right;
                }else {
                    temp=temp.left;
                }
            }
            temp.value=value;
            return;
        }
        root=putHelper(key,value,root);
        size++;
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return this.size;
    }

    //////////////// EVERYTHING BELOW THIS LINE IS OPTIONAL ////////////////
    private K keySetHelper(Set<K> temp, Node p){
        if(p.left==null && p.right==null) {
            return p.key;
        }else if(p.left==null && p.right!=null){
            temp.add(keySetHelper(temp,p.right));
            return p.key;
        }else if(p.left!=null && p.right==null){
            temp.add(keySetHelper(temp,p.left));
            return p.key;
        }
            temp.add(keySetHelper(temp,p.left));
            temp.add(keySetHelper(temp,p.right));
            return p.key;

    }
    /* Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        Set<K> result=new HashSet<>();
        result.add(keySetHelper(result,root));
        return result;
    }

    /** Removes KEY from the tree if present
     *  returns VALUE removed,
     *  null on failed removal.
     */
    private Node findMin(Node p){
        if(p==null){
            return null;
        }
        while(p.left!=null){
            p=p.left;
        }
        return p;
    }
    private Node removeHelper1(K key, Node p){
        if(p==null){
            return null;
        }
        if(p.key.compareTo(key)<0){
            p.right=removeHelper1(key,p.right);
        }else if(p.key.compareTo(key)>0){
            p.left=removeHelper1(key,p.left);
        }else if(p.left!=null && p.right!=null){
            K rightmin=findMin(p.right).key;
            p.key=findMin(p.right).key;
            p.value=findMin(p.right).value;
            p.right=removeHelper1(rightmin,p.right);
        }else{
            p=(p.left!=null)? p.left :p.right;
        }
        return p;
    }
    @Override
    public V remove(K key) {
        V result=get(key);
        removeHelper1(key,root);
        return result;
    }

    /** Removes the key-value entry for the specified key only if it is
     *  currently mapped to the specified value.  Returns the VALUE removed,
     *  null on failed removal.
     **/
    @Override
    public V remove(K key, V value) {
        if(get(key)!=value){
            return  null;
        }
        V result=get(key);
        removeHelper1(key,root);
        return result;
    }

    @Override
    public Iterator<K> iterator() {
        return keySet().iterator();
    }
    public static void main(String[] args) {
        BSTMap<String, Integer> bstmap = new BSTMap<>();
        bstmap.put("hello", 5);
        bstmap.put("cat", 10);
        bstmap.put("fish", 22);
        bstmap.put("zebra", 90);
        bstmap.put("apple",50);
        bstmap.put("zebra", 100);
        bstmap.put("zzebra", 200);
        bstmap.put("yoga", 150);
        //System.out.println(bstmap.remove("hello",5));
        //System.out.println(bstmap.get("ebra"));
       // System.out.println(bstmap.size());
//        Set<String> keyset = bstmap.keySet();
//        for(String s:keyset){
//            System.out.println(s);
//        }
        Iterator<String> I= bstmap.iterator();
        while(I.hasNext()){
           String s= I.next();
            System.out.println(s);
        }

    }


}
