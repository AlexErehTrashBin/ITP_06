package ru.vsu.cs.vvp2022.g112.ereshkin_a_v.task06.experiments;

import ru.vsu.cs.vvp2022.g112.ereshkin_a_v.task06.Solution;

public class Calculator {
	public static Chart getIdeal(double from, double to, int count) {
		Chart c = new Chart();
		double step = (to - from) / (count - 1);
		for (int i = 0; i < count; i++) {
			double x = from + i * step;
			c.add(x, Solution.idealFraction(x));
		}
		return c;
	}
	public static Chart getForN(double from, double to, int count, int n){
		Chart c = new Chart();
		double step = (to - from) / (count - 1);
		for (int i = 0; i < count; i++) {
			double x = from + i * step;
			c.add(x, Solution.fractionN(x, n));
		}
		return c;
	}
	public static Chart getDeltaForE(double from, double to, int count, double eps){
		Chart c = new Chart();
		double step = (to - from) / (count - 1);
		for (int i = 0; i < count; i++) {
			double x = from + i * step;
			c.add(x, 1E12 * Math.abs(Solution.fractionE(x, eps).sum() - Solution.idealFraction(x)));
		}
		return c;
	}
	public static Chart getForE(double from, double to, int count, double eps){
		Chart c = new Chart();
		double step = (to - from) / (count - 1);
		for (int i = 0; i < count; i++) {
			double x = from + i * step;
			c.add(x, Solution.fractionE(x, eps).sum());
		}
		return c;
	}
	public static Chart getNForE(double from, double to, int count, double eps){
		Chart c = new Chart();
		double step = (to - from) / (count - 1);
		for (int i = 0; i < count; i++) {
			double x = from + i * step;
			c.add(x, Solution.fractionE(x, eps).count());
		}
		return c;
	}
}
