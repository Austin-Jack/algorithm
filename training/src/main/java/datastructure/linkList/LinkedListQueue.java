package datastructure.linkList;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 使用链表实现队列
 * @author luolinyuan
 * @since 2025/4/25
 */
public class LinkedListQueue {
	/**
	 * 队列节点类
	 * 
	 * 用于存储队列中的元素和指向下一个节点的引用
	 * 每个节点包含：
	 * - 存储的值（val）
	 * - 指向下一个节点的引用（next）
	 */
	private static class Node {
		/**
		 * 节点存储的值
		 */
		public int val;

		/**
		 * 指向下一个节点的引用
		 * 如果为null，表示这是队列的最后一个节点
		 */
		public Node next;
	}

	/**
	 * 队列的头节点
	 * 
	 * 指向队列的第一个元素
	 * 如果队列为空，则为null
	 */
	private Node head;

	/**
	 * 队列的尾节点
	 * 
	 * 指向队列的最后一个元素
	 * 如果队列为空，则为null
	 */
	private Node tail;

	/**
	 * 队列中元素的数量
	 * 
	 * 用于快速获取队列大小
	 * 当队列为空时为0
	 */
	private int size;

	/**
	 * 构造一个空的队列
	 * 
	 * 初始化：
	 * - 头节点为null
	 * - 尾节点为null
	 * - 队列大小为0
	 */
	public LinkedListQueue() {
		this.size = 0;
		this.head = null;
		this.tail = null;
	}

	/**
	 * 将指定元素添加到队列的末尾
	 * @param val 要添加到队列的元素
	 */
	public void offer(int val) {
		Node node = new Node();
		node.val = val;
		if (this.tail == null) {
			this.head = this.tail = node;
		} else {
			this.tail.next = node;
			this.tail = node;
		}
		this.size++;
	}

	/**
	 * 移除并返回队列头部的元素
	 * @return 队列头部的元素
	 * @throws IllegalStateException 如果队列为空
	 */
	public int poll() {
		if (this.size == 0) {
			throw new IllegalStateException("队列无元素，出队失败");
		}
		int val = this.head.val;
		this.head = this.head.next;
		if (this.head == null) {
			this.tail = null;
		}
		this.size--;
		return val;
	}

	/**
	 * 返回队列头部的元素，但不移除它
	 * @return 队列头部的元素
	 * @throws IllegalStateException 如果队列为空
	 */
	public int peek() {
		if (this.size == 0) {
			throw new IllegalStateException("队列无元素");
		}
		return this.head.val;
	}

	/**
	 * 检查队列是否为空
	 * @return 如果队列为空返回true，否则返回false
	 */
	public boolean isEmpty() {
		return this.size == 0;
	}

	/**
	 * 返回队列中元素的数量
	 * @return 队列中元素的数量
	 */
	public int size() {
		return this.size;
	}

	/**
	 * 打印队列中的所有元素
	 */
	public void printQueue() {
		Node current = this.head;
		while (current != null) {
			System.out.print(current.val + " ");
			current = current.next;
		}
		System.out.println();
	}

	/**
	 * 主方法，用于测试队列的功能
	 * 
	 * 测试内容包括：
	 * - 创建队列
	 * - 入队操作
	 * - 出队操作
	 * - 查看队首元素
	 * - 判断队列是否为空
	 * - 获取队列大小
	 * 
	 * @param args 命令行参数（未使用）
	 */
	public static void main(String[] args) {
		int testSize = 1000000;
		int maxVal = 1000000;
		int testTimes = 10;
		
		// 测试用例1：大量随机数据入队和出队对比
		System.out.println("测试用例1：大量随机数据测试对比");
		long customTotalTime = 0;
		long javaTotalTime = 0;
		
		for (int t = 0; t < testTimes; t++) {
			// 自定义队列测试
			LinkedListQueue customQueue = new LinkedListQueue();
			long startTime = System.currentTimeMillis();
			for (int i = 0; i < testSize; i++) {
				customQueue.offer((int)(Math.random() * maxVal));
			}
			while (!customQueue.isEmpty()) {
				customQueue.poll();
			}
			long endTime = System.currentTimeMillis();
			customTotalTime += (endTime - startTime);
			
			// Java自带队列测试
			Queue<Integer> javaQueue = new LinkedList<>();
			startTime = System.currentTimeMillis();
			for (int i = 0; i < testSize; i++) {
				javaQueue.offer((int)(Math.random() * maxVal));
			}
			while (!javaQueue.isEmpty()) {
				javaQueue.poll();
			}
			endTime = System.currentTimeMillis();
			javaTotalTime += (endTime - startTime);
		}
		
		System.out.println("自定义队列平均耗时: " + (customTotalTime / testTimes) + "ms");
		System.out.println("Java队列平均耗时: " + (javaTotalTime / testTimes) + "ms");
		
		// 测试用例2：交替入队出队对比
		System.out.println("\n测试用例2：交替入队出队测试对比");
		customTotalTime = 0;
		javaTotalTime = 0;
		
		for (int t = 0; t < testTimes; t++) {
			// 自定义队列测试
			LinkedListQueue customQueue = new LinkedListQueue();
			long startTime = System.currentTimeMillis();
			for (int i = 0; i < testSize; i++) {
				if (Math.random() < 0.5) {
					customQueue.offer((int)(Math.random() * maxVal));
				} else if (!customQueue.isEmpty()) {
					customQueue.poll();
				}
			}
			long endTime = System.currentTimeMillis();
			customTotalTime += (endTime - startTime);
			
			// Java自带队列测试
			Queue<Integer> javaQueue = new LinkedList<>();
			startTime = System.currentTimeMillis();
			for (int i = 0; i < testSize; i++) {
				if (Math.random() < 0.5) {
					javaQueue.offer((int)(Math.random() * maxVal));
				} else if (!javaQueue.isEmpty()) {
					javaQueue.poll();
				}
			}
			endTime = System.currentTimeMillis();
			javaTotalTime += (endTime - startTime);
		}
		
		System.out.println("自定义队列平均耗时: " + (customTotalTime / testTimes) + "ms");
		System.out.println("Java队列平均耗时: " + (javaTotalTime / testTimes) + "ms");
		
		// 测试用例3：正确性测试
		System.out.println("\n测试用例3：正确性测试");
		LinkedListQueue customQueue = new LinkedListQueue();
		Queue<Integer> javaQueue = new LinkedList<>();
		
		// 随机操作并比较结果
		for (int i = 0; i < 10000; i++) {
			if (Math.random() < 0.5) {
				int val = (int)(Math.random() * maxVal);
				customQueue.offer(val);
				javaQueue.offer(val);
			} else if (!customQueue.isEmpty() && !javaQueue.isEmpty()) {
				int customVal = customQueue.poll();
				int javaVal = javaQueue.poll();
				if (customVal != javaVal) {
					System.out.println("错误：出队元素不匹配");
					System.out.println("自定义队列: " + customVal);
					System.out.println("Java队列: " + javaVal);
					return;
				}
			}
		}
		
		// 比较最终状态
		while (!customQueue.isEmpty() && !javaQueue.isEmpty()) {
			if (customQueue.poll() != javaQueue.poll()) {
				System.out.println("错误：最终状态不匹配");
				return;
			}
		}
		
		if (customQueue.isEmpty() != javaQueue.isEmpty()) {
			System.out.println("错误：队列大小不匹配");
			return;
		}
		
		System.out.println("正确性测试通过");
	}
}