package de.lab4inf.mxr.linearalgebra;

import static java.lang.String.format;

import java.util.Objects;




import de.lab4inf.mxr.core.Fact2D;
import de.lab4inf.mxr.core.Mops;
import de.lab4inf.mxr.core.NoSolutionException;
import de.lab4inf.mxr.core.NonNull;
import de.lab4inf.mxr.linearalgebra.tools.MathProblem;

public class MatrxAdder implements Mops<MathProblem, Fact2D<Double[][], Double[][]>, Double[][]>{

	@Override
	public Double[][] solve(MathProblem p, Fact2D<Double[][], Double[][]> facts) throws NoSolutionException {
		
		Objects.requireNonNull(p, "no problem given");
		Objects.requireNonNull(facts, "no fact(s) given");
		

		if (!MathProblem.ADD.equals(p))
			throw new IllegalArgumentException(format("wrong problem"));
		
		Double[][] matrix1 = facts.u; 
		Double[][] matrix2 = facts.v; 
		
		
		if(( matrix1.length != matrix2.length) || (matrix1[0].length != matrix2[0].length)  ) 
			throw new IllegalArgumentException("+ Operation is impossible. The matrices have different dimensions");

		int spaltenZahl = matrix1[0].length;
		int zeilenZahl = matrix1.length; 
		
		@NonNull
		Double[][] result = new Double[matrix1.length][matrix1[0].length];
			
		for(int i=0; i< zeilenZahl; i++ )
			for( int j=0; j < spaltenZahl; j++)
				result[i][j]= matrix1[i][j]+ matrix2[i][j];			
		
		return result; 
	}

	
	
}
