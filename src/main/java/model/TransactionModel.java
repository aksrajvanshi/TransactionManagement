package model;

public class TransactionModel {

	private double amount;
	private String type;
	private long parent_id;
	
	public TransactionModel(double amount, String type) {
		super();
		this.amount = amount;
		this.type = type;
	}
	
	public TransactionModel() {
		super();
	}

	public TransactionModel(double amount, String type, long parent_id) {
		super();
		this.amount = amount;
		this.type = type;
		this.parent_id = parent_id;
	}

	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public long getParent_id() {
		return parent_id;
	}
	public void setParent_id(long parent_id) {
		this.parent_id = parent_id;
	}
	
	
}
