package com.plugins.main;

/**
 * Created by zhangtao on 2015/12/9.
 */
@Deprecated
public class ServerFactory
//        implements InitializingBean , ApplicationContextAware
{
//    private ApplicationContext context;
//
//    @Override
//    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
//        this.context=applicationContext;
//    }
//
//    @Override
//    public void afterPropertiesSet() throws Exception {
//        Map<String,Object> servers=context.getBeansWithAnnotation(RPCServer.class);
//        if(null==servers || servers.isEmpty()){
//            throw new NullPointerException();
//        }
//        _initServerMethod(servers);
//    }
//
//
//    /**
//     * 加载所有对外接口方法，并封装RpcRequest 创建接口方法对象池
//     * @param servers
//     * @throws Exception
//     */
//    private void _initServerMethod(Map<String,Object> servers) throws Exception {
//        for (Map.Entry<String,Object> entry:servers.entrySet()){
//            Method[] methods=entry.getValue().getClass().getDeclaredMethods();
//            for (Method method:methods){
//                RPCServerMethod annotation= method.getAnnotation(RPCServerMethod.class);
//                Deprecated deprecated=method.getAnnotation(Deprecated.class);
//                if(null!=deprecated){
//                    continue;
//                }
//                if(null==annotation){
//                    continue;
//                }
//                RPCAnnotationsHandler handler=new RPCAnnotationsHandler(entry.getValue(),method);
//                _createMethodPool(handler.handler());
//            }
//        }
//    }
//
//    private void _createMethodPool(RpcRequest request) throws Exception {
//        if(null==request){
//            throw new NullPointerException();
//        }
//        try {
//            final String poolKey= Joiner.on("_").join(request.getServerName(),request.getMethondName(),request.getServerVersion());
//            if(ServerResource.serverPools.containsKey(poolKey)){
//                return ;
//            }
//            GenericObjectPool<FastMethod> pool= new ServerMethodPoolHandler(new FastMethodTransform(this.context,request).getObject(), ServerResource.methodPoolConfig).getObject();
//            ServerResource.serverPools.put(poolKey,pool);
//        } catch (Exception e) {
//           throw(e);
//        }
//    }
}
