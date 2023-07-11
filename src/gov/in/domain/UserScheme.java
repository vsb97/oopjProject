package gov.in.domain;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter @Setter
public class UserScheme implements Serializable{
	
	private Scheme s;
	private LocalDate date;
	private boolean approved;
	
	@Override
	public String toString() {
		return s.toString()+"  "+String.format("%-10s%-5s", this.date,this.approved);
	}

}
