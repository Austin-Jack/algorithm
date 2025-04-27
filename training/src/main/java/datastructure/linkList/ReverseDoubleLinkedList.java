package datastructure.linkList;
import java.util.List;
import java.util.ArrayList;

/**
 * @author luolinyuan
 * @since 2025/4/25
 */
public class ReverseDoubleLinkedList {
    public static void main(String[] args) {
        int maxVal = 300;
        int maxNodeSize = 1000;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            DoubleNode head = generateDoubleLinkedList(maxVal, maxNodeSize);
            DoubleNode l1 = copyDoubleLinkedList(head);
            DoubleNode l2 = copyDoubleLinkedList(head);
            if (!compareDoubleLinkedList(reverseDoubleLinkedList(l1), comparator(l2))) {
                System.out.println("oOps!... DoubleLinkedList is:");
                printDoubleLinkedList(head);
                System.out.println("reverseDoubleLinkedList is:");
                printDoubleLinkedList(l1);
                System.out.println(" comparator is:");
                printDoubleLinkedList(l2);
            }
        }
        System.out.println("Congratulations！！");
    }

    public static DoubleNode reverseDoubleLinkedList(DoubleNode head) {
        if (head == null) {
            return null;
        }
        DoubleNode pre = null;
        DoubleNode next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            head.prev = next;
            pre = head;
            head = next;
        }
        return pre;
    }

    public static DoubleNode copyDoubleLinkedList(DoubleNode head) {
        if (head == null) {
            return null;
        }
        DoubleNode newHead = new DoubleNode();
        newHead.val = head.val;
        DoubleNode current = newHead;
        DoubleNode prev = null;
        
        while (head.next != null) {
            current.prev = prev;
            current.next = new DoubleNode();
            current.next.val = head.next.val;
            prev = current;
            current = current.next;
            head = head.next;
        }
        current.prev = prev;
        return newHead;
    }

    public static void printDoubleLinkedList(DoubleNode head) {
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
        System.out.println();
    }

    public static DoubleNode comparator(DoubleNode head) {
        if (head == null) {
            return null;
        }
        List<DoubleNode> list = new ArrayList<>();
        while (head != null) {
            list.add(head);
            head = head.next;
        }
        for (int i = list.size() - 1; i >= 1; i--) {
            list.get(i).next = list.get(i - 1);
            list.get(i - 1).prev = list.get(i);
        }
        list.get(0).next = null;
        list.get(list.size() - 1).prev = null;
        return list.get(list.size() - 1);
    }

    public static boolean compareDoubleLinkedList(DoubleNode head1, DoubleNode head2) {
        while (head1 != null && head2 != null) {
            if (head1.val != head2.val) {
                return false;
            }
            if (head1.next != null && head2.next != null) {
                if (head1.next.val != head2.next.val) {
                    return false;
                }
            } else if (head1.next != head2.next) {
                return false;
            }
            head1 = head1.next;
            head2 = head2.next;
        }
        return head1 == null && head2 == null;
    }

    public static DoubleNode generateDoubleLinkedList(int maxVal, int maxNodeSize) {
        int size = (int) (Math.random() * (maxNodeSize + 1));
        if (size == 0) {
            return null;
        }
        
        DoubleNode head = new DoubleNode();
        head.val = (int) (Math.random() * (maxVal + 1));
        head.prev = null;
        
        DoubleNode current = head;
        for (int i = 1; i < size; i++) {
            DoubleNode next = new DoubleNode();
            next.val = (int) (Math.random() * (maxVal + 1));
            next.prev = current;
            current.next = next;
            current = next;
        }
        current.next = null;
        return head;
    }

    private static class DoubleNode {
        public int val;
        public DoubleNode prev;
        public DoubleNode next;
    }
} 