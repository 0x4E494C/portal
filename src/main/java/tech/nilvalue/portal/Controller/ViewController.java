package tech.nilvalue.portal.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import tech.nilvalue.portal.Repository.Appeal;


@Controller
public class ViewController {

    @GetMapping("/")
    public ModelAndView main_page() {
        return new ModelAndView("resume");
    }

    @GetMapping("/contact")
    public ModelAndView appeal_page(ModelAndView mav) {
        mav.addObject("appeal", new Appeal());
        mav.setViewName("contact");
        return mav;
    }
}
