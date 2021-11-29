package de.lab4inf.mxr.linearalgebra;

import static java.lang.String.format;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import de.lab4inf.mxr.core.Fact3D;
import de.lab4inf.mxr.core.Mops;
import de.lab4inf.mxr.core.NoSolutionException;
import de.lab4inf.mxr.linearalgebra.tools.MathProblem;
import de.lab4inf.mxr.linearalgebra.tools.MatrixCreator;

//Source : www.baeldung.com/java-executor-service-tutorial

public class MatrxMultOptimized implements Mops<MathProblem, Fact3D<double[][], double[][], Boolean>, double[][]> {

	@Override
	public double[][] solve(MathProblem p, Fact3D<double[][], double[][], Boolean> facts) throws NoSolutionException {

		Objects.requireNonNull(p, "no problem given");
		Objects.requireNonNull(facts, "no fact(s) given");

		if (!MathProblem.OPTMULT.equals(p))
			throw new IllegalArgumentException(format("wrong problem"));

		double[][] matrix1 = facts.u;
		double[][] matrix2 = facts.v;

		int spaltenZahl1 = matrix1[0].length;
		int zeilenZahl1 = matrix1.length;

		int spaltenZahl2 = matrix2[0].length;
		int zeilenZahl2 = matrix2.length;

		if ((spaltenZahl1 != zeilenZahl2))
			throw new IllegalArgumentException("* Operation is impossible. The matrices have different dimensions");

		double[][] transposed = MatrixCreator.transponieren(matrix2);
		double[][] result = new double[zeilenZahl1][spaltenZahl2];

		boolean concurrent = facts.w;

		if (concurrent) {
			// Executor provides methods to manage termination and produce a "Future" for
			// tracking progess of tasks
			// factory methods of the Executors factory Class
			// Thread pool implementation
			ExecutorService executor = Executors.newCachedThreadPool();

			// The submit() method returns an object or a collection
			List<Future<Boolean>> futures = new ArrayList<>(result.length);
			for (int i = 0; i < result.length; i++) {
				// TODO:
				int aktuel = i;

				// submits a Callable task to the Executor Service and return a result of type Future
				// which allows us to get the result of a task's execution or to check the tasks's status (is it running)
				// Task
				Future<Boolean> future = executor.submit(() -> {
					for (int j = 0; j < result[0].length; j++) {
						result[aktuel][j] = vectorSkalarProdukt(matrix1[aktuel], transposed[j]);
					}
					return true;
				});

				futures.add(future);
			}

			for (Future<Boolean> ft : futures) {
				try {
					// blocking method, wait for the computation to end 
					ft.get();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} else {
			for (int i = 0; i < result.length; i++) {
				for (int j = 0; j < result[0].length; j++) {
					result[i][j] = vectorSkalarProdukt(matrix1[i], transposed[j]);
				}
			}
		}
		return result;
	}

	public static double vectorSkalarProdukt(double[] a, double[] b) {
		double result = 0.0;
		for (int i = 0; i < a.length; i++) {
			result += a[i] * b[i];
		}
		return result;
	}
}
