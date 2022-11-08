import org.junit.Test;

import static org.junit.Assert.*;

public class CodingBatTest {
    /*

Given three ints, a b c, return true if one of b or c is "close" (differing from a by at most 1),
while the other is "far", differing from both other values by 2 or more. Note: Math.abs(num) computes the
absolute value of a number.


closeFar(1, 2, 10) → true
closeFar(1, 2, 3) → false
closeFar(4, 1, 3) → true
     */
    @Test
    public void shouldReturnTrueWhenCloseAndFar() {
        CodingBat codingBat = new CodingBat();
        boolean expectedResult = true;
        boolean actualResult = codingBat.closeFar(1, 2, 10);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void shouldReturnVFalseWhenNumbersAreConsecutive() {
        CodingBat codingBat = new CodingBat();
        boolean expectedResult = false;
        boolean actualResult = codingBat.closeFar(1, 2, 3);

        assertEquals(expectedResult, actualResult);
    }

}