package de.lab4inf.mxr.linearalgebra;

import static java.lang.String.format;

import java.util.Objects;

import de.lab4inf.mxr.core.Fact2D;
import de.lab4inf.mxr.core.Mops;
import de.lab4inf.mxr.core.NoSolutionException;
import de.lab4inf.mxr.core.NonNull;
import de.lab4inf.mxr.linearalgebra.tools.MathProblem;

public class VecAdder implements Mops<MathProblem, Fact2D<Double[], Double[]>, Double[]> {

	
	@Override @NonNull
	public Double[] solve(MathProblem p, Fact2D<Double[], Double[]> facts) throws NoSolutionException {
		
		Objects.requireNonNull(p, "no problem given");
		Objects.requireNonNull(facts, "no fact(s) given");

		if (!MathProblem.ADD.equals(p))
			throw new IllegalArgumentException(format("wrong problem"));
		
		int size1= facts.u.length; 
		int size2 = facts.v.length;
		
		if( size1 != size2)
			throw new IllegalArgumentException("not the same size of vectors");

		
		Double[] myVektor = new Double[size1];
		for (int i =0; i<size1-1; i++)
			myVektor[i] = facts.u[i] + facts.v[i];
		
		
		return myVektor; 
		
	}

}
