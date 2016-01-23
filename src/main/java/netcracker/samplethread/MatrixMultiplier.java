/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package netcracker.samplethread;

import static netcracker.samplethread.Main.matrixProduct;

/**
 *
 * @author Иван
 */
public class MatrixMultiplier extends Thread {

    private int[][] mas1;
    private int[][] mas2;
    private int start,stop; //диапазон строк в первой матрице, которую нужно перемножить на этот диапазон столбцов 2 матрицы в данном потоке

    public MatrixMultiplier(int[][] mas1, int[][] mas2, int start, int stop) {
        this.mas1 = mas1;
        this.mas2 = mas2;
        this.start = start;
        this.stop = stop;
    }

    @Override
    public void run() {
        for (int i = start; i < stop; i++) {
            for (int j = 0; j < mas2.length; j++) {
                int sum = 0;
                for (int r = 0; r < mas2.length; r++) {
                    sum = sum + mas1[i][r] * mas2[r][j];
                }
                matrixProduct[i][j] = sum;
            }
        }
    }
}
