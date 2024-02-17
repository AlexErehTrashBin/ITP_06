package ru.vsu.cs.vvp2022.g112.ereshkin_a_v.task06.experiments;

import java.util.ArrayList;
import java.util.List;

public class Chart {
	public record Point(double x, double y){ }
	private final List<Point> points = new ArrayList<>();
	public void add(double x, double y){
		points.add(new Point(x, y));
	}
	public void add(Point p){
		add(p.x(), p.y());
	}

	public int size(){
		return points.size();
	}
	public Point get(int index){
		return points.get(index);
	}
}
