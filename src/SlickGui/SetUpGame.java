package SlickGui;

import Parser.Map;
import game_objects.*;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;

/**
 * Created by Nuwantha on 12/16/2015.
 */
public class SetUpGame extends BasicGameState {

    private GameContainer gamecontainer = null;
    private Graphics graphics = null;

    private Map map = null;

    private Image background = null;
    private Image coin_img = null;
    private Image brick_img = null;
    private Image brick_75_img = null;
    private Image brick_50_img = null;
    private Image brick_25_img = null;
    private Image lifepack_img = null;
    private Image tank_0_img = null;
    private Image tank_1_img = null;
    private Image tank_2_img = null;
    private Image tank_3_img = null;
    private Image water_img = null;
    private Image stone_img = null;
    private Image bullet_hor_img = null;
    private Image bullet_ver_img = null;

    private ArrayList<Bullet> bulletpack = null;
    private StateBasedGame stateBasedGame = null;

    @Override
    public int getID() {
        return 0;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        this.gamecontainer = gameContainer;
        this.stateBasedGame = stateBasedGame;

        background = new Image("src/images/_original4.png");
        coin_img = new Image("src/images/coinpile.png");
        brick_img = new Image("src/images/brick.png");
        brick_75_img = new Image("src/images/brick-75.png");
        brick_50_img = new Image("src/images/brick-50.png");
        brick_25_img = new Image("src/images/brick-25.png");
        lifepack_img = new Image("src/images/lifepack.png");
        tank_0_img = new Image("src/images/tank-0.png");
        tank_1_img = new Image("src/images/tank-1.png");
        tank_2_img = new Image("src/images/tank-2.png");
        tank_3_img = new Image("src/images/tank-3.png");
        water_img = new Image("src/images/water.jpg");
        stone_img = new Image("src/images/stone.png");
        bullet_hor_img = new Image("src/images/bullet-hor.png");
        bullet_ver_img = new Image("src/images/bullet-ver.png");

        bulletpack = new ArrayList<>();
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        this.gamecontainer = gameContainer;
        this.stateBasedGame = stateBasedGame;

        background = new Image("src/images/_original4.png");
        coin_img = new Image("src/images/coinpile.png");
        brick_img = new Image("src/images/brick.png");
        brick_75_img = new Image("src/images/brick-75.png");
        brick_50_img = new Image("src/images/brick-50.png");
        brick_25_img = new Image("src/images/brick-25.png");
        lifepack_img = new Image("src/images/lifepack.png");
        tank_0_img = new Image("src/images/tank-0.png");
        tank_1_img = new Image("src/images/tank-1.png");
        tank_2_img = new Image("src/images/tank-2.png");
        tank_3_img = new Image("src/images/tank-3.png");
        water_img = new Image("src/images/water.jpg");
        stone_img = new Image("src/images/stone.png");
        bullet_hor_img = new Image("src/images/bullet-hor.png");
        bullet_ver_img = new Image("src/images/bullet-ver.png");

        bulletpack = new ArrayList<>();
        if (bulletpack != null) {
            for (Iterator<Bullet> b = bulletpack.iterator(); b.hasNext(); ) {
                Bullet bullet = b.next();
                if (bullet.is_active()) {
                    bullet.update(i);
                } else {
                    b.remove();
                }
            }
        }
        if (map != null && map.is_game_finished()) {
            stateBasedGame.enterState(1, new FadeInTransition(Color.gray), new FadeOutTransition(Color.darkGray));
        }
    }

    public void update(Map map) throws SlickException {
        this.map = map;
        update(this.gamecontainer, stateBasedGame, 0);
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        try {
            this.graphics = graphics;
            background.draw(0, 0, 972, 648);

            //add bricks
            if (map != null) {
                //player num
                graphics.drawString("Your Player ID :" + map.getClientID(), 700, 300);

                ArrayList<Brick> bricks = map.getBricks();
                for (Brick brick : bricks) {
                    if (brick.getHealth() == 100) {
                        drawEntity(brick, brick_img);
                    } else if (brick.getHealth() == 75) {
                        drawEntity(brick, brick_75_img);
                    } else if (brick.getHealth() == 50) {
                        drawEntity(brick, brick_50_img);
                    } else if (brick.getHealth() == 25) {
                        drawEntity(brick, brick_25_img);
                    }
                }
                //add water
                ArrayList<Water> waters = map.getWaters();
                for (Water water : waters) {
                    drawEntity(water, water_img);
                }
                //add stone walls
                ArrayList<Stone> stones = map.getStones();
                for (Stone stone : stones) {
                    drawEntity(stone, stone_img);
                }

                ArrayList<Tank> tanks = map.getTanks();
                for (Tank tank : tanks) {
                    if (tank.getHealth() > 0) { //otherwise it must not show the image of the tank, tank is dead
                        if (tank.getDirection() == 1) {
                            drawEntity(tank, tank_1_img);
                        } else if (tank.getDirection() == 2) {
                            drawEntity(tank, tank_2_img);
                        } else if (tank.getDirection() == 3) {
                            drawEntity(tank, tank_3_img);
                        } else {
                            drawEntity(tank, tank_0_img);
                        }
                        //if tank is shooting
                        if (tank.isShot()) {
                            if (tank.getDirection() == 0 || tank.getDirection() == 2) {
                                bulletpack.add(new Bullet(new Vector2f(tank.getX(), tank.getY()), tank.getDirection(), bullet_ver_img));
                            } else {
                                bulletpack.add(new Bullet(new Vector2f(tank.getX(), tank.getY()), tank.getDirection(), bullet_hor_img));
                            }
                        }
                    }
                }

                ArrayList<Coin_Pile> coin_piles = map.getCoinPiles();
                for (Coin_Pile coin_pile : coin_piles) {
                    drawEntity(coin_pile, coin_img);
                }

                ArrayList<Life_Pack> life_packs = map.getLifePacks();
                for (Life_Pack life_pack : life_packs) {
                    drawEntity(life_pack, lifepack_img);
                }

                //to move the already available bullets
                for (Bullet bullet : bulletpack) {
                    graphics.drawImage(bullet.getBullet_img(), bullet.getPos().getX(), bullet.getPos().getY());
                }


                //to remove bullets which hit stones ,bricks and tanks
                ArrayList<Entity> all_objects = new ArrayList<>();
                all_objects.addAll(bricks);
                all_objects.addAll(stones);
                all_objects.addAll(tanks);

                if (!bulletpack.isEmpty()) {
                    for (Entity e : all_objects) {
                        Rectangle r = new Rectangle(e.getX(), e.getY(), 58, 58);
                        for (Bullet bullet : bulletpack) {
                            if (bullet.is_active()) {
                                Rectangle p = new Rectangle(bullet.getX(), bullet.getY(), 58, 58);
                                if (r.intersects(p)) {
                                    bullet.setIs_active(false);
                                    bulletpack.remove(bullet);
                                }
                            }
                        }
                    }
                }
            }
        } catch (ConcurrentModificationException e) {

        }
    }


    private Color chooseTankColour(int id) {
        switch (id) {
            case 0:
                return Color.magenta;
            case 1:
                return Color.cyan;
            case 2:
                return Color.pink;
            case 3:
                return Color.yellow;
            case 4:
                return Color.blue;
        }
        return Color.transparent;
    }

    private void drawEntity(Entity entity, Image img) {
        //to draw image
        if (entity.getEnType() != Entity_Type.Tank) {
            img.draw(4 + 60 * (entity.getX()), 4 + 60 * (entity.getY()));
        } else {
            Tank tank = (Tank) entity;
            Color color = chooseTankColour(tank.getId());
            img.draw(4 + 60 * (tank.getX()), 4 + 60 * (tank.getY()), color);
            addDataToTable(tank);
        }

        //to show health
        if (entity.getEnType() == Entity_Type.Brick || entity.getEnType() == Entity_Type.Tank) {
            Health_Entity healthEntity = (Health_Entity) entity;
            graphics.drawString(String.valueOf(healthEntity.getHealth()) + "%", 4 + 60 * (entity.getX()), 8 + 60 * (entity.getY()));
        }

        if (entity.getEnType() == Entity_Type.CoinPile) {
            Coin_Pile coin_pile = (Coin_Pile) entity;
            graphics.drawString(String.valueOf(coin_pile.getValue()) + "$", 4 + 60 * (entity.getX()), 8 + 60 * (entity.getY()));
        }
    }

    public void writeMessage(String message) {
        /*
        if(graphics!=null) {
            graphics.drawString(message, 900, 200);
        }
        */
    }

    private void addDataToTable(Tank tank) {
        int row = 405 + tank.getId() * 35;
        String[] valueArray = new String[]{String.valueOf(tank.getPoints()), String.valueOf(tank.getCoins()), String.valueOf(tank.getHealth())};
        int column = 720;
        for (String cellValue : valueArray) {
            graphics.drawString(cellValue, column, row);
            column += 90;
        }
    }

}
