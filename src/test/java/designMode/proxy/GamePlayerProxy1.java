package designMode.proxy;

/**
 * 普通代理的代理者
 */
public class GamePlayerProxy1 implements IGamePlayer{
    private IGamePlayer gamePlayer = null;

    public GamePlayerProxy1(String name){
        try{
            gamePlayer = new GamePlayer1(this,name);
        }catch (Exception e){

        }
    }


    @Override
    public void login(String user, String password) {
        this.gamePlayer.login(user,password);
    }

    @Override
    public void killBoss() {
        this.gamePlayer.killBoss();
    }

    @Override
    public void upgrade() {
        this.gamePlayer.upgrade();
    }
}
