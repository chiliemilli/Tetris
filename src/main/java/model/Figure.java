package model;

import java.util.ArrayList;
import java.util.List;

public enum Figure {
    I1(0,1,  1,1,  2,1,  3,1), // oooo горизонтальная
    I2(1,0,
               1,1,
               1,2,
               1,3), // оооо вкптикальная

    J1(1,0,
               1,1,
         0,2,  1,2), // J влево

    J2(0,0,
               0,1,  1,1,  2,1),

    J3(1,0,  2,0,
               1,1,
               1,2),

    J4(0,1,  1,1,  2,1,
                          2,2),

    L1(1,0,
               1,1,
               1,2,  2,2),

    L2(0,1,  1,1,  2,1,
              0,2),

    L3(0,0,  1,0,
                     1,1,
                     1,2),

    L4(          2,0,
            0,1,  1,1,  2,1),

    O(0,0,  1,0,
              0,1,  1,1),

    S1(    1,1,  2,1,
            0,2,  1,2),

    S2(0,0,
               0,1,  1,1,
                     1,2),

    T1(0,1,   1,1,  2,1,
                     1,2),

    T2(1,0,
          0,1, 1,1,
              1,2),

    T3(1,0,
         0,1, 1,1, 2,1),

    T4(1,0,
              0,1, 1,1,
              1,2),

    Z1(0,1, 1,1,
                    1,2, 2,2),

    Z2(  2,0,
            1,1, 2,1,
            1,2);

    public final List<Coord> dots;

    Figure(int ... coords){
        dots=new ArrayList<Coord>();
        for (int i = 0; i <coords.length ; i+=2) {
            dots.add(new Coord(coords[i], coords[i+1]));

        }
    }
    public Figure turnRight(){
        switch (this){
            case I1: return I2;
            case I2:return I1;
            case J1: return J2;
            case J2: return J3;
            case J3: return J4;
            case J4: return J1;
            case L1: return L2;
            case L2: return L3;
            case L3: return L4;
            case L4:return L1;
            case O: return O;
            case S1:return S2;
            case S2:return S1;
            case T1:return T2;
            case T2:return T3;
            case T3:return T4;
            case T4:return T1;
            case Z1:return Z2;
            case Z2:
            default: return Z1;
        }
    }

    public Figure turnLeft(){
        return turnRight().turnRight().turnRight(); //чтобы повернуть налево, повернуть три раза направо
    }

    public  static Figure getRandom(){
        return Figure.values()[(int) Math.random()*Figure.values().length];
    }

}
