import java.util.*;

// Class for returning information about a graph in a concise way.
class Evaluation {

  // The number of separate graphs within the map.
  Stack<Integer> stack;

  // Whether or not the graph is direct acyclic.
  boolean statusDAG;

  // Constructor.
  public Evaluation() {

    stack = null;
    statusDAG = true;

  }

  // Getters.
  public Stack<Integer> getStack() {

    return stack;

  }

  public boolean getDAGStatus() {

    return statusDAG;

  }

  // Setters.
  public void setStack(Stack<Integer> s) {

    stack = s;

  }

  public void setDAGStatus(boolean flag) {

    statusDAG = flag;

  }

}
