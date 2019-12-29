package com.datastructure.algorithm.tree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 创建二叉树并遍历
 */
public class BinaryTree {

    /**
     * 构建二叉树
     *
     * @param inputList 输入序列
     * @return
     */
    public static TreeNode createBinaryTree(LinkedList<Integer> inputList) {
        TreeNode node = null;
        if (inputList == null || inputList.isEmpty()) {
            return null;
        }
        Integer data = inputList.removeFirst();
        if (data != null) {
            node = new TreeNode(data);
            //先创建左节点，直到为 null,再创建右边节点
            node.leftChild = createBinaryTree(inputList);
            node.rightChild = createBinaryTree(inputList);
        }
        return node;
    }

    /**
     * 递归实现：前序遍历
     *
     * @param node
     */
    public static void preOrder(TreeNode node) {
        if (node == null) {
            return;
        }

        System.out.println(node.data);
        //递归调用输出左树节点直到 null, 再输出右树节点
        preOrder(node.leftChild);
        preOrder(node.rightChild);
    }

    /**
     * 非递归实现：前序遍历
     *
     * @param treeNode
     */
    public static void preOrderWithStack(TreeNode treeNode) {
        Stack<TreeNode> stack = new Stack<>();

        while (treeNode != null || !stack.isEmpty()) {
            //迭代访问节点的左子节点，并入栈
            while (treeNode != null) {
                System.out.println(treeNode.data);
                stack.push(treeNode);
                treeNode = treeNode.leftChild;
            }

            //如果节点没有左子节点，则弹出栈顶节点，访问右子节点
            if (!stack.isEmpty()) {
                treeNode = stack.pop();
                treeNode = treeNode.rightChild;
            }
        }

    }

    /**
     * 递归实现：中序遍历
     *
     * @param node
     */
    public static void middleOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        //递归调用直到下级为 null, 再输出叶子节点
        middleOrder(node.leftChild);
        System.out.println(node.data);
        middleOrder(node.rightChild);
    }

    /**
     * 递归实现：后序遍历
     *
     * @param node
     */
    public static void postOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        postOrder(node.leftChild);
        postOrder(node.rightChild);
        System.out.println(node.data);

    }

    /**
     * 递归实现：层序遍历
     *
     * @param root
     */
    public static void levelOrder(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            System.out.println(node.data);
            if (node.leftChild != null) {
                queue.offer(node.leftChild);
            }
            if (node.rightChild != null) {
                queue.offer(node.rightChild);
            }
        }
    }

    public static void main(String[] args) {
        Integer[] intArray = {6, 2, 4, null, null, 3, null, null, 5, null, 8};
        LinkedList<Integer> inputList = new LinkedList<>(Arrays.asList(intArray));

        TreeNode node = BinaryTree.createBinaryTree(inputList);
        System.out.println(node);

        System.out.println("--------前序--------");
        preOrder(node);//6,2,4,3,5,8
        System.out.println("--------中序--------");
        middleOrder(node);//4,2,3,6,5,8
        System.out.println("--------后序--------");
        postOrder(node);//4,3,2,8,5,6
        System.out.println("--------层序--------");
        levelOrder(node);//6,2,5,4,3,8
        System.out.println("----非递归实现前序遍历--------");
        preOrderWithStack(node);//6,2,4,3,5,8

    }
}

