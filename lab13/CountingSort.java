import java.util.Arrays;
import java.util.HashMap;

/**
 * Class with 2 ways of doing Counting sort, one naive way and one "better" way
 *
 * @author Akhil Batra, Alexander Hwang
 *
 **/
public class CountingSort {
    /**
     * Counting sort on the given int array. Returns a sorted version of the array.
     * Does not touch original array (non-destructive method).
     * DISCLAIMER: this method does not always work, find a case where it fails
     *
     * @param arr int array that will be sorted
     * @return the sorted array
     */
    public static int[] naiveCountingSort(int[] arr) {
        // find max
        int max = Integer.MIN_VALUE;
        for (int i : arr) {
            max = max > i ? max : i;
        }

        // gather all the counts for each value
        int[] counts = new int[max + 1];
        for (int i : arr) {
            counts[i]++;
        }

        // when we're dealing with ints, we can just put each value
        // count number of times into the new array
        int[] sorted = new int[arr.length];
        int k = 0;
        for (int i = 0; i < counts.length; i += 1) {
            for (int j = 0; j < counts[i]; j += 1, k += 1) {
                sorted[k] = i;
            }
        }

        // however, below is a more proper, generalized implementation of
        // counting sort that uses start position calculation
        int[] starts = new int[max + 1];
        int pos = 0;
        for (int i = 0; i < starts.length; i += 1) {
            starts[i] = pos;
            pos += counts[i];
        }

        int[] sorted2 = new int[arr.length];
        for (int i = 0; i < arr.length; i += 1) {
            int item = arr[i];
            int place = starts[item];
            sorted2[place] = item;
            starts[item] += 1;
        }

        // return the sorted array
        return sorted;
    }

    /**
     * Counting sort on the given int array, must work even with negative numbers.
     * Note, this code does not need to work for ranges of numbers greater
     * than 2 billion.
     * Does not touch original array (non-destructive method).
     *
     * @param arr int array that will be sorted
     */
    public static int[] betterCountingSort(int[] arr) {
        // TODO make counting sort work with arrays containing negative numbers.
        int j=0;
        for(int i:arr){
            if(i>=0){
                j++;
            }
        }
        if(j== arr.length){
            return naiveCountingSort(arr);
        }
        int[] positive=new int[j];
        int[] negative=new int[arr.length-j];
        int k=0;
        int m=0;
        for(int i:arr){
            if(i>=0){
                positive[k]=i;
                k++;
            }else{
                negative[m]=i;
                m++;
            }
        }
        int[] temp1=naiveCountingSort(positive);
        for(int i=0;i<negative.length;i++){
            negative[i]=-negative[i];
        }
        int[] temp2=naiveCountingSort(negative);
        int[] reverse=new int[temp2.length];
        for(int i=0;i<reverse.length;i++){
            reverse[i]=temp2[temp2.length-1-i];
        }
        for(int i=0;i< negative.length;i++){
            negative[i]=-reverse[i];
        }
        int [] result=new int[arr.length];
        int n=0;
        for(;n< negative.length;n++){
            result[n]=negative[n];
        }
        for(int i=0;i< positive.length;i++){
            result[n+i]=temp1[i];
        }


        return result;
    }
//    public static HashMap helper(int[] arr){
//        HashMap<Integer,Integer> HM=new HashMap<>();
//        for(int i=0;i<arr.length;i++){
//            if(HM.containsKey(arr[i])){
//                HM.put(arr[i],(HM.get(arr[i])+1));
//            }else{
//                HM.put(arr[i],1);
//            }
//        }
//        return HM;
//    }
//
    public static void main(String[] args) {
        int[] arr = {9, 5, -4, 2, 1, -2, 5, 3, 0, -2, 3, 1, 1};
        int[] nonNegative = {9, 5, 2, 1, 5, 3, 0, 3, 1, 1};
        int[] result=betterCountingSort(arr);
        int[] result2=betterCountingSort(nonNegative);
        for(int i:result2){
            System.out.println(i);
        }

    }
}
