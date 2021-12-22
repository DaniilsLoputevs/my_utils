package satomi.threads;

import lombok.SneakyThrows;
import lombok.val;

/**
 * 2. Обеспечить остановку потребителя. [#66825]
 * Уровень : 3. Мидл Категория : 3.1. MultithreadingТопик : 3.1.4. Wait, Notify, NotifyAll
 * В этом задании нужно разработать механизм остановки потребителя,
 * когда производитель закончил свою работу.
 * Изменить код, так, что бы потребитель завершал свою работу. Код нужно изменить в методе main.
 */
public class ParallelSearch {
    @SneakyThrows
    public static void main(String[] args) {
        val queue = new SimpleBlockingQueue<Integer>();
        
        val producer = new Thread(() -> {
            try {
                for (int index = 0; index < 3; index++) {
// queue.offer(index); - Немедленное размещение элемента в очереди при наличие свободного места;
// метод вернет true при успешном завершении операции, в противном случае вернет false.
                    
                    System.out.println("producer # offer = " + index);
                    queue.offer(index);
                    Thread.sleep(500);
                    
                    if (Thread.currentThread().isInterrupted()) {
                        return;
                    };
                }
                
            } catch (InterruptedException e) {
                System.err.println("producer was interrupted while sleep");
                e.printStackTrace();
            }
        }, "producer");
        
        val consumer = new Thread(() -> {
            try {
                while (producer.isAlive() || !queue.isEmpty()) {
                    System.out.println("consumer # poll = " + queue.poll());
                    Thread.sleep(1000);
                }
                
                System.out.println("exit while");
            } catch (InterruptedException e) {
                System.err.println("consumer was interrupted while sleep");
                e.printStackTrace();
            }
        }, "consumer");
        
        producer.start();
        consumer.start();
        
        System.out.println("Main # join producer");
        producer.join();
        System.out.println("Main # join consumer");
        consumer.join();
        
        System.out.println("Consumer state : " + consumer.getState());
        System.out.println("Producer state : " + producer.getState());
    }
    
    
    public static void fu () {
        return;
    }
}
