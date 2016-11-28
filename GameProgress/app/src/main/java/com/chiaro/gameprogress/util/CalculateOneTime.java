package com.chiaro.gameprogress.util;

/**
 * V1.0
 * 一次性直接全部计算出出场顺序。会规定多少单位时间
 * Created by chiaro on 2016/11/23.
 */
public class CalculateOneTime {

    /**
     * @param args
     */
    public static void main(String[] args) {
        // 假设一共5个角色，每个人的速度为以下值
        int[] speed = new int[]{165, 151, 143, 128, 125, 117};
        // 假设总行动条为以下值
        double perLength = 200.0;
        int time = 20000;
        gameProcessCal(speed, perLength,time);
    }

    /**
     *  一次性计算出，固定数量单位时间内的个角色出场顺序及次数
     * @param speed  每个角色的速度值
     * @param perLength     行动条的长度
     * @param time      计算多少个单位时间
     */
    public static void gameProcessCal(int[] speed, double perLength,int time) {
        StringBuffer stringBuffer = new StringBuffer(); // 准备一个StringBuffer，记录出场顺序

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

        // 每个角色从开始，一直到当前时间行进的距离
        double[] length = new double[speed.length];

        // 假设一共有10000个单位时间
        for (int i = 0; i <= time; i++) {

            // 计算一下在当前时间的时候，每个角色走了多远
            for (int j = 0; j < speed.length; j++) {
                length[j] = realSpeed[j] * i;
            }

            // 每过一个单位时间就判断一下，是否某个角色该出场了。
            // 即前进的距离是否是perLength的整数倍。
            // 但是由于单位时间内前进的距离不是整数，因此到某个时间时，前进的距离也不是整数。故根据范围来判断
            // 根据范围的话，看 当前行进的距离，是否小于等于下一个行动点，并且只要再走一步，就大于等于下一个行动点了
            for (int k = 0; k < speed.length; k++) {
                if (length[k] <= (count[k] + 1) * perLength && (length[k] + speed[k]) >= (count[k] + 1) * perLength) {
                    // 符合要求
                    // 记录一下
                    stringBuffer.append(k);
                    // count计数加一个
                    count[k] = count[k] + 1;
                }
            }
        }

        System.out.println("各个角色出场顺序为===" + stringBuffer);

        System.out.println("其中，0号角色出场" + count[0] + "次");
        System.out.println("其中，1号角色出场" + count[1] + "次");
        System.out.println("其中，2号角色出场" + count[2] + "次");
        System.out.println("其中，3号角色出场" + count[3] + "次");
        System.out.println("其中，4号角色出场" + count[4] + "次");

    }

}
