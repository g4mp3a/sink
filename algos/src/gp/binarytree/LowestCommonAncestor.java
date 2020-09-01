package gp.binarytree;

public class LowestCommonAncestor {

  private class Node {
    Node left, right;
    int value;
  }

  /**
   * Assume both a, b are present in the tree.
   */
  public static Node lca(Node root, int a, int b) {
    if (root==null) return null;
    if (root.value==a||root.value==b) return root;

    Node left=lca(root.left,a,b);
    Node right=lca(root.right,a,b);
    if (left!=null&&right!=null) return root;
    if (left==null&&right==null) return null;

    return left!=null? left:right;
  }
}
