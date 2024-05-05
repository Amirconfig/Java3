package ca.sheridancollege.khanmoam.database;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import ca.sheridancollege.khanmoam.beans.Appointment;

@Component
public class DatabaseAccess {
	@Autowired
	protected NamedParameterJdbcTemplate jdbctemp;

    // Insert an Appointment into the database
    public void insertAppointment() {
        String query = "INSERT INTO appointment (firstName, email, appointmentDate, appointmentTime) " +
                     "VALUES ('Amir H', 'A_khanmohammadi81@yahoo.com', '2023-04-08', '12:30:00')";
		int i = jdbctemp.update(query, new HashMap<>());
		if( i > 0)
			System.out.println("1 row is inserted in the database");
    }
    
    public void insertAppointmentParameters (String pFirstName, String pEmail, LocalDate pAppointmentDate, LocalTime pAppointmentTime) {
        
    	MapSqlParameterSource parameters = new MapSqlParameterSource();
        String query = "INSERT INTO appointment (firstName, email, appointmentDate, appointmentTime) " +
                "VALUES (:firstName, :email, :appointmentDate, :appointmentTime)";
        
        parameters.addValue("pFirstName", pFirstName);
        parameters.addValue("pEmail", pEmail);
        parameters.addValue("pAppointmentDate", pAppointmentDate);
        parameters.addValue("pAppointmentTime", pAppointmentTime);

		int i = jdbctemp.update(query, parameters);
		if( i > 0)
			System.out.println("1 row is inserted in the database");    
	}
    
    
	//function to accept data as object
	public void insertAppointmentObject(Appointment p) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
        String query = "INSERT INTO appointment (firstName, email, appointmentDate, appointmentTime) " +
                "VALUES (:firstName, :email, :appointmentDate, :appointmentTime)";
		//System.out.println(query);
        parameters.addValue("pFirstName", p.getFirstName());
        parameters.addValue("pEmail", p.getEmail());
        parameters.addValue("pAppointmentDate", p.getAppointmentDate());
        parameters.addValue("pAppointmentTime", p.getAppointmentTime());
		//System.out.println(query);
		int i = jdbctemp.update(query, parameters);
		if( i > 0)
			System.out.println("1 row is inserted in the database");
	}
	

    public List<Appointment> getAppointments() {
        String query = "SELECT * FROM appointment";
        return jdbctemp.query(query, new BeanPropertyRowMapper<Appointment>(Appointment.class));
    }

    public void deleteAppointment(Long id) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String q = "Delete from appointment where id = :id";
		parameters.addValue("id", id);
		int i = jdbctemp.update(q, parameters);
		if( i > 0)
			System.out.println("1 row is inserted in the database");
    }

    
    public Appointment getAppointmentById(Long id) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String q = "Select * from appointment where id = :id";
		parameters.addValue("id", id);
		List<Appointment> p = jdbctemp.query(q, parameters, new BeanPropertyRowMapper<Appointment>(Appointment.class));
		if(p.isEmpty())
			return null;
		else
			return p.get(0);
    }

    public int updateAppointment(Appointment p) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        String q = "update appointment set firstName=:pFirstName, email=:pEmail, appointmentDate=:pAppointmentDate, appointmentTime=:pAppointmentTime where id=:id";
        parameters.addValue("id", p.getId());
        parameters.addValue("pFirstName", p.getFirstName());
        parameters.addValue("pEmail", p.getEmail());
        parameters.addValue("pAppointmentDate", p.getAppointmentDate());
        parameters.addValue("pAppointmentTime", p.getAppointmentTime());
        
        return jdbctemp.update(q, parameters);		
    }

	
	
}
