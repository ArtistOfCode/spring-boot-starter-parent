package com.codeartist.component.autoconfigure.feign;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Feign监控异常处理
 *
 * @author 艾江南
 * @date 2021/7/23
 */
public class FeignRpcErrorDecoder extends ErrorDecoder.Default {

    private final static Logger log = LoggerFactory.getLogger(FeignRpcErrorDecoder.class);

    /**
     * 当Feign请求异常时，这里只考虑内部服务的Feign调用，不适用于对接第三方调用
     * 请求异常和业务异常会进行异常转换，其他异常会走原有流程，重试或者熔断等操作
     */
    @Override
    public Exception decode(String methodKey, Response response) {
//        if (response.status() == HttpStatus.BAD_REQUEST.value() ||
//                response.status() == HttpStatus.SERVICE_UNAVAILABLE.value()) {
//            try {
//                byte[] body = StreamUtils.copyToByteArray(response.body().asInputStream());
//                ResponseError responseError = JSON.parseObject(body, ResponseError.class);
//                log.warn("{}: code:{}, message:{}", methodKey, responseError.getCode(), responseError.getMessage());
//                return new FeignException(responseError);
//            } catch (Exception e) {
//                log.error("Feign exception convert error.", e);
//                return super.decode(methodKey, response);
//            }
//        }
        return super.decode(methodKey, response);
    }
}
