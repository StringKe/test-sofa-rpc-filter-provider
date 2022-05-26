package com.test.rpc;

import cn.dev33.satoken.context.second.SaTokenSecondContext;
import cn.dev33.satoken.context.second.SaTokenSecondContextCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

/**
 * Sa-Token 二级Context - 创建器 [Dubbo版]
 *
 * @author kong
 */
public class SaTokenSecondContextCreatorForSofaRpc implements SaTokenSecondContextCreator {

    @Override
    @Bean
    @Primary
    public SaTokenSecondContext create() {
        return new SaTokenSecondContextForSofaRpc();
    }

}
