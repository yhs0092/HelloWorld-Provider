package training.demo.provider.service;

import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RestSchema(schemaId = "hello")
@RequestMapping("/hello")
public class HelloService {
  @RequestMapping(value = "/{name}", method = RequestMethod.GET)
  public String sayHello(@PathVariable(value = "name") String name) {
    return "Hello, " + name;
  }
}
