package model;

public class SandVO {
	private String name;	//재료이름
	private int num;	//pk
	private int price;	//가격
	private int stock;	//재고
	private int cnt;
	public SandVO() {
	}
	public SandVO(String name, int num, int price, int stock) {
		// TODO Auto-generated constructor stub
		this(name,num,price,stock,0);
	}
	public SandVO(String name, int num, int price, int stock , int cnt) {
		// TODO Auto-generated constructor stub
		this.name= name;
		this.num=num;
		this.price=price;
		this.stock=stock;
		this.cnt=cnt;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	
	@Override
	public String toString() {
		return "IngredientsVO [name=" + name + ", num=" + num + ", price=" + price + ", stock=" + stock + "]";
	}
}