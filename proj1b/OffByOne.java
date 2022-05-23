/**
 * @author YY
 * @create 2022-05-23 5:21 PM
 */
public class OffByOne implements CharacterComparator{

    @Override
    public boolean equalChars(char x, char y) {
        if(x+1==y || x-1==y){
            return true;
        }
        return false;
    }

//    public static void main(String[] args) {
//        OffByOne obo = new OffByOne();
//        System.out.println(obo.equalChars('a', 'b'));
//        System.out.println(obo.equalChars('r', 'q'));
//        System.out.println(obo.equalChars('a', 'e'));
//        System.out.println(obo.equalChars('z', 'a'));
//        System.out.println(obo.equalChars('a', 'a'));
//        System.out.println(obo.equalChars('&', '%'));
//    }
}
