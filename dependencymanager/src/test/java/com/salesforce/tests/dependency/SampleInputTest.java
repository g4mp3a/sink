package com.salesforce.tests.dependency;

import org.junit.Test;
import java.io.IOException;

/**
 * Test for sample input and output
 */
public class SampleInputTest extends BaseTest {

  @Test
  // DEPEND: no output for DEPEND
  public void testDepend() throws IOException {

    String[] input = {
        "DEPEND A B C\n",
        "END\n"
    };

    String expectedOutput = "DEPEND A B C\n" +
        "END\n";

    runTest(expectedOutput, input);
  }

  @Test
  // INSTALL: output "Installing"
  public void testInstall1() throws IOException {

    String[] input = {
        "DEPEND A B\n",
        "INSTALL A\n",
        "END\n"
    };

    String expectedOutput = "DEPEND A B\n" +
        "INSTALL A\n" +
        "Installing B\n" +
        "Installing A\n" +
        "END\n";

    runTest(expectedOutput, input);
  }

  @Test
  // INSTALL: output "Installing"
  public void testInstall2() throws IOException {

    String[] input = {
        "DEPEND A B C\n",
        "INSTALL A\n",
        "END\n"
    };

    String expectedOutput = "DEPEND A B C\n" +
        "INSTALL A\n" +
        "Installing B\n" +
        "Installing C\n" +
        "Installing A\n" +
        "END\n";

    runTest(expectedOutput, input);
  }

  @Test
  // REMOVE: remove the module
  public void testRemove1() throws IOException {

    String[] input = {
        "DEPEND A B C D\n",
        "INSTALL B\n",
        "INSTALL A\n",
        "REMOVE A\n",
        "END\n"
    };

    String expectedOutput = "DEPEND A B C D\n" +
        "INSTALL B\n" +
        "Installing B\n" +
        "INSTALL A\n" +
        "Installing C\n" +
        "Installing D\n" +
        "Installing A\n" +
        "REMOVE A\n" +
        "Removing A\n" +
        "Removing C\n" +
        "Removing D\n" +
        "END\n";

    runTest(expectedOutput, input);
  }

  @Test
  // REMOVE: remove the module
  public void testRemove2() throws IOException {

    String[] input = {
        "DEPEND A B\n",
        "INSTALL B\n",
        "INSTALL A\n",
        "REMOVE B\n",
        "END\n"
    };

    String expectedOutput = "DEPEND A B\n" +
        "INSTALL B\n" +
        "Installing B\n" +
        "INSTALL A\n" +
        "Installing A\n" +
        "REMOVE B\n" +
        "B is still needed\n" +
        "END\n";

    runTest(expectedOutput, input);
  }

  @Test
  // REMOVE: remove the module
  public void testRemove3() throws IOException {

    String[] input = {
        "DEPEND A B C\n",
        "INSTALL B\n",
        "INSTALL A\n",
        "REMOVE C\n",
        "REMOVE A\n",
        "REMOVE C\n",
        "REMOVE C\n",
        "END\n"
    };

    String expectedOutput = "DEPEND A B C\n" +
        "INSTALL B\n" +
        "Installing B\n" +
        "INSTALL A\n" +
        "Installing C\n" +
        "Installing A\n" +
        "REMOVE C\n" +
        "C is still needed\n" +
        "REMOVE A\n" +
        "Removing A\n" +
        "Removing C\n" +
        "REMOVE C\n" +
        "C is not installed\n" +
        "REMOVE C\n" +
        "C is not installed\n" +
        "END\n";

    runTest(expectedOutput, input);
  }

  @Test
  // LIST: Shall list all the installed items in install order.
  public void testList1() throws IOException {

    String[] input = {
        "DEPEND A B\n",
        "INSTALL A\n",
        "LIST\n",
        "END\n"
    };

    String expectedOutput = "DEPEND A B\n" +
        "INSTALL A\n" +
        "Installing B\n" +
        "Installing A\n" +
        "LIST\n" +
        "B\n" +
        "A\n" +
        "END\n";

    runTest(expectedOutput, input);
  }

  @Test
  // LIST
  public void testList2() throws IOException {

    String[] input = {
        "DEPEND A B C\n",
        "INSTALL A\n",
        "LIST\n",
        "END\n"
    };

    String expectedOutput = "DEPEND A B C\n" +
        "INSTALL A\n" +
        "Installing B\n" +
        "Installing C\n" +
        "Installing A\n" +
        "LIST\n" +
        "B\n" +
        "C\n" +
        "A\n" +
        "END\n";

    runTest(expectedOutput, input);
  }

  @Test
  public void testSampleInput() throws IOException {

    String[] input = {
        "DEPEND TELNET TCPIP NETCARD\n",
        "DEPEND TCPIP NETCARD\n",
        "DEPEND NETCARD TCPIP\n",
        "DEPEND DNS TCPIP NETCARD\n",
        "DEPEND BROWSER TCPIP HTML\n",
        "INSTALL NETCARD\n",
        "INSTALL TELNET\n",
        "INSTALL foo\n",
        "REMOVE NETCARD\n",
        "INSTALL BROWSER\n",
        "INSTALL DNS\n",
        "LIST\n",
        "REMOVE TELNET\n",
        "REMOVE NETCARD\n",
        "REMOVE DNS\n",
        "REMOVE NETCARD\n",
        "INSTALL NETCARD\n",
        "REMOVE TCPIP\n",
        "REMOVE BROWSER\n",
        "REMOVE TCPIP\n",
        "LIST\n",
        "END\n"
    };

    String expectedOutput = "DEPEND TELNET TCPIP NETCARD\n" +
        "DEPEND TCPIP NETCARD\n" +
        "DEPEND NETCARD TCPIP\n" +
        "TCPIP depends on NETCARD, ignoring command\n" +
        "DEPEND DNS TCPIP NETCARD\n" +
        "DEPEND BROWSER TCPIP HTML\n" +
        "INSTALL NETCARD\n" +
        "Installing NETCARD\n" +
        "INSTALL TELNET\n" +
        "Installing TCPIP\n" +
        "Installing TELNET\n" +
        "INSTALL foo\n" +
        "Installing foo\n" +
        "REMOVE NETCARD\n" +
        "NETCARD is still needed\n" +
        "INSTALL BROWSER\n" +
        "Installing HTML\n" +
        "Installing BROWSER\n" +
        "INSTALL DNS\n" +
        "Installing DNS\n" +
        "LIST\n" +
        "NETCARD\n" +
        "TCPIP\n" +
        "TELNET\n" +
        "foo\n" +
        "HTML\n" +
        "BROWSER\n" +
        "DNS\n" +
        "REMOVE TELNET\n" +
        "Removing TELNET\n" +
        "REMOVE NETCARD\n" +
        "NETCARD is still needed\n" +
        "REMOVE DNS\n" +
        "Removing DNS\n" +
        "REMOVE NETCARD\n" +
        "NETCARD is still needed\n" +
        "INSTALL NETCARD\n" +
        "NETCARD is already installed\n" +
        "REMOVE TCPIP\n" +
        "TCPIP is still needed\n" +
        "REMOVE BROWSER\n" +
        "Removing BROWSER\n" +
        "Removing TCPIP\n" +
        "Removing HTML\n" +
        "REMOVE TCPIP\n" +
        "TCPIP is not installed\n" +
        "LIST\n" +
        "NETCARD\n" +
        "foo\n" +
        "END\n";

    runTest(expectedOutput, input);
  }
}
