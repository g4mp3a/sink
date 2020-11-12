package com.salesforce.tests.dependency.cmd;

import com.salesforce.tests.dependency.mod.InstallableUnit;
import com.salesforce.tests.dependency.mod.Product;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class InstallCommand extends AbstractCommand implements Command {

  public InstallCommand(Product dependencyMap) {

    super(dependencyMap);
  }

  @Override
  public Map<String, String> execute(List<String> args) {

    // System.out.println("INSTALL COMMAND");
    // System.out.println("args: " + args);
    // LinkedHashMap to maintain input order. TODO: Revisit
    Map<String, String> result = new LinkedHashMap<>();
    for (String componentName : args) {
      InstallableUnit component =
          getDependencyMap().getInstallableUnitInstance(componentName);
      // System.out.println("Preparing to install: " + component);
      if (component.isInstalled()) {
        result.put(component.getName(), "is already installed");
        component.setIsExplicitlyInstalled(true);
        continue;
      }

      install(component, result);
      component.setIsExplicitlyInstalled(true);
    }
    // System.out.println("INSTALL COMMAND");
    return result;
  }

  /**
   * Initial recursive implementation.
   *
   * <pre>
   * TODO: Revisit to see if it needs enhancing.
   * </pre>
   */
  private Map<String, String> install(InstallableUnit current,
      Map<String, String> result) {

    if (current.isInstalled()) {
      // System.out.println(current.getName() + " is already installed");
      // result.put(current.getName(), "is already installed");
      return result;
    }

    // System.out.println("Preparing to install " + current + "'s dependencies:
    // "
    // + current.getDependencies());
    for (InstallableUnit dependency : current.getDependencies()) {
      install(dependency, result);
    }
    result.put("Installing " + current.getName(), "");
    current.setInstalled(true);
    getDependencyMap().addInstalledComponent(current);
    // System.out.println("Installed " + current.getName());
    return result;
  }
}
