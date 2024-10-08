package Game.UI;

import Game.Animations.Animation;
import Game.EntityFactory.Player;
import Game.Events.Observer;
import Game.Manager.AssetManager;
import Game.Manager.Const;


public class Changes
{
    Animation []vfx;
    AssetManager a;
    public Changes()
    {
        a = AssetManager.getInstance();
    }

    public void update()
    {
        Observer o = Observer.getInstance();
        Player p = o.player;
        if(p.getEvent().comboSelect == 1)
        {
            vfx = new Animation[2];
            vfx[0] = new Animation(a.getVfx("Hit_1"), 4, Const.tileSize, Const.tileSize, 2);
            vfx[1] = new Animation(a.getVfx("Hit_2"), 4, Const.tileSize, Const.tileSize, 2);
        }
    }

    public Animation[] getVfx()
    {
        return vfx;
    }
}
