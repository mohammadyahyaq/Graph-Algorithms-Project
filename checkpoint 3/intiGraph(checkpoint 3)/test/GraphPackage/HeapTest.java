/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GraphPackage;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author DELL
 */
public class HeapTest {
    
    public HeapTest() {
    }
    /**
     * Test of insert method, of class Heap.
     */
    @Test
    public void testInsert() {
        Vertex key = new Vertex();
        Heap instance = new Heap(5); // Five is an arb. value.
        boolean expResult = true;
        boolean result = instance.insert(key, 4);
        assertEquals(expResult, result);
    }

    /**
     * Test of trickleUp method, of class Heap.
     */
    @Test
    public void testTrickleUp() {
        int index = 0;
        System.out.println("trickleUp");
        Vertex vertex1 = new Vertex('a');
        Vertex vertex2 = new Vertex('b');
        Vertex vertex3 = new Vertex('c');
        Vertex vertex4 = new Vertex('d');
        Vertex vertex5 = new Vertex('e');
        Vertex vertex6 = new Vertex('f');
        Heap instance = new Heap(6);
        instance.insert(vertex1, 6);
        instance.insert(vertex2, 3);
        instance.insert(vertex3, 5);
        instance.insert(vertex4, 4);
        instance.insert(vertex5, 2);
        instance.insert(vertex6, 7);
        Vertex [] expected = {vertex5,vertex2,vertex3,vertex1,vertex4,vertex6};
        assertArrayEquals(expected, instance.heap);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of extractMin method, of class Heap.
     */
    @Test
    public void testExtractMin() {
        System.out.println("extractMin");
        Heap instance = null;
        Vertex expResult = null;
        Vertex result = instance.extractMin();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of trickleDown method, of class Heap.
     */
    @Test
    public void testTrickleDown() {
        System.out.println("trickleDown");
        int index = 0;
        Heap instance = null;
        instance.trickleDown(index);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of print method, of class Heap.
     */

}
