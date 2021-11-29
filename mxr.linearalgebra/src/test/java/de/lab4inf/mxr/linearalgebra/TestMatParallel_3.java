package de.lab4inf.mxr.linearalgebra;


import static org.junit.jupiter.api.Assertions.*;
import java.util.Random;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import de.lab4inf.mxr.core.Fact2D;
import de.lab4inf.mxr.core.Fact3D;
import de.lab4inf.mxr.core.FactFactory;
import de.lab4inf.mxr.core.Mops;
import de.lab4inf.mxr.linearalgebra.tools.MathProblem;
import de.lab4inf.mxr.linearalgebra.tools.MatrixCreator;

class TestMatParallel_3 {
	
	
	double[][] expected;
	double[][] result;
	static double tolerance = 5E-12;
	Random r = new Random();

	Mops<MathProblem, Fact3D<double[][], double[][], Boolean>, double[][]> parallelmult;
	Mops<MathProblem, Fact2D<double[][], double[][]>, double[][]> myMtrxMulti;
	
double[][] m1,m2,m3;
	@BeforeEach
	void setUp() throws Exception {
		parallelmult = new MatParallel_3();
		myMtrxMulti = new MatrxMult();
		m1 = MatrixCreator.createRndMatrix(512, 512);
		m2 = MatrixCreator.createRndMatrix(12, 12);
		m3 = MatrixCreator.createRndMatrix(512, 512);
	}

	@Test
	void testRndMult() {
		Fact3D<double[][], double[][], Boolean> facts3d = FactFactory.facts(m1, m3, true);
		Fact2D<double[][], double[][]> facts2d = FactFactory.facts(m1, m3);
		result = parallelmult.solve(MathProblem.MULT, facts3d);
		expected = myMtrxMulti.solve(MathProblem.MULT, facts2d);
		MyAssertEquals.assertMatrixEquals(expected, result, tolerance);
	}
	
	@Test
	void testWrongDim() {
		Fact3D<double[][], double[][], Boolean> facts3d = FactFactory.facts(m1, m2, true);
		assertThrows(IllegalArgumentException.class, () -> parallelmult.solve(MathProblem.MULT, facts3d));
	}
	@Test
	void testWrongFact() {
		Fact3D<double[][], double[][], Boolean> facts3d = FactFactory.facts(m1, m3, false);
		assertThrows(IllegalArgumentException.class, () -> parallelmult.solve(MathProblem.MULT, facts3d));	
	}
	@Test
	void testWrongProblem() {
		Fact3D<double[][], double[][], Boolean> facts3d = FactFactory.facts(m1, m3, true);
		assertThrows(IllegalArgumentException.class, () -> parallelmult.solve(MathProblem.WRONG, facts3d));		
	}
}
