package com.test.rpc.filter;


import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaTokenConsts;
import com.alipay.sofa.rpc.context.RpcInvokeContext;
import com.alipay.sofa.rpc.core.exception.SofaRpcException;
import com.alipay.sofa.rpc.core.request.SofaRequest;
import com.alipay.sofa.rpc.core.response.SofaResponse;
import com.alipay.sofa.rpc.ext.Extension;
import com.alipay.sofa.rpc.filter.AutoActive;
import com.alipay.sofa.rpc.filter.Filter;
import com.alipay.sofa.rpc.filter.FilterInvoker;
import lombok.extern.slf4j.Slf4j;

/**
 * Sa-Token 整合 Dubbo Consumer端过滤器
 *
 * @author kong
 */
@Slf4j
@Extension("satoken-provider")
@AutoActive(providerSide = true)
public class SaTokenSofaRpcProviderFilter extends Filter {

    @Override
    public boolean needToLoad(FilterInvoker invoker) {
        return true;
    }

    @Override
    public SofaResponse invoke(FilterInvoker invoker, SofaRequest request) throws SofaRpcException {

        log.info("provider invoke after request = " + RpcInvokeContext.getContext().getAllRequestBaggage());
        log.info("provider invoke after response = " + RpcInvokeContext.getContext().getAllResponseBaggage());

        StpUtil.setTokenValue(RpcInvokeContext.getContext().getRequestBaggage(SaTokenConsts.JUST_CREATED_NOT_PREFIX));

        SofaResponse response = invoker.invoke(request);

        log.info("provider invoke before request = " + RpcInvokeContext.getContext().getAllRequestBaggage());
        log.info("provider invoke before response = " + RpcInvokeContext.getContext().getAllResponseBaggage());

        return response;
    }
}
