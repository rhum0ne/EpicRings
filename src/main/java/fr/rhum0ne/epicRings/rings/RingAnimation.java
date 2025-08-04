/* ===================================================================================
 * 3.  RingAnimation — factory sans dépendance
 * =================================================================================== */
package fr.rhum0ne.epicRings.rings;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;

import java.util.concurrent.ConcurrentLinkedQueue;

@Getter
@Setter
public abstract class RingAnimation {

    private final int states;
    private int currentState;
    private Ring ring;

    public RingAnimation(int states){
        this.states = states;
        this.currentState = 0;
    }

    public void nextState(){
        currentState++;
        if(currentState >= states){
            currentState = 0;
        }
    }

    public abstract void showState(ConcurrentLinkedQueue<Player> players);
}
