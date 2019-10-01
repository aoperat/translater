package Translate;

import java.io.BufferedReader; 
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;
import com.google.gson.Gson;

public class PapagoNMT {
	public String str;

	Scanner sc = new Scanner(System.in);

	String clientId = "qWkpgWMfqNCUANUaMcW2"; // Client ID
	String clientSecret = "MbURpRoJun"; // Client Secret
//	String OriginalText = sc.nextLine(); // ������ ���� (�ӽ÷� �Է¹ް� ����)
//	String source = sc.nextLine(); // ������ ���� �ڵ�
//	String target = sc.nextLine(); // ������ ��� �ڵ�
	
	 
	 
	 public PapagoNMT() {
		// TODO Auto-generated constructor stub
	}

	public PapagoNMT(String OriginalText,String source, String target) { // ����

		try {
			String text = URLEncoder.encode(OriginalText, "UTF-8"); // ������ ����
			String apiURL = "https://openapi.naver.com/v1/papago/n2mt";
			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("X-Naver-Client-Id", clientId);
			con.setRequestProperty("X-Naver-Client-Secret", clientSecret);

			// source : ���� ��� �ڵ�, target : ������ ��� �ڵ�
			String postParams = "source=" + source + "&target=" + target + "&text=" + text;
			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(postParams);
			wr.flush();
			wr.close();
			int responseCode = con.getResponseCode();
			BufferedReader br;
			if (responseCode == 200) { // ���� ȣ��
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else { // ���� �߻�
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = br.readLine()) != null) {
				response.append(inputLine);
			}
			br.close();

			// JSON �Ľ�
			Gson gson = new Gson();
			JSON translate = gson.fromJson(response.toString(), JSON.class);
			Result result = translate.getMessage().getResult();
//			System.out.println(result.getTranslatedText()); // ���� ��� ���
			str = result.getTranslatedText();
			
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		
	}
	public static void main(String[] args) {
		new PapagoNMT();
	}
}