package ca.sheridancollege.khanmoam.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Mission {
	public Long id;
	public String agent;
	public String title;
	public String gadget1;
	public String gadget2;
}
