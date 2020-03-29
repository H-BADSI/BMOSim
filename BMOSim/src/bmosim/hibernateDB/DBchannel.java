package bmosim.hibernateDB;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table( name = "channels")
public class DBchannel {

	@Id
	private int channel_id;

//	private String channel_name;
	
	public DBchannel clone(){
		DBchannel twin = new DBchannel();
		twin.setChannel_id(channel_id);
		return twin;
	}
	
	public int getChannel_id() {
		return channel_id;
	}
	public void setChannel_id(int channel_id) {
		this.channel_id = channel_id;
	}
/*	public String getChannel_name() {
		return channel_name;
	}
	public void setChannel_name(String channel_name) {
		this.channel_name = channel_name;
	}
*/
}
