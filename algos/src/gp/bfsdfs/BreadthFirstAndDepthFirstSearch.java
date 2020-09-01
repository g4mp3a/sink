package gp.bfsdfs;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class BreadthFirstAndDepthFirstSearch {

  public static class BinaryTreeNode {

    public int value;
    public BinaryTreeNode left;
    public BinaryTreeNode right;

    public BinaryTreeNode(final int value) {
      this.value=value;
    }

    public BinaryTreeNode insertLeft(final int leftValue) {
      this.left=new BinaryTreeNode(leftValue);
      return this.left;
    }

    public BinaryTreeNode insertRight(final int rightValue) {
      this.right=new BinaryTreeNode(rightValue);
      return this.right;
    }

    public boolean isLeafNode() {
      return (this.left==null)&&(this.right==null);
    }
  }

  public static boolean binaryTreeBFS(final BinaryTreeNode root, final int target) {

    if (root==null)
      return false;

    final Queue<BinaryTreeNode> queue=new LinkedList<>();
    queue.add(root);
    while (!queue.isEmpty()) {
      final BinaryTreeNode node=queue.poll();
      if (target==node.value)
        return true;

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

    return false;
  }

  public static boolean binaryTreeDFS(final BinaryTreeNode root, final int target) {

    if (root==null)
      return false;

    final Deque<BinaryTreeNode> stack=new ArrayDeque<>();
    stack.push(root);
    while (!stack.isEmpty()) {
      final BinaryTreeNode node=stack.peek();
      if (target==node.value)
        return true;

      if (node.isLeafNode()) {
        continue;
      }

      if (node.left!=null) {
        stack.push(node.left);
      }
      if (node.right!=null) {
        stack.push(node.right);
      }
      stack.pop();
    }

    return false;
  }
}
