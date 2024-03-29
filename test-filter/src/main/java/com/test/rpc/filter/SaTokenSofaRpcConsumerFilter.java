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
@Extension("satoken-consumer")
@AutoActive(consumerSide = true)
public class SaTokenSofaRpcConsumerFilter extends Filter {

    @Override
    public boolean needToLoad(FilterInvoker invoker) {
        return true;
    }

    @Override
    public SofaResponse invoke(FilterInvoker invoker, SofaRequest request) throws SofaRpcException {
        log.info("consumer invoke after request = " + RpcInvokeContext.getContext().getAllRequestBaggage());
        log.info("consumer invoke after response = " + RpcInvokeContext.getContext().getAllResponseBaggage());

        SofaResponse response = invoker.invoke(request);

        // 当子服务更新了Sa-Token，则更新父服务的 Sa-Token 目前无法及时获取到 ResponseBaggage
        // StpUtil.setTokenValue(RpcInvokeContext.getContext().getRequestBaggage(SaTokenConsts.JUST_CREATED_NOT_PREFIX));

        log.info("consumer invoke before request = " + RpcInvokeContext.getContext().getAllRequestBaggage());
        log.info("consumer invoke before response = " + RpcInvokeContext.getContext().getAllResponseBaggage());

        return response;
    }
}
