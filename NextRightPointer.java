// In this BFS approach, we are traversing level wise and polling the current node and if the current node is not the last node of that
// level we are setting the next of it to the q.peek().

// Time Complexity : O(n) - no. of nodes
// Space Complexity : O(n) - height of the tree 
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no
/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}
    
    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};
*/

class Solution {
    public Node connect(Node root) {
        // Base case
        if (root == null) {
            return root;
        }
        // Queue for BFS
        Queue<Node> q = new LinkedList<>();
        // Add root to the queue
        q.add(root);
        // Loop till q is not empty
        while (!q.isEmpty()) {
            // Take the size for processing level wise
            int size = q.size();
            // Loop till size
            for (int i = 0; i < size; i++) {
                // Poll the curr node
                Node curr = q.poll();
                // If it is not the last node of current level, than it has next present at the
                // peek of the queue
                if (i != size - 1) {
                    // So set it's next
                    curr.next = q.peek();
                }
                // Then check if it has childrens, since it is perfect, if it has left than it
                // will have right also
                if (curr.left != null) {
                    // So add both to the queue
                    q.add(curr.left);
                    q.add(curr.right);
                }
            }
        }
        // Return root
        return root;
    }
}

// Most optimal solution with O(1) space - In this approach we are keeping a
// left variable starting from root and left will go on left till left's left is
// not null. This will help us distinguish between the level. Then we also have
// the current variable which will be initially set to left and will help us
// traverse through nodes in each levels

/*
 * // Definition for a Node.
 * class Node {
 * public int val;
 * public Node left;
 * public Node right;
 * public Node next;
 * 
 * public Node() {}
 * 
 * public Node(int _val) {
 * val = _val;
 * }
 * 
 * public Node(int _val, Node _left, Node _right, Node _next) {
 * val = _val;
 * left = _left;
 * right = _right;
 * next = _next;
 * }
 * };
 */

class Solution {
    public Node connect(Node root) {
        // Base case
        if (root == null) {
            return root;
        }
        // Initialize left to root
        Node left = root;
        // Go on till we have next level
        while (left.left != null) {
            // For current level set curr to left
            Node curr = left;
            // Now iterate on this level with the help of curr, till curr is not null
            while (curr != null) {
                // Now curr.left.next will be curr.right
                curr.left.next = curr.right;
                // Iterate on this level horizontally till curr.next is not null
                if (curr.next != null) {
                    // And curr's right's next will be curr's next's left
                    curr.right.next = curr.next.left;
                }
                // Then move forward
                curr = curr.next;
            }
            // Move on the next level
            left = left.left;
        }
        // Return root
        return root;
    }
}

// DFS
class Solution {
    public Node connect(Node root) {
        if (root == null) {
            return root;
        }
        dfs(root.left, root.right);
        return root;
    }

    private void dfs(Node left, Node right) {
        if (left == null) {
            return;
        }
        left.next = right;
        dfs(left.left, left.right);
        dfs(left.right, right.left);
        dfs(right.left, right.right);
    }
}