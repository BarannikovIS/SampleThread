/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package netcracker.samplethread;

import java.util.concurrent.BlockingQueue;
import org.apache.log4j.Logger;

/**
 *
 * @author Иван
 */
public class Reader implements Runnable{
    private static final Logger Log = Logger.getLogger(Reader.class);
    private BlockingQueue<String> blockingQueue;
    public Reader(BlockingQueue<String> blockingQueue) { 
        this.blockingQueue = blockingQueue; 
    }
    
    public void run()
    {
        try
        {
            String s = null;
            while (!((s = blockingQueue.take()).equals("end")))
            Log.info(s);
        }
        catch (InterruptedException intEx)
        {
            Log.error("Interrupted!"+intEx.getMessage(),intEx);
        }
    }
}
