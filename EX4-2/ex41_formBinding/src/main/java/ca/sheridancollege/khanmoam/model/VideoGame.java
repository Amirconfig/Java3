package ca.sheridancollege.khanmoam.model;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class VideoGame {

	
	private Long id;
	private String title;
	private String publisher;
	private String platform;
	private String[] platforms = {"your", "favourite", "consoles!"};
	private BigDecimal price;

}
