package de.lab4inf.mxr.linearalgebra;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import de.lab4inf.mxr.core.Fact2D;
import de.lab4inf.mxr.core.Mops;
import de.lab4inf.mxr.linearalgebra.tools.MathProblem;
import de.lab4inf.mxr.linearalgebra.tools.MatrixCreator;

public class MatrxMultTester {

	
	Double[][] expected;
	Double[][] result;
	static Double tolerance = 5E-12;
	Random r = new Random();


	Mops<MathProblem, Fact2D<Double[][], Double[][]>, Double[][]> myMtrxMulti;

    //1E-12
	
	
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
    	this.myMtrxMulti = new MatrxMult();
    }
    
    
    @Test
    void multMatrixTest1() {
        expected = new Double[][]{
                {5.0, 8.0},
                {8.0, 14.0}
        };
        
        Fact2D<Double[][], Double[][]> facts = Fact2D.fact(m1, m3);
        Double[][] actual = myMtrxMulti.solve(MathProblem.MULT, facts);
        MyAssertEquals.assertMatrixEquals(expected, actual,tolerance);
    }

    @Test
    void multMatrixTest2() {
        expected = new Double[][]{
                {23.0/72.0, 17.0/60.0},
                {23.0/72.0, 17.0/60.0}
        };
        
        Fact2D<Double[][], Double[][]> facts = Fact2D.fact(m5, m6);
        Double[][] actual = myMtrxMulti.solve(MathProblem.MULT, facts);
        MyAssertEquals.assertMatrixEquals(expected, actual,tolerance);
        
        
    }

    @Test
    void wrongDimMatrixTest() {
        try {
        	Fact2D<Double[][], Double[][]> facts = Fact2D.fact(m1, m4);
            @SuppressWarnings("unused")
			Double[][] actual = myMtrxMulti.solve(MathProblem.MULT, facts);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(),"* Operation is impossible. The matrices have different dimensions");
        }
    }
    
    @Test
    void wrongProbTest() {
        try {
        	Fact2D<Double[][], Double[][]> facts = Fact2D.fact(m1, m4);
            @SuppressWarnings("unused")
			Double[][] actual = myMtrxMulti.solve(MathProblem.WRONG, facts);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(),"wrong problem");
        }
    }
    
    @Test
	void hilbertMatrixTest() {
		Double delta = 1.0E-8;
		for (int n = 1; n <= 7; n++) {
			Double[][] a = MatrixCreator.createHilbertMatrix(n);
			Double[][] b = MatrixCreator.createInverseHilbertMatrix(n);
			Double[][] expected = MatrixCreator.createIdMatrix(n);
        	Fact2D<Double[][], Double[][]> facts = Fact2D.fact(a, b);

			
			Double[][] actual = myMtrxMulti.solve(MathProblem.MULT, facts);
			MyAssertEquals.assertMatrixEquals(expected, actual, delta);
		}
	}
    

	//Tr( A.B) = Tt(B).Tr(A)
	@Test
	void multTranspTest() {
		Double delta = 1.0E-8;

		int n = r.nextInt(100); 
		int m = r.nextInt(100);
		int l = r.nextInt(100);
		
		Double[][] a= MatrixCreator.createRndMatrix(n, m);
		Double[][] b = MatrixCreator.createRndMatrix(m, l);
    	Fact2D<Double[][], Double[][]> facts = Fact2D.fact(a, b);
		
		Double[][] ab = myMtrxMulti.solve(MathProblem.MULT, facts);
		Double[][] trAB = MatrixCreator.transpose(ab); 
		
		Double[][] trA = MatrixCreator.transpose(a); 
		Double[][] trB = MatrixCreator.transpose(b); 
		
    	Fact2D<Double[][], Double[][]> facts1 = Fact2D.fact(trB, trA);
		Double[][] trB_trA = myMtrxMulti.solve(MathProblem.MULT, facts1);
		
		MyAssertEquals.assertMatrixEquals(trAB, trB_trA, delta);

		
	}


}
