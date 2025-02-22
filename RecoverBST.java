// In this problem, using a prev and curr pointers, doing the inorder traversal of the BST and comparing, since we know that for a
// BST prev should always be less than current, and also only two nodes that are swapped, we just need to find mismatch. Whenever,
// a mismatch is found, record the value of prev in first and curr(root) in second. Traverse and look for the second mismatch, when
// found update the value of the second with the current root. And if second mismatch is not found that means two adjacent nodes were
// swapped.

// Time Complexity : O(n) - no. of nodes plus
// Space Complexity : O(H) - height of the tree - recursive stack space
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode() {}
 * TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) {
 * this.val = val;
 * this.left = left;
 * this.right = right;
 * }
 * }
 */
class Solution {
    TreeNode prev, first, second;
    int cnt;

    public void recoverTree(TreeNode root) {
        // Base Case
        if (root == null) {
            return;
        }
        // Initialize prev, first, second, and cnt
        prev = null;
        first = second = null;
        cnt = 0;
        // Do the inorder traversal and find first and second mismatch
        inorder(root);
        // Then perform the swap
        int temp = first.val;
        first.val = second.val;
        second.val = temp;

    }

    private void inorder(TreeNode root) {
        // Base, here we are also checking if two times mismatch found, we can stop the
        // inorder traversal
        if (root == null || cnt == 2) {
            return;
        }
        // Make the left recursive call
        inorder(root.left);
        // Then check for the mismatch
        if (prev != null && prev.val > root.val) {
            // Do count plus
            cnt++;
            // If this is the first mismatch, record the value of prev in first
            if (first == null) {
                first = prev;
            }
            // Also record the value of curr root in second
            second = root;
        }
        // Modify the prev and make it root
        prev = root;
        // Make the right recursive call
        inorder(root.right);

    }
}

// Iterative solution
class Solution {
    TreeNode prev, first, second;
    int cnt;

    public void recoverTree(TreeNode root) {
        // Base Case
        if (root == null) {
            return;
        }
        // Initialize prev, first, second, and cnt
        prev = null;
        first = second = null;
        cnt = 0;
        Stack<TreeNode> s = new Stack<>();
        // Loop till root == null or stack is empty
        while (root != null || !s.isEmpty()) {
            // Go left till root is null
            while (root != null) {
                // Add the root value to stack
                s.push(root);
                // Move left
                root = root.left;
            }
            // Once it is null, do stack.pop
            root = s.pop();
            // Check for mismatch
            if (prev != null && prev.val > root.val) {
                // Increment count
                cnt++;
                // If it is first mismatch, set the first to prev
                if (first == null) {
                    first = prev;
                }
                // Set the second to root
                second = root;
            }
            // Check if already two mismatch found, stop
            if (cnt == 2) {
                break;
            }
            // Update the prev to root
            prev = root;
            // Go right
            root = root.right;
        }
        // Now we have first and second, swap both the values
        int temp = first.val;
        first.val = second.val;
        second.val = temp;

    }
}