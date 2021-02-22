

package GraphPackage;

public class prioritySet  implements Comparable<prioritySet>{

    private int v1;
    private int v2;
    private int cost;
    private boolean keep = false;

    public prioritySet(int v1, int v2, int cost){
        this.v1 = v1;
        this.v2 = v2;
        this.cost = cost;
    }

    @Override
    public int compareTo(prioritySet o) {
        return  this.cost -  o.cost;
    }

    public int getV1() {
        return v1;
    }

    public int getV2() {
        return v2;
    }

    public void setV1(int v1) {
        this.v1 = v1;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setV2(int v2) {
        this.v2 = v2;
    }

    public boolean isKeep() {
        return keep;
    }

    public void setKeep(boolean keep) {
        this.keep = keep;
    }

    

}