
package GraphPackage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Graph implements  minPrioQ {

    private final int MAX_VERTS = 20;
    private Vertex vertexlist[] = new Vertex[MAX_VERTS];
    private int adjMat[][] = new int[MAX_VERTS][MAX_VERTS];
    private int nVerts;
    private String printDFS = "";
    private String printBFS = "";
    private boolean isConnected = true; // Initially is connected , it will be changed if the graph is not connected
                                        // from DFS method.
    private Heap heap;
    private Set [] set = new Set [nVerts * nVerts]; //This array for min-prioQ.
    private int setArrayIndex = 0;

    public Graph() {

    }

    public Graph(int numberOfVerteces) {
        // This constructor also initiate all vertices!
        char c = 'a'; // this character is the first character
        for (int i = 0; i < numberOfVerteces; i++) {
            vertexlist[i] = new Vertex(c, i);
            c = (char) (c + 1); // to find the next ascii character
            nVerts++;
        }
    }

    public void addEdgeFromFile(int v1, int v2, int weight) {
        adjMat[v1][v2] = weight;
        adjMat[v2][v1] = weight;

        insert_Q(v1, v2, weight);
        

    }

    public boolean addVertex(char v_lab, int index) {
        // we made it boolean, for giving a msg in AddGRaph soruce code if the
        // vertex already exist and make the user put another one again.
        if (vertexExist(v_lab) == -1 || nVerts == 0) {
            vertexlist[nVerts] = new Vertex(v_lab, index);
            nVerts++;
            return true;
        }

        return false;
    }

    public String displayMatrixForFile() {

        String string = "DISPLAY MATRIX \n";

        int i, j;
        i = 0;
        string += String.format("%-8s", "");

        while (i < nVerts) {
            string += String.format("%-5d", i);
            i++;
        }
        string += String.format("\n");
        i = j = 0;
        while (i < nVerts) {
            string += String.format("\n%-2s%-6d", "", i);
            while (j < nVerts) {
                string += String.format("%-5d", adjMat[i][j]);
                j++;
            }
            string += String.format("\n");
            j = 0;
            i++;
        }

        return string;
    }

  

    public boolean addEdge(char v1_lab, char v2_lab, int weight) { // These parametes are diffirenet from the lab note,
                                                                   // becuase i assumed the user does not numbers of
                                                                   // vertices, but labels!

        int index_v1 = vertexExist(v1_lab);
        int index_v2 = vertexExist(v2_lab);

        if (index_v1 == -1 || index_v2 == -1)
            return false;
        adjMat[index_v1][index_v2] = weight;
        adjMat[index_v2][index_v1] = weight;
        return true;
    }

    
    public void displayGraph() {
        displayGraph(adjMat);
    }

    private void displayGraph(int[][] adjMat) {
        int i, j;
        i = 0;
        System.out.printf("%-8s", "");

        while (i < nVerts) {
            System.out.printf("%-5d", i);
            i++;
        }
        System.out.print("\n");
        i = j = 0;
        while (i < nVerts) {
            System.out.printf("\n%-2s%-6d", "", i);
            while (j < nVerts) {
                System.out.printf("%-5d", adjMat[i][j]);
                j++;
            }
            System.out.print("\n");
            j = 0;
            i++;
        }

        System.out.print("\n");
    }


    public int vertexExist(char v_lab) {

        int i = 0;
        while (i < nVerts) {
            if (vertexlist[i].label == v_lab)
                return i;

            i++;
        }
        return -1;

    }

    public String printVertecesForFile() {
        String string = "Adjacent Vertices of every vertex: \n";

        int i = 0;
        while (vertexlist[i] != null) {
            string += String.format("Vertex " + i + " {" + vertexlist[i].label + "} - VISIT: "
                    + vertexlist[i].wasVisited + " - ADJACENCY: ");
            int numberOfAdj = 0;
            // we need another loop to find all the verteces that connected to the vertex
            int j = 0;
            while (vertexlist[j] != null) {
                if (adjMat[i][j] != 0) {
                    if (numberOfAdj == 0) {
                        // in this case first adjacent will be printed
                        string += String.format("%d", j);
                    } else {
                        string += String.format(", " + j); // only the first number has no comm before it
                    }
                    numberOfAdj++;
                }
                j++;
            }
            string += "\n";
            i++;
        }

        return string;

    }

    public void printVerteces() {
        // Sort the array before printing ...
        sortVertices();
        System.out.println("Adjacent Vertices of every vertex: \n");
        int i = 0;
        while (vertexlist[i] != null) {
            System.out.print("Vertex " + i + " {" + vertexlist[i].label + "} - VISIT: " + vertexlist[i].wasVisited
                    + " - ADJACENCY: ");
            int numberOfAdj = 0;
            // we need another loop to find all the verteces that connected to the vertex
            int j = 0;
            while (vertexlist[j] != null) {
                if (adjMat[i][j] != 0) {
                    if (numberOfAdj == 0) {
                        // in this case first adjacent will be printed
                        System.out.print(j);
                    } else {
                        System.out.print(", " + j); // only the first number has no comm before it
                    }
                    numberOfAdj++;
                }
                j++;
            }
            System.out.println();
            i++;
        }
    }

    // A method that do the real DFS work is this, but it will do it for a given
    // source vertex only.
    private void ApplyDFS(Vertex source) {
        Stack stack = new Stack();// Make a stack using the stack utility in java already...
        stack.push(source); // Push the source
        while (!stack.isEmpty()) {// Avoid nullexecption ;)
            Vertex temp = (Vertex) stack.pop();
            if (temp.wasVisited == false) { // Check and append in string if it's not visited yet
                temp.setVertexVisted(true);
                printDFS += temp.getIndex() + " ";
            }
            for (int i = nVerts - 1; i >= 0; i--) { // for loop it to the number of verteces that have been added only ,
                                                    // not to all the array!
                if (adjMat[temp.getIndex()][i] == 1) {
                    if (this.vertexlist[i].wasVisited == false) {
                        // Means it has not been visited yet...
                        // Push this adj vertex
                        stack.push(this.vertexlist[i]);
                    }
                }
            }
        }
    }

    // Real DFS method which apply the ApplyDFS method for all verteces to assure
    // printing not connected graph :D
    public void DFS() {

        setVrticesUnvisited();
        // Apply DFS to all verteces with a condition if the vertex is not visited.
        for (int i = 0; i < nVerts; i++) {
            if (vertexlist[i].wasVisited == false) {
                ApplyDFS(vertexlist[i]);
                // In our way to DFS the graph , also find if it's connected or not :D
                if (i != 0) {// If any number rather than 0 came here , means it was not able to search all
                             // the graph in one go. Hence it's not connected.
                    isConnected = false;
                }
            }
        }

        printDFS = printDFS.trim();
        printDFS = printDFS.replaceAll(" ", ",");
        System.out.println("DFS traversal: " + printDFS);

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

    public String isConnected() {
        
        return isConnected ? "Graph is connected." : "Graph is not connected.";
    }

    public void BFS() {

        setVrticesUnvisited();

        int i = 0;
        while (vertexlist[i] != null) {
            // this loop will insure that we visit all nodes even it was not connected
            if (!vertexlist[i].wasVisited) {
                applyBFS(vertexlist[i], i);
            }
            i++;
        }

        printBFS = printBFS.trim();
        printBFS = printBFS.replaceAll(" ", ",");
        System.out.println("BFS traversal: " + printBFS);

    }

    public void applyBFS(Vertex source, int Index) {
        Queue<Vertex> BFSQueue = new LinkedList<>();
        source.wasVisited = true;
        printBFS += source.getIndex() + " ";
        Vertex currentVertex = source;
        int i = 0;
        do {
            i = 0;
            while (vertexlist[i] != null) {
                // we need to check if the vertex is adjacent to the current vertex
                if (!vertexlist[i].wasVisited && adjMat[Index][i] > 0) {
                    // we found adjacent not visited vertex
                    vertexlist[i].wasVisited = true;

                    BFSQueue.add(vertexlist[i]);
                }
                i++;
            }
            // now we visited all verteces that are adjacent to the current vertex
            currentVertex = BFSQueue.remove();
            Index = currentVertex.getIndex();
            printBFS += currentVertex.getIndex() + " ";
        } while (!BFSQueue.isEmpty());
    }

    public String getprintDFS() {
        return printDFS;
    }

    private void setVrticesUnvisited() {

        for (int i = 0; i < nVerts; i++) {
            vertexlist[i].setVertexVisted(false);
        }

    }
    
    public void convertToHeap () {
        heap = new Heap(nVerts); // Now create the heap , because previously nVerts was 0.
        for (int i = 0; i < nVerts; i++) {
            heap.insert(vertexlist[i]);
            System.out.print((int)vertexlist[i].label - 97 + " ");
        }
    }
    
    public boolean remove (char key) {
        Vertex temp = heap.remove(vertexlist[(int)key - 97]); // Here since our array is 0 = a , 1 = b .... 9 = j.. use this to convert from char to int number
        if (temp.getIndex() == -1) { // See remove method and see why i made this condition :)
            System.out.println("Cannot remove item , heap is empty");
            return false;
        }
        else {
            System.out.println("Key has been removed.");
            return true;
        } 
            
    }
    
    public void printHeap(){
        this.heap.print();
    }

    public String getPrintBFS() {
        return printBFS;
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

    @Override
    public void insert_Q(int v_one, int v_two, int weight) {

       if(setArrayIndex == 0){
              set[setArrayIndex] = new Set(v_one, v_two, weight);
                setArrayIndex++;
                return;

       }
        for (int i = setArrayIndex; i >= 0; i--) {
            if(set[i].getWeight() < weight )
                set[i+1] = new Set (set[i].getV_one(), set[i].getV_two(), set[i].getWeight());
            else{
                set[i].setV_one(v_one);
                set[i].setV_two(v_two);
                set[i].setWeight(weight);
                break;
            }
        }
        
        setArrayIndex++;

    }

    @Override
    public void deleteMin_Q() {
        setArrayIndex--;

    }

    @Override
    public void decreaseKey_Q(int v, int p) {
        String x = null;

    }

    public Set[] getSet() {
        return set;
    }

    public void setSet(Set[] set) {
        this.set = set;
    }




}
