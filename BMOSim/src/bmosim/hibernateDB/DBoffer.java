package bmosim.hibernateDB;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "offers")
public class DBoffer {
	
	@Id
	@GeneratedValue	
	private int offer_id ;
	
	@ManyToOne (cascade = CascadeType.ALL)
	private DBproduct product_id;
	
	private int quantity;
	private int price;
	private int time;
	
	private boolean damage = false;
	private int failureRate = 0;
	private Date limitDate;
	
	// Customer Criteria
	@Transient
	private int cus_id;
	@Transient
	private int cus_budget_min;
	@Transient
	private int cus_budget_max;
	@Transient
	private int cus_age_min;
	@Transient
	private int cus_age_max;
	@Transient
	private int cus_time;
	@Transient
	private int cus_area;
	@Transient
	private int cus_channel;
	@Transient
	private int cus_nb_buy;
	@Transient
	private int cus_sum_buy;
	@Transient
	private int cus_score;

public DBoffer clone(){
	DBoffer twin = new DBoffer();
	twin.setOffer_id(offer_id);
	twin.setProduct_id(product_id);
	twin.setQuantity(quantity);
	twin.setPrice(price);
	twin.setTime(time);
	twin.setDamage(damage);
	twin.setFailureRate(failureRate);
	twin.setLimitDate(limitDate);
//	twin.setCus_id(cus_id);
//	twin.setCus_budget_min(cus_budget_min);
//	twin.setCus_budget_max(cus_budget_max);
//	twin.setCus_age_min(cus_age_min);
//	twin.setCus_age_max(cus_age_max);
//	twin.setCus_time(cus_time);
//	twin.setCus_area(cus_area);
//	twin.setCus_channel(cus_channel);
//	twin.setCus_nb_buy(cus_nb_buy);
//	twin.setCus_sum_buy(cus_sum_buy);
//	twin.setCus_score(cus_score);
	return twin;
}
	
public int getOffer_id() {
	return offer_id;
}
public void setOffer_id(int number) {
	this.offer_id = number;
}

public DBproduct getProduct_id() {
	return product_id;
}
public void setProduct_id(DBproduct product_id) {
	this.product_id = product_id;
}
public int getQuantity() {
	return quantity;
}
public void setQuantity(int quantity) {
	this.quantity = quantity;
}
public int getPrice() {
	return price;
}
public void setPrice(int price) {
	this.price = price;
}
public int getTime() {
	return time;
}
public void setTime(int time) {
	this.time = time;
}
public boolean getDamage() {
	return damage;
}
public void setDamage(boolean damage) {
	this.damage = damage;
}
public int getFailureRate() {
	return failureRate;
}
public void setFailureRate(int failureRate) {
	this.failureRate = failureRate;
}
public Date getLimitDate() {
	return limitDate;
}
public void setLimitDate(Date limitDate) {
	this.limitDate = limitDate;
}


}
