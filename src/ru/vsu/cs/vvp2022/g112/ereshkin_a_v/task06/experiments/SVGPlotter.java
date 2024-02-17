package ru.vsu.cs.vvp2022.g112.ereshkin_a_v.task06.experiments;

import java.io.PrintStream;
import java.util.List;
import java.util.Locale;

public class SVGPlotter {
	private static final Locale LC = Locale.US;
	private static class SvgUtils {
		public static final String xmlProlog = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>";

		public static void writeProlog(PrintStream out) {
			out.println(xmlProlog);
		}

		public static void drawPolyline(PrintStream out, String color, double width, Chart c) {
			out.printf(LC, "<polyline fill=\"none\" stroke=\"%s\" stroke-width=\"%f\" points=\"", color, width);
			for (int i = 0; i < c.size(); i++) {
				Chart.Point p = c.get(i);
				out.printf(LC, "%f,%f", p.x(), p.y());
			}
			out.println("\"/>\n");
		}

		private static void drawLine(PrintStream out, double x1, double y1, double x2, double y2, String color, double width) {
			out.printf(Locale.US, "<line x1=\"%f\" y1=\"%f\" " +
					"x2=\"%f\" y2=\"%f\" stroke=\"%s\" stroke-width=\"%f\" />%n",
					x1, y1, x2, y2, color, width);
		}
	}

	public record Bounds(double left, double right, double top, double bottom) {
		public double getWidth() {
			return Math.abs(left() - right());
		}

		public double getHeight() {
			return Math.abs(top() - bottom());
		}
	}

	public static void plot(PrintStream out, List<ChartDescription> charts, Bounds bounds) {

		SvgUtils.writeProlog(out);
		out.printf(LC, "<svg xmlns=\"http://www.w3.org/2000/svg\" version=\"1.1\" " +
				"viewBox=\"%f %f %f %f\" width=\"%f\" height=\"%f\">\n",
				bounds.left(), bounds.top(), bounds.getWidth(), bounds.getHeight(),
				bounds.getWidth() * 400, bounds.getHeight() * 400);
		double axisWidth = Math.min(bounds.getWidth(), bounds.getHeight()) * 0.001;
		SvgUtils.drawLine(out, 0, bounds.top(), 0, bounds.bottom(), "black", axisWidth);
		SvgUtils.drawLine(out, bounds.left(), 0, bounds.right(), 0, "black", axisWidth);
		for (ChartDescription cd : charts) {
			Chart c = cd.chart();
			SvgUtils.drawPolyline(out, cd.color(), cd.lineWidth(), c);
		}
		out.println("</svg>");
	}
}
