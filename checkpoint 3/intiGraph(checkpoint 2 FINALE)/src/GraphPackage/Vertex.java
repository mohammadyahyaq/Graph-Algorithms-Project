package GraphPackage;

public class Vertex implements Comparable<Vertex>{

    public char label;
    public boolean wasVisited;
    //I will add a new datamember here to inidate the index of this vertex so i can access it directly without searching for it again and again...
    private int index;
    public Vertex() // constructor
    {

    }

    public void setIndex(int index) {
        this.index = index;
    }
    
     public Vertex(int index) // constructor
    {
        label = (char) (97 + index);
    }
     
    public Vertex(char lab , int index) // constructor
    {
        label = lab;
        this.index = index;
    }
    
    //Changes: I made this method to set wasvisited as true or false depends on the developer... [By Member 2]
    public void setVertexVisted(boolean visit){
        this.wasVisited = visit;
    }

    public int getIndex() {
        return index;
    }
    
    //I will add compareable interface and its methods to sort the array

    @Override
    public int compareTo(Vertex t) {
        if (this.label > t.label)
            return 1;
        else if (this.label < t.label)
            return -1;
        else 
            return 0;
    }
} 

