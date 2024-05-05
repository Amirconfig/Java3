package ca.sheridancollege.khanmoam.database;

import ca.sheridancollege.khanmoam.beans.Mission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class DatabaseAccess {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public void insertMission(Mission mission) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("agent", mission.getAgent());
        parameters.addValue("title", mission.getTitle());
        parameters.addValue("gadget1", mission.getGadget1());
        parameters.addValue("gadget2", mission.getGadget2());

        String query = "INSERT INTO missions(agent, title, gadget1, gadget2) VALUES (:agent, :title, :gadget1, :gadget2)";
        jdbcTemplate.update(query, parameters);
    }

    public List<Mission> getMissionByAgentName(String agentName) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("agent", agentName);

        String query = "SELECT * FROM MISSIONS WHERE agent = :agent";
        return jdbcTemplate.query(query, parameters, new BeanPropertyRowMapper<>(Mission.class));
    }

    public Mission getMissionById(Long id) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("id", id);

        String query = "SELECT * FROM MISSIONS WHERE id = :id";
        List<Mission> missions = jdbcTemplate.query(query, parameters, new BeanPropertyRowMapper<>(Mission.class));

        return missions.isEmpty() ? null : missions.get(0);
    }

    public void deleteMissionById(Long id) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("id", id);

        String query = "DELETE FROM MISSIONS WHERE id = :id";
        jdbcTemplate.update(query, parameters);
    }

    public int updateMission(Mission mission) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("id", mission.getId());
        parameters.addValue("title", mission.getTitle());
        parameters.addValue("gadget1", mission.getGadget1());
        parameters.addValue("gadget2", mission.getGadget2());

        String query = "UPDATE Missions SET title = :title, gadget1 = :gadget1, gadget2 = :gadget2 WHERE id = :id";
        return jdbcTemplate.update(query, parameters);
    }
}
