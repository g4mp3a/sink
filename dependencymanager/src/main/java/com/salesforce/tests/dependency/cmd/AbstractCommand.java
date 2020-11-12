package com.salesforce.tests.dependency.cmd;

import com.salesforce.tests.dependency.mod.Product;

public abstract class AbstractCommand {

  private Product dependencyMap;

  public AbstractCommand(Product dependencyMap) {

    this.dependencyMap = dependencyMap;
  }

  public Product getDependencyMap() {

    return dependencyMap;
  }
}
