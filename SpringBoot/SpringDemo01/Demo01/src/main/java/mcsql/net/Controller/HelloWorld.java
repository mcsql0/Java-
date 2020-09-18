package mcsql.net.Controller;

import mcsql.net.Bean.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.xml.ws.Action;

@ResponseBody
@Controller
public class HelloWorld {

    @Autowired
    public Person person;

    @Autowired
    public ApplicationContext app;

    @RequestMapping("hello")
    public String hellow() {
        return "HelloWorld";
    }

    @RequestMapping("getPerson")
    public String getPerson() {
        return person.toString();
    }

    @RequestMapping("getBean")
    public Boolean getMyBran() {
        return app.containsBean("helloService"); //true
    }
}
