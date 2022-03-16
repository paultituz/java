import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 
import javax.swing.*;
 
import org.ietf.jgss.GSSManager;
 
import java.io.*;
import java.text.BreakIterator;
import java.util.*;


 
 
public class test1 extends JFrame{
 
	JPanel mainPanel=new JPanel();
	JButton button[][]=new JButton[5][5];
	int data[][]=new int[5][5];
	ArrayList<String> num=new ArrayList<String>();
	
	
	public test1(){
		super("5x5排格子");
		Container c=this.getContentPane();
		mainPanel.setLayout(new GridLayout(5, 5, 4, 4));
		c.add(mainPanel);
		for(int i=0;i<5;i++){
			for(int j=0;j<5;j++){
                button[i][j]=new JButton();
                mainPanel.add(button[i][j]);
                button[i][j].addActionListener(new Handler());
                if(i == 0 && j==4 || i==4 && j==0){
                    data[i][j]=-1;
                }else{
    				data[i][j]=0;
                }
			}
		}
		InitNum();
	}
	
	void InitNum(){
		num.clear();
		for(int i=0;i<23;i++){
			num.add(String.valueOf(i));
		}
		for(int i=0;i<5;i++){
			for(int j=0;j<5;j++){
                if(!(i == 0 && j==4 || i==4 && j==0)){
    				Random r=new Random();
    				int index=r.nextInt(num.size());
    				String string=num.get(index);
    				if(string.equals("0")){
    					button[i][j].setText("");
    					data[i][j]=0;
    				}else{
    					button[i][j].setText(string);
    					data[i][j]=Integer.parseInt(string);
    				}
    				num.remove(index);
                }
			}
		}
	}
	
	class Handler implements ActionListener{
 
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			if(e.getActionCommand().length()==0)
				return;
			int n=Integer.parseInt(e.getActionCommand());
			int locationx = 0,locationy = 0;
			int location = 0;
			int aim=-1;//记录空白按钮位置
			for(int i=0;i<5;i++){
				for(int j=0;j<5;j++){
					if(data[i][j]==n){
						locationx=i;
						locationy=j;
						location=i*5+j;
					}
				}
			}
			int cand[]={location-1,location+1,location-5,location+5};
			for(int i=0;i<4;i++){
				if(check(cand[i])){
					aim=cand[i];
				}
			}
			if(aim>=0&&aim<25){
				int temp=data[locationx][locationy];
				button[aim/5][aim%5].setText(String.valueOf(temp));
				data[aim/5][aim%5]=temp;
				button[locationx][locationy].setText("");
				data[locationx][locationy]=0;
			}

			if(isOver()){
				int choice = JOptionPane.showConfirmDialog(null,JOptionPane.YES_NO_OPTION);
			    if(choice == JOptionPane.YES_OPTION)
			    	InitNum();
			    else
			    	System.exit(0);
			}
		}
		
		boolean check(int index){
			if(index>=0 && index<25 && data[(index)/5][(index)%5]==0)
				return true;
			return false;
		}
		
		boolean isOver(){

			int i,j;
			for(i=0;i<25;i++){
				if(i == 4 || i == 20 || i==24){}
				else if(i <= 4 && data[i/5][i%5]!=i+1 || i > 4 && i <=20 && data[i/5][i%5]!=i || i > 20 && data[i/5][i%5]!=i-1)
					break;
			}
			if(i==25)
				return true;
			
			return false;
		}
		
	}
	
	public static void main(String[] args){
		test1 test=new test1();
		test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		test.setSize(500,500);
		test.setVisible(true);
	}
	
}
