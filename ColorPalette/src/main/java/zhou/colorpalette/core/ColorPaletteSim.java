package zhou.colorpalette.core;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by bj
 */
public class ColorPaletteSim extends LinearLayout{

    private ColorBoardSim colorBoard;//色块拾取器
    private ColorPicker colorPicker;//色轮拾取器
    private View currColor;//上次选择的颜色，当前选择的颜色
    private int alpha=255,light=255,red,green,blue,selectColor,lastSelectColor=Color.WHITE;
    private OnColorSelectListener onColorSelectListener;
    private ColorPicker.OnColorSelectOnTouchUPListener onColorSelectOnTouchUPListener;

    public ColorPaletteSim(Context context) {
        this(context, null);
    }

    public ColorPaletteSim(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ColorPaletteSim(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        setOrientation(VERTICAL);
        setGravity(Gravity.CENTER_HORIZONTAL);
        setPadding(5,10,5,2);

        colorBoard=new ColorBoardSim(context);
        colorPicker=new ColorPicker(context);
        currColor=new View(context);

        currColor.setBackgroundColor(Color.WHITE);

        //ColorPicker
        LayoutParams p1=new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,0,1);
        //SeekbarLayout
        LayoutParams p2=new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        //ColorLayout
        LayoutParams p3=new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 100);
        //ColorBoardLayout
        LayoutParams p4=new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        LayoutParams p33=new LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT,1);

        p33.setMargins(10,20,10,20);
        p2.setMargins(10,10,10,10);

        LinearLayout colorColor=new LinearLayout(context);
        colorColor.setMinimumHeight(120);
        colorColor.setOrientation(HORIZONTAL);

        colorColor.addView(currColor,p33);

        addView(colorPicker, p1);
        addView(colorColor,p3);
        addView(colorBoard,p4);

        setClickable(true);

        initEvent();

    }

    private void initEvent() {
        colorPicker.setOnColorSelectListener(new ColorPicker.OnColorSelectListener() {
            @Override
            public void onColorSelect(int color) {
                alpha=Color.alpha(color);
                red=Color.red(color);
                green=Color.green(color);
                blue=Color.blue(color);

                reflashColor();
            }
        });
        colorPicker.setOnColorSelectOnTouchUPListener(new ColorPicker.OnColorSelectOnTouchUPListener() {
            @Override
            public void onColorSelect(int color, boolean inCicle) {
                if (inCicle){
                    if (onColorSelectOnTouchUPListener!=null)
                        onColorSelectOnTouchUPListener.onColorSelect(color,true);
                }else {
                    if (onColorSelectOnTouchUPListener!=null)
                        onColorSelectOnTouchUPListener.onColorSelect(selectColor,false);
                }
            }
        });

        colorBoard.setOnColorSelectListener(new ColorBoardSim.OnColorSelectListener() {
            @Override
            public void onColorSelect(int color) {
                selectColor=color;
                if (onColorSelectOnTouchUPListener!=null)
                    onColorSelectOnTouchUPListener.onColorSelect(selectColor,true);
                currColor.setBackgroundColor(selectColor);
            }
        });
    }

    private int getColor(){
        float d=light/255.0f;
        return Color.argb(alpha, (int) (red * d), (int) (green * d), (int) (blue * d));
    }

    private void reflashColor(){
        selectColor=getColor();
        currColor.setBackgroundColor(selectColor);
    }

    public void setCurrColor(int color){
        this.selectColor=color;
        currColor.setBackgroundColor(color);
    }

    public int getSelectColor(){
        return selectColor;
    }

    public static interface OnColorSelectListener{
        public void onColorSelect(int color);
    }

    public OnColorSelectListener getOnColorSelectListener() {
        return onColorSelectListener;
    }

    public void setOnColorSelectListener(OnColorSelectListener onColorSelectListener) {
        this.onColorSelectListener = onColorSelectListener;
    }

    public ColorPicker.OnColorSelectOnTouchUPListener getOnColorSelectOnTouchUPListener() {
        return onColorSelectOnTouchUPListener;
    }

    public void setOnColorSelectOnTouchUPListener(ColorPicker.OnColorSelectOnTouchUPListener onColorSelectOnTouchUPListener) {
        this.onColorSelectOnTouchUPListener = onColorSelectOnTouchUPListener;
    }

    public void recycle(){
        if(colorPicker!=null){
            if(!colorPicker.isRecycled()){
                colorPicker.recycle();
            }
        }
    }

    public boolean isRecycle(){
        return colorPicker==null||colorPicker.isRecycled();
    }
}
