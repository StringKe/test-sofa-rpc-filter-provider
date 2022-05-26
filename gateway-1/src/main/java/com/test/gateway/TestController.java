package com.test.gateway;

import cn.dev33.satoken.stp.StpUtil;
import com.alipay.sofa.rpc.context.RpcInvokeContext;
import com.alipay.sofa.runtime.api.annotation.SofaReference;
import com.alipay.sofa.runtime.api.annotation.SofaReferenceBinding;
import com.test.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class TestController {

    @SofaReference(interfaceType = TestService.class, binding = @SofaReferenceBinding(bindingType = "bolt"))
    private TestService annotationService;

    @GetMapping("/")
    public String sayClientAnnotation() {

        log.info("gateway handle after request = " + RpcInvokeContext.getContext().getAllRequestBaggage());
        log.info("gateway handle after response = " + RpcInvokeContext.getContext().getAllResponseBaggage());

        log.info("gateway SaToken after request = " + RpcInvokeContext.getContext().getAllRequestBaggage());
        log.info("gateway SaToken after response = " + RpcInvokeContext.getContext().getAllResponseBaggage());

        StpUtil.login("123");

        log.info("gateway SaToken before request = " + RpcInvokeContext.getContext().getAllRequestBaggage());
        log.info("gateway SaToken before response = " + RpcInvokeContext.getContext().getAllResponseBaggage());

        String result = this.annotationService.test();

        log.info("gateway handle after request = " + RpcInvokeContext.getContext().getAllRequestBaggage());
        log.info("gateway handle after response = " + RpcInvokeContext.getContext().getAllResponseBaggage());

        return result;
    }
}
