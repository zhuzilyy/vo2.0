package com.zl.vo_.own.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.netease.nim.uikit.common.ui.widget.SwitchButton;
import com.zl.vo_.R;


public class EaseSwitchButton extends FrameLayout{

    private ImageView openImage;
    private ImageView closeImage;
    //---------------
    private boolean isChoose = false;// 记录当前按钮是否打开,true为打开,flase为关闭

    private boolean isChecked;
    private boolean isChangeOn = false;

    private boolean isInterceptOn = false;

    private OnChangedListener onChangedListener;
    //----------------

    public EaseSwitchButton(Context context, AttributeSet attrs, int defStyle) {
        this(context, attrs);
    }

    public EaseSwitchButton(Context context) {
        this(context, null);
    }
    
    public EaseSwitchButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.EaseSwitchButton);
        Drawable openDrawable = ta.getDrawable(R.styleable.EaseSwitchButton_switchOpenImage);
        Drawable closeDrawable = ta.getDrawable(R.styleable.EaseSwitchButton_switchCloseImage);
        int switchStatus = ta.getInt(R.styleable.EaseSwitchButton_switchStatus, 0);
        ta.recycle();
        
        LayoutInflater.from(context).inflate(R.layout.ease_widget_switch_button, this);
        openImage = (ImageView) findViewById(R.id.iv_switch_open);
        closeImage = (ImageView) findViewById(R.id.iv_switch_close);
        if(openDrawable != null){
            openImage.setImageDrawable(openDrawable);
        }
        if(closeDrawable != null){
            closeImage.setImageDrawable(closeDrawable);
        }
        if(switchStatus == 1){
            closeSwitch();
        }
        
    }
    
    /**
     * is switch open
     */
    public boolean isSwitchOpen(){
        return openImage.getVisibility() == View.VISIBLE;
    }

    public void openSwitch(){
    	openImage.setVisibility(View.VISIBLE);
    	closeImage.setVisibility(View.INVISIBLE);
    }
    
    public void closeSwitch(){
    	openImage.setVisibility(View.INVISIBLE);
    	closeImage.setVisibility(View.VISIBLE);
    }

    //////////-------------

    public void setOnChangedListener(OnChangedListener listener) {// 设置监听器,当状态修改的时候
        isChangeOn = true;
        onChangedListener = listener;
    }

    public interface OnChangedListener {

        abstract void OnChanged(View v, boolean checkState);
    }

    public void setCheck(boolean isChecked) {
        this.isChecked = isChecked;
        isChoose = isChecked;
        if (isChecked == false) {

        }
        invalidate();
    }

    public boolean isChoose() {
        return this.isChoose;
    }

    public boolean getCheck() {
        return this.isChecked;
    }

    public void setInterceptState(boolean isIntercept) {// 设置监听器,是否在重画钱拦截事件,状态由false变true时 拦截事件
        isInterceptOn = isIntercept;
        // onInterceptListener = listener;
    }
    
}
