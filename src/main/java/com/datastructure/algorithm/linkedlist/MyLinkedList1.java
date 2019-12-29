package com.datastructure.algorithm.linkedlist;

public class MyLinkedList1 {
    //头节点指针
    private Node head;
    //尾节点指针
    private Node last;
    //链表实际长度
    private int size;

    /**
     * 插入节点
     *
     * @param data
     * @param index
     */
    public void insert(int data, int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }

        Node insertNode = new Node(data);

        if (size == 0) {
            //空链表新增第一个
            head = insertNode;
            last = insertNode;
        } else if (index == 0) {
            //插入头部
            insertNode.next = head;
            head = insertNode;
        } else if (index == size) {
            //插入尾部
            last.next = insertNode;
            last = insertNode;
        } else {
            //插入中间
            //找到前置节点
            Node prevNode = get(index - 1);
            //找到后置节点
            Node nextNode = prevNode.next;
            //前置节点next指针指向新插入的节点
            prevNode.next = insertNode;
            //新插入的节点的next指针指向原后置节点
            insertNode.next = nextNode;
        }
        size++;
    }

    /**
     * 删除节点
     *
     * @param index
     * @return
     */
    public Node remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("超出链表的范围");
        }

        Node removeNode = null;

        if (index == 0) {
            //删除头节点
            removeNode = head;
            head = head.next;
        } else if (index == size - 1) {
            //删除尾节点
            Node prevNode = get(index - 1);
            removeNode = prevNode;
            //前置节点变为尾节点
            last = prevNode;
            //尾节点的next变为null
            prevNode.next = null;
        } else {
            //删除中间节点
            //获取前置节点
            Node prevNode = get(index - 1);
            //根据前置节点获取下下个节点(要删除的节点的下一个节点)
            Node nextNode = prevNode.next.next;
            removeNode = prevNode.next;
            prevNode.next = nextNode;
        }

        size--;

        return removeNode;

    }

    /**
     * 链表查找元素
     *
     * @param index
     * @return
     */
    private Node get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        Node temp = head;
        for (int i = 0; i < index; i++) {
            //逐一查找
            temp = temp.next;
        }
        return temp;
    }

    /**
     * 输出链表
     */
    public void output() {
        Node temp = head;
        while (temp != null) {
            System.out.println(temp.data);
            temp = temp.next;
        }
    }

    public static void main(String[] args) {
        MyLinkedList1 myLinkedList1 = new MyLinkedList1();
        myLinkedList1.insert(3, 0);
        myLinkedList1.insert(7, 1);
        myLinkedList1.insert(4, 2);
        //插入尾节点
        myLinkedList1.insert(2, 3);
        //插入头节点
        myLinkedList1.insert(6, 0);
        //插入中间节点
        myLinkedList1.insert(8, 2);

        myLinkedList1.output();

        System.out.println("--------------------------");

        //移除头节点
        myLinkedList1.remove(0);
        //移除中间节点
        myLinkedList1.remove(3);

        myLinkedList1.output();
    }
}


