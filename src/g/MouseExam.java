package g;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class MouseExam extends JFrame implements MouseMotionListener, MouseListener
{
	JLabel label = new JLabel("���콺 �巡�� �غ���");
	JTextField jtextfiled = new JTextField();

	public MouseExam()
	{
		this.add(label, "North");
		this.add(jtextfiled, "South");

		this.setSize(300, 400);
		this.setVisible(true);

		//X��ư ������ �� ����
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//�̺�Ʈ ������ �߰�
		this.addMouseMotionListener(this);
		this.addMouseListener(this);

	}

	public static void main(String[] args)
	{
		new MouseExam();
	}

	@Override
	public void mouseDragged(MouseEvent e)
	{
		//�巡�׽� ��ǥ ���
		jtextfiled.setText("(" + e.getX() + ", " + e.getY() + ")");
	}

	@Override
	public void mouseMoved(MouseEvent e)
	{}

	@Override
	public void mouseClicked(MouseEvent e)
	{}

	@Override
	public void mousePressed(MouseEvent e)
	{}

	@Override
	public void mouseReleased(MouseEvent e)
	{}

	@Override
	public void mouseEntered(MouseEvent e)
	{
		//���콺 ���� ������ ���� �޽��� ���
		jtextfiled.setText("���콺 ����?");
	}

	@Override
	public void mouseExited(MouseEvent e)
	{
		//���콺 ���� ������ �ٱ��� �޽��� ���
		jtextfiled.setText("���콺 �ٱ���?");
	}

}
