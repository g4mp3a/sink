package com.salesforce.tests.dependency.mod;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * This class represents the dependency map of an entire system at a point in
 * time. A single instance of this class is used by a running
 * {@code DependencyManagerApp}.
 *
 * @author gautampriya
 */
public class Product {

  private final Map<String, InstallableUnit> components = new LinkedHashMap<>();

  private final Map<String, InstallableUnit> componentsInInstallOrder =
      new LinkedHashMap<>();

  public void addInstalledComponent(InstallableUnit unit) {

    this.componentsInInstallOrder.put(unit.getName(), unit);
  }

  public void removeInstalledComponent(InstallableUnit unit) {

    this.componentsInInstallOrder.remove(unit.getName());
  }

  /**
   * Returns {@code InstallableUnit} instance corresponding to supplied name. It
   * returns the instance found in the system dependency map. When the instance
   * is not found in the map, it creates a new instance, adds it to the map and
   * returns it.
   *
   * @param name name of {@code InstallableUnit}
   * @return {@code InstallableUnit}
   */
  public InstallableUnit getInstallableUnitInstance(String name) {

    InstallableUnit target = components.get(name);
    if (target == null) {
      target = new InstallableUnit(name);
      components.put(name, target);
    }
    return target;
  }

  /**
   * Returns {@code Set} of {@code InstallableUnit}s that are installed in the
   * system.
   *
   * @return {@code Set<InstallableUnit>}
   */
  public List<InstallableUnit> getInstalledUnitsInOrder() {

    return componentsInInstallOrder.values().stream()
        .filter(m -> m.isInstalled())
        .collect(Collectors.toList());
  }
}
