package gp.binarytree;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * De/Serialize a binary tree from/to a list using level order traversal.
 * 
 * @formatter:off 4 / \ 3 5 / \ \ 7 8 1
 *
 *                The tree above is serialized into this list [4,3,5,7,8,1]. The
 *                same list is de-serialized into the tree above.
 * @formatter:on
 * @author gautampriya
 */
public class TreeToListAndBack {

  public static class BinaryTreeNode<T> {

    public T value;
    public BinaryTreeNode<T> left;
    public BinaryTreeNode<T> right;

    public BinaryTreeNode(final T value) {
      this.value=value;
    }

    public BinaryTreeNode<T> insertLeft(final T leftValue) {
      this.left=new BinaryTreeNode<>(leftValue);
      return this.left;
    }

    public BinaryTreeNode<T> insertRight(final T rightValue) {
      this.right=new BinaryTreeNode<>(rightValue);
      return this.right;
    }

    public boolean isLeafNode() {
      return (this.left==null)&&(this.right==null);
    }
  }

  public static <T> BinaryTreeNode<T> deserialize(final List<T> input) {

    if (input==null)
      return null;

    final LinkedList<T> inputLL=new LinkedList<>(input);
    final Queue<BinaryTreeNode<T>> queue=new LinkedList<>();
    final BinaryTreeNode<T> root=new BinaryTreeNode<>(inputLL.remove());
    queue.add(root);
    while (!queue.isEmpty()) {
      final BinaryTreeNode<T> node=queue.poll();
      if (!inputLL.isEmpty()) {
        node.left=new BinaryTreeNode<>(inputLL.remove());
        queue.add(node.left);
      }
      if (!inputLL.isEmpty()) {
        node.right=new BinaryTreeNode<>(inputLL.remove());
        queue.add(node.right);
      }
    }

    return root;
  }

  public static <T> List<T> serialize(final BinaryTreeNode<T> root) {

    if (root==null)
      return null;

    final List<T> returnValue=new ArrayList<>();
    final Queue<BinaryTreeNode<T>> queue=new LinkedList<>();
    queue.add(root);

    while (!queue.isEmpty()) {
      final BinaryTreeNode<T> node=queue.poll();
      returnValue.add(node.value);

      if (node.isLeafNode()) {
        continue;
      }

      if (node.left!=null) {
        queue.add(node.left);
      }
      if (node.right!=null) {
        queue.add(node.right);
      }
    }
    return returnValue;
  }

  public static void main(final String[] args) {
    final Result result=JUnitCore.runClasses(TreeToListAndBack.class);
    for (final Failure failure : result.getFailures()) {
      System.out.println(failure.toString());
    }
    if (result.wasSuccessful()) {
      System.out.println("All tests passed.");
    }
  }

  // tests
  @Test
  public void testWithExampleTree() throws Exception {
    final List<Integer> input=Arrays.asList(4,3,5,7,8,1);
    assertEquals(input,serialize(deserialize(input)));
  }

  @Test
  public void testWithSingleNodeTree() throws Exception {
    final List<Integer> input=Arrays.asList(4);
    assertEquals(input,serialize(deserialize(input)));
  }

  @Test
  public void testWithFullTree() throws Exception {
    final List<Integer> input=Arrays.asList(4,3,5);
    assertEquals(input,serialize(deserialize(input)));
  }

  @Test
  public void testWithTriangularTree() throws Exception {
    final BinaryTreeNode<Integer> root=new BinaryTreeNode<>(3);
    root.insertLeft(4).insertLeft(1);
    root.insertRight(2).insertRight(9);

    final List<Integer> input=serialize(root);
    assertEquals(Arrays.asList(3,4,2,1,9),input);
    assertEquals(input,serialize(deserialize(input)));
  }

  @Test
  public void testWithUnbalancedTree() throws Exception {
    final BinaryTreeNode<Integer> root=new BinaryTreeNode<>(3);
    root.insertLeft(4).insertLeft(1);

    final List<Integer> input=serialize(root);
    assertEquals(Arrays.asList(3,4,1),input);
    assertEquals(input,serialize(deserialize(input)));
  }
}
