package satellite;

import java.io.File;
import java.util.Scanner;

public class Satellite {
    public static void main(String[] args) throws Exception {
        File f = new File("input.txt").exists() ? new File("input.txt") : new File("muhold.be");
        Scanner sc = new Scanner(f);
        int n = sc.nextInt(), m = sc.nextInt();
        int[][] img1 = readImg(sc, n, m), img2 = readImg(sc, n, m);
        sc.close();
        printRes(img1, img2);
    }

    static int[][] readImg(Scanner sc, int n, int m) {
        int[][] img = new int[n][m];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                img[i][j] = sc.nextInt();
        return img;
    }

    static int getBound(int[][] a, int[][] b, boolean isRow, boolean isMin) {
        int p = isRow ? a.length : a[0].length, s = isRow ? a[0].length : a.length;
        int start = isMin ? 0 : p - 1, end = isMin ? p : -1, step = isMin ? 1 : -1;
        for (int i = start; i != end; i += step)
            for (int j = 0; j < s; j++)
                if (isRow ? a[i][j] != b[i][j] : a[j][i] != b[j][i]) return i + 1;
        return 0;
    }

    static void printRes(int[][] a, int[][] b) {
        int x1 = getBound(a, b, true, true), y1 = getBound(a, b, false, true);
        int x2 = getBound(a, b, true, false), y2 = getBound(a, b, false, false);
        System.out.println(x1 + " " + y1 + " " + x2 + " " + y2);
    }
}