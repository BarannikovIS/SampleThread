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
    private int num; //номер строки в первой матрице, которую нужно перемножить на все столбцы 2 матрицы в данном потоке

    public MatrixMultiplier(int[][] mas1, int[][] mas2, int num) {
        this.mas1 = mas1;
        this.mas2 = mas2;
        this.num=num;
    }

    @Override
    public void run() {
            for (int j = 0; j < mas2.length; j++) {
                int sum = 0;
                for (int r = 0; r < mas2.length; r++) {
                    sum = sum + mas1[num][r] * mas2[r][j];
                }
                matrixProduct[num][j] = sum;
            }
    }
}
