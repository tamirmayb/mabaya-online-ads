package com.mabaya.ads;

//calc nodes
class CALC {

    static class Node {
        String data;
        Node next;
    };

    static Node push(Node head_ref, String new_data) {
        Node new_node = new Node();

        new_node.data = new_data;

        new_node.next = (head_ref);

        (head_ref) = new_node;
        return head_ref;
    }

    static double sumOfNodes(Node head) {
        // if head = null
        if (head == null)
            return -1;

        int sum = 0;

        Node current = head; // Initialize current
        boolean isAdd = true;
        boolean isMul = false;

        while (current != null) {
            if(current.data.equals("A")) {
                isAdd = true;
                isMul = false;
            } else if (current.data.equals("M")) {
                isAdd = false;
                isMul = true;
            } else {
                if(isAdd) {
                    sum += Integer.parseInt(current.data);
                } else if(isMul) {
                    sum *= Integer.parseInt(current.data);
                }
            }
            current = current.next;
        }

        return sum;
    }

    public static void main(String args[])
    {
        Node head = null;

        head=push(head, "7");
        head=push(head, "A");
        head=push(head, "8");
        head=push(head, "M");
        head=push(head, "3");

        System.out.println("Sum of nodes = " + sumOfNodes(head));
    }
}
