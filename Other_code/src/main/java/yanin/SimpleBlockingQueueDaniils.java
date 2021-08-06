package yanin;

import java.util.LinkedList;
import java.util.Queue;

public class SimpleBlockingQueueDaniils<T> {
    
    private final Queue<T> queue = new LinkedList<>();
    
    //    private int lastIndexTook = -1;
    public SimpleBlockingQueueDaniils() {
    }
    
    public synchronized void offer(T value) {
//        if (queue.size() == lastIndexTook) {
//            try {
//                wait();
//            } catch (InterruptedException e) {
//                System.out.println("SimpleBlockingQueueDaniils.offer() :: Thread interrupted: " + Thread.currentThread().getName());
//                e.printStackTrace();
//            }
//        }
        queue.offer(value);
        notifyAll();
    }
    
    public synchronized T poll() throws InterruptedException {
        T value = null;
//        try {
//            while (queue.isEmpty()) {
//                wait();
//            }
        value = queue.poll();
        notifyAll();
//        } catch (InterruptedException e) {
//            System.out.println("SimpleBlockingQueueDaniils.offer() :: Thread interrupted: " + Thread.currentThread().getName());
//            e.printStackTrace();
//        }
        return value;
    }
    
    public synchronized int getSize() {
        return queue.size();
    }
    
    public synchronized boolean isEmpty() {
        return queue.isEmpty();
    }
}

