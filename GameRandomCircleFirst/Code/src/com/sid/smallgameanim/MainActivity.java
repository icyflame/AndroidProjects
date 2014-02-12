package com.sid.smallgameanim;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.TextPaint;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class MainActivity extends Activity implements OnTouchListener {

	private float cx, cy, radius;
	private int score, wi, he;
	private Paint paint;
	private TextPaint textpaint;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initVariables();

		View v = new View(this) {

			@Override
			protected void onDraw(Canvas canvas) {

				wi = this.getWidth();
				he = this.getHeight();

				Log.i("Widths", "Canvas : " + canvas.getHeight() + ", View: "
						+ this.getHeight());

				canvas.drawCircle(cx, cy, radius, paint);
				canvas.drawText("Score : " + score, 40, 40, textpaint);

				postInvalidateDelayed(1000);

				Runnable action = new Runnable() {

					@Override
					public void run() {
						selectNewCenter(wi, he);

					}

				};

				postDelayed(action, 1000);

				// canvas.drawBitmap(g, 0, 0, null);
				super.onDraw(canvas);
			}

			private void selectNewCenter(int wi, int he) {
				cx = (float) (Math.random() * 1000);
				cy = (float) (Math.random() * 1000);

				cx %= wi;
				cy %= he;
			}
		};

		v.setOnTouchListener(this);

		setContentView(v);

	}

	private void initVariables() {
		cx = cy = 100;

		radius = 80;

		textpaint = new TextPaint();

		textpaint.setTextSize(30);

		paint = new Paint();

		paint.setColor(Color.BLACK);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onTouch(View arg0, MotionEvent event) {

		float x = event.getX();

		float y = event.getY();

		Log.i("Scores", cx + ", " + cy + " : " + x + ", " + y);

		// cx = x;
		// cy = y;

		double dist = Math.sqrt((cx - x) * (cx - x) + (cy - y) * (cy - y));

		if (dist < radius) {

			Log.i("Score", "Score has increased!");
			score += 1;
		}

		return false;
	}

}