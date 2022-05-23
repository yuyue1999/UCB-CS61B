import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author YY
 * @create 2022-05-23 6:47 PM
 */

import org.junit.Test;

    public class TestOffByN{

        // You must use this CharacterComparator and not instantiate
        // new ones, or the autograder might be upset.
        static CharacterComparator offByN = new OffByN(5);

        // Your tests go here.
        //Uncomment this class once you've created your CharacterComparator interface and OffByOne class.
        @Test
        public void testequalChars(){
            Palindrome p=new Palindrome();
            assertFalse(p.isPalindrome("flake",offByN));
            assertTrue(p.isPalindrome("f",offByN));
            assertTrue(p.isPalindrome("",offByN));
            assertFalse(p.isPalindrome("done",offByN));
        }
    }


