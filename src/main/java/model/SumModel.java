package model;

public class SumModel {

	private double sum;

	public double getSum() {
		return sum;
	}

	public void setSum(double sum) {
		this.sum = sum;
	}

	public SumModel(double sum) {
		super();
		this.sum = sum;
	}

	public SumModel() {
		super();
	}
	
	public void addAmount(double amount){
		this.sum += amount;
	}
}
