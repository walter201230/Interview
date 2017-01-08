package com.liangdianshui;

import java.util.ArrayList;

public class Pilgrimage {

    final static int times[] = { 1, 2, 5, 10 };// 花费时间
    final static String names[] = { "大师兄", "二师兄", "三师兄", "师傅" };// 人物

    public static void main(String[] args) {
        Integer other_bridges[] = { 0, 0, 0, 0 };// 桥另一边
        Integer bridges[] = { 1, 1, 1, 1 };// 当前桥这边 ，1表示存在，0表示不在

        // 开始递归
        loop(bridges, other_bridges, 0, new StringBuffer());
    }

    private static void loop(Integer[] bridges, Integer[] other_bridges,
            int time, StringBuffer msg) {
        ArrayList<Integer> positions = new ArrayList<Integer>();// 记录还在起始端人的下标
        for (int i = 0; i < bridges.length; i++) {
            if (bridges[i] == 1) {
                positions.add(i);// 记录下标
            }
        }
        int len = positions.size();

        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) { // 循环取结合
                // 记录状态
                Integer[] zt_bridges = bridges.clone();
                Integer[] zt_other_bridges = other_bridges.clone();
                int zt_time = time;
                StringBuffer zt_msg = new StringBuffer(msg);

                // 过桥---------
                time += times[positions.get(j)];// 花费时间直接取最大的
                move(bridges, other_bridges, 1, positions.get(i));
                move(bridges, other_bridges, 1, positions.get(j));
                msg.append(" 过桥:" + names[positions.get(i)] + "&"
                        + names[positions.get(j)]);
                // System.out.print(" 过桥:" + names[positions.get(i)] + "_"
                // + names[positions.get(j)]);

                // 回来接人------
                if (isend(other_bridges)) {
                    msg.append(" 总共花费:" + time);
                    System.out.println(msg.toString());
                    // System.out.println(" 总共花费:" + time);
                    return;
                }
                int k = 0;
                for (int ii = 0; ii < other_bridges.length; ii++) {// 选择最快的回来
                    if (other_bridges[ii] == 1) {
                        k = ii;
                        break;
                    }
                }
                time += times[k];
                move(bridges, other_bridges, 0, k);
                msg.append("  回来:" + names[k]+"  ***  ");
                // System.out.print(" 回来:" + names[k]);

                // 继续循环遍历
                loop(bridges.clone(), other_bridges.clone(), time,
                        new StringBuffer(msg));

                // 还原状态
                bridges = zt_bridges;
                other_bridges = zt_other_bridges;
                time = zt_time;
                msg = zt_msg;
            }
        }
    }

    /**
     * 移动的那个人
     * 
     * @param bridges
     * @param other_bridges
     * @param direction
     *            方向
     * @param position
     */
    private static void move(Integer[] bridges, Integer[] other_bridges,
            int direction, int position) {
        if (direction == 1) {// 往另一端走
            bridges[position] = 0;
            other_bridges[position] = 1;
        } else {// 回来接人走
            bridges[position] = 1;
            other_bridges[position] = 0;
        }
    }

    // 判断是否已经结束了
    // 当other_bridges {1,1,1,1}表示结束
    private static boolean isend(Integer[] other_bridges) {
        for (int i : other_bridges) {
            if (i == 0)
                return false;
        }
        return true;
    }

}