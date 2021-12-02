
public class SingleLinkedList<E> {
    private Node<E> head;
    private int size;

    /** private inner class */
    private static class Node<E>
    {
        private E data;
        private Node<E> next;
        /** Creates a new node with a null next field
         @param dataItem  The data stored
         */
        private Node(E dataItem)
        {
            data = dataItem;
            next = null;
        }
        /** Creates a new node that references another node
         @param dataItem  The data stored
         @param nodeRef  The node referenced by new node
         */
        private Node(E dataItem, Node<E> nodeRef)
        {
            data = dataItem;
            next = nodeRef;
        }
    } //end class Node


    public SingleLinkedList( )
    {
        // initially empty list
        head = null;
        size = 0;
    }

    public int size()
    {
        return size;
    }

    private void addFirst(E item)
    {
        Node<E> temp = new Node<E>(item, head);
        head = temp;
        size++;
    }

    private void addAfter(Node<E> node, E item)
    {
        Node<E> temp = new Node<E>(item, node.next);
        node.next = temp;
        size++;
    }

    private E removeAfter(Node<E> node)
    {
        Node<E> temp = node.next;
        if (temp != null)
        {
            node.next = temp.next;
            size--;
            return temp.data;
        }
        else
            return null;
    }

    private E removeFirst()
    {
        Node<E> node = head;
        if (head != null)
        {
            head = head.next;
            size--;
            return node.data;
        }
        else
            return null;
    }
    private Node<E> getNode(int index)
    {
        Node<E> node = head;
        for (int i = 0; i < index && node != null; i++) {
            node = node.next;
        }
        return node;
    }

    public void printContent(int index)
    {
        Node<E> node = getNode(index);
        if (node != null)
            System.out.println(node.data);
    }

    // Return the object at the specified index
    public E get(int index)
    {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(Integer.toString(index));
        }
        Node<E> node = getNode(index);
        return node.data;
    }

    // Replace the element at the specified index by the
    // new value anEntry and return the old value
    public E set (int index, E anEntry)
    {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(Integer.toString(index));
        }
        Node<E> node = getNode(index); // calling private method
        E result = node.data;
        node.data = anEntry;
        return result;
    }

    // Method to insert a new item at the specified index in
    // the list
    public void add (int index, E item)
    {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException(Integer.toString(index));
        }
        if (index == 0)
            addFirst(item);
        else {
            Node<E> node = getNode(index - 1);
            addAfter(node, item);
        }
    }

    // Method to insert a new item (node) at the end of the list
    public boolean add (E item)
    {
        add(size, item); // calling public method add
        return true;
    }

    // Remove the node at the given index and return its data
    public E remove (int index)
    {
        if (index < 0 || index >= size) {
            throw new
                    IndexOutOfBoundsException(Integer.toString(index));
        }
        E  item;
        if (index == 0)
            item = removeFirst( );
        else {
            Node<E> node = getNode(index - 1);
            item = removeAfter(node);
        }
        return item; // return the deleted value
    }

    // Method to search an item in the list. If found, return its
    // location, else return -1. It is assumed that the class of item
    // implements equals method. The method finds the first
    // occurrence of item in the list.
    public int indexOf(E item)
    {
        int index = 0;
        Node<E> node = head;
        while (node != null)
        {
            if ( item.equals( node.data) )
                return index;
            else {
                node = node.next;
                index++;
            }
        }
        return -1; // item not found
    }

    // Method to remove the first occurrence of the item from
    // the list, if present and return true, else return false
    public boolean remove (E item)
    {
        int index = indexOf(item);
        if (index != -1) {
            remove(index);  // remove node at the index
            return true;        // item found
        }
        else
            return false;  // item not found
    }

    // Method to check whether an object is in the list. If found,
    // return true, else return false.
    public boolean contains(E item)
    {
        int index = indexOf(item);
        if (index != -1)
            return true;    // item found
        else
            return false;  // item not found
    }

    // Method to delete all nodes from the list and make it empty
    public void clear( )
    {
        while (head != null ){
            head.data = null;
            head = head.next;
        }
        size = 0;
    }

    public String toString( )
    {
        String str = "";
        Node<E> nodeRef = head;
        for(int i = 0; i < size; i++) {
            str = str + nodeRef.data + " ";
            nodeRef = nodeRef.next;
        }
        return str;
    }

    public boolean shiftRightRotate()
    {
        if (size <=1)
            return false;
        Node<E> node, prev;
        node = head.next;
        prev = head;
        while (node.next != null)
        {
            node = node.next;
            prev = prev.next;
        }
        E myData = node.data;
        Node<E> temp = new Node<E>(myData,head);
        head = temp;
        prev.next = null;
        return true;
    }

    public E setExampleFromTest(int index, E item) {
        if (size == 0 || index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node<E> node = head;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        E myData = node.data;
        node.data = item;
        return myData;
    }

    public int countOdd()
    {
        Node<E> node = head;
        int count = 0;
        while(node != null)
        {
            if ((int)node.data%2 == 1)
                count++;
            node = node.next;
        }
        return count;
    }

    public int countOddFor()
    {
        int count = 0;
        for(Node<E> node = head; node != null;node = node.next)
            if ((int)node.data%2 == 1)
                count++;
        return count;
    }

    //method to be accepts one parameter of type Integer.
    // The method prints all elements greater than item and returns their count.
    public int printGreater (int item)
    {
        Node <E> node = head;
        int count = 0;
        while (node != null)
        {
            if( (Integer) node.data > item)
            {
                count++;
                System.out.println(node.data);
            }
            node = node.next;
        }
        return  count;
    }

    //The method returns true if the first half of the list contains the same
    // elements of the second half in the same order.
    // If the list does not have even number of elements, so halfs are not equal,
    // the method returns false.
    public boolean compareHalfs ()
    {
        Node <E> node1, node2;
        node1 = head;
        node2 = getNode(size/2);
        while (node2 != null)
        {
            if (node1.data.equals(node2.data))
            {
                node1 = node1.next;
                node2 = node2.next;
            }
            else return false;
        }
        return true;
    }

    //The method removes all the occurrences of item in the list.
    public void removeAll(E item)
    {
        if (size == 0)return;
        Node <E> node = head;
        for (int i=0; i < size; i++)
        {
            if((node.data).equals(item))
            {
                remove(i);
                i--;
            }
            node = node.next;
        }
    }
    
}