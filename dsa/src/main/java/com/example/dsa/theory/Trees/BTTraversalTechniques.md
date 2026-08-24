# Binary Tree Traversal Templates

## DFS - Depth First Search

DFS goes **deep into the tree** before coming back.

### 1. Preorder

**Root → Left → Right**

```java
void preorder(TreeNode root) {
    if (root == null)
        return;

    // Process root
    preorder(root.left);
    preorder(root.right);
}
```

### 2. Inorder

**Left → Root → Right**

```java
void inorder(TreeNode root) {
    if (root == null)
        return;

    inorder(root.left);
    // Process root
    inorder(root.right);
}
```

### 3. Postorder

**Left → Right → Root**

```java
void postorder(TreeNode root) {
    if (root == null)
        return;

    postorder(root.left);
    postorder(root.right);
    // Process root
}
```

### 4. Right-First DFS

**Root → Right → Left**

Useful when we want to prioritize the **right side** of the tree.

```java
void rightFirst(TreeNode root) {
    if (root == null)
        return;

    // Process root
    rightFirst(root.right);
    rightFirst(root.left);
}
```

---

# BFS - Breadth First Search

BFS processes the tree **level by level**.

### Level Order Traversal

Uses a **Queue**.

```java
void levelOrder(TreeNode root) {
    if (root == null)
        return;

    Queue<TreeNode> q = new LinkedList<>();
    q.offer(root);

    while (!q.isEmpty()) {
        TreeNode node = q.poll();

        // Process node

        if (node.left != null)
            q.offer(node.left);

        if (node.right != null)
            q.offer(node.right);
    }
}
```

### Level-by-Level Template

Use this when you need to know which nodes belong to the **same level**.

```java
Queue<TreeNode> q = new LinkedList<>();
q.offer(root);

while (!q.isEmpty()) {

    int size = q.size();

    for (int i = 0; i < size; i++) {
        TreeNode node = q.poll();

        // Process node

        if (node.left != null)
            q.offer(node.left);

        if (node.right != null)
            q.offer(node.right);
    }
}
```

---

# Quick Revision

```text
Tree Traversal
│
├── DFS
│   ├── Preorder   → Root → Left → Right
│   ├── Inorder    → Left → Root → Right
│   ├── Postorder  → Left → Right → Root
│   └── Right-First → Root → Right → Left
│
└── BFS
    └── Level Order → Level by Level → Queue
```

### Easy Rule

* **Need to go deep / recursively?** → DFS
* **Need level-by-level?** → BFS
* **Need rightmost node?** → Right-first DFS or BFS
* **Need sorted order from a BST?** → Inorder
* **Need children before parent?** → Postorder
