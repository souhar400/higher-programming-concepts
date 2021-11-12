package de.lab4inf.mxr.linearalgebra;

import static java.lang.String.format;

import java.util.Objects;

import de.lab4inf.mxr.core.Fact2D;
import de.lab4inf.mxr.core.Mops;
import de.lab4inf.mxr.core.NoSolutionException;

public class MatrxVecMult implements Mops<String, Fact2D<Double[][], Double[]>, Double[]> {
	static final String PROBLEM = "MAT_VEC";

	@Override
	public Double[] solve(String p, Fact2D<Double[][], Double[]> facts) throws NoSolutionException {
		Objects.requireNonNull(p, "no problem given");
		Objects.requireNonNull(facts, "no fact(s) given");

		if (!PROBLEM.equals(p))
			throw new IllegalArgumentException(format("wrong problem %s != %s", p, PROBLEM));

		Double[][] matrix1 = facts.u;
		Double[] vector = facts.v;

		if (matrix1[0].length != vector.length)
			throw new IllegalArgumentException("Multiplication is because of the dimensions not possible");

		Double[] result = new Double[matrix1[0].length]; 
		for (int i =0; i< result.length; i++)
			for (int j=0; j<matrix1[0].length; j++)
				result[i] += matrix1[i][j]*vector[j]; 
		return result;
	}
}
