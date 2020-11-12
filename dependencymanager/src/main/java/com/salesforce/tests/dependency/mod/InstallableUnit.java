package com.salesforce.tests.dependency.mod;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * This class represents an installable unit.
 *
 * @author gautampriya
 */
public class InstallableUnit {

  private String name;
  private List<InstallableUnit> dependencies = new ArrayList<InstallableUnit>();
  private Set<InstallableUnit> dependents = new HashSet<InstallableUnit>();

  // Assume its implicitly installed, unless otherwise commanded.
  private boolean isExplicitlyInstalled = false;
  private boolean installed = false;

  InstallableUnit(String name) {

    this.name = name;
  }

  public String getName() {

    return name;
  }

  public boolean isInstalled() {

    return installed;
  }

  public void setInstalled(boolean installed) {

    this.installed = installed;
  }

  public Set<InstallableUnit> getDependents() {

    return dependents;
  }

  public boolean hasDependents() {

    return !dependents.isEmpty();
  }

  public boolean hasDependencies() {

    return !dependencies.isEmpty();
  }

  public List<InstallableUnit> getDependencies() {

    return dependencies;
  }

  public boolean addDependency(InstallableUnit d) {

    return dependencies.add(d);
  }

  public boolean addDependent(InstallableUnit d) {

    return dependents.add(d);
  }

  @Override
  public boolean equals(Object o) {

    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;

    InstallableUnit that = (InstallableUnit) o;

    if (name != null ? !name.equals(that.name) : that.name != null)
      return false;

    return true;
  }

  @Override
  public String toString() {

    return name;
  }

  @Override
  public int hashCode() {

    return name != null ? name.hashCode() : 0;
  }

  public boolean isExplicitlyInstalled() {

    return isExplicitlyInstalled;
  }

  public void setIsExplicitlyInstalled(boolean isExplicitlyInstalled) {

    this.isExplicitlyInstalled = isExplicitlyInstalled;
  }
}
