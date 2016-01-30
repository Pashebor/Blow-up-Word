package com.pashebor.wordsgame.core.game;


import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.MotionEvent;
import com.pashebor.wordsgame.WordGame;
import com.pashebor.wordsgame.core.Scene;

public class StartScreen extends Scene{

	private int maxTime = 3000;
	private int past;
	private boolean change = false;
	private RectF rect;
	
	@Override
	public void initialize() {
		
		int w = WordGame.get().getDisplay().getWidth();
		int h = WordGame.get().getDisplay().getHeight();
		
		rect = new RectF(w*.25f, h-h*.20f, w-w*.25f, (h-h*.20f)+h*.05f);
	}
	
	float perc;
	@Override
	public void updateBehaviour(long delay) throws Exception {
			past += delay;
			
			if(past > maxTime && !change) {
				change = true;
				WordGame.get().changeScene(SceneFactory.getMenuScreen(WordGame.get().getResources()));
			}
	}

	@Override
	public void draw(Canvas canvas) {
		super.draw(canvas);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		return true;
	}

	@Override
	protected Paint getPaint() {
		return null;
	}
}
