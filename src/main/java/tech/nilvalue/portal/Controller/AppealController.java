package tech.nilvalue.portal.Controller;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import tech.nilvalue.portal.Repository.Appeal;
import tech.nilvalue.portal.Service.AppealServiceImpl;



// Реализовать контроллер для получения обращения пользователем по запросу UID
@RestController
public class AppealController {

    private final AppealServiceImpl appealServiceImpl;

    @Autowired
    public AppealController(AppealServiceImpl appealServiceImpl) {
        this.appealServiceImpl = appealServiceImpl;
    }

    @PostMapping("/contact")
    public ModelAndView appeal_page(@ModelAttribute Appeal appeal, ModelAndView model, HttpServletRequest request) {
        appealServiceImpl.create(appeal, request);
        model.setViewName("ok");
        model.setStatus(HttpStatusCode.valueOf(201));
        return model;
    }
}
