package lab9;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import static org.junit.Assert.assertTrue;

/**
 *  A hash table-backed Map implementation. Provides amortized constant time
 *  access to elements via get(), remove(), and put() in the best case.
 *
 *  @author Joey Yu
 */
public class MyHashMap<K, V> implements Map61B<K, V> {

    private static final int DEFAULT_SIZE = 16;
    private static final double MAX_LF = 0.75;

    private ArrayMap<K, V>[] buckets;
    private int size;

    private int loadFactor() {
        return size / buckets.length;
    }

    public MyHashMap() {
        buckets = new ArrayMap[DEFAULT_SIZE];
        this.clear();
    }

    /* Removes all of the mappings from this map. */
    @Override
    public void clear() {
        this.size = 0;
        for (int i = 0; i < this.buckets.length; i += 1) {
            this.buckets[i] = new ArrayMap<>();
        }
    }

    /** Computes the hash function of the given key. Consists of
     *  computing the hashcode, followed by modding by the number of buckets.
     *  To handle negative numbers properly, uses floorMod instead of %.
     */
    private int hash(K key) {
        if (key == null) {
            return 0;
        }

        int numBuckets = buckets.length;
        return Math.floorMod(key.hashCode(), numBuckets);
    }

    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        return buckets[hash(key)].get(key);
    }

    /* Associates the specified value with the specified key in this map. */
    @Override
    public void put(K key, V value) {
        if(((double)size/buckets.length)>MAX_LF){
            ArrayMap[] temp=buckets;
            buckets=new ArrayMap[buckets.length*2];
            for (int i = 0; i < this.buckets.length; i += 1) {
                this.buckets[i] = new ArrayMap<>();
            }
            for(int i=0;i<temp.length;i++){
                Set<K> tempset = temp[i].keySet();
                for(K k:tempset){
                    //this.put(k, (V) temp[i].get(k));
                    int temphash=hash(k);
                    buckets[temphash].put(k,(V) temp[i].get(k));
                }
            }
        }
        int hashkey=hash(key);
        if(get(key)==null){
            buckets[hashkey].put(key,value);
            size++;
        }else{
            buckets[hashkey].put(key,value);
        }
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size;
    }

    //////////////// EVERYTHING BELOW THIS LINE IS OPTIONAL ////////////////

    /* Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        Set<K> result=new HashSet<>();
        for(int i=0;i< buckets.length;i++){
            ArrayMap<K,V> temp=buckets[i];
            for(K k:temp.keySet()){
                result.add(k);
            }
        }
        return result;
    }

    /* Removes the mapping for the specified key from this map if exists.
     * Not required for this lab. If you don't implement this, throw an
     * UnsupportedOperationException. */
    @Override
    public V remove(K key) {
        if(get(key)==null){
            return null;
        }
        int hashcode=hash(key);
        size--;
        return buckets[hashcode].remove(key);

    }

    /* Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for this lab. If you don't implement this,
     * throw an UnsupportedOperationException.*/
    @Override
    public V remove(K key, V value) {

        if(get(key)==null){
            return null;
        }
        int hashcode=hash(key);
        if(get(key)==value){
            size--;
        }
        return buckets[hashcode].remove(key,value);
    }

    @Override
    public Iterator<K> iterator() {
        return this.keySet().iterator();
    }

//    public static void main(String[] args) {
//        MyHashMap<String, Integer> b = new MyHashMap<String, Integer>();
//        for (int i = 0; i < 455; i++) {
//            b.put("hi" + i, 1);
//            //make sure put is working via containsKey and get
////            boolean result=(null != b.get("hi" + i)
////                    && b.containsKey("hi" + i));
//            System.out.println(b.get("hi" + i));
//        }
//        System.out.println(b.size());
//        b.put("hi" + 49, 1);
//        System.out.println(b.size());
//        System.out.println(b.get("hi" + 49));
//        System.out.println(b.containsKey("hi49"));

    }

