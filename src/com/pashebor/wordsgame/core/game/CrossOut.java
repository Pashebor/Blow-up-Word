package com.pashebor.wordsgame.core.game;


import com.pashebor.wordsgame.core.Actor;;

public class CrossOut extends Actor {

	private int LAST_FRAME = 11;
	
	public static int count = 0;
	
	public CrossOut() {
		super("Explosion", "Explosion");
		CrossOut.count++;
	}

	public void start() {
		setAnimation("explosion");
	}
	
	@Override
	public void updateBehaviour(long delay) throws Exception {
		
		super.updateBehaviour(delay);
		
		if(getAnimation().getFrame() >= LAST_FRAME) {
			setRemove(true);
		}	
	}
	
	@Override
	public void destroy() {
		CrossOut.count--;
	}
}
