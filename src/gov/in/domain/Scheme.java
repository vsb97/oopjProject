package gov.in.domain;

import java.io.Serializable;
import java.util.Arrays;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class Scheme implements Serializable, Comparable{
	
	private String id;
	private String name;
	private int sAge;
	private int eAge;
	private String category[] = new String[5];
	private String details;
	
	@Override
	public boolean equals(Object obj) {
		Scheme s = (Scheme) obj;
		return this.id.equals(s.id);
	}

	@Override
	public String toString() {
		return String.format("%-10s%-30s%-6d%-6d%-20s%-50s", this.id,this.name,this.sAge,this.eAge,this.getCategoryStr(),this.details);
	}

	private String getCategoryStr() {
		String str="{";
		for(String s: this.category) {
			if(s!=null)
				str+=s+" ";
		}
		str+="}";
		return str;
	}

	@Override
	public int compareTo(Object o) {
		Scheme s = (Scheme) o;
		return this.name.compareTo(s.name);
	}
	
	
}
