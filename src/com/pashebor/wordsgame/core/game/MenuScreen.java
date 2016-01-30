package com.pashebor.wordsgame.core.game;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader.TileMode;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.view.MotionEvent;
import com.example.wordsgame.R;
import com.pashebor.wordsgame.core.Factory;
import com.pashebor.wordsgame.WordGame;
import com.pashebor.wordsgame.core.Scene;
import com.pashebor.wordsgame.core.StaticSprite;

public class MenuScreen extends Scene implements OnCompletionListener{

	PanelInicial panel;
	private Paint backg;
	private LinearGradient gradient;
	Bitmap back;
	
	public MenuScreen() {
		
		float w = WordGame.get().getDisplay().getWidth();
		float h = WordGame.get().getDisplay().getHeight();
		
		createLayer();
		createLayer();
		
		StaticSprite stp = new StaticSprite("IMG_FUNDO");
		stp.setImage(BitmapFactory.decodeResource(Factory.get().getRes(), R.drawable.block_letter));
		
		panel = new PanelInicial();
//		painel.setX((w-480)/2);
		add(panel, 1);
		add(stp, 0);
		
		gradient = new LinearGradient(0, 0, w, h, Color.rgb(255,255,255), Color.rgb(255, 255, 255), TileMode.REPEAT);
		backg = new Paint();
		backg.setShader(gradient);		
	
		stp.setY((h-stp.getH())/2);
		stp.setX((w-stp.getW())/2);
		
		isLevel = false;
	}

	@Override
	public void initialize() {
		WordGame.get().startMusic();
	}
	
	@Override
	protected Paint getPaint() {
		return backg;
	}

//	@Override
//	public void draw(Canvas canvas) {
//
//		super.draw(canvas);		
//	}
	
	@Override
	public void updateBehaviour(long delay) throws Exception {

	}

	@Override
	public void onBack() {
//		Engine.get().finish();
		System.exit(0);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {

		if (event.getAction() == MotionEvent.ACTION_UP) {

			float x = event.getX(), y = event.getY();
			panel.touchReleased((int) x, (int) y);

		} else if (event.getAction() == MotionEvent.ACTION_DOWN) {

			float x = event.getX(), y = event.getY();
			panel.touchPressed((int) x, (int) y);
		}

		return true;
	}

	public void onCompletion(MediaPlayer mp) {
		// TODO Auto-generated method stub
		
	}
}
