package gov.in.domain;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class Person implements Serializable{
	
	private String fName;
	private String lName;
	private String contactNo;
	private String email;
	private String address;
	
	@Override
	public String toString() {
		return String.format("%-12s%-15s%-11s%-30s%-12s", this.fName,this.lName,this.contactNo,this.email,this.address);
	}

}
