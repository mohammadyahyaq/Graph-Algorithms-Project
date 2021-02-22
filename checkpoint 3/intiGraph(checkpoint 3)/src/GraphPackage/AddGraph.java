// nullExceptionTeam
// Member 1: Turki Alzubaidi   (1740408)
// Member 2: Ammar Joharji     (1742559)
// Member 3: Mohammad alghafli (1741679)
package GraphPackage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class AddGraph {

    public static void main(String[] args) throws FileNotFoundException {

        File input = new File("input.txt");
        Scanner read = new Scanner(input);

        boolean isWeighted = read.hasNext("WEIGHTED");
        read.next(); // Pass the first line in file.
        int num_vertex = read.nextInt();
        int num_edge = read.nextInt();

        if (num_vertex > 20 || num_edge > (num_vertex * num_vertex)) {
            System.out.println("incorrect data, check number of verices (MAX = 20) and/or number of edges (MAX = number of vertices^2");
            System.exit(0);
        }

        Graph graph = new Graph(num_vertex);

        int vertex_one, vertex_two;
        while (!(read.hasNext("QUIT"))) {

            vertex_one = read.nextInt();
            vertex_two = read.nextInt();

            if (isWeighted) {
                graph.addEdgeFromFile(vertex_one, vertex_two, read.nextInt());
            } else {
                graph.addEdgeFromFile(vertex_one, vertex_two, 1);
            }

        }

        File output = new File("output.txt");
        PrintWriter write = new PrintWriter(output);

        write.print(graph.displayMatrixForFile() + "\n");
        write.print(graph.printVertecesForFile() + "\n");

        graph.DFS();
        write.print("DFS traversal: " + graph.getprintDFS() + "\n");
        graph.BFS();
        write.print("BFS traversal: " + graph.getPrintBFS() + "\n");
        write.print(graph.isConnected());
        write.close();
        read.close();
        test(graph);
    }

    static void test(Graph graph){

                //This is just to check if it's working correct or not :D 
                /*
                Vertex v = new Vertex(0);
                Vertex v1 = new Vertex(1);
                Vertex v2 = new Vertex(2);
                Vertex v3 = new Vertex(3);
                Vertex v4 = new Vertex(4);
                graph.insertHeap(v, 05);
                graph.insertHeap(v1,04);
                graph.insertHeap(v2,3);
                graph.insertHeap(v3,02);
                graph.insertHeap(v4,0);
                graph.printHeap();
                graph.printHeap();
                */

    }




}
