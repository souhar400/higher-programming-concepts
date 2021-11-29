package de.lab4inf.mxr.linearalgebra.tools;

import java.util.ArrayList;
import java.util.List;
import de.lab4inf.mxr.core.Fact2D;
import de.lab4inf.mxr.core.Fact3D;
import de.lab4inf.mxr.core.FactFactory;
import de.lab4inf.mxr.linearalgebra.*;

public class Timing {
	private MatrxMult unoptimized;
	private MatSeriell_3 optimized;
	private MatParallel_3 parallel;
	private ArrayList<Fact2D<double[][], double[][]>> factsContainer;
	private List<Long> measuresUnoptimized;
	private List<Long> measuresOptimized;
	private List<Long> measuresParallel;
	private int[] dims= {128, 256, 512, 1024, 2048, 4096};

	
	public Timing(MatrxMult unoptimized, MatSeriell_3 optimized, MatParallel_3 parallel) {
		this.unoptimized = unoptimized;
		this.optimized = optimized;
		this.parallel = parallel;
		this.measuresUnoptimized = new ArrayList<>();
		this.measuresOptimized = new ArrayList<>();
		this.measuresParallel = new ArrayList<>();
		this.factsContainer = new ArrayList<>();
		this.createMatrizes();
	}
	private void createMatrizes(){
		for(int i: dims) {
			this.factsContainer.add(FactFactory.facts(MatrixCreator.createRndMatrix(i, i),MatrixCreator.createRndMatrix(i, i)));
		}
	}
	private void timeUnoptimized() {
		for(Fact2D<double[][], double[][]> element : factsContainer) {
			long startTime = System.nanoTime();
			unoptimized.solve(MathProblem.MULT, element);
			long stopTime = System.nanoTime();
			measuresUnoptimized.add(stopTime- startTime);
		}
	}
	private void timeOptimized() {
		for(Fact2D<double[][], double[][]> element : factsContainer) {
			Fact3D<double[][], double[][], Boolean> fact = FactFactory.facts(element.u, element.v, false);
			long startTime = System.nanoTime();
			optimized.solve(MathProblem.MULT, fact);
			long stopTime = System.nanoTime();
			measuresOptimized.add(stopTime-startTime);
		}
	}
	private void timeParallel() {
		for(Fact2D<double[][], double[][]> element : factsContainer) {
			Fact3D<double[][], double[][], Boolean> fact = FactFactory.facts(element.u, element.v, true);
			long startTime = System.nanoTime();
			parallel.solve(MathProblem.MULT, fact);
			long stopTime = System.nanoTime();
			measuresParallel.add(stopTime-startTime);
		}
	}
	public void printTable() {
		this.timeUnoptimized();
		this.timeOptimized();
		this.timeParallel();
		String dim = new String("dim");
		String base = new String("baseline");
		String opt = new String("optimized");
		String para = new String("parallelized");
		String speed = new String("speedup");
		System.out.format("%4s|%15s     |%20s     |%20s     |%10s\n", dim, base, opt, para, speed);
		System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------");
		for(int i = 0; i < dims.length; i++) {
			long unopt = measuresUnoptimized.get(i);
			long optim = measuresOptimized.get(i);
			long par = measuresParallel.get(i);			
			System.out.printf("%4d|%15d     |%15d|%3.1f     |%15d|%3.1f     |%3.1f",
				dims[i], unopt, optim,(float)unopt/optim, par, (double) unopt/par, (double)optim/par);
			System.out.println();
		}
		
	}
	
	public static void main(String args[]) {
		MatParallel_3 parallel = new MatParallel_3();
		MatSeriell_3 optimized = new MatSeriell_3();
		MatrxMult unoptimized = new MatrxMult();

		Timing timing = new Timing(unoptimized, optimized, parallel);
		timing.printTable();
//		double[][] result = Arrays.stream(m1)
//				.map(r -> IntStream.range(0, m3[0].length)
//						.mapToDouble(i-> IntStream.range(0, m3.length)
//								.mapToDouble(j -> r[i] * m3[j][i]).sum())
//						.toArray())
//				.toArray(double[][]::new);

	}
}
