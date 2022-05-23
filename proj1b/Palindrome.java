import java.util.Arrays;

/**
 * @author YY
 * @create 2022-05-23 4:27 PM
 */
public class Palindrome {
    public Deque<Character> wordToDeque(String word){
        char[] temp= word.toCharArray();
        LinkedListDeque<Character> LLD=new LinkedListDeque<>();
        for(int i=0;i<temp.length;i++){
            LLD.addLast(temp[i]);
        }
        return LLD;
    }
    public boolean isPalindrome(String word){
        if(word.length()==1 || word.length()==0){
            return true;
        }
        Deque<Character> result=new LinkedListDeque<>();
        Deque<Character> temp=wordToDeque(word);
        int length=temp.size();
        for(int i=0;i<length;i++){
            char removed=temp.removeLast();
            result.addLast(removed);
        }
        String finall="";
        for(int j=0;j<result.size();j++){
            finall=finall+result.get(j);
        }
        if(finall.equals(word)){
            return true;
        }
        return false;
    }
    public boolean isPalindrome(String word, CharacterComparator cc){
        if(word.length()==1 || word.length()==0){
            return true;
        }
        char[] words=word.toCharArray();
        Deque<Character> result=new LinkedListDeque<>();
        Deque<Character> temp=wordToDeque(word);
        int length=temp.size();
        for(int i=0;i<length;i++){
            char removed=temp.removeLast();
            result.addLast(removed);
        }
        if(word.length()%2==0){
            for(int j=0;j<result.size();j++){
                boolean off_by_one=cc.equalChars(result.get(j),words[j]);
                if(off_by_one==false){
                    return false;
                }
            }
            return true;
        }
        int mid=length/2;
        for(int j=0;j<result.size();j++){
            boolean off_by_one=cc.equalChars(result.get(j),words[j]);

            if(off_by_one==false && j!=mid){
                return false;
            }
        }
        return true;

    }

    public static void main(String[] args) {
        String word="flake";
        Palindrome P=new Palindrome();
        //Deque<Character> result=P.wordToDeque(word);
        //System.out.println(result.size());
        //result.printDeque();
        P.isPalindrome(word);
        char[] temp=word.toCharArray();
        System.out.println(temp.length);
        OffByOne off=new OffByOne();
        System.out.println(P.isPalindrome(word, off));

    }
}
