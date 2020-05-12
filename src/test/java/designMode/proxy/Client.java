package designMode.proxy;

public class Client {
    public static void main(String[] args) {
        IGamePlayer player = new GamePlayer("张三");
        //定义一个代练者
        IGamePlayer proxy = new GamePlayerProxy(player);
        System.out.println("开始时间是："+System.currentTimeMillis());
        proxy.login("zhangsan","password");
        proxy.killBoss();
        proxy.upgrade();
        System.out.println("结束时间是："+System.currentTimeMillis());
    }
}
