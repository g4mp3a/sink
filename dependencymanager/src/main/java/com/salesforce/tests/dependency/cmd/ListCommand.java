package com.salesforce.tests.dependency.cmd;

import com.salesforce.tests.dependency.mod.Product;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ListCommand extends AbstractCommand implements Command {

  public ListCommand(Product dependencyMap) {

    super(dependencyMap);
  }

  @Override
  public Map<String, String> execute(List<String> args) {

    Map<String, String> result = new LinkedHashMap<>();
    getDependencyMap().getInstalledUnitsInOrder()
        .forEach(e -> result.put(e.getName(), ""));
    return result;
  }
}
