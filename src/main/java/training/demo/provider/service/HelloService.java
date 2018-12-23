package training.demo.provider.service;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.netflix.config.DynamicPropertyFactory;
import com.netflix.config.DynamicStringProperty;

@RestSchema(schemaId = "hello") // 该注解声明这是一个REST接口类，CSEJavaSDK会扫描到这个类，根据它的代码生成接口契约
@RequestMapping("/hello")  // @RequestMapping是Spring的注解，这里在使用Spring MVC风格开发REST接口
public class HelloService {
  private DynamicStringProperty sayHelloPrefix = DynamicPropertyFactory.getInstance()
      .getStringProperty("hello.sayHello", "");

  // for microservice version 0.0.1
//  @RequestMapping(value = "/{name}", method = RequestMethod.GET)
//  public String sayHello(@PathVariable(value = "name") String name) {
//    return sayHelloPrefix.get() + name;
//  }

  // for microservice version 0.0.2
  @RequestMapping(value = "/{name}", method = RequestMethod.GET)
  public String sayHello(@PathVariable(value = "name") String name) {
    Calendar calendar = new GregorianCalendar();
    int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
    String greet = null;
    if (hourOfDay < 12) {
      greet = "Good morning.";
    } else if (hourOfDay < 18) {
      greet = "Good afternoon.";
    } else if (hourOfDay < 22) {
      greet = "Good evening.";
    } else {
      greet = "Good night.";
    }
    return sayHelloPrefix.get() + name + ". " + greet;
  }
}
