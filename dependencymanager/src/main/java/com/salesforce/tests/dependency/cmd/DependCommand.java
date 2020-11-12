package com.salesforce.tests.dependency.cmd;

import com.salesforce.tests.dependency.mod.InstallableUnit;
import com.salesforce.tests.dependency.mod.Product;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DependCommand extends AbstractCommand implements Command {

  public DependCommand(Product dependencyMap) {

    super(dependencyMap);
  }

  @Override
  public Map<String, String> execute(List<String> args) {

    // System.out.println("DEPEND COMMAND");
    // System.out.println("args: " + args);

    String dependentName = args.get(0);
    InstallableUnit dependent =
        getDependencyMap().getInstallableUnitInstance(dependentName);

    Map<String, String> result = new LinkedHashMap<>();

    for (String dependencyName : args.subList(1, args.size())) {
      InstallableUnit dependency =
          getDependencyMap().getInstallableUnitInstance(dependencyName);

      // Circular dependency check
      if (dependency.getDependencies().contains(dependent)) {
        result.put(dependency.getName(),
            "depends on " + dependent + ", ignoring command");
        continue;
      }

      dependent.addDependency(dependency);
      dependency.addDependent(dependent);
      // System.out.println(
      // dependencyName + "'s dependents are: " + dependency.getDependents());
    }
    // System.out.println(
    // dependent + "'s dependency list: " + dependent.getDependencies());
    // System.out.println("DEPEND COMMAND");
    return result;
  }
}
