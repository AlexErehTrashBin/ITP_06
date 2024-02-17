package ru.vsu.cs.vvp2022.g112.ereshkin_a_v.task06;

import java.io.PrintStream;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		/* Input part */
		PrintStream out = System.out;
		Scanner scanner = new Scanner(System.in);
		out.print("Введите x: ");
		double x = scanner.nextDouble();
		out.print("Введите n: ");
		int n = scanner.nextInt();
		out.print("Введите эпсилон: ");
		double eps = scanner.nextDouble();

		/* Computation part */
		double ideal = Solution.idealFraction(x);

		double resultN = Solution.fractionN(x, n);
		double deltaN = Math.abs(resultN - ideal);

		Result rE = Solution.fractionE(x, eps);
		double deltaE = Math.abs(rE.sum() - ideal);

		Result rE10 = Solution.fractionE(x, eps * 0.1);
		double deltaE10 = Math.abs(rE10.sum() - ideal);

		/* Output part */
		out.printf("Ideal      : %.15f \n", ideal);
		out.printf("fractionN  : %.15f; delta = %e \n", resultN, deltaN);
		out.printf("fractionE  : %.15f; delta = %e; count = %d \n", rE.sum(), deltaE, rE.count());
		out.printf("fractionE10: %.15f; delta = %e; count = %d \n", rE10.sum(), deltaE10, rE10.count());
	}
}