package de.lab4inf.mxr.linearalgebra;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import de.lab4inf.mxr.core.Fact3D;
import de.lab4inf.mxr.core.Mops;
import de.lab4inf.mxr.linearalgebra.tools.MathProblem;
import de.lab4inf.mxr.linearalgebra.tools.MatrixCreator;

public class MatrixOptimizedTester {
			
	double[][] expected;
	double[][] result;
	static double tolerance = 5E-12;
	Random r = new Random();
	
	
	final double[][] m1 = {
            {0.0, 1.0, 2.0},
            {1.0, 2.0, 3.0}
    };

    final double[][] m2 = {
            {0.0, 1.0, 2.0},
            {1.0, 2.0, 3.0}
    };

    final double[][] m3 = {
            {0.0, 1.0},
            {1.0, 2.0},
            {2.0, 3.0}
    };
    final double[][] m4 = {
            {0.0, 1.0},
            {1.0, 2.0}
    };

    final double[][] m5 = {
            {1.0/2.0, 1.0/3.0, 1.0/6.0},
            {1.0/2.0, 1.0/3.0, 1.0/6.0}
    };

    final double[][] m6 = {
            {1.0/4.0, 1.0/3.0},
            {1.0/3.0, 1.0/4.0},
            {1.0/2.0, 1.0/5.0},

    };
    
    Mops<MathProblem, Fact3D<double[][], double[][], Boolean>, double[][]> myOptimMatMult; 
   
    @BeforeEach
    void setUp() throws Exception {
    	this.myOptimMatMult = new MatrxMultOptimized();
    }
    
    // SIMPLE mutli matrix 1  : Seriell 
    @Test
    void serMultMatrixTest1() {
        expected = new double[][]{
                {5.0, 8.0},
                {8.0, 14.0}
        };
        
        Fact3D<double[][], double[][], Boolean> facts = Fact3D.fact(m1, m3, false);
        double[][] actual = myOptimMatMult.solve(MathProblem.OPTMULT, facts);
        MyAssertEquals.assertMatrixEquals(expected, actual,tolerance);
    }
    
    // SIMPLE mutli matrix 1 : Parallel 
    @Test
    void parMultMatrixTest1() {
        expected = new double[][]{
                {5.0, 8.0},
                {8.0, 14.0}
        };
        
        Fact3D<double[][], double[][], Boolean> facts = Fact3D.fact(m1, m3, true);
        double[][] actual = myOptimMatMult.solve(MathProblem.OPTMULT, facts);
        MyAssertEquals.assertMatrixEquals(expected, actual,tolerance);
    }

    // SIMPLE mutli matrix 2 : Seriell
    @Test
    void serMultMatrixTest2() {
        expected = new double[][]{
                {23.0/72.0, 17.0/60.0},
                {23.0/72.0, 17.0/60.0}
        };
        
        Fact3D<double[][], double[][], Boolean> facts = Fact3D.fact(m5, m6, false);
        double[][] actual = myOptimMatMult.solve(MathProblem.OPTMULT, facts);
        MyAssertEquals.assertMatrixEquals(expected, actual,tolerance);
    }
    
    // SIMPLE mutli matrix 2 : Parallel
    @Test
    void parMultMatrixTest2() {
        expected = new double[][]{
                {23.0/72.0, 17.0/60.0},
                {23.0/72.0, 17.0/60.0}
        };
        
        Fact3D<double[][], double[][], Boolean> facts = Fact3D.fact(m5, m6, true);
        double[][] actual = myOptimMatMult.solve(MathProblem.OPTMULT, facts);
        MyAssertEquals.assertMatrixEquals(expected, actual,tolerance);
    }

    //Wrong Dim : matrix OPTMULT (2,3)*(2,2)
    @Test
    void wrongDimMatrixTest() {
        try {
        	Fact3D<double[][], double[][], Boolean> facts = Fact3D.fact(m1, m4, true);
            @SuppressWarnings("unused")
			double[][] actual = myOptimMatMult.solve(MathProblem.OPTMULT, facts);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(),"* Operation is impossible. The matrices have different dimensions");
        }
    }
    
    //wrong problem
    @Test
    void wrongProbTest() {
        try {
        	Fact3D<double[][], double[][], Boolean> facts = Fact3D.fact(m1, m4, true);
            @SuppressWarnings("unused")
			double[][] actual = myOptimMatMult.solve(MathProblem.WRONG, facts);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(),"wrong problem");
        }
    }
    
    // hilbert(n)*hilbert(n)^-1 : Parallel	 
    @Test
	void parHilbertMatrixTest() {
		//double delta = 1.0E-5;
		double delta = 1.0E-8;
		for (int n = 1; n <= 7; n++) {
			double[][] a = MatrixCreator.createHilbertMatrix(n);
			double[][] b = MatrixCreator.createInverseHilbertMatrix(n);
			double[][] expected = MatrixCreator.createIdMatrix(n);
        	Fact3D<double[][], double[][], Boolean> facts = Fact3D.fact(a, b, true);
			
			double[][] actual = myOptimMatMult.solve(MathProblem.OPTMULT, facts);
			MyAssertEquals.assertMatrixEquals(expected, actual, delta);
		}
	}
    
    // hilbert(n)*hilbert(n)^-1 : Seriell 
    @Test
	void serHilbertMatrixTest() {
		//double delta = 1.0E-5;
		double delta = 1.0E-8;
		for (int n = 1; n <= 7; n++) {
			double[][] a = MatrixCreator.createHilbertMatrix(n);
			double[][] b = MatrixCreator.createInverseHilbertMatrix(n);
			double[][] expected = MatrixCreator.createIdMatrix(n);
        	Fact3D<double[][], double[][], Boolean> facts = Fact3D.fact(a, b, false);
			
			double[][] actual = myOptimMatMult.solve(MathProblem.OPTMULT, facts);
			MyAssertEquals.assertMatrixEquals(expected, actual, delta);
		}
	}
    
}
