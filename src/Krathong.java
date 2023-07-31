import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Krathong {
	public static void main(String[] args) {
		MyKrathong frame = new MyKrathong();
		frame.setVisible(true);
	}
}
class MyKrathong extends JFrame
{
	Loy pn = new Loy();
	public MyKrathong() {

		
		setTitle("ลอยกระทงคนโสด");
		setSize(828, 315);
		setResizable(false);
		setLocationRelativeTo(null);
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		add(pn);
	}
}
class Loy extends JPanel 
{
	Image bg =  Toolkit.getDefaultToolkit()
			.createImage(System.getProperty("user.dir")
			+ File.separator + "Bg.png");
	
	Image krathong =  Toolkit.getDefaultToolkit()
			.createImage(System.getProperty("user.dir")
			+ File.separator + "krathong.png");
	
	Image boom =  Toolkit.getDefaultToolkit()
			.createImage(System.getProperty("user.dir")
			+ File.separator + "fire.gif");
	
	
	int [] thongX = new int[5];
	int [] thongY = new int[5];
	boolean [] fire = new boolean[5];
	
	public Loy() {
		setSize(828, 315);
		setLayout(null);
		
		for(int i= 0;i < thongX.length ;i++)
		{
			thongX[i] = new Random().nextInt(500)+0;
			thongY[i] = new Random().nextInt(20)-20;
		}
		
		Timer time = new Timer();
		time.scheduleAtFixedRate(new TimeKrathong(this), 0, 100);
		
		addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				//System.out.println(e.getX()+ " " + e.getY());
				
				for(int i = 0 ;i < thongX.length ; i++)
				{
					//System.out.println(thongX[i]+ " " + thongY[i]+200);
					if((e.getX() >= thongX[i] && e.getX() <= thongX[i]+50) && (e.getY() >= thongY[i]+200 && e.getY() <= thongY[i]+250))
					{
						fire[i] = true;
					}
				}
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}
	
	@Override
	public void paint(Graphics g) {
		g.drawImage(bg, 0, 0, this);
		for(int i = 0;i < 5; i++)
		{
			// + 200 เพราะ จะได้อยู่ระหว่างแม่น้ำ
			g.drawImage(krathong, thongX[i], 200 + thongY[i]  , this); // Start 500 End 0
			if(fire[i] == true)
			{
				g.drawImage(boom, thongX[i], thongY[i]+200  ,55 , 55, this); 
				//System.out.println("x");
			}

		}
		
	}

}
class TimeKrathong extends TimerTask
{
	Loy pn;
	TimeKrathong(Loy pn)
	{
		this.pn = pn;
	}
	@Override
	public void run() {
		for(int i = 0 ;i < pn.thongX.length; i++)
		{
			pn.thongX[i] += -1;
			if(pn.thongX[i] < -50) // ใช้ -50 เพราะอยากให้ กระทงมันลอยออกไปนอกเฟรมนิดนึงก่อนจะได้รู้สึกว่ามันลอยหายไปแล้ว
			{
				pn.thongX[i] = 500;
				pn.fire[i] = false;
				pn.thongY[i] = new Random().nextInt(50)-20;
			}
		}
		pn.repaint();
	}
}