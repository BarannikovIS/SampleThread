/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package netcracker.samplethread;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import org.apache.log4j.Logger;

/**
 *
 * @author Иван
 */
public class Main {

    private static final Logger Log = Logger.getLogger(Main.class);
    static int[][] matrixProduct;

    public static void main(String[] args) {
        //SortingMultipleArrays();
        //TestBlockingQueue();
        TestMatrix();
    }

    public static void TestMatrix() {
        int m = 50;
        int threads = 8;
        int[][] mas1 = new int[m][m];
        int[][] mas2 = new int[m][m];
        matrixProduct = new int[m][m];

        Random rnd = new Random();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                mas1[i][j] = rnd.nextInt(50);
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                mas2[i][j] = rnd.nextInt(50);
            }
        }

        int range = m / threads;
        MatrixMultiplier[] multiplier = new MatrixMultiplier[threads];
        for (int i = 0; i < threads; i++) {
            if (i == threads - 1) {
                multiplier[i] = new MatrixMultiplier(mas1, mas2, i * range, m);
            } else {
                multiplier[i] = new MatrixMultiplier(mas1, mas2, i * range, (i + 1) * (range));
            }
            multiplier[i].start();
        }

        for (int j = 0; j < multiplier.length; j++) {
            try {
                multiplier[j].join();
            } catch (InterruptedException ex) {
                Log.error(ex.getMessage(), ex);
            }
        }

        for (int k = 0; k < matrixProduct.length; k++) {
            for (int f = 0; f < matrixProduct.length; f++) {
                Log.info("index row " + k + " column index " + f + " value of the item " + matrixProduct[k][f]);
            }
        }
    }

    public static void SortingMultipleArrays() {
        int countMas = 3;
        ArrayList<int[]> arrays = new ArrayList<int[]>();
        Thread[] threads = new Thread[countMas];
        ArrayList<Sort> sorts = new ArrayList<Sort>();

        int[] mas1 = {56, 1, 34, 2, 78, 5, 3, 7};
        int[] mas2 = {9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
        int[] mas3 = {5, 8, 7, 101, 5, 4, 3, 5, 1, 1, 22};
        arrays.add(mas1);
        arrays.add(mas2);
        arrays.add(mas3);

        for (int i = 0; i < countMas; i++) {
            Sort s = new Sort(arrays.get(i));
            sorts.add(s);
            threads[i] = new Thread(s);
            threads[i].start();
        }
        for (int j = 0; j < countMas; j++) {
            try {
                threads[j].join();
            } catch (InterruptedException ex) {
                Log.error(ex.getMessage(), ex);
            }
        }
        for (int k = 0; k < countMas; k++) {
            String s = "";
            int sortMas[] = sorts.get(k).getMas();
            for (int m = 0; m < sortMas.length; m++) {
                s = s + " " + sortMas[m];
            }
            Log.info(s);
        }
    }

    public static void TestBlockingQueue() {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue(1, true);
        (new Thread(new Writer(blockingQueue))).start();
        (new Thread(new Reader(blockingQueue))).start();
    }
}
