package designMode.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * 动态代理的场景类
 */
public class Client2 {
    public static void main(String[] args) {
        IGamePlayer player = new GamePlayer("张三");
        InvocationHandler handler = new GamePlayIH(player);
        System.out.println("开始时间是："+System.currentTimeMillis());
        //获得类的class loader
        ClassLoader cl = player.getClass().getClassLoader();
        //动态产生一个代理者GamePlayer.class.getInterfaces()
        IGamePlayer proxy = (IGamePlayer) Proxy.newProxyInstance(cl,new Class[]{GamePlayer.class},handler);
        proxy.login("zhangsan","password");
        proxy.killBoss();
        proxy.upgrade();
        System.out.println("结束时间是："+System.currentTimeMillis());
    }
}
