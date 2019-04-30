import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int x,k;
    static class Node{
        long data;
        int number;
        boolean flag = false;
        Node parent = null;
        ArrayList<Node> arrayList;

        Node(int number,long data) {
            this.number = number;
            this.data = data;
            this.arrayList = new ArrayList<>();
        }
    }

    static class GenTree{
        Node root ;

        GenTree(Node root) {
            this.root=root;
        }

        void postOrder(Node node){
            for(Node i : node.arrayList){ //Iterate over children
                postOrder(i);
            }

            if(node.parent != null){
                node.parent.data = node.parent.data + node.data; //Add child data to parent
                if(node.data < -x){
                    node.parent.data = node.parent.data - node.data; //Subtract child's data from parent
                    node.parent.flag=true; //Parent's child is removed
                }
            }

        }

        Node pt;
        void preOrder(Node node){
            if(node.parent != null){
                if(node.data < 0 && node.flag ){
                    pt=node.parent;
                    do{
                        pt.data = pt.data - node.data;
                        k++;
                        pt=pt.parent;
                    }while (pt != null);
                    return;
                }
            }else{
                if(node.data < 0 && node.flag ){
                    node.data=0;
                    k++;
                    return;
                }
            }

            for(Node i : node.arrayList){ //Iterate over children
                preOrder(i);
            }
        }
    }

    public static void main (String[] args) throws java.lang.Exception {

        Scanner sc = new Scanner(System.in);
        int t;
        t = sc.nextInt();

        while (t != 0) {
            int n,i;
            n = sc.nextInt(); //Input number of nodes
            x = sc.nextInt(); //Input X
            k=0;

            int[] dataArray = new int[n]; //Array stores value of nodes
            for(i=0;i<n;i++) dataArray[i] = sc.nextInt();

            int[][] edgesArray = new int[n-1][2]; //Array stores edges
            for(i=0;i<n-1;i++){
                edgesArray[i][0] = sc.nextInt();
                edgesArray[i][1] = sc.nextInt();
            }

            ArrayList<Node> nodeArrayList = new ArrayList<>(); //List stores nodes
            for(i=0;i<n;i++) nodeArrayList.add(new Node(i,dataArray[i]));


            for(i=0;i<n-1;i++){ //Link child and parent
                nodeArrayList.get(edgesArray[i][0] - 1).arrayList.add(nodeArrayList.get(edgesArray[i][1] - 1));
                nodeArrayList.get(edgesArray[i][1] - 1).parent=nodeArrayList.get(edgesArray[i][0] - 1);
            }

            GenTree genTree = new GenTree(nodeArrayList.get(0)); //Set root node

            genTree.postOrder(genTree.root); //Bottom-up check and remove nodes

            if(n==1){
                if(genTree.root.data < -x){
                    k++;
                    genTree.root.data=0;
                }
            }else{
                genTree.preOrder(genTree.root);
            }

            System.out.println(genTree.root.data - (x * k));

            t--;
        }
    }
}
