package tech.nilvalue.portal.Service;

import jakarta.servlet.http.HttpServletRequest;
import tech.nilvalue.portal.Repository.Appeal;


import java.util.List;

public interface AppealService {
    Appeal create(Appeal appeal, HttpServletRequest request);
    Appeal update(Long id, String email, Appeal appeal);
    Appeal delete(Long id, String email);
    Appeal findById(Long id, String email);

    List<Appeal> findAll();
    List<Appeal> findByEmail(String email);
    List<Appeal> findByIpAddress(String ipAddress);

}
