package de.lab4inf.mxr.linearalgebra;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import de.lab4inf.mxr.core.Fact2D;
import de.lab4inf.mxr.core.Mops;
import de.lab4inf.mxr.linearalgebra.tools.MathProblem;
import de.lab4inf.mxr.linearalgebra.tools.MatrixCreator;

public class MatrxAdderTester {
	Double[][] expected;
	Double[][] result;
	static Double tolerance = 5E-12;
	Random r = new Random();
	
	Mops<MathProblem, Fact2D<Double[][], Double[][]>, Double[][]> myMtrxAdder;
	

	final Double[][] m1 = {
            {0.0, 1.0, 2.0},
            {1.0, 2.0, 3.0}
    };

    final Double[][] m2 = {
            {0.0, 1.0, 2.0},
            {1.0, 2.0, 3.0}
    };

    final Double[][] m3 = {
            {0.0, 1.0},
            {1.0, 2.0},
            {2.0, 3.0}
    };
    final Double[][] m4 = {
            {0.0, 1.0},
            {1.0, 2.0}
    };

    final Double[][] m5 = {
            {1.0/2.0, 1.0/3.0, 1.0/6.0},
            {1.0/2.0, 1.0/3.0, 1.0/6.0}
    };

    final Double[][] m6 = {
            {1.0/4.0, 1.0/3.0},
            {1.0/3.0, 1.0/4.0},
            {1.0/2.0, 1.0/5.0},

    };

 
	
	
    @BeforeEach
    void setUp() throws Exception {
    	this.myMtrxAdder = new MatrxAdder();
    }
    
   
    @Test
    void testAddMatrix() {
        expected = new Double[][]{
                {0.0, 2.0, 4.0},
                {2.0, 4.0, 6.0}
        };
        
        Fact2D<Double[][], Double[][]> facts = Fact2D.fact(m1, m2);
		Double[][] actual = myMtrxAdder.solve(MathProblem.ADD, facts);
		MyAssertEquals.assertMatrixEquals(expected, actual, tolerance);
	}
    
    @Test
    void wrongDimMatrixTest() {
        try {
        	Fact2D<Double[][], Double[][]> facts = Fact2D.fact(m1, m4);
            @SuppressWarnings("unused")
			Double[][] actual = myMtrxAdder.solve(MathProblem.ADD, facts);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(),"+ Operation is impossible. The matrices have different dimensions");
        }
    }
    
    @Test
    void wrongProbTest() {
        try {
        	Fact2D<Double[][], Double[][]> facts = Fact2D.fact(m1, m2);
            @SuppressWarnings("unused")
			Double[][] actual = myMtrxAdder.solve(MathProblem.WRONG, facts);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(),"wrong problem");
        }
    }
    
  //Tr( A.B) = Tt(B).Tr(A)
  	@Test
  	void addTranspTest() {
  		Double delta = 1.0E-8;

  		int n = r.nextInt(100); 
  		int m = r.nextInt(100);
  		
  		Double[][] a= MatrixCreator.createRndMatrix(n, m);
  		Double[][] b = MatrixCreator.createRndMatrix(n, m);
      	Fact2D<Double[][], Double[][]> facts = Fact2D.fact(a, b);
  		
  		Double[][] ab = myMtrxAdder.solve(MathProblem.ADD, facts);
  		Double[][] trAB = MatrixCreator.transpose(ab); 
  		
  		Double[][] trA = MatrixCreator.transpose(a); 
  		Double[][] trB = MatrixCreator.transpose(b); 
  		
      	Fact2D<Double[][], Double[][]> facts1 = Fact2D.fact(trB, trA);
  		Double[][] trB_plus_trA = myMtrxAdder.solve(MathProblem.ADD, facts1);
  		
  		MyAssertEquals.assertMatrixEquals(trAB, trB_plus_trA, delta);

  		
  	}

}
