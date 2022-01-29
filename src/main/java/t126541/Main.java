package t126541;

import java.util.Scanner;

public class Main {
    public static int find(String str, String word) {
        //if (!str.contains(word)) return -1;
        int found = 0;
        int wrdSize = word.length();
        int till = str.length() - wrdSize + 1;
        for (int i = 0; i < till; i++) {
            if (str.regionMatches(i, word, 0, wrdSize))
                found++;
        } return found == 0 ? -1 : found;
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str, word;
        str = sc.next();
        word = sc.next();
        System.out.println(find(str, word));
    }
}
