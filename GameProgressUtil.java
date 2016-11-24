
/**
 * Created by chiaro on 2016/11/23.
 */
public class GameProgressUtil {

    /**
     * @param args
     */
    public static void main(String[] args) {
        // ����һ��5����ɫ��ÿ���˵��ٶ�Ϊ����ֵ
        int[] speed = new int[]{165,151,143,128,125,117};
        // �������ж���Ϊ����ֵ
        double perLength = 200.0;
        gameProcessCal(speed,perLength);
    }

    /**
     * �������ɫ����˳��
     */
    private static void gameProcessCal(int[] speed , double perLength) {
        StringBuffer stringBuffer  = new StringBuffer(); // ׼��һ��StringBuffer����¼����˳��

        // �������е��ٶ���һ������ֵ�������Ҫ����һ�¡�
        // ÿ��һ����׼��λ�����ж����ϸý�ɫ�����ж���Զ
        double[] realSpeed = new double[speed.length];
        for(int i = 0;i<speed.length;i++){
            realSpeed[i] = speed[i] / perLength ;
        }

        // ��¼ÿ����ɫ�����˶��ٴ�
        int[] count = new int[speed.length];
        for(int i = 0;i<count.length;i++){
            count[i] = 0;
        }

        // ÿ����ɫһ���н��ľ���
        double[] length = new double[speed.length];

        // ����һ����10000����λʱ��
        for (int i = 0 ; i<= 5000 ; i++){

            // ����һ���ڵ�ǰʱ���ʱ��ÿ����ɫ���˶�Զ
            for (int j = 0 ;j<speed.length;j++){
                length[j] = realSpeed[j] * i;
            }

            // ÿ��һ����λʱ����ж�һ�£��Ƿ�ĳ����ɫ�ó����ˡ�
            // ��ǰ���ľ����Ƿ���perLength����������
            // �������ڵ�λʱ����ǰ���ľ��벻����������˵�ĳ��ʱ��ʱ��ǰ���ľ���Ҳ�����������ʸ��ݷ�Χ���ж�
            // ���ݷ�Χ�Ļ����� ��ǰ�н��ľ��룬�Ƿ�С�ڵ�����һ���ж��㣬����ֻҪ����һ�����ʹ��ڵ�����һ���ж�����
            for (int k = 0;k<speed.length; k++){
                if(length[k] <= (count[k]+1)*perLength && (length[k]+speed[k]) >= (count[k]+1)*perLength){
                    // ����Ҫ��
                    // ��¼һ��
                    stringBuffer.append(k);
                    // count������һ��
                    count[k] = count[k] +1;
                }
            }
        }

        System.out.println("������ɫ����˳��Ϊ==="+stringBuffer);

        System.out.println("���У�0�Ž�ɫ����"+count[0]+"��");
        System.out.println("���У�1�Ž�ɫ����"+count[1]+"��");
        System.out.println("���У�2�Ž�ɫ����"+count[2]+"��");
        System.out.println("���У�3�Ž�ɫ����"+count[3]+"��");
        System.out.println("���У�4�Ž�ɫ����"+count[4]+"��");

    }

}
