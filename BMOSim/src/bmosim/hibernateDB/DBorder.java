package bmosim.hibernateDB;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import bmosim.model.OrderState;

@Entity
@Table(name = "orders")
public class DBorder {

	@Id
	@GeneratedValue
	private int order_id;
	
	@ManyToOne
	@JoinColumn (name = "customer_address_hashCode")
	private DBcustomer customer_id;
	@ManyToOne (cascade = CascadeType.ALL)
	private DBoffer offer_id;
	@Enumerated(EnumType.STRING)
	private OrderState status;
	
	
	@Transient
	private int quantity;
	@Transient
	private Date order_date;
	@Transient
	private Date agreed_date;
	@Transient
	private Date shipping_date;
	@Transient
	private Date delivery_date;

	public DBorder clone(){
		DBorder twin = new DBorder();
		twin.setOrder_id(order_id);
		twin.setCustomer_id(customer_id);
		twin.setOffer_id(offer_id);
		twin.setStatus(status);
//		twin.setQuantity(quantity);
//		twin.setOrder_date(order_date);
//		twin.setAgreed_date(agreed_date);
//		twin.setShipping_date(shipping_date);
//		twin.setDelivery_date(delivery_date);
		return twin;
	}
	
	public int getOrder_id() {
		return order_id;
	}
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	public DBcustomer getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(DBcustomer customer_id) {
		this.customer_id = customer_id;
	}
	public DBoffer getOffer_id() {
		return offer_id;
	}
	public void setOffer_id(DBoffer offer_id) {
		this.offer_id = offer_id;
	}

	public OrderState getStatus() {
		return status;
	}
	public void setStatus(OrderState status) {
		this.status = status;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Date getOrder_date() {
		return order_date;
	}
	public void setOrder_date(Date order_date) {
		this.order_date = order_date;
	}
	public Date getAgreed_date() {
		return agreed_date;
	}
	public void setAgreed_date(Date agreed_date) {
		this.agreed_date = agreed_date;
	}
	public Date getShipping_date() {
		return shipping_date;
	}
	public void setShipping_date(Date shipping_date) {
		this.shipping_date = shipping_date;
	}
	public Date getDelivery_date() {
		return delivery_date;
	}
	public void setDelivery_date(Date delivery_date) {
		this.delivery_date = delivery_date;
	}
	
	
	
	
}
