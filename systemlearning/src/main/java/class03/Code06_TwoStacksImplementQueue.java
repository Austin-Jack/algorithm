package class03;

import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;

public class Code06_TwoStacksImplementQueue {

	public static class TwoStacksQueue {
		public Stack<Integer> stackPush;
		public Stack<Integer> stackPop;

		public TwoStacksQueue() {
			stackPush = new Stack<Integer>();
			stackPop = new Stack<Integer>();
		}

		// push栈向pop栈倒入数据
		private void pushToPop() {
			if (stackPop.empty()) {
				while (!stackPush.empty()) {
					stackPop.push(stackPush.pop());
				}
			}
		}

		public void add(int pushInt) {
			stackPush.push(pushInt);
			pushToPop();
		}

		public int poll() {
			if (stackPop.empty() && stackPush.empty()) {
				throw new RuntimeException("Queue is empty!");
			}
			pushToPop();
			return stackPop.pop();
		}

		public int peek() {
			if (isEmpty()) {
				throw new RuntimeException("Queue is empty!");
			}
			pushToPop();
			return stackPop.peek();
		}

		public boolean isEmpty() {
			return stackPush.empty() && stackPop.empty();
		}
	}

	public static void main(String[] args) {
		// 简单测试
		TwoStacksQueue test = new TwoStacksQueue();
		test.add(1);
		test.add(2);
		test.add(3);
		System.out.println(test.peek());
		System.out.println(test.poll());
		System.out.println(test.peek());
		System.out.println(test.poll());
		System.out.println(test.peek());
		System.out.println(test.poll());

		// 对照测试
		System.out.println("开始对照测试...");
		int testTimes = 10000;
		int maxValue = 1000;
		TwoStacksQueue myQueue = new TwoStacksQueue();
		Queue<Integer> testQueue = new LinkedList<>();
		boolean isEqual = true;

		for (int i = 0; i < testTimes; i++) {
			if (myQueue.isEmpty()) {
				// 如果队列为空，随机选择添加操作
				int num = (int) (Math.random() * maxValue);
				myQueue.add(num);
				testQueue.add(num);
			} else {
				// 随机选择操作：0表示添加，1表示取出
				int choice = (int) (Math.random() * 2);
				if (choice == 0) {
					int num = (int) (Math.random() * maxValue);
					myQueue.add(num);
					testQueue.add(num);
				} else {
					if (myQueue.peek() != testQueue.peek()) {
						System.out.println("Oops! 队列头部不一致");
						System.out.println("我的队列头部: " + myQueue.peek());
						System.out.println("标准队列头部: " + testQueue.peek());
						isEqual = false;
						break;
					}
					myQueue.poll();
					testQueue.poll();
				}
			}
		}

		if (isEqual) {
			System.out.println("测试通过！");
		} else {
			System.out.println("测试失败！");
		}
	}

}
