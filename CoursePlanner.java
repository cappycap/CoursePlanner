import java.util.*;

public class CoursePlanner {
  // this method prints out: whether it is possible to take all the given courses and
  // one possible schedule for the given courses

  // Define a schedule variable globally.
  public static Stack<Integer> schedule = null;

  public static void plan(int numberOfCourses, int[][] prerequisites) {

    // Figure out whether this plan is valid.
    boolean valid = check(numberOfCourses, prerequisites);

    // Begin printing logic.
    System.out.print("Given " + Integer.toString(numberOfCourses) + " courses and prerequisites as ");

    pgPrint(prerequisites);

    System.out.println();

    if (valid) {

      System.out.println("It is possible to take all the given courses.");

      System.out.print("A possible schedule is ");

      while (!schedule.empty()) {

        System.out.print(schedule.pop());

        if (schedule.empty()) {

          System.out.print(".");

        } else {

          System.out.print(", ");

        }

      }

      System.out.println();

    } else {

      System.out.println("It is not possible to take all the given courses.");

    }

    System.out.println();

    // End printing logic.

    return;
  }

  // this is a helper method for plan; it returns a boolean to indicate if a given series of courses can be possibly scheduled
  public static boolean check(int numberOfCourses, int[][] prerequisites) {

    // Establish our boolean to return.
    boolean isLegit = false;

    // Generate Hashtable for our prerequisites.
    // <Class,List of Classes that Class is a prereq for>
    Map<Integer,ArrayList<Integer>> map = new HashMap<Integer,ArrayList<Integer>>();

    int reqSize = prerequisites.length;

    // First, fill the map with every single node acting as its own Class.
    for (int i = 0; i < reqSize; i++) {

      if (!map.containsKey(prerequisites[i][0])) {

        ArrayList<Integer> array = new ArrayList<Integer>();
        map.put(prerequisites[i][0], array);

      }

      if (!map.containsKey(prerequisites[i][1])) {

        ArrayList<Integer> array = new ArrayList<Integer>();
        map.put(prerequisites[i][1], array);

      }

    }

    // Now, we find neighbors for each node and add them to the proper ArrayList.
    for (int i = 0; i < reqSize; i++) {

      map.get(prerequisites[i][1]).add(prerequisites[i][0]);

    }

    // Check whether we have too many courses.
    if (numberOfCourses < map.keySet().size()) {

      return isLegit;

    } else {

      // Check whether or not we have a completed graph.
      Evaluation eval = startTS(map);

      boolean isCircular = eval.getDAGStatus();

      // Update global variable
      schedule = eval.getStack();

      if (!isCircular) {

        return isLegit;

      } else {

        // Graph is fully connected, so this course layout is possible.
        isLegit = true;

        return isLegit;

      }

    }

  }

  public static Evaluation startTS(Map<Integer,ArrayList<Integer>> map) {

    // Start our evaluation.
    Evaluation eval = new Evaluation();

    // Define HashMap for keeping track of visitation.
    Map<Integer,String> visit = new HashMap<Integer,String>();

    // Generate keyset of original map.
    Set<Integer> keySet = map.keySet();

    for (Integer i : keySet) {

      visit.put(i, "false");

    }

    // Create Stack.
    Stack<Integer> stack = new Stack<Integer>();

    for(Integer s : map.keySet()) {

      // Visit nodes if they haven't been visited yet.
      if (visit.get(s) != "temp" && visit.get(s) != "perm") {

        visit(s, stack, map, visit, eval);

      }

    }

    // Carry stack for use later.
    eval.setStack(stack);

    return eval;

  }

  public static void visit(Integer s, Stack<Integer> stack, Map<Integer,ArrayList<Integer>> map, Map<Integer,String> visit, Evaluation eval) {

    // Check whether or not this node has been visited.
    if (visit.get(s) == "temp") {

      // Graph is cyclic.
      eval.setDAGStatus(false);

      return;

    }
    if (visit.get(s) == "perm") { return; }

    // Set current node to having been temporarily visited.
    visit.put(s, "temp");

    // Begin DFS by recursively calling visit for each neighbor.
    for(Integer m : map.get(s)) {

      visit(m, stack, map, visit, eval);

    }

    // You are now climbing back up the graph. Update node to permanent.
    visit.put(s, "perm");

    stack.push(s);

  }

  // Method for printing prerequisites.
  public static void pgPrint(int[][] pre) {

    int size = pre.length;

    System.out.print("{");

    for (int i = 0; i < size; i++) {

      System.out.print("{" + pre[i][0] + ", " + pre[i][1] +"}");

      if (i < size - 1){

        System.out.print(", ");

      }

    }

    System.out.print("}");

  }

}
