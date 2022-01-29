package t142144;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    // TODO: reduce memory use
    private static void addOne(Map<Integer, Integer> map, int key) {
        if (map.containsKey(key)) {
            map.put(key, map.get(key) + 1);
        } else {
            map.put(key, 1);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(), t = scanner.nextInt();
        HashMap<Integer, Integer> map = new HashMap<>(8000);
        for (int i = 0; i < n; i++) {
            int j = scanner.nextInt();
            addOne(map, j);
        }
        PrintStream ps = System.out;
        Integer zero = 0;
        for (int i = 0; i < t; i++) {
            int j = scanner.nextInt();  // ask for height
            ps.print(map.getOrDefault(j, zero));
            ps.print(' ');
        } ps.println();
    }
}
