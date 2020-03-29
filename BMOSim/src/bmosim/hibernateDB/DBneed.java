package bmosim.hibernateDB;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "needs")
public class DBneed {

	@Id
	@GeneratedValue
	private int need_id;
	private int customer_id;
	private int price;
	private int quality;
	private int time;
	private Integer type;
	private boolean treated;
	private int score = 0;
	
	public DBneed clone(){
		DBneed twin = new DBneed();
		twin.setNeed_id(need_id);
		twin.setCustomer_id(customer_id);
		twin.setPrice(price);
		twin.setQuality(quality);
		twin.setTime(time);
		twin.setType(type);
		twin.setTreated(treated);
		twin.setScore(score);
		return twin;
	}
	
	public int getNeed_id() {
		return need_id;
	}
	public void setNeed_id(int need_id) {
		this.need_id = need_id;
	}
	public int getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getQuality() {
		return quality;
	}
	public void setQuality(int quality) {
		this.quality = quality;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public boolean isTreated() {
		return treated;
	}
	public void setTreated(boolean treated) {
		this.treated = treated;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	
	
}
