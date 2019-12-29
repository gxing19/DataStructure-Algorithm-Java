package com.datastructure.algorithm.queue;

public class LoopQueue {

    private int[] array;
    private int front;
    private int rear;

    public LoopQueue(int size) {
        this.array = new int[size];
    }

    /**
     * 入队
     *
     * @param element 入队的元素
     */
    public void enQueue(int element) throws Exception {

        if ((rear + 1) % array.length == front) {
            throw new Exception("队列已满");
        }
        //入队元素
        array[rear] = element;
        //队尾指针指向出队元素的位置
        rear = (rear + 1) % array.length;

    }

    /**
     * 出队
     *
     * @return
     */
    public int deQueue() throws Exception {
        if (rear == front) {
            throw new Exception("队列已空");
        }
        //队头元素出队
        int deQueueElement = array[front];
        //队头指针后移
        front = (front + 1) % array.length;
        return deQueueElement;
    }

    /**
     * 输出队列
     */
    public void output() {
        //如果队头指针与队尾指针不相等，则队列仍有元素
        for (int i = front; i != rear; i = (i + 1) % array.length) {
            //输出队头到队尾之间的元素
            System.out.println(array[i]);
        }
    }

    public static void main(String[] args) throws Exception {
        LoopQueue loopQueue = new LoopQueue(6);

        loopQueue.enQueue(1);
        loopQueue.enQueue(3);
        loopQueue.enQueue(7);
        loopQueue.enQueue(2);
        loopQueue.enQueue(4);

        loopQueue.output();
        System.out.println("-----------------------");

        loopQueue.deQueue();
        loopQueue.output();

        loopQueue.deQueue();
        loopQueue.deQueue();
        loopQueue.deQueue();
        loopQueue.output();
    }
}
