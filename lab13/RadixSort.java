import java.util.*;

/**
 * Class for doing Radix sort
 *
 * @author Akhil Batra, Alexander Hwang
 *
 */
public class RadixSort {
    /**
     * Does LSD radix sort on the passed in array with the following restrictions:
     * The array can only have ASCII Strings (sequence of 1 byte characters)
     * The sorting is stable and non-destructive
     * The Strings can be variable length (all Strings are not constrained to 1 length)
     *
     * @param asciis String[] that needs to be sorted
     *
     * @return String[] the sorted array
     */
    public static String[] sort(String[] asciis) {
        // TODO: Implement LSD Sort
        String[] temp=new String[asciis.length];
        for(int i=0;i< temp.length;i++){
            temp[i]=asciis[i];
        }
        LinkedList<String>[] store=new LinkedList[256];
        for(int i=0;i<store.length;i++){
            store[i]=new LinkedList<>();
        }
        int MaxLength=Integer.MIN_VALUE;
        for(int i=0;i< asciis.length;i++){
            if(asciis[i].length()>MaxLength){
                MaxLength=asciis[i].length();
            }
        }
        HashMap<String,Integer> spaceTimes=new HashMap<>();
        for(int i=0;i<temp.length;i++){
            spaceTimes.put(temp[i], MaxLength- temp[i].length());
        }
        for(int i=MaxLength-1;i>=0;i--){
            for(int j=0;j<temp.length;j++){
                if(temp[j].length()<i+1){
                    store[32].add(temp[j]);
                }else {
                    store[(int)temp[j].charAt(i)].add(temp[j]);
                }
            }
            int n=0;
            for(int k=0;k< store.length;k++){
                while(!store[k].isEmpty()){
                    temp[n]=store[k].removeFirst();
                    n++;
                }
            }
        }
        return temp;
    }

    /**
     * LSD helper method that performs a destructive counting sort the array of
     * Strings based off characters at a specific index.
     * @param asciis Input array of Strings
     * @param index The position to sort the Strings on.
     */
    private static void sortHelperLSD(String[] asciis, int index) {
        // Optional LSD helper method for required LSD radix sort
        return;
    }

    /**
     * MSD radix sort helper function that recursively calls itself to achieve the sorted array.
     * Destructive method that changes the passed in array, asciis.
     *
     * @param asciis String[] to be sorted
     * @param start int for where to start sorting in this method (includes String at start)
     * @param end int for where to end sorting in this method (does not include String at end)
     * @param index the index of the character the method is currently sorting on
     *
     **/
    private static void sortHelperMSD(String[] asciis, int start, int end, int index) {
        // Optional MSD helper method for optional MSD radix sort
        return;
    }

//    public static void main(String[] args) {
//        String[] temp={"bcd", "cb","a","apf","ad","!apple"};
//        String[] result=sort(temp);
//        for(String s:result){
//            System.out.println(s);
//        }



    }


