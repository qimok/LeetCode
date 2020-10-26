class UF {

    // 连通分量个数
    private int count;
    // 存储一颗树（用 parent 数组记录每个节点的父节点，相当于指向父节点的指针，所以 parent 数组内实际存储着一个森林（若干棵多叉树））
    private int[] parent;
    // 记录树的`重量`（通过`重量`，可以将小树接到大树下面，以此来维持树的平衡性，防止在极端情况下退化成链表）
    private int[] size;

    public UF(int n) {
        this.count = n;
        parent = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            // 初始化时，每个节点的父节点是自己本身，即自己指向自己
            parent[i] = i;
            size[i] = 1;
        }
    }

    public void union(int p, int q) {
        int rootP = findRoot(p);
        int rootQ = findRoot(q);
        if (rootP == rootQ) return;
        // 小树接到大树下面，较平衡
        if (size[rootP] > size[rootQ]) {
            parent[rootQ] = rootP;
            size[rootP] += size[rootQ];
        } else {
            parent[rootP] = rootQ;
            size[rootQ] += size[rootP];
        }
        count--;
    }

    public boolean connected(int p, int q) {
        int rootP = findRoot(p);
        int rootQ = findRoot(q);
        return rootP == rootQ;
    }

    private int findRoot(int x) {
        while (parent[x] != x) {
            // 在 findRoot 函数中进行路径压缩，保证任意树的高度保持在常数，使得 union 和 connected 方法的时间复杂度为 O(1)
            // 进行路径压缩（只要树的高度达到 3，就会进行路径压缩）
            parent[x] = parent[parent[x]];
            x = parent[x];
        }
        return x;
    }

    public int count() {
        return count;
    }

}