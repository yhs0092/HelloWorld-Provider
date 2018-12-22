package training.demo.provider.filter;

import javax.ws.rs.core.Response.Status;

import org.apache.servicecomb.common.rest.filter.HttpServerFilter;
import org.apache.servicecomb.core.Invocation;
import org.apache.servicecomb.foundation.vertx.http.HttpServletRequestEx;
import org.apache.servicecomb.swagger.invocation.Response;
import org.apache.servicecomb.swagger.invocation.exception.CommonExceptionData;
import org.apache.servicecomb.swagger.invocation.exception.InvocationException;

public class DemoServerFilter implements HttpServerFilter {
  @Override
  public int getOrder() {
    return 0; // order值越小优先级越高
  }

  @Override
  public Response afterReceiveRequest(Invocation invocation, HttpServletRequestEx requestEx) {
    String shouldReject = requestEx.getHeader("reject");
    if ("true".equalsIgnoreCase(shouldReject)) {
      // 返回一个Response来打断后续处理流程
      return Response.failResp(
          new InvocationException(Status.FORBIDDEN, new CommonExceptionData("reject by DemoServerFilter")));
    }

    invocation.addContext("letStrangerPass", "true");
    // 返回null会继续后面的处理流程
    return null;
  }
}
