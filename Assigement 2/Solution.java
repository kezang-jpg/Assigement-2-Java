import java.util.*;

public class Solution<Key extends Comparable<Key>, Value> 
{
    private Node root;             // root of BST

    private class Node 
    {
        private Key key;           // sorted by key
        private Value val;         // associated data
        private Node left, right;  // left and right subtrees
        private int size;          // number of nodes in subtree

        public Node(Key key, Value val, int size) 
        {
            this.key = key;
            this.val = val;
            this.size = size;
        }
    }

    /**
     * Initializes an empty symbol table.
     */
    public Solution() 
    {
        root=null;
    }

    /**
     * Returns true if this symbol table is empty.
     * @return {@code true} if this symbol table is empty; {@code false} otherwise
     */
    public boolean isEmpty() 
    {
        if(root==null)
        {
            return true;
        }
        return false;   
    }

    /**
     * Returns the number of key-value pairs in this symbol table.
     * @return the number of key-value pairs in this symbol table
     */
    public int size() 
    {
        if (root == null) 
            return 0; 
          
        // Using level order Traversal. 
        Queue<Node> k = new LinkedList<Node>(); 
        k.offer(root); 
          
        int count = 1;  
        while (!k.isEmpty()) 
        { 
            Node tmp = k.poll(); 
            // when the queue is empty: 
            // the poll() method returns null. 
            if (tmp != null) 
            { 
                if (tmp.left != null) 
                {  
                    count++;   
                    k.offer(tmp.left); 
                } 
                if (tmp.right != null) 
                {  
                    count++;  
                    k.offer(tmp.right); 
                } 
            } 
        } 
        return count; 
       
    }

    // return number of key-value pairs in BST rooted at x
    // private int size(Node x) {
        
       
    // }

    /**
     * Does this symbol table contain the given key?
     *
     * @param  key the key
     * @return {@code true} if this symbol table contains {@code key} and
     *         {@code false} otherwise
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    // public boolean contains(Key key) {
       
    // }

    /**
     * Returns the value associated with the given key.
     *
     * @param  key the key
     * @return the value associated with the given key if the key is in the symbol table
     *         and {@code null} if the key is not in the symbol table
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public Value get(Key key) 
    {
        if(key == null)
        {
            throw new IllegalArgumentException("argument to get value() is null");
        }
        Node focusNode = root;
        while(focusNode.key != key)
        {
            int cmp = key.compareTo(focusNode.key);
            if(cmp < 0)
            {
                focusNode = focusNode.left;
            }
            else if(cmp > 0)
            {
                focusNode = focusNode.right;
            }
        }
        if(focusNode.key == key)
        {
            System.out.println(focusNode.val);
        }
        return focusNode.val;
        
    }

    // private Value get(Node x, Key key) {
       
        
        
    // }

    /**
     * Inserts the specified key-value pair into the symbol table, overwriting the old 
     * value with the new value if the symbol table already contains the specified key.
     * Deletes the specified key (and its associated value) from this symbol table
     * if the specified value is {@code null}.
     *
     * @param  key the key
     * @param  val the value
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public void put(Key key, Value val) 
    {
        if (key == null) 
        {
            throw new IllegalArgumentException("Key is Null");    
        }
        else 
        {
            Node newest = new Node(key,val,1);
            if(root == null)
            {
                root = newest;
            }
            else
            {
                Node x = root;
                Node parent;
                while( x!=null )
                {
                    parent = x;
                    int temp = key.compareTo(x.key);
                    if(temp < 0)
                    {
                        x = x.left;
                        if(x == null)
                        {
                            parent.left = newest;
                            root.size++;
                            return;
                        }
                        else if(x.key == key)
                        {
                            x.val = val;
                            return;
                        }
                    }
                    else if(temp > 0)
                    {
                        x = x.right;
                        if(x == null)
                        {
                            parent.right = newest;
                            root.size++;
                            return;
                        }
                        else if(x.key == key)
                        {
                            x.val = val;
                            return;
                        }
                    }
                }
            }
        }
       
    }

    // private Node put(Node x, Key key, Value val) {
        
    // }

    /**
     * Returns the smallest key in the symbol table.
     *
     * @return the smallest key in the symbol table
     * @throws NoSuchElementException if the symbol table is empty
     */
     public Key min() 
     {
        if (isEmpty())throw new NoSuchElementException("Symbol table is empty");
        return (min(root).key);                     
      } 
    private Node min(Node x) 
    { 
        Node temp = x;

        while(temp.left !=null)
        {
            temp = temp.left;
        }
        return temp;     
    } 
   

    /**
     * Returns the largest key in the symbol table less than or equal to {@code key}.
     *
     * @param  key the key
     * @return the largest key in the symbol table less than or equal to {@code key}
     * @throws NoSuchElementException if there is no such key
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public Key floor(Key key) 
    {
        if (key == null) throw new IllegalArgumentException("No particular key that was being searched. ");
        if (isEmpty()) throw new NoSuchElementException("Empty symboltable");
            Node x = floor(root,key);
            if(x == null)
                return null;
            else 
                return x.key;      
    } 
    private Node floor(Node x, Key key) 
    {
        Node small = null;
        while (x != null)
        {
            int compare = key.compareTo(x.key);
            if(compare == 0) return x;
            if(compare > 0)
            {
                small = x;
                x = x.right;
            }
            else
            {
                x = x.left;
            }
        }
        return small;
    } 

    
    

    /**
     * Return the key in the symbol table whose rank is {@code k}.
     * This is the (k+1)st smallest key in the symbol table.
     *
     * @param  k the order statistic
     * @return the key in the symbol table of rank {@code k}
     * @throws IllegalArgumentException unless {@code k} is between 0 and
     *        <em>n</em>–1
     */
     public Key select(Key key){
        if(isEmpty()){
            throw new NoSuchElementException("calls floor() with empty symbol table");
        }
        Node curNode = root;
        Node parent = null;
        while(curNode != null)
        {
            parent = curNode;
            int cmp = key.compareTo(parent.key);
            if(key == null){
                throw new IllegalArgumentException("calls floor() with a null key");
            }
            if(cmp > 0)
            {
                curNode = parent.right;
            }
            //This condition is for checking floor of given key in left side
            else if(cmp < 0)
            {
                curNode = parent.left;
                //This condition checks if key is greater than left parent then it returns the curNode 
                int cmto = key.compareTo(parent.key);
                if(cmto > 0 )
                {
                    return curNode.key;
                }
                //This is for if the key is still less then left parent
                else
                {
                    curNode = parent.left;
                } 
            }
        }
        return parent.key;
    }

    

    /**
     * Returns all keys in the symbol table in the given range,
     * as an {@code Iterable}.
     *
     * @param  lo minimum endpoint
     * @param  hi maximum endpoint
     * @return all keys in the symbol table between {@code lo} 
     *         (inclusive) and {@code hi} (inclusive)
     * @throws IllegalArgumentException if either {@code lo} or {@code hi}
     *         is {@code null}
     */
    public Iterable<Key> keys(Key lo, Key hi) 
    {
        Queue<Key> queue = new LinkedList<Key>();
        keys(root, queue, lo, hi);
        return queue;
    } 

    private void keys(Node x,Queue<Key> queue, Key lo, Key hi) 
    { 
        if (x == null)  
        return;  
    
        Node cur = x;
        int cm = lo.compareTo(cur.key);
        int cmp = hi.compareTo(cur.key);
          
        while (cur != null) {  
            if (cur.left == null)  
            {   
                if (cm <= 0 && cmp >= 0)  queue.offer(cur.key);    
                cur = cur.right;  
            }  
            else {  
                Node pre = cur.left;    
                while (pre.right != null && pre.right != cur)  
                    pre = pre.right;  
                if (pre.right == null)  
                {  
                    pre.right = cur;  
                    cur = cur.left;  
                }  
                else 
                {  
                    pre.right = null;    
                    if (cm <= 0 && cmp >= 0)  queue.offer(cur.key);   
                    cur = cur.right;  
                }  
            }
        }
    }

    // private void keys(Node x,Queue<Key> queue, Key lo, Key hi) { 
        
    // } 

   
    /* Run the program by giving the approriate command obtained from
    input files through input.txt files. The output should be displayed
    exactly like the file output.txt shows it to be.*/
  
    public static void main(String[] args) 
    { 
        Solution <String, Integer> obj = new Solution <String, Integer>();
        obj.put("ABDUL",1);
        obj.get("ABDUL");
        obj.put("HRITHIK",2);
        obj.put("SAI",3);
        obj.put("SAMAL",6);
        obj.get("SAI");
        obj.put("TASHI",4);
        System.out.println(obj.size());
        System.out.println(obj.min());
        System.out.println(obj.floor("HRITHIK"));
        System.out.println(obj.floor("HAHA"));
        for (String s : obj.keys("ABDUL","TASHI"))
        System.out.print(s + " ");
        System.out.println();
        System.out.println();
        obj.put("CHIMI",5);
        obj.put("SAMAL",4);
        obj.get("SAMAL");
        obj.put("NIMA",7);
        System.out.println(obj.size());
        obj.get("CHIMI");
        System.out.println(obj.floor("CHIMA"));
        for (String s : obj.keys("ABDUL","TASHI"))
        System.out.print(s + " ");
        System.out.println();
    }
}