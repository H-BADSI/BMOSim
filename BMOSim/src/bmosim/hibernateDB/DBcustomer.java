package bmosim.hibernateDB;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import madkit.kernel.AgentAddress;


@Entity
@Table(name = "customers")
public class DBcustomer {
	@Id
	private int customer_address_hashCode;
	@Column(columnDefinition = "LONGBLOB")
	private AgentAddress customer_address;
	private int budget;
	private int age;
	private int quality;
	private int time;
	@Transient
	private String area;
	@ManyToMany (cascade = CascadeType.ALL)
	private List<DBtype> types;
	@ManyToMany (cascade = CascadeType.ALL)
	private List<DBchannel> channels;
	@OneToMany (cascade = CascadeType.ALL)
	private List<DBneed> needs;
	
	private int nb_buy;
	private int sum_buy;
	private int score;
	
	public DBcustomer clone(){
		DBcustomer twin = new DBcustomer();
		twin.setCustomer_address_hashCode(customer_address_hashCode);
		twin.setCustomer_address(customer_address);
		twin.setBudget(budget);
		twin.setAge(age);
		twin.setQuality(quality);
		twin.setTime(time);
		twin.setArea(area);
		twin.setTypes(new ArrayList<DBtype>(types));
		twin.setChannels(new ArrayList<DBchannel>(channels));
		twin.setNeeds(new ArrayList<DBneed>(needs));
		twin.setNb_buy(nb_buy);
		twin.setSum_buy(sum_buy);
		twin.setScore(score);
		
		return twin;
	}
	
	public int getCustomer_address_hashCode() {
		return customer_address_hashCode;
	}
	public void setCustomer_address_hashCode(int customer_address_hashCode) {
		this.customer_address_hashCode = customer_address_hashCode;
	}
	public AgentAddress getCustomer_address() {
		return customer_address;
	}
	public void setCustomer_address(AgentAddress customer_address) {
		this.customer_address = customer_address;
	}
	public int getBudget() {
		return budget;
	}
	public void setBudget(int budget) {
		this.budget = budget;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
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
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public List<DBtype> getTypes() {
		return types;
	}
	public void setTypes(List<DBtype> types) {
		this.types = types;
	}
	public List<DBchannel> getChannels() {
		return channels;
	}
	public void setChannels(List<DBchannel> channels) {
		this.channels = channels;
	}
	public List<DBneed> getNeeds() {
		return needs;
	}
	public void setNeeds(List<DBneed> needs) {
		this.needs = needs;
	}
	public int getNb_buy() {
		return nb_buy;
	}
	public void setNb_buy(int nb_buy) {
		this.nb_buy = nb_buy;
	}
	public int getSum_buy() {
		return sum_buy;
	}
	public void setSum_buy(int sum_buy) {
		this.sum_buy = sum_buy;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	
	
	
	
}
