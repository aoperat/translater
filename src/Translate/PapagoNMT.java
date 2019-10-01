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
//	String OriginalText = sc.nextLine(); // 번역할 원문 (임시로 입력받게 설정)
//	String source = sc.nextLine(); // 번역할 원문 코드
//	String target = sc.nextLine(); // 번역할 언어 코드
	
	 
	 
	 public PapagoNMT() {
		// TODO Auto-generated constructor stub
	}

	public PapagoNMT(String OriginalText,String source, String target) { // 번역

		try {
			String text = URLEncoder.encode(OriginalText, "UTF-8"); // 번역할 원문
			String apiURL = "https://openapi.naver.com/v1/papago/n2mt";
			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("X-Naver-Client-Id", clientId);
			con.setRequestProperty("X-Naver-Client-Secret", clientSecret);

			// source : 원문 언어 코드, target : 번역할 언어 코드
			String postParams = "source=" + source + "&target=" + target + "&text=" + text;
			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(postParams);
			wr.flush();
			wr.close();
			int responseCode = con.getResponseCode();
			BufferedReader br;
			if (responseCode == 200) { // 정상 호출
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else { // 에러 발생
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = br.readLine()) != null) {
				response.append(inputLine);
			}
			br.close();

			// JSON 파싱
			Gson gson = new Gson();
			JSON translate = gson.fromJson(response.toString(), JSON.class);
			Result result = translate.getMessage().getResult();
//			System.out.println(result.getTranslatedText()); // 번역 결과 출력
			str = result.getTranslatedText();
			
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		
	}
	public static void main(String[] args) {
		new PapagoNMT();
	}
}