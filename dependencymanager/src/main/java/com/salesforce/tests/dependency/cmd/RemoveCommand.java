package com.salesforce.tests.dependency.cmd;

import com.salesforce.tests.dependency.mod.InstallableUnit;
import com.salesforce.tests.dependency.mod.Product;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class RemoveCommand extends AbstractCommand implements Command {

  public RemoveCommand(Product dependencyMap) {

    super(dependencyMap);
  }

  @Override
  public Map<String, String> execute(List<String> args) {

    InstallableUnit component =
        getDependencyMap().getInstallableUnitInstance(args.get(0));

    if (component != null && component.isInstalled()) {

      Set<InstallableUnit> installedDependents =
          component.getDependents().stream()
              .filter(InstallableUnit::isInstalled).collect(Collectors.toSet());

      if (!installedDependents.isEmpty()) {
        Map<String, String> result = new LinkedHashMap<>();
        result.put(component.getName(), "is still needed");
        return result;
      }
      // It will be removed by the call to {@code uninstall} if it does not have
      // any installed dependents
      // component.setInstalled(false);
      component.setIsExplicitlyInstalled(false);
      return uninstall(component);
    }

    // TODO: Could use Map.of
    Map<String, String> result = new HashMap<>();
    result.put(args.get(0), "is not installed");
    return result;
  }

  private Map<String, String> uninstall(InstallableUnit parent) {

    Map<String, String> result = new LinkedHashMap<>();
    Set<InstallableUnit> installedDependents = parent.getDependents().stream()
        .filter(InstallableUnit::isInstalled).collect(Collectors.toSet());

    if (installedDependents.size() > 0) {
      // result.put(parent.getName(), "is still needed");
      return result;
    }

    parent.setInstalled(false);
    getDependencyMap().removeInstalledComponent(parent);
    result.put("Removing " + parent.getName(), "");

    // Clean up parent's dependencies. Only transitive dependencies that are
    // unique to the parent will be uninstalled.
    for (InstallableUnit dependency : parent.getDependencies()) {
      if (dependency.isInstalled() && !dependency.isExplicitlyInstalled()) {
        result.putAll(uninstall(dependency));
      }
    }
    return result;
  }
}
