package t126533;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int cnt = scanner.nextByte();
        //int[] heights = new int[cnt];
        int max, min;
        max = min = scanner.nextShort();
        for (int i = 1; i < cnt; i++) {
            int read = scanner.nextShort();
            if (read > max)
                max = read;
            else if (read < min)
                min = read;
        }
        System.out.println(max - min);
        System.out.println((max - min) > 50 ? 'N' : 'Y');
    }
}
