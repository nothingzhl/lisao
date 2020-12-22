package org.zhl.structure;

/**
 * 跳表实现<br/>
 * <a href="https://www.cnblogs.com/xuqiang/archive/2011/05/22/2053516.html">参考这个</a>
 */
public class SkipList {

    /**
     * 晋升概率
     */
    private static final float SKIPLIST_P = 0.5f;

    /**
     * 索引最高level级别
     */
    private static final int MAX_LEVEL = 16;

    private int levelCount = 1;

    // 头结点,哨兵节点，本身不存数据，下一跳指向各层索引的头节点
    private Node head = new Node();

    /**
     * 查
     *
     * @param value
     *
     * @return
     */
    public Node find(int value) {

        Node p = this.head;

        // 从最大层开始查找，找到前一节点，通过--i，移动到下层再开始查找
        for (int i = levelCount - 1; i >= 0; i--) {
            while (p.forwards[i] != null && p.forwards[i].data < value) {
                // 找到前一节点
                p = p.forwards[i];
            }
        }

        if (p.forwards[0] != null && p.forwards[0].data == value) {
            return p.forwards[0];
        } else {
            return null;
        }

    }

    /**
     * 插入
     *
     * @param value
     */
    public void insert(int value) {

        int level = randomLevel();

        // 创建新节点
        Node newNode = new Node();
        newNode.data = value;
        // 表示从最大层到最低层，都要有节点数据
        newNode.maxLevel = level;

        // 记录要更新的层数，表示新节点要更新到哪几层
        // 每层的初始化都指向head
        Node[] update = new Node[level];
        for (int i = 0; i < level; i++) {
            update[i] = head;
        }

        /**
         * 1.层是从下到上的，最下层编号是0，最上层编号是15<br/>
         *
         */
        Node p = head;
        for (int i = level - 1; i >= 0; i--) {
            // 找到第i层索引的插入位置，将插入位置前面的节点保存到update数组
            while (p.forwards[i] != null && p.forwards[i].data < value) {
                // p 向后移动
                p = p.forwards[i];
            }
            // 这里update[i]表示当前层节点的前一节点，因为要找到前一节点，才好插入数据
            update[i] = p;
        }

        // 将每一层节点和后面节点关联
        for (int i = 0; i < level; i++) {
            // 记录当前层节点后面节点指针
            newNode.forwards[i] = update[i].forwards[i];
            // 前一个节点的指针，指向当前节点
            update[i].forwards[i] = newNode;
        }

        // 更新层高
        if (levelCount < level) {
            levelCount = level;
        }

        System.out.println(newNode);
    }

    /**
     * 删除
     *
     * @param value
     */
    public void delete(int value) {
        Node[] update = new Node[levelCount];
        Node p = head;

        for (int i = levelCount - 1; i >= 0; i--) {
            while (p.forwards[i] != null && p.forwards[i].data < value) {
                p = p.forwards[i];
            }
            update[i] = p;
        }

        if (p.forwards[0] != null && p.forwards[0].data == value) {
            for (int i = levelCount - 1; i >= 0; i--) {
                if (update[i].forwards[i] != null && update[i].forwards[i].data == value) {
                    update[i].forwards[i] = update[i].forwards[i].forwards[i];
                }
            }
        }

        while (levelCount > 1 && head.forwards[levelCount] == null) {
            levelCount--;
        }
    }

    /**
     * 一级索引中元素个数应该占原始数据的50%<br/>
     * 二级索引中元素个数占25%<br/>
     * 三级索引中12.25，一直到最顶层
     *
     * @return
     */
    private int randomLevel() {
        int level = 1;

        while (Math.random() < SKIPLIST_P && level < MAX_LEVEL) {
            level += 1;
        }
        return level;
    }

    /**
     * 打印每一层所有数据。
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SkipList Level Count = ").append(levelCount).append("\n");
        // 层次
        for (int i = levelCount - 1; i >= 0; i--) {
            sb.append("level ").append(i).append(" --> ");
            for (Node p = head; p.forwards[i] != null; p = p.forwards[i]) {
                sb.append(p.forwards[i].data);
                if (p.forwards[i].forwards[i] != null) {
                    sb.append(",").append(" ");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * 跳表的节点定义
     */
    public class Node {

        private int data = -1;

        /**
         * 表示当前节点位置的下一个节点所有层的数据，从上层切换到下层，就是数组下标-1<br/>
         * forrwards[3] 表示当前节点在第三层的下一节点
         */
        private Node forwards[] = new Node[MAX_LEVEL];

        private int maxLevel = 0;

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("SkipList Node ").append(data);
            sb.append(", maxLevel = ").append(maxLevel).append("\n");
            for (int i = maxLevel - 1; i >= 0; i--) {
                sb.append("level ").append(i).append(" --> ");
                if (forwards[i] == null) {
                    sb.append("null");
                } else {
                    sb.append(forwards[i].data);
                }
                sb.append("\n");
            }
            return sb.toString();
        }
    }

}
