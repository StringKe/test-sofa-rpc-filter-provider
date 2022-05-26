package com.test.rpc;

import cn.dev33.satoken.context.model.SaRequest;
import cn.dev33.satoken.context.model.SaResponse;
import cn.dev33.satoken.context.model.SaStorage;
import cn.dev33.satoken.context.second.SaTokenSecondContext;
import cn.dev33.satoken.exception.ApiDisabledException;
import com.alipay.sofa.rpc.context.RpcInvokeContext;
import com.test.rpc.model.SaRequestForSofaRpc;
import com.test.rpc.model.SaResponseForSofaRpc;
import com.test.rpc.model.SaStorageForSofaRpc;

/**
 * Sa-Token 上下文 [Dubbo版本]
 *
 * @author kong
 */
public class SaTokenSecondContextForSofaRpc implements SaTokenSecondContext {

    @Override
    public SaRequest getRequest() {
        return new SaRequestForSofaRpc(RpcInvokeContext.getContext());
    }

    @Override
    public SaResponse getResponse() {
        return new SaResponseForSofaRpc(RpcInvokeContext.getContext());
    }

    @Override
    public SaStorage getStorage() {
        return new SaStorageForSofaRpc(RpcInvokeContext.getContext());
    }

    @Override
    public boolean matchPath(String pattern, String path) {
        throw new ApiDisabledException();
    }

    @Override
    public boolean isValid() {
        return RpcInvokeContext.getContext() != null;
    }

}
