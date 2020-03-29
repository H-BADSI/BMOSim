package bmosim.hibernateDB;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "products")
public class DBproduct {
	
    @Id
    @GeneratedValue
	private int product_id;
    private String product_name;
	private int buy_price;
	private int sell_price;
	private int quality;
	private int time_of_life;
	@ManyToOne (cascade = CascadeType.ALL)
	private DBtype type;
	private int quantity;
	
	public DBproduct clone(){
		DBproduct twin = new DBproduct();
		twin.setProduct_id(product_id);
		twin.setBuy_price(buy_price);
		twin.setSell_price(sell_price);
		twin.setQuality(quality);
		twin.setTime_of_life(time_of_life);
		twin.setType(type);
		twin.setQuantity(quantity);
		return twin;
	}
	
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public int getBuy_price() {
		return buy_price;
	}
	public void setBuy_price(int buy_price) {
		this.buy_price = buy_price;
	}
	public int getSell_price() {
		return sell_price;
	}
	public void setSell_price(int sell_price) {
		this.sell_price = sell_price;
	}
	public int getQuality() {
		return quality;
	}
	public void setQuality(int quality) {
		this.quality = quality;
	}
	public int getTime_of_life() {
		return time_of_life;
	}
	public void setTime_of_life(int time) {
		this.time_of_life = time;
	}
	public DBtype getType() {
		return type;
	}
	public void setType(DBtype type) {
		this.type = type;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	

	
	
	
}
