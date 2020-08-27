package bmosim.exchange.objects;

import bmosim.hibernateDB.DBproduct;
import bmosim.model.OrderState;

public class report {
    public int customerID;
    public String cusSegment;
    public int orderID;
    public int offerID;
    public DBproduct product;
    public OrderState status;
    public int quantity;

    @Override
    public String toString() {
        return "report "+customerID+" "+orderID+" "+offerID+" "+quantity+" segment ="+cusSegment;
    }
}
