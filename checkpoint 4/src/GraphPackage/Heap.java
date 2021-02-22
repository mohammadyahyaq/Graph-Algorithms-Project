//بسم الله الرحمن الرحيم

/*
This is a Min Heap
• Its parent is (x-1) / 2.
• Its left child is 2*x + 1.
• Its right child is 2*x + 2.
*/
package GraphPackage;

public class Heap  implements minPrioQ  {
    Vertex heap [];
    private int heapSize;
    private int currentIndex;
/**
 * This is the constructor of Heap class
 * @param heapSize is used to initialize the heap with a given size
 * We Update heapSize as well.
 */
    public Heap(int heapSize) {//Give me the size of the heap ... 
        this.heap = new Vertex[heapSize];
        this.heapSize = heapSize;
    }
    //Getter for heapSize
    public int getHeapSize() {
        return heapSize;
    }
    //Setter for heapSize
    public void setHeapSize(int heapSize) {
        this.heapSize = heapSize;
    }
/**
 * This method is used to insert a vertex in the Heap
 * @param key is used to pass the actual vertex
 * @param cost is used to assign it with the given vertex
 * @return will return true if the insertion succeed , or false if the insertion is not succeed 
 */
    public boolean insert(Vertex key, int cost) { // Give me Vertex object, not char :D 
        if (this.currentIndex == this.heapSize) { // Full
            return false;
        } 
        else {
            Vertex newVertex = key;
            newVertex.setCost(cost);
            heap[currentIndex] = newVertex;
            trickleUp(currentIndex++); // Increase the currentIndex after this statement
            return true;
        }
    }
/**
 * This method is used to TrickleUp the inserted vertex. Same as Heapify
 * @param index is used to determine the index of the vertex to be trickled up
 */
    public void trickleUp (int index) {
        int parentIndex = (index-1) / 2;
        Vertex temp = heap[index]; //Save it here
        while (index > 0 && heap[parentIndex].getCost() > temp.getCost()){
            heap[index] = heap[parentIndex];
            index = parentIndex;
            parentIndex = (parentIndex-1)/2; // Update parent
        }
        //Now copy the root to the last
        heap[index] = temp;
    }
/**
 * This method is used to extract the minimum cost of the vertices in the Heap
 * @return Will return a vertex.
 * If vertex's index is -1 , then failed to extract the minimum vertex because the heap is empty.
 * If vertex's index is not -1, then it succeed to extract the minimum and will return it back to the caller.
 */
    public Vertex extractMin() {
        Vertex returnedVertex = new Vertex();
        if (currentIndex <= 0) {//Means empty
            //IMPORTANT ************************************************
            // IT WILL RETURN VERTEX AND IT"S INDEX WILL BE -1 MEANS REMOVE WAS (NOT) SUCCESSFULL.
            //Please when you use this method , first check the index of the vertex , like this : 
            /*
            if (vertex.getIndex() == -1)
                Print heap is empty.
            else 
                Vertex temp = vertex; <<< the returned vertex
            */
            returnedVertex.label = 0; // To not get error if the user used .label
            returnedVertex.setIndex(-1);
            return returnedVertex;
        } 
        else {
            //Save the root
            returnedVertex = heap[0];    
            heap[0] = heap[--currentIndex]; // root = last
            trickleDown(0); //The root
            return returnedVertex;
        }
        
    }
    
    public void changeCost(int index , int cost){//Remove by index
        if (index == 0 && heap[index].getCost() >= cost) {
            heap[index].setCost(cost);
        }
        else {
            heap[index].setCost(cost);
            int parent = (index - 1) / 2;
            if (index == 0 || heap[parent].getCost() < heap[index].getCost()) {
                trickleDown(index);
            } else {
                trickleUp(index);
            }
        }
    }

    /**
 * This method is used to TrickleDown the inserted vertex. Same as Heapify Down
 * @param index is used to determine the index of the vertex to be trickled Down
 */
    public void trickleDown (int index){
        int largerChild;
        Vertex temp = heap[index];
        while(index < currentIndex/2){//While the Vertex has at least one child 
            int leftChild = 2 * index + 1;
            int rightChild = leftChild + 1;
            //Find the largest now
            if (rightChild < currentIndex && heap[leftChild].getCost() > heap[rightChild].getCost())
                //If right child exists and left is less than right then :
                largerChild = rightChild; 
            else 
                largerChild = leftChild;
            
            //if temp is >= largerChild
            if (temp.getCost() < heap[largerChild].getCost() ) 
                break;
            heap[index] = heap[largerChild];
            index = largerChild; // Move down and repeat
        }
        heap[index] = temp; // Now copy root to last
    }
/**
 * This method is used to print all vertices in the heap.
 */
    public void print (){
        System.out.println("");
        //Print to the currentIndex only , not to all heap size!
        for (int i = 0; i < currentIndex; i++) {
            System.out.print(heap[i].label + " ");
            
        }
    }
/**
 * This method is used to test the TrickleDown method.
 * Why we need this method?
 * Answer: While extraction of min element, real heap length is not changed. Only the current index (max index).
 * We need to get actual heap length to test it with some pre-defined array. (Check testTrickleDown method in HeapTest.java)
 * How? Ans: It will copy all elements from 0 to currentIndex in another array. Then return it
 * @return 
 */
    public Vertex [] generateActualHeapArray() {
        //This method is used to test the TrickleDown method.
        //Why we need this method?
        //Ans: While extraction of min element, real heap length is not changed. Only the current index (max index).
        //We need to get actual heap length to test it with some pre-defined array. (Check testTrickleDown method in HeapTest.java)
        //How? 
        //Ans: It will copy all elements from 0 to currentIndex in another array. Then return it
        Vertex [] heapWithExactLength = new Vertex[currentIndex];
        for (int i = 0; i < currentIndex; i++) {
            heapWithExactLength[i] = heap[i];
        }
        return heapWithExactLength;
    } 
    


    @Override
    public void decreaseKey_Q(Vertex v, int cost) {
        //we need to find the index of the vertex inside the heap
        int vertexIndex = -1;
        for (int i = 0; i < heapSize; i++) {
            if (v == heap[i]) {
                vertexIndex = i;
                break;
            }
        }

        v.setCost(cost);
        trickleUp(vertexIndex);
    }

    @Override
    public void insert_Q(Vertex v, int cost) {
//        v.setCost(cost);
//        pq[index_q] = v;
//        index_q++;
    }

    @Override
    public Vertex deleteMin_Q() {

//        int min_cost, index; // The second var. is for shifting in the next loop after deleting.
//        min_cost = Integer.MAX_VALUE;
//        index = 0;
//        for (int i = 0; i < pq.length; i++) {
//            if(pq[i].getCost() < min_cost){
//                min_cost = pq[i].getCost();
//                index = i;
//            }
//        }
//
//        for (int i = index; i < pq.length -1 ; i++)  pq[i] = pq[i +1]; //Shifting
//        return pq[index]; //Return Vertex with least cost.
        return null;

    }
    
    public boolean isEmpty(){
        if (currentIndex == 0) {
            return true;
        }
        return false;
    }
    
    public int isContains(Vertex vertex){
        for (int i = 0; i < currentIndex; i++) {
            if (vertex == heap[i]) {
                return i;
            }
        }
        return -1;
    }
    
    public Vertex getVertex(int index){
        return heap[index];
    }
    
    public int getIndex (Vertex key){
        for (int i = 0; i < currentIndex; i++) {
            if (key == heap[i]) {
                return i;
            }
        }
        return -1;
    }
    
}
