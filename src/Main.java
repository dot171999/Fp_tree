import java.util.*;
import java.lang.*;

public class Main {

    static class Node{
        char data;
        ArrayList<Node> arrayList;
        HashMap<Character, Integer> map;
        HashMap<Character, Integer> pos;
        int count;

        Node(char data,int count) {
            this.data = data;
            this.arrayList = new ArrayList<>();
            this.map = new HashMap<>();
            this.pos = new HashMap<>();
            this.count = count;
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

            System.out.println("Item = "+node.data+"  Count = "+node.count);
        }
    }

    public static void main (String[] args){

        Scanner sc = new Scanner(System.in);
        int n;

        n = sc.nextInt();

        ArrayList<StringBuilder> itemArrayList = new ArrayList<>();

        int i,j,k;
        int[] itemCount =new int[10];

        for(i=0;i<n;i++){
            itemArrayList.add(new StringBuilder(sc.next()));
        }

        System.out.println("\nSize of List = " +itemArrayList.size()+"\n");

        for(i=0;i<n;i++){
            System.out.println("Item "+(i+1)+" = "+itemArrayList.get(i));
        }

        for(i=0;i<n;i++){
            for(j=0;j<itemArrayList.get(i).length();j++){
                itemCount[Character.getNumericValue(itemArrayList.get(i).charAt(j))]++;
            }
        }

        System.out.println();

        for(i=0;i<10;i++){
            System.out.println("Count of "+i+" = "+itemCount[i]);
        }

        System.out.println();

        for(k=0;k<n;k++){
            for (i = 0; i < itemArrayList.get(k).length()-1; i++){
                for (j = 0; j < itemArrayList.get(k).length()-i-1; j++){
                    if (itemCount[Character.getNumericValue(itemArrayList.get(k).charAt(j))] < itemCount[Character.getNumericValue(itemArrayList.get(k).charAt(j+1))])
                    {
                        char temp = itemArrayList.get(k).charAt(j);
                        itemArrayList.get(k).setCharAt(j, itemArrayList.get(k).charAt(j+1));
                        itemArrayList.get(k).setCharAt(j+1, temp);
                    }
                }
            }
        }

        for(i=0;i<n;i++){
            System.out.println("New Item "+(i+1)+" = "+itemArrayList.get(i));
        }

        System.out.println();

        GenTree genTree=new GenTree(new Node('0',1));

        Node pt;
        for(i=0;i<n;i++){
            pt=genTree.root;
            for(j=0;j<itemArrayList.get(i).length();j++){
                if(pt.map.containsKey(itemArrayList.get(i).charAt(j))){
                    pt=pt.arrayList.get(pt.pos.get(itemArrayList.get(i).charAt(j)));
                    pt.count=pt.count+1;
                    System.out.println("Already Present  "+pt.data+"  Count = "+pt.count);
                }else{
                    pt.arrayList.add(new Node(itemArrayList.get(i).charAt(j),1));
                    pt.pos.put(itemArrayList.get(i).charAt(j),pt.arrayList.size()-1);
                    pt.map.put(itemArrayList.get(i).charAt(j),1);
                    pt=pt.arrayList.get(pt.arrayList.size()-1);
                    System.out.println("Adding new node  "+pt.data+"  Count = "+pt.count);
                }
            }
        }

        System.out.println("\n## Post Order Tree Traversal ##\n");

        genTree.postOrder(genTree.root);
    }
}

