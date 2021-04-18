package com.YaroslavGorbach.delusionalgenerator.screen.chartView.draw;

import android.content.Context;
import android.graphics.Canvas;

import androidx.annotation.NonNull;

import com.YaroslavGorbach.delusionalgenerator.screen.chartView.animation.data.AnimationValue;
import com.YaroslavGorbach.delusionalgenerator.screen.chartView.data.Chart;

public class DrawManager {

	private final DrawController controller;
	private final Chart chart;

	public DrawManager(@NonNull Context context) {
		chart = new Chart();
		controller = new DrawController(context, chart);
	}

	public Chart chart() {
		return chart;
	}

	public void updateTitleWidth() {
		controller.updateTitleWidth();
	}

	public void draw(@NonNull Canvas canvas) {
		controller.draw(canvas);
	}

	public void updateValue(@NonNull AnimationValue value) {
		controller.updateValue(value);
	}
}
