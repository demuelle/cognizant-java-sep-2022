import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class RandomNumberExample {

    public static void main(String[] args) {
        Random myRandomNumberGenerator = new Random();

        System.out.println(myRandomNumberGenerator.nextInt());
        System.out.println(myRandomNumberGenerator.nextDouble());
        System.out.println(myRandomNumberGenerator.nextBoolean());

        for (int i = 0; i < 50; i++)
            System.out.print((myRandomNumberGenerator.nextInt(10)) + " ");

        List<String> states = new ArrayList<>(Arrays.asList("Alabama", "Arkansas", "Arizona", "Alaska", "California"));
        System.out.println("\nRandom state! " + states.get(myRandomNumberGenerator.nextInt(states.size())));
    }
}
