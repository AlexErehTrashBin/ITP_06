package ru.vsu.cs.vvp2022.g112.ereshkin_a_v.task06;

public class Solution {
	public static final double COMPARATOR_EPS = 1E-30;
	public static double idealFraction(double x) {
		double z = 1 + x;
		return 1 / (z*z*z);
	}

	public static double fractionN(double x, int n) {
		double sum = 1.0;
		double term = 1.0;
		for (int i = 0; i < n; i++) {
			term *= -x * (i + 3) / (i + 1);
			sum += term;
		}
		return sum;
	}

	public static Result fractionE(double x, double eps) {
		double sum = 1.0;
		double term = 1.0;
		int i = 0;
		// COMPARATOR_EPS - условный ноль (для сравнения двух чисел типа double)
		while (Math.abs(term) >= eps) {
			term *= -x * (i + 3) / (i + 1);
			sum += term;
			i++;
		}
		return new Result(sum, i - 1);
	}

}
