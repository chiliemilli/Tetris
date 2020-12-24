package service;

import model.Coord;
import model.Figure;
import model.Mappable;
import ui.Config;
import ui.Window;

public class FlyFigure {
    private Figure figure; //active figure
    private Coord coord;
    private boolean landed;
    private int ticks;
    Mappable map;



    public FlyFigure(Mappable map){
        this.map=map;
        figure=Figure.getRandom();
        coord=new Coord(Config.WIDTH/2-2,-1);
        landed=false;
        ticks=2;
    }

    public Figure getFigure() {
        return figure;
    }

    public Coord getCoord() {
        return coord;
    }

    public boolean isLanded(){
        return landed;
    }

    public boolean canPlaceFigure(){
        return canMoveFigure(figure,0,0);
    }

    private boolean canMoveFigure(Figure figure,int sx, int sy){
        if (coord.x + sx + figure.top.x<0) return false; //left
        if (coord.x + sx + figure.bottom.x>= Config.WIDTH) return false; //right
        //if (coord.y + sy +figure.top.y<0) return  false; //up
        if (coord.y + sy +figure.bottom.y>=Config.HEIGHT) return  false; //down
        for (Coord dot: figure.dots) {
            if (map.getBoxColor(coord.x + dot.x + sx, coord.y + dot.y + sy) > 0) {
                return false;
            }
        }
        return  true;

    }

    public void moveFigure(int sx, int sy){
        if (canMoveFigure(figure,sx,sy)){
            coord=coord.plus(sx,sy);
        }else if (sy==1){
            if (ticks>0){
                ticks--;
            }else landed=true;
        }
    }

    public   void turnFigure(int direction){
        Figure rotated=direction== 1 ? figure.turnRight() : figure.turnLeft();
        if (canMoveFigure(rotated,1,0)) {
            figure=rotated;
            moveFigure(1,0);
        }else
        if (canMoveFigure(rotated,0,0)){
            figure=rotated;
        }else
        if (canMoveFigure(rotated,-1,0)){
            figure=rotated;
            moveFigure(-1,0);
        }else
        if (canMoveFigure(rotated,0,-1)){
            figure=rotated;
            moveFigure(0,-1);
        }

    }

}
