package org.zhl.backtracking;

/**
 * 八皇后的解法
 */
public class EightQueen {

    /**
     * 全局变量或者是成员变量，下标表示行，值表示queen在哪一行
     */
    int[] result = new int[8];

    public void cal8Queen(int row) {
        // 如果8个棋子都放置好了，打印结果
        if (row == 8) {
            print(result);
            // 问题解决就不递归了
            return;
        }

        // 每一行都有8种放法
        for (int column = 0; column < 8; column++) {
            // 有些放法不满足要求
            if (isOk(row, column)) {
                // 记录第row行的棋子放到column中
                result[row] = column;
                // 递归调用
                cal8Queen(row+1);
            }
        }

    }

    /**
     * 是否满足要求
     * @param row
     * @param column
     * @return
     */
    private boolean isOk(int row, int column) {

        int leftUp = column -1;
        int rightUp =column +1;

        // 逐行往上考察每一行
        for (int i = row-1; i >=0 ; i--) {
            // 判断第i行的column列有棋子否,竖直方向
            if (result[i] == column) {
                return false;
            }
            // i行，左对角线上是否有棋子
            if (leftUp >=0) {
                if (result[i] ==leftUp) {
                    return false;
                }
            }
            // i行，右对角线上是否有棋子
            if (rightUp < 8){
                if (result[i] == rightUp) {
                    return false;
                }
            }

            leftUp--;
            rightUp++;
        }

        return true;
    }

    int count = 0;

    /**
     * 打印值
     *
     * @param result
     */
    private void print(int[] result) {
        for (int row = 0; row < 8; row++) {
            for (int column = 0; column < 8; column++) {
                if (result[row] == column) {
                    System.out.print("Q ");
                }else {
                    System.out.print("* ");
                }
            }
            System.out.println();
        }
        System.out.println();
        System.out.println(++count);
    }

    public static void main(String[] args) {
        new EightQueen().cal8Queen(0);
    }

}
