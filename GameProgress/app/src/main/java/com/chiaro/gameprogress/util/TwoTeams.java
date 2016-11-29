package com.chiaro.gameprogress.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * V1.2
 * 分两个队，每队人数不固定  速度有差异
 * Created by chiaro on 2016/11/28.
 */
public class TwoTeams {
    /**
     * @param args
     */
    public static void main(String[] args) {

        // 假设有两个队伍a/b，每个队伍成员的速度如下
        int[] aTeam = new int[]{165, 151, 143, 128, 125, 117};
        int[] bTeam = new int[]{134, 166, 129, 128, 105};
        // 假设总行动条为以下值
        double perLength = 200.0;

        startPlay(aTeam, bTeam, perLength);

    }

    /**
     * 分两个队，每队人数不固定  速度有差异  控制出场和结束
     *
     * @param aTeam     第一个队的速度数组
     * @param bTeam     第二个队的速度数组
     * @param perLength 行动条单位长度
     */
    private static void startPlay(int[] aTeam, int[] bTeam, double perLength) {

        Scanner scanner = new Scanner(System.in);// 创建输入流扫描器

        System.out.println("输入\"y\"并回车到下一位，输入其他内容结束");
        System.out.println("现在开始");

        // 先建一个数组，包含所有的角色
        // 为了防止错位，这个新数组前面是aTeam，后面是bTeam
        int[] allTeam = new int[aTeam.length + bTeam.length];
        for (int i = 0; i < allTeam.length; i++) {
            if (i < aTeam.length) {
                allTeam[i] = aTeam[i];
            } else {
                allTeam[i] = bTeam[i - aTeam.length];
            }
        }


        // 由于已有的速度是一个简单数值，因此需要计算一下。
        // 每过一个标准单位，在行动条上该角色到底行动多远
        // 计算所有人的，最后再根据位置，去判断是哪个team
        double[] realSpeed = new double[allTeam.length];
        for (int i = 0; i < allTeam.length; i++) {
            realSpeed[i] = allTeam[i] / perLength;
        }

        // 记录每个角色出场了多少次
        // 记录所有人的，最后再根据位置，去判断是哪个team
        int[] count = new int[allTeam.length];
        for (int i = 0; i < count.length; i++) {
            count[i] = 0;
        }

        // 记录第几个出场的是谁
        List<Integer> turn = new ArrayList<>();

        // 每个角色从开始，一直到当前时间行进的距离
        double[] length = new double[allTeam.length];

        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            // 计算一下在当前时间的时候，每个角色走了多远
            for (int j = 0; j < realSpeed.length; j++) {
                length[j] = realSpeed[j] * i;
            }

            // 每过一个单位时间就判断一下，是否某个角色该出场了。
            for (int k = 0; k < realSpeed.length; k++) {

                // 即前进的距离是否是perLength的整数倍。
                // 但是由于单位时间内前进的距离不是整数，因此到某个时间时，前进的距离也不是整数。故根据范围来判断
                // 根据范围的话，看 当前行进的距离，是否小于等于下一个行动点，并且只要再走一步，就大于等于下一个行动点了
                if (length[k] <= (count[k] + 1) * perLength && (length[k] + realSpeed[k]) >= (count[k] + 1) * perLength) {
                    // 速度排名第k的符合要求，要出场了
                    // 先加到队列里，记录一下
                    turn.add(k);

                    // count计数加一个
                    count[k] = count[k] + 1;

                    if (k < aTeam.length) {
                        System.out.println("第" + turn.size() + "出手的人是：aTeam的" + k);
                    } else {
                        System.out.println("第" + turn.size() + "出手的人是：bTeam的" + (k - aTeam.length));
                    }

                    System.out.print("请输入（输入\"y\"并回车继续，输入其他内容结束）：");
                    String command = scanner.nextLine();// 获取用户输入的一行文本
                    if (null != command && command.equals("y")) { // 如果要继续
                        // 不用做什么
                    } else {
                        // 如果结束了，则打出到现在为止所有的顺序然后return

                        for (int n = 0; n < count.length; n++) {
                            if (n < aTeam.length) {
                                System.out.println("aTeam的" + n + "出场了" + count[n] + "次");
                            } else {
                                System.out.println("bTeam的" + (n-aTeam.length)+ "出场了" + count[n - aTeam.length] + "次");
                            }
                        }
                        return;
                    }

                }


            }

        }


    }
}
