package de.lab4inf.mxr.simple;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import de.lab4inf.mxr.core.Fact2D;
import de.lab4inf.mxr.core.FactVA;
import de.lab4inf.mxr.core.Mops;

public class AdderTester {

	final static String PROBLEM = Adder.PROBLEM;
	final static String VAR_PROBLEM = VarAdder.PROBLEM;

	Mops<String, Fact2D<Double, Double>, Double> myAdder;
	Mops<String, FactVA<Double>, Double> myVarDoubleAdder;

	@BeforeEach
	void setUp() throws Exception {
		myAdder = new Adder();
		myVarDoubleAdder = new VarAdder();
	}

	// Wrong Problem : ADD_ZAHLEN statt ADD_NUM
	@Test
	void testWrongProblem() {
		Fact2D<Double, Double> facts = Fact2D.fact(2.0, 1.0);
		assertThrows(IllegalArgumentException.class, () -> myAdder.solve("irgdwas", facts));
	}

	// Wrong Problem : ADD_ZAHLEN statt ADD_NUM
	@Test
	void testVarWrongProblem() {

		Random r = new Random();
		int laenge = r.nextInt(100);
		Double[] myArray = new Double[laenge];
		for (int i = 0; i < myArray.length; i++) {
			myArray[i] = r.nextDouble();
		}
		FactVA<Double> facts = FactVA.fact(myArray);
		assertThrows(IllegalArgumentException.class, () -> myVarDoubleAdder.solve("ADD_ZAHLEN", facts));
	}

	// Problem ist NP
	@Test
	void testNoProblem() {
		Fact2D<Double, Double> facts = Fact2D.fact(2.0, 1.0);
		assertThrows(NullPointerException.class, () -> myAdder.solve(null, facts));
	}

	// Facts sind NP
	@Test
	void testNoFacts() {
		Fact2D<Double, Double> facts = null;
		assertThrows(NullPointerException.class, () -> myAdder.solve(PROBLEM, facts));
	}

	// (Doubles positiv, DOuble positiv)
	@Test
	void testPositivDoubles() {
		Fact2D<Double, Double> facts = Fact2D.fact(1.0, 2.0);
		Double result = myAdder.solve(PROBLEM, facts);
		assertEquals(3.0, result);
	}

	// (Double positiv, Double negativ)
	@Test
	void testPositivNegativDoubles() {
		Fact2D<Double, Double> facts = Fact2D.fact(2.0, -1.0);
		Double result = myAdder.solve(PROBLEM, facts);
		assertEquals(1.0, result);
	}

	// (Double negativ, double negativ)
	@Test
	void testNegativValues() {
		Fact2D<Double, Double> facts = Fact2D.fact(-1.0, -2.0);
		Double result = myAdder.solve(PROBLEM, facts);
		assertEquals(-3.0, result);
	}

	// (RandomZahl , 0.0 )
	@Test
	void testZahlUndNull() {
		Random r = new Random();
		Double randomValue = r.nextDouble();

		Fact2D<Double, Double> facts = Fact2D.fact(randomValue, 0.0);
		Double actual = myAdder.solve(PROBLEM, facts);
		assertEquals(randomValue, actual);
	}

	// (Random , Opposite)
	@Test
	void testRandomAndOpposite() {
		Random r = new Random();
		Double randomValue = r.nextDouble();

		Fact2D<Double, Double> facts = Fact2D.fact(randomValue, -randomValue);
		Double actual = myAdder.solve(PROBLEM, facts);
		assertEquals(0.0, actual);
	}

	// (Random , Random)
	@Test
	void testRandomZweimal() {
		Random r = new Random();
		Double randomValue = r.nextDouble();

		Fact2D<Double, Double> facts = Fact2D.fact(randomValue, randomValue);
		Double actual = myAdder.solve(PROBLEM, facts);
		assertEquals(2 * randomValue, actual);
	}

	// ( Random1, Random2)
	@Test
	void testTwoRandom() {
		Random r = new Random();
		Double randomValue1 = r.nextDouble();
		Double randomValue2 = r.nextDouble();

		Fact2D<Double, Double> facts = Fact2D.fact(randomValue1, randomValue2);
		Double actual = myAdder.solve(PROBLEM, facts);
		assertEquals(randomValue1 + randomValue2, actual);
	}

	// Pas de moyen pour tester la somme => somme d'une série connue
	// Array random double : FactVA<Double>
	@Test
	void testDoubleArray() {
		Double expected = 0.0;

		Random r = new Random();
		int laenge = r.nextInt(100);
		Double[] myArray = new Double[laenge];

		for (int i = 0; i < myArray.length; i++) {
			myArray[i] = r.nextDouble();
			expected = expected + myArray[i];
		}

		FactVA<Double> facts = FactVA.fact(myArray);

		Double actual = myVarDoubleAdder.solve(VAR_PROBLEM, facts);
		assertEquals(expected, actual);
	}

	// ******************************************
	// Arithmetische Folge : Un=n : FactVA<Double>
	@Test
	void testIntegerFolge() {
		Random r = new Random();
		int laenge = r.nextInt(100);
		//int laenge = 5;
		Double[] myArray = new Double[laenge];

		for (int i = 0; i < laenge; i++) {
			myArray[i] = Double.valueOf(i);
		}

		// Partielle Summe einer arithmetischer Folge: S=n*(U0+Un)/2;
		Double expected = Double.valueOf(((laenge - 1) * laenge) / 2);

		FactVA<Double> facts = FactVA.fact(myArray);

		Double actual = myVarDoubleAdder.solve(VAR_PROBLEM, facts);
		assertEquals(expected, actual);
	}
	

	// Große Zahlen ohne Toleranz
	@Test
	void testGroßeZahlen() {

		int n = 5;
		Double zahl1 = Math.pow(10.0, n) + 1.0 / 3.0;
		Double zahl2 = -Math.pow(10.0, n) - 2.0 / 5.0;
		Double expected = -1.0 / 15.0;

		Fact2D<Double, Double> facts = Fact2D.fact(zahl1, zahl2);

		Double actual = myAdder.solve(PROBLEM, facts);
		assertNotEquals(expected, actual);
	}
	
	

	// Große Zahlen mit Toleranz
	@Test
	void testGroßeZahlen_tol() {

		int n = 5;
		Double zahl1 = Math.pow(10.0, n) + 1.0 / 3.0;
		Double zahl2 = -Math.pow(10.0, n) - 2.0 / 5.0;
		Double expected = -1.0 / 15.0;

		Fact2D<Double, Double> facts = Fact2D.fact(zahl1, zahl2);

		Double actual = myAdder.solve(PROBLEM, facts);
		assertEquals(expected, actual, 1E-12);
	}

}
