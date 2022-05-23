/**
 * @author YY
 * @create 2022-05-23 6:41 PM
 */
public class OffByN implements CharacterComparator{
    private int N;
    public OffByN(int N){
        this.N=N;
    }
    @Override
    public boolean equalChars(char x, char y) {
        if(x+this.N==y || x-this.N==y){
            return true;
        }
        return false;
    }
}
