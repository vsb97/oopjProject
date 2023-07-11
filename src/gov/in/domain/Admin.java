package gov.in.domain;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class Admin extends Person implements Serializable{
	
	private int id;
	private String password;
	
	private static int tempId = 100; 
	
	public Admin(String fname, String lname, String contNo, String email, String add, String password) {
		super(fname, lname, contNo, email, add);
		this.id = ++tempId;
		this.password = password;
	}
		
	@Override
	public boolean equals(Object obj) {
		Admin a = (Admin) obj;
		return this.id == a.id;
	}
	
	@Override
	public String toString() {
		
//		return "Admin [id=" + id +super.toString() + "]";
		return (String.format("%-7d%-15s", this.id, this.password)+" "+super.toString()) ;
//		return (String.format("%-7d", this.id)+" "+super.toString()) ;
	}
	
	

}
