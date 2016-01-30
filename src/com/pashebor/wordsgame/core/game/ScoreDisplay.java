package com.pashebor.wordsgame.core.game;


import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import com.pashebor.wordsgame.WordGame;
import com.pashebor.wordsgame.core.SceneObject;

public class ScoreDisplay extends SceneObject{

	Paint textPaint;
	String text = "Score:";
	String textLevel = "LV:";
	private long score = 0;
	
	
	public ScoreDisplay() {
		super("Score","Score");
		
		textPaint = new Paint();
		
		textPaint.setColor(Color.BLACK);
		textPaint.setTypeface(SceneFactory.getSystemFont());
		textPaint.setTextSize(16);
		textPaint.setAntiAlias(true);		
		
	}	
	
	@Override
	public void updateBehaviour(long delay) throws Exception {
		super.updateBehaviour(delay);
		
		if(WordGame.get().getLevel() >= 99)
			score=9999;
	}
	
	public void drawTexts(Canvas canvas) {
		
		Rect bounds2 = new Rect();
		textPaint.getTextBounds(textLevel + "999", 0, (textLevel+"999").length(), bounds2);
		
		Rect bounds = new Rect();
		textPaint.getTextBounds(text + score, 0, 1, bounds);
		
		float dy = bounds.height() + ((getH()-bounds.height())/2);
		
		canvas.drawText(textLevel + WordGame.get().getLevel(), getX(), getY()+dy, textPaint);
		
		canvas.drawText(text+score, getX()+bounds2.width(), getY()+dy, textPaint);
		
		
		
	}
	
	public void add(int points) {
		score+=points;
		
		if(WordGame.get().getLevel() >= 99)
			score=9999;
		
	}
	
	@Override
	public void draw(Canvas canvas) {
		drawTexts(canvas);
	}

	public long getScore() {
		return score;
	}

	public void setScore(long score) {
		this.score = score;
	}
	
}
