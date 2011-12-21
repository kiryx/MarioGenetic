/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mariogenetic.game;

import java.sql.Time;
import java.util.Date;
import mariogenetic.main.GlobalVariables;
import mariogenetic.gene.ResultData;


/**
 *
 * @author alice
 */
public class GameState {

    public static GameState singleton;
    
    public static int RESULT_NONE = 0;
    public static int RESULT_TIMEOUT = 1;
    public static int RESULT_WON = 2;
    public static int RESULT_LOST = 3;
    public static String[] result_strings = {"No result","Timeout","Won","Lost"};

    public long started;
    public long now;
    public long finished;
    public int score;
    public int result;
    boolean game_over = false;
    private GameState()
    {
        now = started = new Date().getTime();
        score = 0;
        game_over=false;
        result = RESULT_NONE;
    }

    private GameState(GameState gs) {
        this();
        this.now = gs.now;
        this.score = gs.score;
        this.finished = gs.finished;
        this.result = gs.result;
        this.game_over = gs.game_over;
        this.started = gs.started;
    }
    public void reset()
    {
        now = started = new Date().getTime();
        score = 0;
        game_over=false;
        result = RESULT_NONE;
        GlobalVariables.main.resources.reset();
        GlobalVariables.main.renderer.reset();
    }
    
    public void updateTime(long d_time)
    {
        now+=d_time;
    }
    public void setGameOver(int reason)
    {
//        finished = new Date().getTime();
        finished = now;
        result = reason;
        game_over=true;
    }
    public String toString()
    {
//        long now = new Date().getTime();
        return String.format("Result: %s, Score: %d, Time: %d Cam %s",result_strings[result] ,score,(now-started),GlobalVariables.main.renderer.camera);
    }

    public void check() {

        //zmiana trybu
        if(GlobalVariables.MODE_CURRENT!=GlobalVariables.MODE_NEXT)
        {
            GlobalVariables.MODE_CURRENT = GlobalVariables.MODE_NEXT;
            switch(GlobalVariables.MODE_CURRENT)
            {
                case GlobalVariables.MODE_USER:
                {
                    
//                    Global.main.logic=new LogicHuman_Temporary();
//                    Global.main.logic = new LogicTime();
                    GlobalVariables.main.controller = new ControllerHuman();
                    break;
                }
                case GlobalVariables.MODE_TIME:
                {
//                    Global.main.logic=new LogicMario();
                    GlobalVariables.main.controller = new ControllerTime();
                    break;
                }
            }
        }
        else if(result != RESULT_NONE)
        {
            if(GlobalVariables.MODE_CURRENT == GlobalVariables.MODE_USER)
            {
                System.out.print(GlobalVariables.global_result_counter+++" ");
                System.out.println("(H):"+new ResultData(this));
                reset();
            }
            else if(GlobalVariables.MODE_CURRENT == GlobalVariables.MODE_TIME)
            {

            }
        }
    }
    public long timeElapsed()
    {
//        long now = new Date().getTime();
        return now-started;
    }

    public static GameState getInstance()
    {
        if(singleton == null)
        {
            singleton = new GameState();
        }
        return singleton;
    }
    


}
