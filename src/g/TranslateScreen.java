package g;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class TranslateScreen extends JFrame{
   
   private JLabel lbTxt;
   
   public TranslateScreen() {
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      init();
      
      add(lbTxt);
      addKeyListener(new KeyListener() {
         
         @Override
         public void keyTyped(KeyEvent e) {}
         @Override
         public void keyReleased(KeyEvent e) {}
         @Override
         public void keyPressed(KeyEvent e) {
            if(e.getKeyCode() == KeyEvent.VK_F10) {   // �Է��� Ű�� F10�̸�
               capture();                              // capture �Լ� ����
            }
         }
      });
      
      setSize(200, 100);
      setVisible(true);
   }
   
   public void capture() {
      try {
         // Robot Ŭ������ �ڹ� ���α׷��� �ӽ������� �ü�� ������ Ű���� & ���콺 ���� ������ ȹ���� �� �ְ� ���ָ� ���ܰ� �߻��� �� �����Ƿ� try-catch�� ���
         // ���Ŀ� ȭ���� ũ�⸦ �̿��ؼ� Robot Ŭ������ createScreenCapture�� �̿��ؼ� ȭ���� ĸ���� �� ����
         Robot robot = new Robot();
         // Rectangle Ŭ������ ��ǥ �������� �簢���� ���� ��� �� (x, y), �ʺ� �� ���̷� ���ǵǴ� ������ ����
         // Toolkit.getDefaultToolkit().getScreenSize() �� ȭ���� ũ�⸦ ������, getScreenSize�� Dimension���� ���ϵǰ� Rectangle ������ Dimension ����
         Rectangle fullScreenSize = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
         // Robot Ŭ������ createScreenCapture �Լ��� ȭ�� ĸ�ĸ� ����, BufferedImage�� ���� �� ������ �Ű������� RectangleŸ���� ����
         BufferedImage bfImg = robot.createScreenCapture(fullScreenSize);
         
         // ĸ���� ������ �����ϱ� ���� ���� Ȯ��
         // TODO ���� Ŭ���� ����
         File folder = new File("C:\\", "Capture");
         // ������ ���� ��� ���� ����
         // TODO exists() �Լ� ����
         if(!folder.exists()) {
            // TODO mkdir() �Լ� ����
            folder.mkdir();
         }
         
         // ������ �ִ� ���� ���� : ���ϸ��� Ȯ���ؼ� ĸ���� ���� ���� �����ϱ� ����
         String files[] = getFileName(new File("C:\\Capture"));
         int saveFileNum = 1;            // ĸ���� ���� �ѹ����� �ʿ�, ���ϸ� ���� �ѹ����Ͽ� ����
         String saveFileName = "screenshot_";      // ĸ���� ������ ������ �� ���� ���ϸ�
         String saveFileNameAll = null;
         
         // Ȯ���ڸ� ������ ���ϸ��� ������ ����, ������ ������ �𸣹Ƿ� ArrayList�̿�
         ArrayList<String> fileName = new ArrayList<String>();
         
         for (int i = 0; i < files.length; i++) {
            String fileNameAll[] = files[i].split("\\.");
            String fileExtension = fileNameAll[fileNameAll.length-1];
            String fileRealName = files[i].replace("." + fileExtension, "");
            
            fileName.add(fileRealName);
         }
         
         for (int i = 0; i < fileName.size(); i++) {
            if(fileName.get(i).equals(saveFileName + saveFileNum + "")) {
               saveFileNum++;
            }
         }
         
         saveFileNameAll = saveFileName + saveFileNum + ".jpg";
         
         File capturedFile = new File("C:\\Capture", saveFileNameAll);
         
         ImageIO.write(bfImg, "jpg", capturedFile);
         lbTxt.setText("successs : " + saveFileNameAll);
      } catch (Exception e) {
         e.printStackTrace();
      }
      
   }
   
   public String[] getFileName(File file) {
      // TODO ���� Ŭ���� ���� ���� �� ����
      String fileList[] = null;      // �Ѱܹ��� ���(file) �ȿ� �ִ� ���ϸ���� ����
      
      // �Ѱܹ��� ��ΰ� ������ ���, ���� ���� ������ fileList�� ����
      if(file.isDirectory()) {
         fileList = file.list();         //TODO .list() ����
         
         // ���� �� �������� ��Ȯ��
         for (int i = 0; i < fileList.length; i++) {
            File innerFileList = new File(fileList[i]);
            
            if (innerFileList.isDirectory()) {
               getFileName(innerFileList);
            }
         }
      }
      
      return fileList;
   }
   
   private void init() {
      lbTxt = new JLabel("�׽�Ʈ ĸ�� : F10");
   }

   public static void main(String[] args) {
      new TranslateScreen();

   }

}