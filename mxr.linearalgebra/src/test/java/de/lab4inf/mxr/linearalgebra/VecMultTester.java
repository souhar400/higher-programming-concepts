package de.lab4inf.mxr.linearalgebra;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import de.lab4inf.mxr.core.Fact2D;
import de.lab4inf.mxr.core.Mops;
import de.lab4inf.mxr.linearalgebra.tools.MathProblem;

public class VecMultTester {
	Mops<MathProblem, Fact2D<Double[], Double[]>, Double[]> myVecMult;
	Random r = new Random();
    //TODO: static double tolerance = 5.2E-12;
	
	
	@BeforeEach
	void setUp() throws Exception {
		this.myVecMult = new VecMult();
	}

	// ( Double_Array , {0.0} )
	@Test
	void zeroVektorMultTest() {
		int size = r.nextInt(100);

		Double[] vec1 = new Double[size];
		Double[] vec2 = new Double[size];

		for (int i = 0; i < size - 1; i++) {
			vec1[i] = r.nextDouble();
		}

		for (int i = 0; i < size - 1; i++) {
			vec2[i] = 0.0;
		}

		Fact2D<Double[], Double[]> facts = new Fact2D<Double[], Double[]>(vec1, vec2);
		Double[] actual = myVecMult.solve(MathProblem.MULT, facts);
		Double[] expected = vec2;

		assertArrayEquals(actual, expected);
	}

	// ( Double_Array , - Double_Array)
	@Test
	void oppositeVektorMultTest() {
		int size = r.nextInt(100);

		Double[] vec1 = new Double[size];
		Double[] vec2 = new Double[size];
		Double[] expected = new Double[size];

		for (int i = 0; i < size - 1; i++)
			vec1[i] = r.nextDouble();

		for (int i = 0; i < size - 1; i++)
			vec2[i] = -vec1[i];

		for (int i = 0; i < size - 1; i++)
			expected[i] = -Math.pow(vec1[i], 2);

		Fact2D<Double[], Double[]> facts = new Fact2D<Double[], Double[]>(vec1, vec2);
		Double[] actual = myVecMult.solve(MathProblem.MULT, facts);

		assertArrayEquals(actual, expected);
		//TODO: assertArrayEquals(vec1, vec2, tolerance);
	}

	// ( Double_Array , Double_Array)
	@Test
	void sameVektorMultTest() {
		int size = r.nextInt(100);

		Double[] vec1 = new Double[size];
		Double[] vec2 = new Double[size];
		Double[] expected = new Double[size];

		for (int i = 0; i < size - 1; i++) {
			vec1[i] = r.nextDouble();
			vec2[i] = vec1[i];
			expected[i] = Math.pow(vec1[i], 2);
		}

		Fact2D<Double[], Double[]> facts = Fact2D.fact(vec1, vec2);
		Double[] actual = myVecMult.solve(MathProblem.MULT, facts);

		assertArrayEquals(actual, expected);
	}

	// (Exception: not same size)
	@Test
	void notSameSizeMultTest() {
		int size1 = r.nextInt(100);
		Double[] vec1 = new Double[size1];
		Double[] vec2 = new Double[size1 + 1];

		Fact2D<Double[], Double[]> facts = new Fact2D<Double[], Double[]>(vec1, vec2);

		try {
			@SuppressWarnings("unused")
			Double[] actual = myVecMult.solve(MathProblem.MULT, facts);
			fail("Ouuups, Test fails because no exception was thron for wrong dimensions ");
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "not the same size of vectors");

		}
	}

	// nicht effizient : (radom Vektor , random Vektor)
	@Test
	void twoRandVecMultTest() {
		int size = r.nextInt(100);
		Double[] vec1 = new Double[size];
		Double[] vec2 = new Double[size];
		Double[] expected = new Double[size];

		for (int i = 0; i < size - 1; i++) {
			vec1[i] = r.nextDouble();
			vec2[i] = r.nextDouble();
			expected[i] = vec1[i] * vec2[i];
		}
		
		Fact2D<Double[], Double[]> facts = Fact2D.fact(vec1, vec2);
		Double[] actual = myVecMult.solve(MathProblem.MULT,  facts);
		assertArrayEquals(actual, expected);
	}
	
	//Exception: falsches Problem 
	@Test
	void falseProbTest() {
		
		int size = 10;
		Double[] vec1 = new Double[size];
		Double[] vec2 = new Double[size];
		
		for (int i = 0; i < size - 1; i++) 
			vec1[i] = r.nextDouble();
		

		for (int i = 0; i < size - 1; i++) 
			vec2[i] = r.nextDouble();
		


		Fact2D<Double[], Double[]> facts = new Fact2D<Double[], Double[]>(vec1, vec2);
		try {
			@SuppressWarnings("unused")
			Double[] actual = myVecMult.solve(MathProblem.WRONG, facts);
			fail("Oups, no exception has been thrown ");
		}
		catch( Exception e) {
			assertEquals(e.getMessage(), "wrong problem");
		}
	}
	
	
}
