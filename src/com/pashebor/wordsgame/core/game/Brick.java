package com.pashebor.wordsgame.core.game;


import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import com.pashebor.wordsgame.WordGame;
import com.pashebor.wordsgame.core.Actor;
import com.pashebor.wordsgame.core.SoundManager;
import com.pashebor.wordsgame.core.StaticSprite;
import com.pashebor.wordsgame.core.game.SceneFactory.ArrowType;

public class Brick extends Actor{
	long timeToExplode = 0;
	long elapsed = 0;
	
	long timeToTurnExplode;
	
	boolean exploded = false;
	
	boolean started;
	private boolean selected;
	int selectedId;
	int col;
	int row;
	String letter;
	String trueLetter;
	
	Brick left;
	Brick right;
	Brick top;
	Brick bottom;
	
	Paint paint;
	int TEXT_SIZE = 32;
	
	StaticSprite arrow = null;
	float arrowX;
	float arrowY;
	
	public Brick(String id) {
		super("bric", id);
		
		paint = new Paint();
		
		paint.setColor(Color.BLACK);
		paint.setTypeface(SceneFactory.getSystemFont());
		paint.setTextSize(TEXT_SIZE);//Engine.get().getDisplay().getHeight()/16
		paint.setTextAlign(Paint.Align.CENTER);
		paint.setAntiAlias(true);
	}

	public long getTimeToExplode() {
		return timeToExplode;
	}

	public void setTimeToExplode(long timeToExplode) {
		this.timeToExplode = timeToExplode;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
		
		if(!selected)
			paint.setColor(Color.BLACK);
		else
			paint.setColor(Color.BLUE);
		
		removeNextSelecion();
	}

	public int getCol() {
		return col;

	}

	public void setCol(int col) {
		this.col = col;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public String getLetter() {
		return letter;
	}

	public void setLetter(String letter) {
			this.letter = letter;
	}

	public int getSelectedId() {
		return selectedId;
	}

	public void setSelectedId(int selectedId) {
		this.selectedId = selectedId;
	}

	public Brick getLeft() {
		return left;
	}

	public void setLeft(Brick left) {
		this.left = left;
	}

	public Brick getRight() {
		return right;
	}

	public void setRight(Brick right) {
		this.right = right;
	}

	public Brick getTop() {
		return top;
	}

	public void setTop(Brick top) {
		this.top = top;
	}

	public Brick getBottom() {
		return bottom;
	}

	public void setBottom(Brick bottom) {
		this.bottom = bottom;
	}

	public String getTrueLetter() {
		return trueLetter;
	}
	
	public void setTrueLetter(String trueLetter) {
		this.trueLetter = trueLetter;
	}
	
	@Override
	public void setH(float h) {
		super.setH(h);
		
		paint.setTextSize(getH() - (getH()/2));		
	}
	
	@Override
	public void draw(Canvas canvas) {
		super.draw(canvas);
		
		String drawned = "";
		
		if(((BlockScreen)getScene()).isGameOver()) {
			if(row == 4) {
				
				switch(col) {
				case 1:
					drawned = "O";
					break;
				case 2:
					drawned = "V";
					break;
				case 3:
					drawned = "E";
					break;
				case 4:
					drawned = "R";
					break;
				case 5:
					drawned = "!";
					break;
				case 6:
					drawned = "!";
					break;
				default:
					return;
				}

			} else if(row == 5) {
				switch(col) {
				case 2:
					drawned = "G";
					break;
				case 3:
					drawned = "A";
					break;
				case 4:
					drawned = "M";
					break;
				case 5:
					drawned = "E";
					break;
				default:
					return;					
				}
			
			} else {
				return;
			}
			
		} else {
			drawned = letter;
		}
							
		Rect bounds = new Rect();
			
		paint.getTextBounds(drawned, 0, 1, bounds);
			
		float dy = bounds.height()+((getH()-bounds.height())/2);
	
		canvas.drawText(drawned, getX()+(getW()/2), getY()+dy, paint);
	}	

//	public void printMemory() {
//		ActivityManager actvityManager = (ActivityManager) WordsHunter.get().getSystemService( WordsHunter.ACTIVITY_SERVICE );
//		ActivityManager.MemoryInfo mInfo = new ActivityManager.MemoryInfo ();
//		actvityManager.getMemoryInfo( mInfo );
//		// Print to log and read in DDMS
//		Log.i( "WORDHUNTER", " minfo.availMem " + mInfo.availMem );
//		Log.i( "WORDHUNTER", " minfo.lowMemory " + mInfo.lowMemory );
//		Log.i( "WORDHUNTER", " minfo.threshold " + mInfo.threshold );		
//	}
	
	public void removeNextSelecion() {	
		if(arrow != null)
			arrow.setRemove(true);
		
		arrow = null;
	}
	
	public void setNextSelecion(int nextColSelecion, int nextRowSelecion) {

		ArrowType type;
		
		if(nextRowSelecion < row) {
			if(nextColSelecion < col) {				
				arrowX = getX();
				arrowY = getY()+getH();
				type = ArrowType.leftDown;
				
				
			} else if(nextColSelecion > col) {
				
				type = ArrowType.rightDown;
				
				arrowX = getX()+getW();
				arrowY = getY()+getH();
				
			} else {
				type = ArrowType.down;
				
				arrowX = getX()+getW()/2;
				arrowY = getY()+getH();
			}
		} else if(nextRowSelecion > row) {
			if(nextColSelecion < col) {
				type = ArrowType.leftUp;
				
				arrowX = getX();
				arrowY = getY();
				
			} else if(nextColSelecion > col) {
				type = ArrowType.rightUp;
				
				arrowX = getX()+getW();
				arrowY = getY();
				
			} else {
				type = ArrowType.up;
				
				arrowX = getX()+getW()/2;
				arrowY = getY();
			}			
		} else {
			if(nextColSelecion < col) {
				type = ArrowType.left;
				
				arrowX = getX();
				arrowY = getY()+getH()/2;
				
			} else {
				type = ArrowType.right;
				
				arrowX = getX()+getW();
				arrowY = getY()+getH()/2;
			}						
		}
		
		arrow = SceneFactory.createArrow(arrowX, arrowY, type);
		getScene().add(arrow, 2);
	}
	
	private void testGameOver(Brick b) {
		
		if(((BlockScreen)b.getScene()).isGameOver()) {
			if(b.row == 4) {
				
				switch(b.col) {
				case 1:
				case 2:
				case 3:
				case 4:
				case 5:
				case 6:
					b.explosaoGameOver();
				default:
					return;
				}
				
			} else if(b.row == 5) {
				switch(b.col) {
				case 2:
				case 3:
				case 4:
				case 5:
					b.explosaoGameOver();
					break;
				default:
					return;					
				}				
			} 
		}
	}
	
	public void explosaoGameOver () {
//		if(!getAnimation().getName().equals(PISCANDO_ANIM)) {
//			super.setAnimation(PISCANDO_ANIM);				
//			setSelected(true);
//			setTimeToExplode(10000000);
//			createExplosion();
//			
////			Log.i( "WORDHUNTER", " EXPLOSIONS" + count);
////			printMemory();
//		}		
	}
	
	
	@Override
	public void updateBehaviour(long delay) throws Exception {
		
		testGameOver(this);
		
		if(!started) {
			if(timeToExplode > 0) {
//				super.setAnimation(PISCANDO_ANIM);
//				getAnimation().setRepeat(true);
			}
			started = true;
		}
		
		super.updateBehaviour(delay);	
		
		
		if(((BlockScreen) getScene()).isShowMenu())
			return;
		
		
		if(bottom != null) {
			if(getRectF().bottom > bottom.getY()) {
				setVelY(0);
				setY(bottom.getY()-bottom.getH());
			}		
		}

		if(timeToTurnExplode > 0 || timeToExplode > 0)
			elapsed+=delay;
		
		if(timeToTurnExplode > 0 && timeToTurnExplode < elapsed && timeToExplode == 0) {
			started = false;
			timeToExplode = timeToTurnExplode/2;
			elapsed = 0;
		}
		
		if(timeToExplode > 0) {
			if((timeToExplode/2) < elapsed && getAnimation().getFrameDuration() > 20) {
				getAnimation().setFrameDuration(20);
			}
					
			if(timeToExplode < elapsed && ((BlockScreen)getScene()).canRemoveBric() && WordGame.get().getGameType().equals(GameType.ACTION)) {
				explode();
			}
		}
	}

	
	public void verifyAndExplode() {
		
		if(!selected && timeToExplode < elapsed && WordGame.get().getGameType().equals(GameType.NORMAL)) {
			explode();			
		}
	}

	public void explode() {
		exploded = true;
		((BlockScreen)getScene()).explosiveRemove(this);
		
		createCrossOut();		
	}
	
	private void createCrossOut() {
		if(CrossOut.count < 12) {
			Actor a = SceneFactory.createCrossOut();
			a.setX(getX());
			a.setY(getY());
			((BlockScreen)getScene()).add(a, 2);
		}
		
		SoundManager.playSound(SoundManager.EXPLOSION, 1);
	}
	
	
	@Override
	public void setAnimation(String name) {
		if(timeToExplode <= 0) {
			super.setAnimation(name);
		}
	}
	
	@Override
	public void setRemove(boolean remove) {
		super.setRemove(remove);
		removeNextSelecion();
	}
	
	public void destroy() {
		SceneFactory.removeLetter(trueLetter);
	}	
}
