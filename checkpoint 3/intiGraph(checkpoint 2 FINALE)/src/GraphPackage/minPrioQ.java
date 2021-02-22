


package GraphPackage;

public interface minPrioQ {

    void insert_Q(int v_one, int v_two, int p); // We add one more paramater, becuase we have array of set, so we need a pair of vertices with their weight.
    void deleteMin_Q();
    void decreaseKey_Q(int v, int weight);


}