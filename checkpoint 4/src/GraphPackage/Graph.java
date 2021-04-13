package GraphPackage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.Arrays;

/**
 *
 * @author nullExceptionTeam CPCS324 Project (2019-2020)
 * Member 1: Turki Alzubaidi
 * Member 2: Ammar Joharji 
 * Member 3: Mohammad alghafli
 *
 */
public class Graph  {

    private final int MAX_VERTS = 20;
    private Vertex vertexlist[] = new Vertex[MAX_VERTS];
    private int adjMat[][] = new int[MAX_VERTS][MAX_VERTS];
    private int nVerts;
    private Heap heap;
    private prioritySet [] edgeSet;
    private int setIndex;


  

    /**
     * This constructor initialize a new object of a graph with number of
     * vertices.
     *
     * @param numberOfVerteces How many Vertices in the graph.
     * @param  num_edge lol.
     */
    public Graph(int numberOfVerteces, int num_edge) {
        // This constructor also initiate all vertices!
        char c = 'a'; // this character is the first character
        for (int i = 0; i < numberOfVerteces; i++) {
            vertexlist[i] = new Vertex(c, i);
            c = (char) (c + 1); // to find the next ascii character
            nVerts++;
        }
        edgeSet = new prioritySet [num_edge];
    
    }

    /**
     * This method is used when reading graph info. from a file. We gather info.
     * about edges by these three params.
     *
     * @param v1 source vertex.
     * @param v2 destination vertex.
     * @param weight the weight(/cost) of the edge.
     */
    public void addEdgeFromFile(int v1, int v2, int weight) {
        adjMat[v1][v2] = weight;
        adjMat[v2][v1] = weight;
 
        edgeSet[setIndex] = new prioritySet(v1, v2, weight);
        setIndex++;

    }


    /**
     * This method checks if a vertex exists in vertex array or not.
     *
     * @param v_lab label of vertex to search by it.
     * @return index of the vertex, -1 if vertex not found.
     */
    public int vertexExist(char v_lab) {

        int i = 0;
        while (i < nVerts) {
            if (vertexlist[i].label == v_lab) {
                return i;
            }

            i++;
        }
        return -1;

    }


    private void sortVertices() {// Sort the array
        // Convert the array to array list
        ArrayList<Vertex> list = new ArrayList<>();
        for (int i = 0; i < nVerts; i++) {// Add elements only not null elements...
            list.add(vertexlist[i]);
        }
        Collections.sort(list); // Sort
        vertexlist = list.toArray(new Vertex[MAX_VERTS]); // Convert it back to normal array with same size
        for (int i = 0; i < nVerts; i++) { // Reset the indeces also
            vertexlist[i].setIndex(i);
        }
    }




    public void printHeap() {
        this.heap.print();
    }



    public int getMAX_VERTS() {
        return MAX_VERTS;
    }

    public Vertex[] getVertexlist() {
        return vertexlist;
    }

    public int[][] getAdjMat() {
        return adjMat;
    }

    public int getnVerts() {
        return nVerts;
    }

    public void displayVertex(int v) {

    }





    public void setAdjMat(int[][] adjMat) {
        this.adjMat = adjMat;
    }

    public void kurskal(){

        int [] vertices = new int [nVerts];
        for (int i = 0; i < vertices.length; i++){
            vertices[i] = i ;
        };
        
        Arrays.sort(edgeSet); // Sorting it from biggest to lowest, performing greedy approach.
        
        int i = 0;
        while(i < edgeSet.length) {
            
            int v1 = edgeSet[i].getV1();
            int v2 = edgeSet[i].getV2();

            if(vertices[v1] != vertices[v2]) {

                int max_set = maxSet(vertices[v1], vertices[v2], i, v1, v2, vertices);

                if(max_set == 0 || max_set > 0) vertices[v2] = vertices[v1];
                else vertices[v1] = vertices [v2];
                edgeSet[i].setKeep(true);
            } 
            
            i++;

        }
        
     
     }

    private int maxSet(int set1, int set2, int currentEdge, int v1, int v2, int [] vertices) {

        int i, set1_wins, set2_wins;
        i = set1_wins = set2_wins =0;
        
        while(i < vertices.length ){

            if(vertices[i] == set1) set1_wins++;
            if(vertices[i] == set2) set2_wins++;

            i++;
        }
        
        if((set1_wins > set2_wins) && set2_wins > 1 )
            union(set1, set2,currentEdge, v2, vertices);
        
         if(set1_wins < set2_wins && set1_wins >= 2 )
             union(set2, set1, currentEdge, v1, vertices);
        
        return set1_wins-set2_wins;


    }
    
    private void union(int winnerSet, int set, int currentEdge, int vertex, int [] vertices){
       
        for (int i = 0; i < currentEdge; i++) {
          if ((edgeSet[i].getV1() == vertex) || (edgeSet[i].getV2() == vertex) && vertices[vertex] == set){
           //   edgeSet[i].setKeep(false);
              vertices[edgeSet[i].getV1()] = winnerSet;
              vertices[edgeSet[i].getV2()] = winnerSet;
              break;
          }
             
        }
        
        
    }

    public String PrintKruskal(){
        
        String print = "";

        for (int i = 0; i < edgeSet.length; i++) {
            if(edgeSet[i].isKeep() == true){
                print += "Edge from " + edgeSet[i].getV1() + " to " + edgeSet[i].getV2() + " has weight " + edgeSet[i].getCost() + "\n";
            }
        }

        return print;
    }

     public void MSTprim (int sourceChar){
        Vertex source = vertexlist[sourceChar];
        setCostMax();
        setPreviousVertexNull();
        source.setCost(0);
        int[][] mstMat = new int[nVerts][nVerts];
        this.heap = new Heap(nVerts);
        for (int i = 0; i < nVerts; i++) {
            if (vertexlist[i] == source) {
                heap.insert(vertexlist[i], 0);
            } else {
                heap.insert(vertexlist[i], Integer.MAX_VALUE);
            }
        }
        while (!heap.isEmpty()){
            Vertex temp = heap.extractMin();
            int index = temp.getIndex();
            if (temp.getPreviousVertex() != null) {
                mstMat[index][temp.getPreviousVertex().getIndex()] = temp.getCost();
                //mstMat[temp.getPreviousVertex().getIndex()][index] = temp.getCost();
            }
            //Update the adjecent vertices for temp
            if (index != -1) {//Find if the vertex exists or not
                for (int i = 0; i < nVerts; i++) {
                    if (adjMat[index][i] != 0) { // If there's an edge between those 2
                        if (heap.isContains(vertexlist[i])!= -1 && adjMat[index][i] < vertexlist[i].getCost()) { // Main Prim condition
                            vertexlist[i].setPreviousVertex(temp);
                            vertexlist[i].setCost(adjMat[index][i]); 
                            //heap.getVertex(i).setCost(adjMat[index][i]);
                            heap.changeCost(heap.getIndex(vertexlist[i]), adjMat[index][i]);
                            heap.getVertex(i).setPreviousVertex(temp);
                        }
                    }
                }        
            }
            else {
                System.out.println("Vertex not found.");
            }
        }
        displayMstPrim(mstMat);
        printMSTPrim(mstMat);
        
    }
    
    private void displayMstPrim(int[][] mstPrim) {
        int i, j;
        i = 0;
        System.out.printf("%-8s", "");

        while (i < mstPrim.length) {
            System.out.printf("%-5d", i);
            i++;
        }
        System.out.print("\n");
        i = j = 0;
        while (i < mstPrim.length) {
            System.out.printf("\n%-2s%-6d", "", i);
            while (j < mstPrim[i].length) {
                System.out.printf("%-5d", mstPrim[i][j]);
                j++;
            }
            System.out.print("\n");
            j = 0;
            i++;
        }

        System.out.print("\n");
    }
    
    private void printMSTPrim(int [][] mstPrim){
        int cost =0;
        for (int i = 0; i < mstPrim.length; i++) {
            for (int j = 0; j < mstPrim[i].length; j++) {
                cost += mstPrim[i][j];
            }
        }
        System.out.println("Total weight of MST by Prim's algorithm: " + cost);
        System.out.println("The edges in the tree are:");
        for (int i = 0; i < mstPrim.length; i++) {
            for (int j = 0; j < mstPrim[i].length; j++) {
                if (mstPrim[i][j] != 0) {
                    System.out.println("Edge from " + i + " to " + j + " has weight " + mstPrim[i][j]);
                }
            }
        }
    }

     public void setCostMax() {
        for (int i = 0; i < nVerts; i++) {
            vertexlist[i].setCost(Integer.MAX_VALUE);
        }
    }

     public void setPreviousVertexNull() {
        for (int i = 0; i < nVerts; i++) {
            vertexlist[i].setPreviousVertex(null);
        }
    }
    
    

}
