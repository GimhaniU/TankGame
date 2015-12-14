package SlickGui;



        import Parser.Map;
        import game_objects.*;
        import org.newdawn.slick.*;
        import java.util.ArrayList;

/**
 * Created by Gimhani on 12/12/2015.
 */

public class SetUp extends BasicGame {
    private GameContainer gamecontainer=null;
    private Graphics graphics=null;

    private Map map=null;

    private Image background=null;
    private Image coin_img=null;
    private Image brick_img=null;
    private Image brick_75_img=null;
    private Image brick_50_img=null;
    private Image brick_25_img=null;
    private Image lifepack_img=null;
    private Image tank_0_img=null;
    private Image tank_1_img=null;
    private Image tank_2_img=null;
    private Image tank_3_img=null;
    private Image water_img=null;
    private Image stone_img=null;

    private ArrayList<Bullet> bulletpack=null;
    private int time_passed;

    public SetUp(String title) {
        super(title);
    }

    @Override
    public void init(GameContainer gameContainer) throws SlickException {
        this.gamecontainer=gameContainer;

        background=new Image("src/images/_original4.png");
        coin_img=new Image("src/images/coinpile.png");
        brick_img=new Image("src/images/brick.png");
        brick_75_img=new Image("src/images/brick-75.png");
        brick_50_img=new Image("src/images/brick-50.png");
        brick_25_img=new Image("src/images/brick-25.png");
        lifepack_img=new Image("src/images/lifepack.png");
        tank_0_img=new Image("src/images/tank-0.png");
        tank_1_img=new Image("src/images/tank-1.png");
        tank_2_img=new Image("src/images/tank-2.png");
        tank_3_img=new Image("src/images/tank-3.png");
        water_img=new Image("src/images/water.jpg");
        stone_img=new Image("src/images/stone.png");

        bulletpack=new ArrayList<>();
        time_passed=0;
    }

    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {
        ArrayList<Tank> tanks = map.getTanks();
        bulletpack=new ArrayList<>();
        int j=1;
        for(Tank tank:tanks){
            bulletpack.add(new Bullet(j));
            j++;
        }
    }
    public void update(Map map) throws SlickException {
        this.map=map;
        update(this.gamecontainer, 0);
    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
        this.graphics=graphics;
        background.draw(0, 0, 972, 648);



        //add bricks
        if(map!=null) {
            //player num
            graphics.drawString("Your Player ID :"+map.getClientID(),700,300);

            ArrayList<Brick> bricks = map.getBricks();
            for (Brick brick : bricks) {
                if(brick.getHealth()==100) {
                    drawEntity(brick, brick_img);
                }else if(brick.getHealth()==75) {
                    drawEntity(brick, brick_75_img);
                }else if(brick.getHealth()==50) {
                    drawEntity(brick, brick_50_img);
                }else if(brick.getHealth()==25) {
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
                if(tank.getDirection()==1){
                    drawEntity(tank,tank_1_img);
                }else if(tank.getDirection()==2){
                    drawEntity(tank,tank_2_img);
                }else if(tank.getDirection()==3){
                    drawEntity(tank,tank_3_img);
                }else{
                    drawEntity(tank,tank_0_img);
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
        }
    }

    private Color chooseTankColour(int id){
        switch (id){
            case 0:
                return Color.red;
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

    private void drawEntity(Entity entity,Image img) {
        //to draw image
        if(entity.getEnType() != Entity_Type.Tank) {
            img.draw(4 + 60 * (entity.getX()), 4 + 60 * (entity.getY()));
        }else{
            Tank tank=(Tank)entity;
            Color color = chooseTankColour(tank.getId());
            img.draw(4 + 60 * (tank.getX()), 4 + 60 * (tank.getY()), color);
            addDataToTable(tank);
        }

        //to show health
        if(entity.getEnType() == Entity_Type.Brick || entity.getEnType()==Entity_Type.Tank) {
            Health_Entity healthEntity=(Health_Entity)entity;
            graphics.drawString(String.valueOf(healthEntity.getHealth())+"%",4 + 60 * (entity.getX()), 8 + 60 * (entity.getY()));
        }

    }

    public void writeMessage(String message){
        /*
        if(graphics!=null) {
            graphics.drawString(message, 900, 200);
        }
        */
    }

    private void addDataToTable(Tank tank) {
        int row=405+tank.getId()*35;
        String[] valueArray=new String[]{"P"+tank.getId(),String.valueOf(tank.getPoints()),String.valueOf(tank.getCoins()),String.valueOf(tank.getHealth())};
        int column = 640;
        for (String cellValue: valueArray) {
            graphics.drawString(cellValue,column,row);
            column+=100;
        }
    }
}
