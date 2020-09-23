import java.util.*;

public class PlanTest {
  public static void main(String[] args) {

    System.out.println();

    // Prompt user for how many tests they'd like to run.
    System.out.println("Welcome to CoursePlanner testing!");
    System.out.print("Enter how many tests you'd like to perform: ");

    // Grab user input.
    Scanner input = new Scanner(System.in);

    int n = input.nextInt();

    input.close();

    System.out.println();

    // Begin testing.
    Random r = new Random();

    for (int i = 0; i < n; i++) {

      // Build an array with at least 3 pairs, max of 10 pairs.
      int[][] array1 = prerequisiteGenerator(r.nextInt(8) + 3);

      // Test this plan with at least 2 classes, max 7 classes.
      CoursePlanner.plan(r.nextInt(6) + 2, array1);

    }

  }

  // this method generates a list of prerequisites as a 2D array
  private static int[][] prerequisiteGenerator(int size) {

    Random r = new Random();

    int[][] pre = new int[size][2];

    for (int i = 0; i < size; i++) {

      pre[i][0] = r.nextInt(11);

      int randInt = r.nextInt(11);

      while (randInt == pre[i][0]){

        randInt = r.nextInt(11);

      }

      pre[i][1] = randInt;

    }

    return pre;

  }

}
