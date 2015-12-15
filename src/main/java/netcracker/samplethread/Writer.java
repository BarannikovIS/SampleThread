/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package netcracker.samplethread;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import org.apache.log4j.Logger;

/**
 *
 * @author Иван
 */
public class Writer implements Runnable {
    private static final Logger Log = Logger.getLogger(Writer.class);
    private BlockingQueue<String> blockingQueue;
    List<String> wordlist = Arrays.asList("dsfdsf","123","qqqq","321","zzz");
    public Writer(BlockingQueue<String> blockingQueue) { 
        this.blockingQueue = blockingQueue; 
    }
    public void run() {
        try
        {
            for (int i=0;i<wordlist.size();i++){
                blockingQueue.put(wordlist.get(i));
            }
            blockingQueue.put("end");
        }
        catch (InterruptedException intEx)
        {
            Log.error("Interrupted!"+intEx.getMessage(),intEx);
        }
    }
}
