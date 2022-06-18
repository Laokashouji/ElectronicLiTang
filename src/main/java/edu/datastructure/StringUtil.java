package edu.datastructure;

import org.junit.Test;

public class StringUtil {

    @Test
    public void KMPTest() {

        String source = "数据结构课程设计报告";
        String target = "课程设计";

        System.out.println(KMP(source, target, 0));
    }

    public static int[] getNextVal(String target) {
            int[] nextVal = new int[target.length()];
            nextVal[0] = -1;

            for (int i = 0, k = -1; i < target.length() - 1; ) {
                if (k == -1 || target.charAt(i) == target.charAt(k)) {
                    i++;
                    k++;
                    if (target.charAt(i) == target.charAt(k)) {
                        nextVal[i] = nextVal[k];
                    } else {
                        nextVal[i] = k;
                    }
                } else {
                    k = nextVal[k];
                }
            }
            return nextVal;
    }

    public static int KMP(String source, String target, int pos) {
        int i = pos, j = 0;
        int next[] = getNextVal(target);
        while (i < source.length() && j < target.length()) {
            if (j == -1 || source.charAt(i) == target.charAt(j)) {
                i++;
                j++;
            } else {
                j = next[j];
            }
        }
        return j >= target.length() ? i - target.length() : -1;
    }
}
