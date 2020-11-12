package com.salesforce.tests.dependency;

import com.salesforce.tests.dependency.cmd.Command;
import com.salesforce.tests.dependency.cmd.DependCommand;
import com.salesforce.tests.dependency.cmd.InstallCommand;
import com.salesforce.tests.dependency.cmd.ListCommand;
import com.salesforce.tests.dependency.cmd.RemoveCommand;
import com.salesforce.tests.dependency.mod.Product;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * The entry point for the Test program
 */
public class Main {

  private Map<String, Command> commands;

  public Main(Product system) {

    Map<String, Command> cmds = new HashMap<>();
    cmds.put("DEPEND", new DependCommand(system));
    cmds.put("INSTALL", new InstallCommand(system));
    cmds.put("REMOVE", new RemoveCommand(system));
    cmds.put("LIST", new ListCommand(system));
    commands = Collections.unmodifiableMap(cmds);
  }

  public static void main(String[] args) {

    // read input from stdin
    try (Scanner scan = new Scanner(System.in)) {
      Main installer =
          new Main(new Product());

      while (true) {
        String line = scan.nextLine();

        // no action for empty input
        if (line == null || line.length() == 0) {
          continue;
        }

        // the END command to stop the program
        if ("END".equals(line)) {
          System.out.println("END");
          break;
        }

        // Please provide your implementation here
        installer.processLine(line);
      }
    }
  }

  /**
   * Execute a single command. Invalid commands are silently ignored. A command
   * w/o an argument is treated as invalid.
   *
   * @param line command string
   */
  public void processLine(String line) {

    String[] arguments = line.split("[ ]+");
    Command cmd = commands.get(arguments[0]);
    if (cmd == null) {
      // TODO: Change to Logger
      System.out.println("Unknown command " + line);
      throw new IllegalArgumentException("Unknown command " + line);
    }

    System.out.println(line);
    List<String> args = new LinkedList<String>(Arrays.asList(arguments));
    args.remove(0); // drop command string
    Map<String, String> result = cmd.execute(args);
    result.entrySet().stream()
        .forEach(
            e -> System.out.println((e.getKey() + " " + e.getValue()).trim()));
  }
}
