package com.amegdev.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.model.charts.pie.PieChartModel;

@Named
@ViewScoped
public class ReporteBean implements Serializable {

	private PieChartModel pieModel;

	public PieChartModel getPieModel() {
		return pieModel;
	}

	public void setPieModel(PieChartModel pieModel) {
		this.pieModel = pieModel;
	}
	
}
