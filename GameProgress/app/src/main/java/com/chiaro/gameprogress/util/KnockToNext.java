package com.chiaro.gameprogress.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * V1.1
 * 可控制出现，并控制是否结束
 * Created by chiaro on 2016/11/24.
 */
public class KnockToNext {

    /**
     * @param args
     */
    public static void main(String[] args) {
        // 假设一共5个角色，每个人的速度为以下值
        int[] speed = new int[]{165, 151, 143, 128, 125, 117};
        // 假设总行动条为以下值
        double perLength = 200.0;

        startPlay(speed, perLength);

    }

    /**
     *  每个角色按照速度依次出场，可控制继续或者结束
     * @param speed  每个角色的速度值
     * @param perLength     行动条的长度
     */
    public static void startPlay(int[] speed, double perLength) {
        Scanner scanner = new Scanner(System.in);// 创建输入流扫描器

        System.out.println("输入\"y\"并回车到下一位，输入其他内容结束");
        System.out.println("现在开始");

        // 由于已有的速度是一个简单数值，因此需要计算一下。
        // 每过一个标准单位，在行动条上该角色到底行动多远
        double[] realSpeed = new double[speed.length];
        for (int i = 0; i < speed.length; i++) {
            realSpeed[i] = speed[i] / perLength;
        }

        // 记录每个角色出场了多少次
        int[] count = new int[speed.length];
        for (int i = 0; i < count.length; i++) {
            count[i] = 0;
        }

        // 记录第几个出场的是谁
        List<Integer> turn = new ArrayList<>();

        // 每个角色从开始，一直到当前时间行进的距离
        double[] length = new double[speed.length];

        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            // 计算一下在当前时间的时候，每个角色走了多远
            for (int j = 0; j < speed.length; j++) {
                length[j] = realSpeed[j] * i;
            }

            // 每过一个单位时间就判断一下，是否某个角色该出场了。
            for (int k = 0; k < speed.length; k++) {

                // 即前进的距离是否是perLength的整数倍。
                // 但是由于单位时间内前进的距离不是整数，因此到某个时间时，前进的距离也不是整数。故根据范围来判断
                // 根据范围的话，看 当前行进的距离，是否小于等于下一个行动点，并且只要再走一步，就大于等于下一个行动点了
                if (length[k] <= (count[k] + 1) * perLength && (length[k] + speed[k]) >= (count[k] + 1) * perLength) {
                    // 速度排名第k的符合要求，要出场了
                    // 先加到队列里，记录一下
                    turn.add(k);

                    // count计数加一个
                    count[k] = count[k] + 1;

                    System.out.println("第" + turn.size() + "出手的人是：" + k);

                    System.out.print("请输入（输入\"y\"并回车继续，输入其他内容结束）：");
                    String command = scanner.nextLine();// 获取用户输入的一行文本
                    if (null != command && command.equals("y")) { // 如果要继续
                        // 不用做什么
                    } else {
                        // 如果结束了，则打出到现在为止所有的顺序然后return
                        System.out.print("各个角色出场顺序为：");
                        for (int m = 0; m < turn.size(); m++) {
                            System.out.print(turn.get(m));
                        }
                        System.out.println();
                        for (int n = 0 ; n<speed.length;n++){
                            System.out.println("其中，"+n+"号角色出场" + count[n] + "次");
                        }

                        return;
                    }
                }
            }

        }
    }

}
