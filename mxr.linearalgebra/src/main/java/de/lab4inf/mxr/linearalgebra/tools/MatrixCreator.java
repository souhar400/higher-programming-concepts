package de.lab4inf.mxr.linearalgebra.tools;

public class MatrixCreator {

	public static Double[] createRndVector(int n) {
		Double[] x = new Double[n];
		for (int i = 0; i < n; i++) {
			x[i] = Math.random() * 10;
		}
		return x;
	}

	public static Double[][] createRndMatrix(int n, int m) {
		Double[][] a = new Double[n][m];
		for (int i = 0; i < n; i++) {
			a[i] = createRndVector(m);
		}
		return a;
	}
	
	public static Double[][] createHilbertMatrix(int n) {
		Double hilbert[][] = new Double[n][n];

		for (int i = 1; i <= n ; i++) {
			for (int j = 1; j <= n ; j++) {
				hilbert[i - 1][j - 1] = 1.0 / (i + j - 1.0);
			}
		}
		return hilbert;
	}

	public static Double fac(int n) {
		Double fact = 1.0;
		for (int i = 2; i <= n; i++) {
			fact *= i;
		}
		return fact;
	}

	public static Double[][] createInverseHilbertMatrix(int n) {
		Double inverse[][] = new Double[n][n];

		for (int i = 1; i <= n ; i++) {
			for (int j = 1; j <= n ; j++) {
				inverse[i - 1][j - 1] = (Math.pow(-1, i + j) * fac(i + n - 1) * fac(j + n - 1))
						/ (Math.pow(fac(i - 1), 2) * Math.pow(fac(j - 1), 2) * fac(n - j) * fac(n - i) * (i + j - 1));
			}
		}
		return inverse;
	}

	 public static Double[][] createIdMatrix(int n) {
		Double[][] idMAtrix = new Double[n][n];
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

	 public static Double[][] transpose(Double[][] a) {
		Double[][] transposed = new Double[a[0].length][a.length];
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[0].length; j++) {
				transposed[j][i] = a[i][j];
			}
		}
		return transposed;
	}
}
