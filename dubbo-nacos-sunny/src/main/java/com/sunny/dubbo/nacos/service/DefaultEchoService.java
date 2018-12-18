package com.sunny.dubbo.nacos.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.dubbo.rpc.RpcContext;

@Service(version = "${demo.service.version}")
public class DefaultEchoService implements EchoService {

    @Override
    public String echo(String message) {
        RpcContext rpcContext = (RpcContext) RpcContext.getContext();
        return String.format("Echo [port : %d] :  %s",
                rpcContext.getLocalPort(),
                message);
    }
}
