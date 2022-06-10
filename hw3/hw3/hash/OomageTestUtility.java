package hw3.hash;

import java.util.HashMap;
import java.util.List;

public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        /* TODO:
         * Write a utility function that returns true if the given oomages
         * have hashCodes that would distribute them fairly evenly across
         * M buckets. To do this, convert each oomage's hashcode in the
         * same way as in the visualizer, i.e. (& 0x7FFFFFFF) % M.
         * and ensure that no bucket has fewer than N / 50
         * Oomages and no bucket has more than N / 2.5 Oomages.
         */
        HashMap<Integer,Integer> HM_II=new HashMap<>();
        int Size=oomages.size();
        for(Oomage O :oomages){
           int bucketNum = (O.hashCode() & 0x7FFFFFFF) % M;
            if(!HM_II.containsKey(bucketNum)){
                HM_II.put(bucketNum,1);
            }else {
                HM_II.put(bucketNum,HM_II.get(bucketNum)+1);
            }
        }
        for(int i:HM_II.values()){
            if(i<(Size/50) || i>(Size/2.5)){
                return false;
            }
        }
        return true;
    }
}
