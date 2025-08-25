package tech.nilvalue.portal.Repository;

import jakarta.validation.constraints.Email;
import org.springframework.data.domain.Limit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppealRepository extends CrudRepository<Appeal, String> {

    List<Appeal> searchAppealsByEmail( @Email String email );

    List<Appeal> searchAppealsByIpAddress( String ipAddress );
}
