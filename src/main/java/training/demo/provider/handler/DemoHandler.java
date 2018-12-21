package training.demo.provider.handler;

import javax.ws.rs.core.Response.Status;

import org.apache.servicecomb.core.Handler;
import org.apache.servicecomb.core.Invocation;
import org.apache.servicecomb.swagger.invocation.AsyncResponse;
import org.apache.servicecomb.swagger.invocation.exception.CommonExceptionData;
import org.apache.servicecomb.swagger.invocation.exception.InvocationException;

public class DemoHandler implements Handler {
  @Override
  public void handle(Invocation invocation, AsyncResponse asyncResp) throws Exception {
    // 取出请求参数，这里的排列和服务契约描述的一致
    Object arg = invocation.getArgs()[0];
    String letStrangerPass = invocation.getContext("letStrangerPass");
    if ("stranger".equalsIgnoreCase(arg.toString()) && !"true".equalsIgnoreCase(letStrangerPass)) {
      // 把请求拦截下来，返回403
      asyncResp.producerFail(new InvocationException(Status.FORBIDDEN, new CommonExceptionData("Don't know you :(")));
      return;
    }

    // 继续执行后面的逻辑
    invocation.next(asyncResp);
  }
}
