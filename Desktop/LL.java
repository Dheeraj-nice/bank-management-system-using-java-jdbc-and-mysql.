class LL{
    Node head;
   
    
   
    class Node {
        int data;
        Node next;
        
        Node(int data){
            this.data=data;
            this.next=null;
            
        }
    }
    //Inserting a node at first
    public void addfirst(int data){
        Node newNode=new Node(data);
        if(head==null){
            head=newNode;
            return;
        }
        newNode.next=head;
        head=newNode;
    }
        
    //insert at last
    public void addlast(int data){
        Node newNode=new Node(data);
        if(head==null){
            head=newNode;
            return;
        }
        Node currentNode=head;
        while(currentNode.next!=null){
            currentNode=currentNode.next;
        }
        currentNode.next=newNode;
    }
     //print function 
     public void print(){
         if(head==null){
             System.out.println("list is empty");
             return;
         }
         Node currentNode=head;
         while(currentNode!=null){
             System.out.print(currentNode.data+"->");
             currentNode=currentNode.next;
         }
         System.out.println("Null");
     
         }
         public static void main(String[] args){
             LL list=new LL();
             list.addfirst(1);
             list.addfirst(2);
             list.addlast(3);
             list.print();
             
         }
     
        
    
    
}