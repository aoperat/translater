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
            if(e.getKeyCode() == KeyEvent.VK_F10) {   // 입력한 키가 F10이면
               capture();                              // capture 함수 실행
            }
         }
      });
      
      setSize(200, 100);
      setVisible(true);
   }
   
   public void capture() {
      try {
         // Robot 클래스는 자바 프로그램이 임시적으로 운영체제 수준의 키보드 & 마우스 제어 권한을 획득할 수 있게 해주며 예외가 발생할 수 있으므로 try-catch문 사용
         // 이후에 화면의 크기를 이용해서 Robot 클래스의 createScreenCapture를 이용해서 화면을 캡쳐할 수 있음
         Robot robot = new Robot();
         // Rectangle 클래스는 좌표 공간에서 사각형의 왼쪽 상단 점 (x, y), 너비 및 높이로 정의되는 영역을 지정
         // Toolkit.getDefaultToolkit().getScreenSize() 은 화면의 크기를 가져옴, getScreenSize는 Dimension으로 리턴되고 Rectangle 생성시 Dimension 전달
         Rectangle fullScreenSize = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
         // Robot 클래스의 createScreenCapture 함수가 화면 캡쳐를 지원, BufferedImage로 받을 수 있으며 매개변수로 Rectangle타입을 받음
         BufferedImage bfImg = robot.createScreenCapture(fullScreenSize);
         
         // 캡쳐한 파일을 저장하기 위한 폴더 확인
         // TODO 파일 클래스 설명
         File folder = new File("C:\\", "Capture");
         // 폴더가 없을 경우 폴더 생성
         // TODO exists() 함수 설명
         if(!folder.exists()) {
            // TODO mkdir() 함수 설명
            folder.mkdir();
         }
         
         // 폴더에 있는 파일 저장 : 파일명을 확인해서 캡쳐한 파일 명을 생성하기 위함
         String files[] = getFileName(new File("C:\\Capture"));
         int saveFileNum = 1;            // 캡쳐한 파일 넘버링에 필요, 파일명 끝에 넘버링하여 구분
         String saveFileName = "screenshot_";      // 캡쳐한 파일을 저장할 때 사용될 파일명
         String saveFileNameAll = null;
         
         // 확장자를 제외한 파일명을 저장할 공간, 파일의 갯수를 모르므로 ArrayList이용
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
      // TODO 파일 클래스 설명 보충 후 정리
      String fileList[] = null;      // 넘겨받은 경로(file) 안에 있는 파일명들을 저장
      
      // 넘겨받은 경로가 폴더일 경우, 폴더 안의 파일을 fileList에 저장
      if(file.isDirectory()) {
         fileList = file.list();         //TODO .list() 설명
         
         // 파일 안 폴더여부 재확인
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
      lbTxt = new JLabel("테스트 캡쳐 : F10");
   }

   public static void main(String[] args) {
      new TranslateScreen();

   }

}