package empapp.entity;

import org.hibernate.envers.RevisionListener;

import java.time.LocalDateTime;
import java.util.UUID;

public class EmployeesRevisionEntityListener implements RevisionListener {

    @Override
    public void newRevision(Object o) {
        if (o instanceof EmployeesRevisionEntity e) {
            e.setUsername("user " + LocalDateTime.now());
            e.setRandom(UUID.randomUUID().toString());
        }
    }
}
