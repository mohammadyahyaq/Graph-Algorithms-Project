package GraphPackage;

public interface minPrioQ {

    void insert_Q(Vertex v, int cost);
    Vertex deleteMin_Q();
    void decreaseKey_Q(Vertex v, int cost);


}