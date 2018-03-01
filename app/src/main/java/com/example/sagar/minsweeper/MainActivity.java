package com.example.sagar.minsweeper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnLongClickListener {

    static int size =5;
    static int mines =5;
    static int count =0;
    static int score=0;
    static boolean hasStarted=false;
    LinearLayout rootLayout ;
    MinsweeperButton board[][];
    int[] di={-1,-1,-1,0,0,1,1,1};
    int[] dj={-1,0,1,-1,1,-1,0,1};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rootLayout= findViewById(R.id.rootLayout);
        Intent intent = getIntent();
        String  str = intent .getStringExtra("name");
        Toast.makeText(this,"Welcome "+ str,Toast.LENGTH_SHORT).show();
        init();
    }
    public void init(){
        board= new MinsweeperButton[size][size];
        score=0;
        hasStarted=false;
        count=0;
        setup();
    }
    public void setup(){
        rootLayout.removeAllViews();
        for(int i = 0;i<size;i++){
            LinearLayout rowLayout = new LinearLayout(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,0,1);
            rowLayout.setLayoutParams(params);
            rowLayout.setOrientation(LinearLayout.HORIZONTAL);
            for(int j = 0;j<size;j++){
                MinsweeperButton button = new MinsweeperButton(this,i,j);
                LinearLayout.LayoutParams buttonParams = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT,1);
                button.setLayoutParams(buttonParams);
                button.setOnClickListener(this);
                button.setOnLongClickListener(this);
                rowLayout.addView(button);
                board[i][j] = button;
            }
            rootLayout.addView(rowLayout);







        }

    }

    private void setupMines(int row, int col) {
        Random r= new Random();
        int ri,rj;
        for(int i=0;i<mines;i++){
            ri=r.nextInt(size);
            rj=r.nextInt(size);
            int x= board[ri][rj].getVal();
            if(ri!=row&&rj!=col&&x!=-1) {
                board[ri][rj].setVal(-1);
                setneighbours(ri, rj);
            }
            else
                i--;
        }
    }

    public void setneighbours(int row, int col){
        for (int i=0;i<8;i++){
            if(row+di[i]<size&&col+dj[i]<size&&row+di[i]>=0&&col+dj[i]>=0) {
                int val = board[row + di[i]][col + dj[i]].getVal();
                if (val != -1) {
                    board[row + di[i]][col + dj[i]].setVal(val + 1);
                }
            }
        }
    }


    @Override
    public void onClick(View v) {
        MinsweeperButton button = (MinsweeperButton) v;
        if(!hasStarted){
            hasStarted=true;
            int row=button.getRow();
            int col= button.getcol();
            setupMines(row,col);
        }

        if(!button.getflag()) {
            int val = button.getVal();
            if (val == -1) {
                Toast.makeText(this, "GAME OVER " + score, Toast.LENGTH_SHORT).show();
                gameover();


            }
            button.setEnabled(false);
            if (val > 0) {
                score += val;
            }
            count++;
            if (count == (size * size) - mines) {
                Toast.makeText(this, "YOU WON " + score, Toast.LENGTH_SHORT).show();
                gameover();
            }
            if (val > 0) {

                button.setText(String.valueOf(val));
            }
            if (val == 0) {
                button.setBackgroundResource(R.drawable.accessed_bg);

                int row = button.getRow();
                int col = button.getcol();
                for (int i = 0; i < 8; i++) {
                    if (row + di[i] < size && col + dj[i] < size && row + di[i] >= 0 && col + dj[i] >= 0) {
                        if (board[row + di[i]][col + dj[i]].isEnabled())

                            onClick(board[row + di[i]][col + dj[i]]);
                    }
                }
            }
        }

    }

    private void gameover() {

        for(int i=0;i<size;i++){
            for (int j=0;j<size;j++){
                int val=board[i][j].getVal();
                if(val==-1)
                    board[i][j].setText("X");
                board[i][j].setEnabled(false);
            }
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.i1){
           size=5;
           mines=5;
          // item.setChecked(true) ;
           init();
        }
        else if(item.getItemId() == R.id.i2){
            size=10;
            mines=15;
           // item.setChecked(true) ;

            init();
        }
        else if (item.getItemId()==R.id.i3){
            size=15;
            mines=25;
          //  item.setChecked(true) ;
           init();
        }
        if(item.getItemId()==R.id.i4){
            init();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onLongClick(View view) {
        MinsweeperButton button = (MinsweeperButton) view;
        if(!button.getflag()){
           button.setflag(true);
            button.setText("flag");
        }
        else{
            button.setflag(false);
            button.setText(null);
        }
        return true;
    }
}
