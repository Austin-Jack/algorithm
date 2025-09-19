package datastructure.linkList;
import java.util.List;
import java.util.ArrayList;

/**
 * @author luolinyuan
 * @since 2025/4/25
 *
 */
public class ReverseLinkedList {
	public static void main(String[] args) {
		int maxVal = 300;
		int maxNodeSize = 1000;
		int testTimes = 1000000;
		for (int i = 0;i < testTimes ; i++) {
			Node head = generateLinkedList(maxVal, maxNodeSize);
			Node l1 = copyLinkedList(head);
			Node l2 = copyLinkedList(head);
			if (!compareLinkedList(reverseLinkedList(l1), comparator(l2))) {
				System.out.println("oOps!... LinkedList is:");
				printLinkedList(head);
				System.out.println("reverseLinkedList is:");
				printLinkedList(head);
				System.out.println(" comparator is:");
				printLinkedList(head);
			}
		}
		System.out.println("Congratulations！！");
	}
	
	public static Node reverseLinkedList(Node head) {
		Node pre = null, next = null;
		while (head != null) {
			next = head.next;
			head.next = pre;
			pre = head;
			head = next;
		}
		return pre;
	}
	
	public static Node copyLinkedList(Node head) {
		Node node = null;
		while (head != null) {
			node = new Node();
			node.val = head.val;
			node.next = head.next;
			head = head.next;
		}
		return node;
	}
	
	public static void printLinkedList(Node head) {
		while (head != null) {
			System.out.print(head.val + " ");
			head = head.next;
		}
	}
	
	public static Node comparator(Node head) {
		if (head == null) {
			return null;
		}
		List<Node> list = new ArrayList<>();
		while (head != null) {
			list.add(head);
			head = head.next;
		}
		for (int i = list.size() - 1; i >= 1; i--) {
			list.get(i).next = list.get(i - 1);
		}
		list.get(0).next = null;
		return list.get(list.size() - 1);
	}
	
	public static boolean compareLinkedList(Node head1, Node head2) {
		//不为空则比较值
		while (head1 != null && head2 != null) {
			if (head1.val != head2.val) {
				return false;
			}
			head1 = head1.next;
			head2 = head2.next;
		}
		//必须同时为空 要么到了最尾部 要么本身都没节点
		return head1 == null && head2 == null;
	}
	
	
	public static Node generateLinkedList(int maxVal, int maxNodeSize) {
		int size = (int) (Math.random() * (maxNodeSize + 1));
		int i = 0;
		Node next = null;
		do {
			Node node = new Node();
			node.val = (int) (Math.random() * (maxVal + 1));
			node.next = next;
			next = node;
		} while (i++ < size);
		return next;
	}
	
	
	private static class Node {
		public int val;
		public Node next;
	}
}
