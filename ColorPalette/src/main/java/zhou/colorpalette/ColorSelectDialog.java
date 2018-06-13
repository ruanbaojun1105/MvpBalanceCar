package zhou.colorpalette;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;

import zhou.colorpalette.core.ColorPaletteSim;
import zhou.colorpalette.core.ColorPicker;

/**
 * 颜色选择器：ArtDialog
 */
public class ColorSelectDialog {

    private AlertDialog dialog;
    private ColorPaletteSim colorPalette;
    private OnColorSelectListener onColorSelectListener;
    private int color=-1;

    public ColorSelectDialog(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        colorPalette = new ColorPaletteSim(context);
        colorPalette.setOnColorSelectOnTouchUPListener(new ColorPicker.OnColorSelectOnTouchUPListener() {
            @Override
            public void onColorSelect(int color, boolean inCicle) {
//                Log.e("颜色板抬起事件","------------"+color+"-----"+(inCicle?"在圈内":"在圈外"));
                if (onColorSelectListener != null) {
                    onColorSelectListener.onSelectFinish(color);
                }
            }
        });
        builder.setView(colorPalette);
        builder.setCancelable(true);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (onColorSelectListener != null) {
                    color=colorPalette.getSelectColor();
                    onColorSelectListener.onSelectFinish(color);
                }
            }
        });
        builder.setNegativeButton("取消", null);
        dialog = builder.create();
        dialog.setCanceledOnTouchOutside(true);
    }


    public void show() {
        if(color==-1){
            color=Color.BLACK;
        }
        colorPalette.setCurrColor(color);
        dialog.show();
    }

    public static interface OnColorSelectListener {
        public void onSelectFinish(int color);
    }

    public OnColorSelectListener getOnColorSelectListener() {
        return onColorSelectListener;
    }

    public void setOnColorSelectListener(OnColorSelectListener onColorSelectListener) {
        this.onColorSelectListener = onColorSelectListener;
    }

    public void setLastColor(int color){
        colorPalette.setCurrColor(color);
    }
}
