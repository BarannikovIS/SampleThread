/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package netcracker.samplethread;

/**
 *
 * @author Иван
 */
public class Sort implements Runnable {

    private int[] mas;

    public int[] getMas(){
        return mas;
    }
    public Sort(int[] mas) {
        this.mas = mas;
    }

    @Override
    public void run() {
        for (int i = 1; i < mas.length; i++) {
            int currElem = mas[i];
            int prevKey = i - 1;
            while (prevKey >= 0 && mas[prevKey] > currElem) {
                mas[prevKey + 1] = mas[prevKey];
                mas[prevKey] = currElem;
                prevKey--;
            }
        }
    }

}
