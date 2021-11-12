package de.lab4inf.mxr.linearalgebra.tools;

public class MatrixCreator {

	public static double[] createRndVector(int n) {
		double[] x = new double[n];
		for (int i = 0; i < n; i++) {
			x[i] = Math.random() * 10;
		}
		return x;
	}

	public static double[][] createRndMatrix(int n, int m) {
		double[][] a = new double[n][m];
		for (int i = 0; i < n; i++) {
			a[i] = createRndVector(m);
		}
		return a;
	}
	
	public static double[][] createHilbertMatrix(int n) {
		double hilbert[][] = new double[n][n];

		for (int i = 1; i <= n ; i++) {
			for (int j = 1; j <= n ; j++) {
				hilbert[i - 1][j - 1] = 1.0 / (i + j - 1.0);
			}
		}
		return hilbert;
	}

	public static double fac(int n) {
		double fact = 1.0;
		for (int i = 2; i <= n; i++) {
			fact *= i;
		}
		return fact;
	}

	public static double[][] createInverseHilbertMatrix(int n) {
		double inverse[][] = new double[n][n];

		for (int i = 1; i <= n ; i++) {
			for (int j = 1; j <= n ; j++) {
				inverse[i - 1][j - 1] = (Math.pow(-1, i + j) * fac(i + n - 1) * fac(j + n - 1))
						/ (Math.pow(fac(i - 1), 2) * Math.pow(fac(j - 1), 2) * fac(n - j) * fac(n - i) * (i + j - 1));
			}
		}
		return inverse;
	}

	 public static double[][] createIdMatrix(int n) {
		double[][] idMAtrix = new double[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (i == j) {
					idMAtrix[i][j] = 1.0;
				} else {
					idMAtrix[i][j] = 0.0;
				}
			}
		}
		return idMAtrix;
	}

	 public static double[][] transpose(double[][] a) {
		double[][] transposed = new double[a[0].length][a.length];
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[0].length; j++) {
				transposed[j][i] = a[i][j];
			}
		}
		return transposed;
	}
}
