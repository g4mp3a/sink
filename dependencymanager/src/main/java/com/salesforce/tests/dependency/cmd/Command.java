package com.salesforce.tests.dependency.cmd;

import java.util.List;
import java.util.Map;

public interface Command {

  // TODO: Revisit map's value type
  Map<String, String> execute(List<String> args);
}
