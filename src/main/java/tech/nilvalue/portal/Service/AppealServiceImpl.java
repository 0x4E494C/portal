package tech.nilvalue.portal.Service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.client.HttpClientErrorException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;

import tech.nilvalue.portal.Repository.Appeal;
import tech.nilvalue.portal.Repository.AppealRepository;



// Методы CRUD будут реализованы с возможностью получения конкретного обращения по запросу пользователя
// Необходимо добавить в репозиторий поле для более простого UID обращения.
@Service
@Validated
@Transactional
public class AppealServiceImpl implements AppealService {

    private final AppealRepository appealRepository;

    public AppealServiceImpl(AppealRepository appealRepository) {
        this.appealRepository = appealRepository;
    }

    @Override
    public Appeal create(Appeal appeal, HttpServletRequest request) throws HttpClientErrorException {
           if (
                   (appealRepository.searchAppealsByEmail(appeal.getEmail()).toArray().length < 3) &&
                   (appealRepository.searchAppealsByIpAddress(request.getRemoteAddr()).toArray().length < 3)
           ) {
               appeal.setIpAddress(request.getRemoteAddr());
               appeal.setDate(Timestamp.from(Instant.now()));
               System.out.println("POST RQ\n" + appeal.toString() + "\n BY " + request.getHeader("User-Agent"));
               return appealRepository.save(appeal);
           } else {
               throw new HttpClientErrorException(HttpStatusCode.valueOf(400), "Вы оставили слишком много обращений");
           }
    }

    @Override
    public Appeal update(Long id, String email, Appeal appeal) {
        return null;
    }

    @Override
    public Appeal delete(Long id, String email) {
        return null;
    }

    @Override
    public List<Appeal> findAll() {
        return null;
    }

    @Override
    public Appeal findById(Long id, String email) {
        return null;
    }

    @Override
    public List<Appeal> findByEmail(String email) {
        return appealRepository.searchAppealsByEmail(email);
    }

    @Override
    public List<Appeal> findByIpAddress(String ipAddress) {
        return appealRepository.searchAppealsByIpAddress(ipAddress);
    }


}
