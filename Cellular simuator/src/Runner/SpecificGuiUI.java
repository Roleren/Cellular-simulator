//package Runner;
//
//import java.awt.BorderLayout;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.util.ArrayList;
//
//import javax.swing.JButton;
//import javax.swing.JPanel;
//import javax.swing.Timer;
//
//import Atoms.atom;
//
//public class SpecificGuiUI extends JPanel implements ActionListener {
//	int x;
//	int y;
//	Painter mainFrame;
//	JPanel creations, statusBar;
//	JButton restart, pause, exit;
//	Timer timer;
//	int step;
//	Simulator simulator;
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = 1L;
//	
//	public  SpecificGuiUI(int x, int y, ArrayList<atom> possibleObjects, Simulator simulator) {
//		//Initialization
//		this.x = x;
//		this.y = y; 
//		this.simulator = simulator;
//		timer = new Timer(100, this);
//		mainFrame = new Painter(simulator);
//		creations = new JPanel();
//		statusBar = new JPanel();
//		
//		//Additions to JFrame
//		add(mainFrame,BorderLayout.CENTER);
//		add(creations,BorderLayout.WEST);
//		add(statusBar,BorderLayout.SOUTH);
//		//Buttons
//		JButton restart = new JButton("Restart");
//		restart.addActionListener(this);
//		restart.setAlignmentX(CENTER_ALIGNMENT);
//		JButton pause = new JButton("Pause");
//		pause.addActionListener(this);
//		pause.setAlignmentX(CENTER_ALIGNMENT);
//		JButton exit = new JButton("Exit");
//		exit.addActionListener(this);
//		exit.setAlignmentX(CENTER_ALIGNMENT);
//		
//		statusBar.add(restart);
//		statusBar.add(pause);
//		statusBar.add(exit);
//		
//		
//		timer.start();
//	}
//	@Override
//	public void actionPerformed(ActionEvent arg0) {
//		if(arg0.getSource() == pause){
//			if(timer.isRunning()){
//				pause.setText("fortsett");
//				timer.stop();
//			}
//			else{
//				pause.setText("Pause");
//				timer.start();
//			}
//		}
//		else if(arg0.getSource() == exit){
//			System.exit(0);
//		}
//		else if(arg0.getSource() == timer){
//			simulator.nesteSteg();
//			step++;
//			repaint();
//		}
//		
//	}
//
//}
