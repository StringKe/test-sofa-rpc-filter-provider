package com.test.rpc.model;


import cn.dev33.satoken.context.model.SaRequest;
import com.alipay.sofa.rpc.context.RpcInvokeContext;

/**
 * Request for Dubbo
 *
 * @author kong
 */
public class SaRequestForSofaRpc implements SaRequest {

    /**
     * 底层对象
     */
    protected RpcInvokeContext rpcContext;

    /**
     * 实例化
     *
     * @param rpcContext rpcContext对象
     */
    public SaRequestForSofaRpc(RpcInvokeContext rpcContext) {
        this.rpcContext = rpcContext;
    }

    /**
     * 获取底层源对象
     */
    @Override
    public Object getSource() {
        System.out.println("getSource Request");
        return rpcContext;
    }

    /**
     * 在 [请求体] 里获取一个值
     */
    @Override
    public String getParam(String name) {
        System.out.println("getParam = " + name);
        // 不传播 url 参数
        return null;
    }

    /**
     * 在 [请求头] 里获取一个值
     */
    @Override
    public String getHeader(String name) {
        System.out.println("getHeader = " + name);
        // 不传播 header 参数
        return null;
    }

    /**
     * 在 [Cookie作用域] 里获取一个值
     */
    @Override
    public String getCookieValue(String name) {
        System.out.println("getCookieValue = " + name);
        // 不传播 cookie 参数
        return null;
    }

    /**
     * 返回当前请求path (不包括上下文名称)
     */
    @Override
    public String getRequestPath() {
        System.out.println("getRequestPath");
        // 不传播 requestPath
        return null;
    }

    /**
     * 返回当前请求的url，例：http://xxx.com/test
     *
     * @return see note
     */
    public String getUrl() {
        System.out.println("getUrl");
        // 不传播 url
        return null;
    }

    /**
     * 返回当前请求的类型
     */
    @Override
    public String getMethod() {
        System.out.println("getMethod");
        // 不传播 method
        return null;
    }

    /**
     * 转发请求
     */
    @Override
    public Object forward(String path) {
        System.out.println("forward = " + path);
        // 不传播 forward 动作
        return null;
    }

}
