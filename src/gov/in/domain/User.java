package gov.in.domain;

import java.io.Serializable;
import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class User  extends Person implements Serializable{
	
	private int id;
    private int age;
    private String category;
    private ArrayList<UserScheme> schemes;
    
    @Override
    public boolean equals(Object obj) {
    	User u = (User)obj;
    	return this.id==u.id;
    }
    
    public void setUserSchemList(ArrayList<UserScheme> list) {
    	this.schemes=list;
    }
    
	@Override
	public String toString() {
		return String.format("%-3d", this.id)+" "+super.toString()+"  "+String.format("%-6d%-10s", this.age,this.category)+" "+this.schemes.toString();
//		return String.format("%-3d", this.id)+" "+super.toString()+"  "+String.format("%-6d%-10s", this.age,this.category)+" "+this.schemes.toString();

	}
    
    
}
