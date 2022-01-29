package t126537;

import java.util.*;

public class Main implements Runnable {
    private final Map<Integer, Integer> root = new HashMap<>();
    private final Cache cache = new Cache();


    public void run() {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < 8; i++) {
            mergeWithRoot(split(sc.nextByte()));
        }
        long result = 1L;
        for (Map.Entry<Integer, Integer> entry : root.entrySet()) {
            int key = entry.getKey(), value = entry.getValue();
            for (int i = 0; i < value; i++) result *= key;
        }
        System.out.println(result);
    }

    private static void addOne(Map<Integer, Integer> map, int key) {
        if (map.containsKey(key)) {
            map.put(key, map.get(key) +1);
        } else {
            map.put(key, 1);
        }
    }

    private void split(int num, Map<Integer, Integer> putTo) {
        if (cache.hasCached(num)) {
            mapAdd(putTo, cache.get(num));
            return;
        }
        if (knownList.get(num)) {
            addOne(putTo, num);
            return;
        }

        for (int known : knownInt) {
            if (num % known == 0) {
                // can divide
                addOne(putTo, known);
                int num1 = num / known;
                //split(num1, putTo);
                Map<Integer, Integer> newMap = new HashMap<>();
                split(num1, newMap);
                mapAdd(putTo, newMap);
                cache.cache(num1, newMap);
                return;
            }
        } //throw new RuntimeException("Unknown cannot-divide num" + num);
        addOne(putTo, num); // very odd number
    }

    protected Map<Integer, Integer> split(int num) {
        //assert (num <= 0 || num > 100);
        // checkIndex done
        if (cache.hasCached(num)) {
            return cache.get(num);
        }

        HashMap<Integer, Integer> map = new HashMap<>();
        split(num, map);
        cache.cache(num, map);
        return map;
    }

    private static void mapAdd(Map<Integer, Integer> target, Map<Integer, Integer> added) {
        for (Map.Entry<Integer, Integer> entry : added.entrySet()) {
            int key = entry.getKey(), value = entry.getValue();
            if (target.containsKey(key)) {
                target.put(key, target.get(key) + value);
            } else {
                target.put(key, value);
            }
        }
    }

    protected void mergeWithRoot(Map<Integer, Integer> map) {
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int key = entry.getKey();
            int value = entry.getValue();
            if (!root.containsKey(key) || root.get(key) < value) {
                root.put(key, value);
            }
        }
    }

    private static final int[] knownInt = {
            2, 3, 5, 7,
            11, 13, 17, 19,
            23, 29, 31, 37,
            41, 43, 47,
            53, 59, 61, 67,
            71, 73, 79,
            83, 89, 97
    };
    private static final BitSet knownList = new BitSet(98);
    static {
        // verify done
        for (int i : knownInt)
            knownList.set(i);
    }

    static final class Cache {
        final Map<Integer, Map<Integer, Integer>> original2splitRes = new HashMap<>();
        {
            original2splitRes.put(1, Collections.emptyMap());
            // this can prevent adding {1: ???} to the root map
        }

        boolean hasCached(int num) {
            return original2splitRes.containsKey(num);
        }

        void cache(int num, Map<Integer, Integer> map) {
            original2splitRes.put(num, map);
        }

        Map<Integer, Integer> get(int num) {
            return original2splitRes.get(num);
        }
    }

    /* WRAPPER */

    public static void main(String[] args) {
        new Main().run();
    }
}
