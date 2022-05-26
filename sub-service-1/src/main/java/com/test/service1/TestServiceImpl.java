package com.test.service1;

import cn.dev33.satoken.stp.StpUtil;
import com.alipay.sofa.rpc.context.RpcInvokeContext;
import com.alipay.sofa.runtime.api.annotation.SofaService;
import com.alipay.sofa.runtime.api.annotation.SofaServiceBinding;
import com.test.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@SofaService(interfaceType = TestService.class, bindings = {@SofaServiceBinding(bindingType = "bolt")})
@Component
@Slf4j
public class TestServiceImpl implements TestService {
    @Override
    public String test() {

        log.info("service SaToken after request = " + RpcInvokeContext.getContext().getAllRequestBaggage());
        log.info("service SaToken after response = " + RpcInvokeContext.getContext().getAllResponseBaggage());

        StpUtil.login("456");

        log.info("service SaToken after request = " + RpcInvokeContext.getContext().getAllRequestBaggage());
        log.info("service SaToken after response = " + RpcInvokeContext.getContext().getAllResponseBaggage());


        return "test string";
    }
}
