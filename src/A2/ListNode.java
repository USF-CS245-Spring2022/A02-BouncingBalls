// @author: Mia Kobayashi
// @date and version: 24 Mar 2022, v.4
// CS245 Lab 6: ListNode

package lab06;

import java.util.List;
import java.util.Objects;

public class ListNode<T> {

    private static class Node<T> {
        T data; //data carried by this node
        Node<T> next; //ref to next node in list, null if none

        //constructor
        public Node(T val) {
            data = val;
            next = null;
        }
    }



    private int size; //initially 0
    private Node<T> head; //initially null

//    public void ListNode() { //default constructor
//        head = null;
//        size = 0;
//    }

    private int size() {
        return size;
    }

    /**
     * Appends the specified element to the end of this list (optional
     * operation).
     *
     * <p>Lists that support this operation may place limitations on what
     * elements may be added to this list.  In particular, some
     * lists will refuse to add null elements, and others will impose
     * restrictions on the type of elements that may be added.  List
     * classes should clearly specify in their documentation any restrictions
     * on what elements may be added.
     *
     * @param item element to be appended to this list
     * @return {@code true}
     * @throws ClassCastException if the class of the specified element
     *                            prevents it from being added to this list
     * @throws NullPointerException if the specified element is null and this
     *                              list does not permit null elements
     * @throws IllegalArgumentException if some property of this element
     *                                  prevents it from being added to this list
     */
    public boolean add(Object item) throws ClassCastException, NullPointerException, IllegalArgumentException {
        if (head != null) {
//            System.out.println("head data: " + head.data);
//            System.out.println(item.getClass());
//            System.out.println(head.data.getClass());
            if (!Objects.equals(item.getClass(), head.data.getClass())) {
                throw new ClassCastException();
            }
        }

        if (item == null) {
            throw new NullPointerException();
        }
        if (Objects.equals("", item)) {
            if (!Objects.equals(head, null) && head.data instanceof String) {
                throw new IllegalArgumentException();
            }
        }

        Node newNode = new Node(item);
        newNode.next = null;

        if (head == null) { //if list is empty, make newNode as head
            head = newNode;
        }
        else { //traverse until last node
            Node curr = head;
            while (curr.next != null) {
                if (curr.next == null) {
                    throw new NullPointerException();
                }
                curr = curr.next;
            }
            curr.next = newNode;
        }
        size++;
        return true;
    } //end add(item)


    /**
     * Inserts the specified element at the specified position in this list
     * (optional operation).  Shifts the element currently at that position
     * (if any) and any subsequent elements to the right (adds one to their
     * indices).
     *
     * @param position index at which the specified element is to be inserted
     * @param item element to be inserted
     * @throws ClassCastException if the class of the specified element
     *                            prevents it from being added to this list
     * @throws NullPointerException if the specified element is null and
     *                              this list does not permit null elements
     * @throws IllegalArgumentException if some property of the specified
     *                                  element prevents it from being added to this list
     * @throws IndexOutOfBoundsException if the index is out of range
     *                                   ({@code index < 0 || index > size()})
     */
    public void add(int position, Object item) throws ClassCastException, NullPointerException, IllegalArgumentException, IndexOutOfBoundsException {
        if (head != null) {
//            System.out.println("head data: " + head.data);
//            System.out.println(item.getClass());
//            System.out.println(head.data.getClass());
            if (!Objects.equals(item.getClass(), head.data.getClass())) {
                throw new ClassCastException();
            }
        }

        if (item == null) {
            throw new NullPointerException();
        }
        if (Objects.equals("", item)) {
            if (!Objects.equals(head, null) && head.data instanceof String) {
                throw new IllegalArgumentException();
            }
        }

        if (!(position >= 0 && position < size())) { // 0 <= position < size()
//            System.out.println("illegal pos");
//            throw new IllegalArgumentException(); //testcase wants this
            throw new IndexOutOfBoundsException(); //but this is more right???
        }

        Node newNode = new Node(item);

        //if adding to pos 0
        if (position == 0) {
            newNode.next = head;
            head = newNode;
            size++;
            return;
        }

        //adding to any pos past 0
        Node prev = head;
        for (int i = 1; i < position; i++) {
            prev = prev.next;
        }
        newNode.next = prev.next;
        prev.next = newNode;
        size++;
    } //end add(position, item)


    /**
     * Returns {@code true} if this list contains the specified element.
     * More formally, returns {@code true} if and only if this list contains
     * at least one element {@code e} such that
     * {@code Objects.equals(o, e)}.
     *
     * @param item element whose presence in this list is to be tested
     * @return {@code true} if this list contains the specified element
     * @throws ClassCastException if the type of the specified element
     *                            is incompatible with this list
     * @throws NullPointerException if the specified element is null and this
     *                              list does not permit null elements
     */
    public boolean contains(Object item) throws ClassCastException, NullPointerException {
        if (head != null) {
//            System.out.println("head data: " + head.data);
//            System.out.println(item.getClass());
//            System.out.println(head.data.getClass());
            if (!Objects.equals(item.getClass(), head.data.getClass())) {
                throw new ClassCastException();
            }
        }

        if (item == null) {
            throw new NullPointerException();
        }

        Node node = head;

        for (int i = 0; i < size(); i++) {
            if (Objects.equals(node.data, item)) {
                return true;
            }
            node = node.next;
        }

        return false;
    } //end contains()


    /**
     * Returns the element at the specified position in this list.
     *
     * @param position index of the element to return
     * @return the element at the specified position in this list
     * @throws IndexOutOfBoundsException if the index is out of range
     *                                   ({@code index < 0 || index >= size()})
     */
    public Object get(int position) throws IndexOutOfBoundsException {
        Node curr = head;
        if (!(position >= 0 && position < size())) { //if position is not [0, size()), throw exception
            throw new IndexOutOfBoundsException();
        }

        for (int i = 0; i < position; i++) { //traverses until it gets to position where node is at
            curr = curr.next;
        }
        return curr.data;
    } //end get()


    /**
     * Removes the last element in this list. Returns the element that was removed from the
     * list.
     *
     * @return the element previously at the specified position
     * @throws IndexOutOfBoundsException if the index is out of range
     *                                   ({@code index < 0 || index >= size()})
     */
    public Object removeLast() throws IndexOutOfBoundsException {
        //if empty list, index out of bounds
        if (head == null) {
            throw new IndexOutOfBoundsException();
        }

        Node curr = head;
        while (curr.next != null) {
            curr = curr.next;
        }
        size--;
        return curr.data;
    } //end removeLast()


    /**
     * Removes the first occurrence of the specified element from this list,
     * if it is present (optional operation).  If this list does not contain
     * the element, it is unchanged.  More formally, removes the element with
     * the lowest index {@code i} such that
     * {@code Objects.equals(o, get(i))}
     * (if such an element exists).  Returns {@code true} if this list
     * contained the specified element (or equivalently, if this list changed
     * as a result of the call).
     *
     * @param item element to be removed from this list, if present
     * @return {@code true} if this list contained the specified element
     * @throws ClassCastException if the type of the specified element
     *                            is incompatible with this list
     * @throws NullPointerException if the specified element is null and this
     *                              list does not permit null elements
     */
    public boolean remove(Object item) throws ClassCastException, NullPointerException {
        if (head != null) {
//            System.out.println("head data: " + head.data);
//            System.out.println(item.getClass());
//            System.out.println(head.data.getClass());
            if (!Objects.equals(item.getClass(), head.data.getClass())) {
                throw new ClassCastException();
            }
        }

        if (item == null) {
            throw new NullPointerException();
        }

        Node curr = head;

        if (head.data == null) {
            throw new NullPointerException();
        }
        if (Objects.equals(head.data, item)) { //if removing first item
            head = curr.next;
            size--;
            return true;
        }

        Node prev = null;
        for (int i = 0; i < size(); i++) {
            if (Objects.equals(curr.data, item)) { //found item
//                System.out.println("found item");
                prev.next = curr.next;
                size--;
                return true;
            }
            prev = curr;
            curr = curr.next;
        }
        return false; //could not remove / item not found
    } //end remove()


    /**
     * Removes the element at the specified position from the end of the list (optional
     * operation).  Shifts any subsequent elements to the left (subtracts one
     * from their indices).  Returns the head of the list.
     *
     * @param position the index of the item the end of the list to be removed
     * @return the head of the list
     * @throws IndexOutOfBoundsException if the index is out of range
     *                                   ({@code index < 0 || index >= size()})
     */
    public Object removeFromEnd(int position){
        if (!(position >= 0 && position < size())) {
            throw new IndexOutOfBoundsException();
        }

        if (position == 0) { //removing "last" pos item, but nonexistent
            return null;
        }

        int pos = size - position; //fixme +1? if size is actual num of elements ...

        Node curr = head;
        Node prev = null;

        for (int i = 0; i < pos; i++) { //removing item from any pos past 0
            prev = curr;
            curr = curr.next;
        }
        prev.next = curr.next;
        size--;
        System.out.println(curr.data);
        return curr.data;
    } //end removeFromEnd()


    /**
     * Shows ListNode as a String, with each object in parentheses separated by “arrows” (->).
     * A ListNode of {1, 2, 3} should return the String "(1) -> (2) -> (3)".
     * @return String representation of the ListNode
     */
    @Override
    public String toString() {
        Node curr = head;

        String listString = "";

        for (int i = 0; i < size(); i++) {
            if (i == size() - 1) {
                listString += "(" + curr.data + ")";
            }
            else {
                listString += "(" + curr.data + ") -> ";
            }
            curr = curr.next;
        }
        return listString;
    } //end toString()


    public static void main(String[] args) {
        System.out.println("Hello gradle!--------------");
    }
}
