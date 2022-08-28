import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SettingFrame extends JFrame {
	ProgressPanel proPanel = new ProgressPanel();
	JLabel log = new JLabel("Test", JLabel.CENTER);

	public SettingFrame() throws Exception {
		setTitle("Database Setting");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(Color.black);
		setSize(500, 500);
		setVisible(true);

//		add(proPanel);
//		add(log, BorderLayout.SOUTH);
	}
}

class ProgressPanel extends JPanel {
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.orange);

		try {
			TableSetting.createDB();
			g.fillArc(0, 0, this.getWidth(), this.getHeight(), 90, -90);
			revalidate();
			Thread.sleep(500);

			TableSetting.createGrants();
			g.fillArc(0, 0, this.getWidth(), this.getHeight(), 0, -90);
			revalidate();
			Thread.sleep(500);

			TableSetting.createTable();
			g.fillArc(0, 0, this.getWidth(), this.getHeight(), -90, -90);
			revalidate();
			Thread.sleep(500);

			TableSetting.createData();
			g.fillArc(0, 0, this.getWidth(), this.getHeight(), -180, -90);
			revalidate();
		} catch (Exception e) {
			
		}
	}

	public void paint() {

	}
}
