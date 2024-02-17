package ru.vsu.cs.vvp2022.g112.ereshkin_a_v.task06.experiments;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import ru.vsu.cs.vvp2022.g112.ereshkin_a_v.task06.experiments.SVGPlotter.*;

public class Main4Experiments {
	public static void drawIdeal(ChartDescription ideal, Bounds bounds){
		try {
			SVGPlotter.plot(new PrintStream("./output_images/ideal.svg"), List.of(ideal), bounds);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	public static void checkForVariousE(int nMax, double left, double right, int pointsAmount, ChartDescription ideal, Bounds bounds){
		try {
			double eps = 1;
			for (int i = 0; i <= nMax; i++) {
				eps *= 0.1;
				ChartDescription x = new ChartDescription(
						Calculator.getForE(left, right, pointsAmount, eps),
						"pink", 0.02
				);
				String path = String.format("./output_images/e_changes/actual_%2d.svg", i);
				SVGPlotter.plot(new PrintStream(path), Arrays.asList(ideal, x), bounds);
			}
		} catch (FileNotFoundException e){
			e.printStackTrace();
		}
	}
	public static void checkNForVariousE(int nMax, double left, double right, int pointsAmount, ChartDescription ideal, Bounds bounds){
		try {
			double eps = 1;
			for (int i = 0; i <= nMax; i++) {
				eps *= 0.1;
				ChartDescription x = new ChartDescription(
						Calculator.getNForE(left, right, pointsAmount, eps),
						"violet", 0.03
				);
				String path = String.format("./output_images/n_for_e/actual_%2d.svg", i);
				SVGPlotter.plot(new PrintStream(path), List.of(/*ideal,*/ x), bounds);
			}
		} catch (FileNotFoundException e){
			e.printStackTrace();
		}
	}
	public static void checkForVariousN(int nMax, double left, double right, int pointsAmount, ChartDescription ideal, Bounds bounds){
		try {
			for (int i = 0; i <= nMax; i++) {
				ChartDescription x = new ChartDescription(
						Calculator.getForN(left, right, pointsAmount, i),
						"blue", 0.02
				);
				String path = String.format("./output_images/n_changes/actual_%2d.svg", i);
				SVGPlotter.plot(new PrintStream(path), Arrays.asList(ideal, x), bounds);
			}
		} catch (FileNotFoundException e){
			e.printStackTrace();
		}
	}
	public static void checkDeltaForVariousE(int nMax, double left, double right, int pointsAmount, ChartDescription ideal, Bounds bounds){
		try {
			double eps = 1;
			for (int i = 0; i <= nMax; i++) {
				eps *= 0.1;
				ChartDescription x = new ChartDescription(
						Calculator.getDeltaForE(left, right, pointsAmount, eps),
						"green", 0.02
				);
				String path = String.format("./output_images/e_delta/actual_%2d.svg", i);
				SVGPlotter.plot(new PrintStream(path), List.of(/*ideal,*/ x), bounds);
			}
		} catch (FileNotFoundException e){
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		final double left = -0.999999;
		final double right = 0.999999;
		final int pointsAmount = 500;
		final Bounds bounds = new Bounds(-1, 1, -0.5, 2);
		Bounds boundsForNVary = new Bounds(-1, 1, -0.5, 30);
		final Chart idealChart = Calculator.getIdeal(left, right, pointsAmount);
		ChartDescription ideal = new ChartDescription(idealChart, "red", 0.02);

		final int N = 20;
		final int EPS_COUNT = 7;
		/* SVG generation part */
		System.out.println("Начинаю генерацию SVG...");
		checkForVariousN(N, left, right, pointsAmount, ideal, bounds);
		System.out.println("Сгенерированы файлы для различного количества слагаемых");
		checkForVariousE(EPS_COUNT, left, right, pointsAmount, ideal, bounds);
		System.out.println("Сгенерированы файлы для различных Е");
		checkDeltaForVariousE(N, left, right, pointsAmount, ideal, bounds);
		System.out.println("Сгенерированы файлы показывающие delta для различных ЕPS");
		checkNForVariousE(N, left, right, pointsAmount, ideal, boundsForNVary);
		System.out.println("Сгенерированы файлы показывающие количество слагаемых для различных ЕPS");
		drawIdeal(ideal, bounds);
	}
}
