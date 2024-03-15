package projekt;

import java.util.concurrent.Semaphore;

public class TrainConsumerProducer extends Thread{
    public TrainQueue buffer;
    private final int maxSize;
    private final Semaphore producerSemaphore;
    private final Semaphore consumerSemaphore;
    private final Semaphore mutex;
    public boolean is_renovation;


    public TrainConsumerProducer(int maxSize) {
        this.maxSize = maxSize;
        buffer = new TrainQueue(maxSize);
        producerSemaphore = new Semaphore(maxSize);
        consumerSemaphore = new Semaphore(0);
        mutex = new Semaphore(1);
        is_renovation=false;
    }

    public void produce(Train item) throws InterruptedException {
        producerSemaphore.acquire();
        mutex.acquire();
        buffer.add(item);
        mutex.release();
        consumerSemaphore.release();
    }
    public void produce1() throws InterruptedException {
        producerSemaphore.acquire();

        consumerSemaphore.release();
    }
    public void produce2(Train item) throws InterruptedException {

        mutex.acquire();
        buffer.add(item);
        mutex.release();

    }

    public Train consume(Train train) throws InterruptedException {
        consumerSemaphore.acquire();
        mutex.acquire();
        buffer.remove(train);
        mutex.release();
        producerSemaphore.release();
        return train;
    }

}