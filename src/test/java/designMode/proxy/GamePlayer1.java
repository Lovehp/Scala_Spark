package designMode.proxy;

/**
 * 普通代理的游戏者
 */
public class GamePlayer1 implements IGamePlayer {
    private String name = "";

    public GamePlayer1(IGamePlayer _gamePlayer,String _name) throws Exception{
        if (_gamePlayer == null) {
            throw new Exception("不能创建真是角色！");
        }else {
            this.name = _name;
        }
    }

    @Override
    public void login(String user, String password) {
        System.out.println("登录名为"+user+"的用户"+this.name);
    }

    @Override
    public void killBoss() {
        System.out.println(this.name+"在打怪！");
    }

    @Override
    public void upgrade() {
        System.out.println(this.name+"又升了一级！");
    }
}
