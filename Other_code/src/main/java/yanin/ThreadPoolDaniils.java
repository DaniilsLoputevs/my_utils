package yanin;

import java.util.ArrayList;
import java.util.List;

public class ThreadPoolDaniils {
    private final int threadCount = Runtime.getRuntime().availableProcessors();
    private final List<Thread> threads = new ArrayList<>();
    private final SimpleBlockingQueueDaniils<Runnable> tasks = new SimpleBlockingQueueDaniils<>();
    private final Object syncObj = new Object();
    
    public static void main(String[] args) {
        ThreadPoolDaniils threadPool = new ThreadPoolDaniils();
        threadPool.work(createTask(1)); // 1
        threadPool.work(createTask(2)); // 2
        threadPool.work(createTask(3)); // 3
        threadPool.work(createTask(4)); // 4
        threadPool.work(createTask(5)); // 5
        threadPool.work(createTask(6)); // 6
        threadPool.work(createTask(7)); // 7
        threadPool.init();
        threadPool.work(createTask(8)); // 8
        threadPool.work(createTask(9)); // 9
        threadPool.work(createTask(10)); // 10
        threadPool.shutdown();


//        var thread = new Thread(() -> {
//            while (true) {
//                System.out.println("some");
//                if (Thread.interrupted()) {
//                    break;
//                }
//            }
//        });
//
//        thread.start();
//
//        // main sleep up to 2 sec
//        try {
//            Thread.sleep(2 * 1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        thread.interrupt();
//        thread.isInterrupted();
    
    }
    
    private static Runnable createTask(int taskNum) {
        return () -> {
            var rsl = 0;
            for (int i = 0; i <= 100; i++) {
//                System.out.println("loading: " + i);
//                System.out.println("work-task() :: Thread: " + Thread.currentThread().getName());
                rsl = i;
            }
            System.out.println("Task #" + taskNum + " - finished! Num = " + rsl);
        };
    }
    
    public void init() {
        for (int i = 0; i < threadCount; i++) {
            
            threads.add(new Thread(() -> {
                int taskCount = tasks.getSize();
                while (true) {
                    try {
                        while (!tasks.isEmpty()) {
                            tasks.poll().run();
                            if (Thread.interrupted()) break;
                        }
                        if (Thread.interrupted()) break;
                        if (taskCount == tasks.getSize())
//                        Thread.sleep(1 * 1000);
                            syncObj.wait();
                    } catch (InterruptedException e) {
                        break;
//                        System.out.println("ThreadPoolDaniils.init() :: Thread interrupted: " + Thread.currentThread().getName());
//                        e.printStackTrace();
                    }
                }
                
            }, "Poll thread #" + i));
        }
        threads.forEach(Thread::start);
    }
    
    public void work(Runnable job) {
        tasks.offer(job);
//        for (var t : threads) {
//
//        }
//        threads.forEach(syncObj::notify);
        syncObj.notifyAll();
    }
    
    public void shutdown() {
        threads.forEach(Thread::interrupt);
    }
}