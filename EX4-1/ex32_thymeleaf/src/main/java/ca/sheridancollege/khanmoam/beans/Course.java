package ca.sheridancollege.khanmoam.beans;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Course {

	private Long id;
	private String prefix;
	private String code;
	private String name;

}
