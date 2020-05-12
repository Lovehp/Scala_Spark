package designMode.proxy;

public class Client1 {
    public static void main(String[] args) {
        IGamePlayer proxy = new GamePlayerProxy1("张三");
        //定义一个代练者
        System.out.println("开始时间是："+System.currentTimeMillis());
        proxy.login("zhangsan","password");
        proxy.killBoss();
        proxy.upgrade();
        System.out.println("结束时间是："+System.currentTimeMillis());
    }
}
