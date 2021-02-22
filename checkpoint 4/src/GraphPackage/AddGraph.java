// nullExceptionTeam
// Member 1: Turki Alzubaidi   (1740408) made Kurskal.
// Member 2: Ammar Joharji     (1742559) made Prim's.
// Member 3: Mohammad alghafli (1741679) made JUNIT testing on Kruskan and prims, also comparing its running times with observation.

package GraphPackage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author nullExceptionTeam CPCS324 Project (2019-2020)
 * Member 1: Turki  Alzubaidi (1740408)
 * Member 2: Ammar Joharji (1742559)
 * Member 3: Mohammad alghafli (1741679)
 *
 */

public class AddGraph {

    /**
     * our main method for calling other methods, initialzing graph with its vertices and edges. writing output file.
     * @param args
     * @throws FileNotFoundException 
     */
    public static void main(String[] args) throws FileNotFoundException {

        File input = new File("input.txt");
        Scanner read = new Scanner(input);

        int num_vertex = read.nextInt();
        int num_edge = read.nextInt();

        if (num_vertex > 20 || num_edge > (num_vertex * num_vertex)) {
            System.out.println("incorrect data, check number of verices (MAX = 20) and/or number of edges (MAX = number of vertices^2");
            System.exit(0);
        }

        Graph graph = new Graph(num_vertex, num_edge);
        
        int vertex_one, vertex_two;
        while (read.hasNextLine()) {

            vertex_one = read.nextInt();
            vertex_two = read.nextInt();
            
            graph.addEdgeFromFile(vertex_one, vertex_two, read.nextInt());

        }

        File output = new File("output.txt");
        PrintWriter write = new PrintWriter(output);
    
        graph.kurskal();
        System.out.print("\nKruskal MST: \n" + graph.PrintKruskal());
        write.println("\"\\nKruskal MST: \n" + graph.PrintKruskal());
        graph.MSTprim(0);
        write.close();
        read.close();

    }
  
 
}
