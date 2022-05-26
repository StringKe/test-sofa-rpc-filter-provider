package com.test.rpc.model;

import cn.dev33.satoken.context.model.SaStorage;
import com.alipay.sofa.rpc.context.RpcInvokeContext;
import lombok.extern.slf4j.Slf4j;

/**
 * Storage for Servlet
 *
 * @author kong
 */
@Slf4j
public class SaStorageForSofaRpc implements SaStorage {

    /**
     * 底层对象
     */
    protected RpcInvokeContext rpcContext;

    /**
     * 实例化
     *
     * @param rpcContext rpcContext对象
     */
    public SaStorageForSofaRpc(RpcInvokeContext rpcContext) {
        this.rpcContext = rpcContext;
    }

    /**
     * 获取底层源对象
     */
    @Override
    public Object getSource() {
        return rpcContext;
    }

    /**
     * 在 [Request作用域] 里写入一个值
     */
    @Override
    public void set(String key, Object value) {
        log.info("set key:{},value:{}", key, value);
        rpcContext.putRequestBaggage(key, value.toString());
        rpcContext.putResponseBaggage(key, value.toString());
    }

    /**
     * 在 [Request作用域] 里获取一个值
     */
    @Override
    public Object get(String key) {
        return rpcContext.getRequestBaggage(key);
    }

    /**
     * 在 [Request作用域] 里删除一个值
     */
    @Override
    public void delete(String key) {
        rpcContext.removeRequestBaggage(key);
    }

}
