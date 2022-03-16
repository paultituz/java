import java.awt.*;
import java.awt.event.*;

public class draw extends Frame implements ActionListener,MouseMotionListener, MouseListener
{
	static draw frm=new draw();
	static MenuBar mb=new MenuBar();
	static Menu m=new Menu("Graph");

	static MenuItem m1 = new MenuItem("line");
	static MenuItem m2=new MenuItem("rectangle");
	static MenuItem m3=new MenuItem("round rectangle");
	static MenuItem m4=new MenuItem("oval");
	static MenuItem m5=new MenuItem("arc");

	
	int xs,ys,xe,ye,mode=0;


	public static void main(String args[]){


		frm.addMouseListener(frm);
		frm.addMouseMotionListener(frm);
		frm.setSize(500,700);
		frm.setVisible(true);
		frm.setMenuBar(mb);

		mb.add(m);
		m.add(m1);
		m.add(m2);
		m.add(m3);
		m.add(m4);
		m.add(m5);

		m1.addActionListener(frm);
		m2.addActionListener(frm);
		m3.addActionListener(frm);
		m4.addActionListener(frm);
		m5.addActionListener(frm);

		frm.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});

	}

	public void actionPerformed(ActionEvent e){
		MenuItem c=(MenuItem) e.getSource();

		if(c==m1){
			mode=1;
		}
		else if(c==m2){
			mode=2;
		}
		else if(c==m3){
			mode=3;
		}
		else if(c==m4){
			mode=4;
		}
		else if(c==m5){
			mode=5;
		}
	}

	public void mousePressed(MouseEvent e){
		xs=e.getX();
		ys=e.getY();
	}

	public void mouseReleased(MouseEvent e){
		xe=e.getX();
		ye=e.getY();
		Graphics g=getGraphics();
		paint(g);
	}

	public void mouseDragged(MouseEvent e){
		Graphics g=getGraphics();

		if(mode==1){

			xe=e.getX();
			ye=e.getY();
			g.setColor(Color.black);
			g.drawLine(xs,ys,xe,ye);
			xs=xe;
			ys=ye;

		}

		else if(mode==5){
			
			g.drawArc(xs,ys,xe,ye,0,180);
			
		}
				
	}
	public void paint(Graphics g){

		if(xs > xe){
			int temp = xs;
			xs = xe;
			xe = temp;
		}
		if(ys > ye){
			int temp = ys;
			ys = ye;
			ye = temp;
		}

		g.setColor(Color.blue);
		switch(mode){
			case 2:
				g.fillRect(xs,ys,xe-xs,ye-ys);
				break;
			case 3:
				g.fillRoundRect(xs,ys,xe-xs,xe-xs,10,10);
				break;
			case 4:
				g.fillOval(xs,ys,xe-xs,ye-ys);
				break;

		}

		g.setColor(Color.black);
		switch(mode){
			case 2:
				g.drawRect(xs,ys,xe-xs,ye-ys);
				break;
			case 3:
				g.drawRoundRect(xs,ys,xe-xs,xe-xs,10,10);
				break;
			case 4:
				g.drawOval(xs,ys,xe-xs,ye-ys);
				break;

		}

	}

	public void mouseMoved(MouseEvent e){}
	public void mouseEntered(MouseEvent e){}
	public void mouseExited(MouseEvent e){}
	public void mouseClicked(MouseEvent e){}
	

}