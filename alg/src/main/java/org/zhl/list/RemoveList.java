package org.zhl.list;

/**
 * @author zhanghanlin
 * @date 2023/5/24
 **/
public class RemoveList {

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode deleteNode(ListNode head, int val) {
        if (head == null)
            return head;
        head.next = deleteNode(head.next, val);
        return head.val == val ? head.next : head;
    }

    public ListNode reversalNode(ListNode head){

        if (head == null) return new ListNode(-1);

        final ListNode listNode = reversalNode(head.next);
        listNode.next= head;

        return listNode;
    }

    public ListNode reversalNode2(ListNode head){
        if (head == null){
            return head;
        }
        ListNode currentHead = head;
        final ListNode listNode = this.reversalNode(head);
        return currentHead.next;
    }

}
