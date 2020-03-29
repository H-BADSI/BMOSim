package bmosim.hibernateDB;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "types")
public class DBtype {

	@Id
	private Integer obj_type;
//	private String type_name;
	
	public DBtype clone(){
		DBtype twin = new DBtype();
		twin.setObj_type(obj_type);
		return twin;
	}
	
	public int getObj_type() {
		return obj_type;
	}
	public void setObj_type(int obj_type) {
		this.obj_type = obj_type;
	}
/*	public String getType_name() {
		return type_name;
	}
	public void setType_name(String type_name) {
		this.type_name = type_name;
	}
*/	
}
