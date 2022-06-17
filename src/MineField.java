import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.print.Printable;
import java.io.File;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;

public class MineField implements MouseListener{
	JFrame frame;
	Btn[][] board = new Btn[10][10];
	int openButton=0;
	
	
public MineField() {
	
	frame = new JFrame("MineField");
	frame.setSize(600,600);
	
	frame.setLayout(new GridLayout(10,10));
	

	
	for(int row = 0;row <board.length;row++) {
		for(int column = 0; column < board[0].length;column++) {
			Btn b = new Btn(row, column);
			frame.add(b);
			b.addMouseListener(this);
			board[row][column]=b;	
			
		}
		
	}
	
	
	
	generateMine();
	updateCount();
	printMine();
	
	frame.setVisible(true);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
}

public void generateMine() {
	int i =0;
	while(i<10){
		
		int randRow = (int)(Math.random() * board.length);
		int randCol = (int) (Math.random() * board[0].length);
		while(board[randRow][randCol].isMine()) {
			randRow = (int) (Math.random() * board.length);
			randCol = (int) (Math.random() * board[0].length);
		}
		board[randRow][randCol].setMine(true);
		i++;
	}
	
}
public void print() {
	for(int row = 0; row <board.length; row++) {
		for(int column = 0; column < board[0].length; column++) {
			if(board[row][column].isMine()) {
			board[row][column].setIcon(new ImageIcon(getClass().getResource("mine.png")));
			}else{
				board[row][column].setText(board[row][column].getCount()+ "");
				board[row][column].setEnabled(false);
			}
		}
	   }	
	}
public void printMine() {
for(int row = 0; row < board.length; row++){
	for (int col = 0; col< board[0].length; col++) {
		if(board[row][col].isMine()) {
			board[row][col].setIcon(new ImageIcon(getClass().getResource("mine.png")));
		}
	}
}
}


public void updateCount() {
	for(int row = 0; row <board.length; row++) {
		for(int column = 0; column < board[0].length; column++) {
			if(board[row][column].isMine()) {
				counting(row, column);
		
			}
		  }
	}
       }

public void counting(int row,int column) {
	for(int i = row -1; i<=row +1;i++) {
		for (int k=column -1; k <= column +1;k++){
			try {
			int value = board[i][k].getCount();
			board[i][k].setCount(++value);
		}
		catch(Exception e) {
			
		}
			
		
	}
	}
}
public void open(int row,int col) {
	if(row < 0 || row >=board.length || col < 0 || col>=board[0].length || board[row][col].getText().length() > 0 || board[row][col].isEnabled() == false) {
		return;
	}else if(board[row][col].getCount()!=0){
		board[row][col].setText(board[row][col].getCount()+"");
		board[row][col].setEnabled(false);
		openButton++;
		
	}else {
		board[row][col].setEnabled(false);
		open(row-1, col);
		open(row+1, col);
		open(row, col-1);
		open(row, col+1);
		openButton++;
	}

	}


@Override
public void mouseClicked(MouseEvent e) {
	Btn b = (Btn) e.getComponent();
	if(e.getButton()==1){
		
		if(b.isMine()) {
			JOptionPane.showMessageDialog(frame,"Game Over!");
			print();
		}else {
			open(b.getRow(), b.getColumn());
			if(openButton == (board.length * board[0].length)-10) {
				JOptionPane.showMessageDialog(frame, "Congratulations, you win!");
				print();
			}
		}
	}else if(e.getButton()==3) {
	if(!b.isFlag()) {
		b.setIcon(new ImageIcon(getClass().getResource("flag.png")));
		b.setFlag(true);
	}else {
		b.setIcon(null);
		b.setFlag(false);
	}	
	
	}

}

@Override
public void mousePressed(MouseEvent e) {
	// TODO Auto-generated method stub
	
}

@Override
public void mouseReleased(MouseEvent e) {
	// TODO Auto-generated method stub
	
}

@Override
public void mouseEntered(MouseEvent e) {
	// TODO Auto-generated method stub
	
}

@Override
public void mouseExited(MouseEvent e) {
	// TODO Auto-generated method stub
	
}

}
