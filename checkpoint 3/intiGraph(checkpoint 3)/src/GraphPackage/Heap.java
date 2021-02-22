//بسم الله الرحمن الرحيم

/*
This is a Min Heap
• Its parent is (x-1) / 2.
• Its left child is 2*x + 1.
• Its right child is 2*x + 2.
*/
package GraphPackage;

public class Heap {
    Vertex heap [];
    private int heapSize;
    private int currentIndex;

    public Heap(int heapSize) {//Give me the size of the heap ... 
        this.heap = new Vertex[heapSize];
        this.heapSize = heapSize;
    }

    public int getHeapSize() {
        return heapSize;
    }

    public void setHeapSize(int heapSize) {
        this.heapSize = heapSize;
    }

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
    
    public void print (){
        System.out.println("");
        //Print to the currentIndex only , not to all heap size!
        for (int i = 0; i < currentIndex; i++) {
            System.out.print(heap[i].label + " ");
            
        }
    }

    public Vertex[] getHeap() {
        return heap;
    }
    
    
}
