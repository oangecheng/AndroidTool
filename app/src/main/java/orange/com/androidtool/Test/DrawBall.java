package orange.com.androidtool.Test;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.text.AttributedCharacterIterator;

/**
 * Created by Orange on 2016/10/9.
 */
public class DrawBall extends View {
    public float currentX = 40;
    public float currentY = 50;

    Paint p = new Paint();

    public DrawBall(Context context){
        super(context);
    }

    public DrawBall(Context context, AttributeSet set){
        super(context, set);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        p.setColor(Color.RED);

        canvas.drawCircle(currentX, currentY, 15, p);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        currentX = event.getX();
        currentY = event.getY();

        invalidate();

        return true;
    }
}
