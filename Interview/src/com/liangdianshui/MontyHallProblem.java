package com.liangdianshui;

import java.util.Random;

public class MontyHallProblem {

    public static void main(String[] args) {
        // 重复五次
        for (int i = 0; i < 4; i++) {
            montyHallProblem();
            System.out.println("----------------------------------");
        }
    }

    public static void montyHallProblem() {
        Random random = new Random(); // 这里不讨论Random为伪随机的问题
        int changeCount = 0;
        for (int i = 0; i < 1000000.0f; i++) { // 模拟一百万次
            // 假设有三个门
            int[] doors = new int[3];

            // 随机抽取一扇门 ,在门后放奖品
            int rIndex = random.nextInt(3);
            doors[rIndex] = 1;

            // 观众选的门号
            int randomSelect = random.nextInt(3);

            // 主持人从剩下的两扇门中排除一个
            while (true) {
                int randomDelete = random.nextInt(3);
                // 主持人不会打开参赛者已经选了的门（排除参赛者选择的门）
                if (randomDelete == randomSelect) {
                    continue;
                }
                // 主持人不会打开有奖品的门（排除有奖品的门）
                if (doors[randomDelete] == 1) {
                    continue;
                }

                for (int j = 0; j < 3; j++)// 换门
                {
                    if (j == randomSelect)// 不换门（因为我们要得到的是换门的概率，因此把不换门的排除掉）
                        continue;
                    // 排除主持人打开了那个门（因为门已经打开，所以不能换，排除掉）
                    if (j == randomDelete)
                        continue;
                    if (doors[j] == 1) {
                        changeCount++;// 换了门后中奖的次数
                        break;
                    }
                }
                break;
            }
        }
        System.out.println("换门中奖率:" + changeCount / 1000000.0f);
    }

}