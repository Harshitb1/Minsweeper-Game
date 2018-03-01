package com.example.sagar.minsweeper;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.view.View;

/**
 * Created by SAGAR on 03-02-2018.
 */

public class MinsweeperButton extends AppCompatButton {
     int row,col;
    int val;
    boolean flag;

    public MinsweeperButton(Context context, int i,int j) {
        super(context);
        val=0;
        row=i;
        col=j;
        flag=false;
    }

    public void setVal(int v){
        val=v;
    }
    public int getRow(){
        return row;
    }
    public int getcol(){
        return col;
    }
    public int getVal(){
        return val;
    }
    public void setflag( boolean b){
        flag=b;
    }
    public boolean getflag(){
        return flag;
    }


}
