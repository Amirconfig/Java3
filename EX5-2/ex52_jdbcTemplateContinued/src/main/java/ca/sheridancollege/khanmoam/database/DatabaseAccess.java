package ca.sheridancollege.khanmoam.database;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.khanmoam.models.Student;

@Repository
public class DatabaseAccess {
	
	@Autowired
	NamedParameterJdbcTemplate jdbctemp;
	
	public void insertProduct() {
		String query = "insert into product(name,studentID,programme,GPA) " 
				+ "values ('Amir', 991646689, 'computer programming', 3.89)";
		
		int i = jdbctemp.update(query, new HashMap<>());
		if(i>0)
			System.out.println("1 row is inserted in the database");
		
		String query1 = "insert into product(name,studentID,programme,GPA) " 
				+ "values ('Ben', 991647690, 'Photography', 3.75)";
		
		int j = jdbctemp.update(query1, new HashMap<>());
		if(j>0)
			System.out.println("1 row is inserted in the database");
		
		String query2 = "insert into product(name,studentID,programme,GPA) " 
				+ "values ('Emma', 991656832, 'Animation', 3.90)";
		
		int k = jdbctemp.update(query2, new HashMap<>());
		if(k>0)
			System.out.println("1 row is inserted in the database");
		
		String query3 = "insert into product(name,studentID,programme,GPA) " 
				+ "values ('Ava', 991636741, 'Cyber Security', 3.79)";
		
		int l = jdbctemp.update(query3, new HashMap<>());
		if(l>0)
			System.out.println("1 row is inserted in the database");
	}
	
	public List<Student> getProducts() {
		//List<Product> pList = new ArrayList<>();
		String query="Select * from Product";
		return jdbctemp.query(query, new BeanPropertyRowMapper<Student>(Student.class));
	}

}
