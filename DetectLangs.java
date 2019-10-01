package Translate;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;
import com.google.gson.Gson;

public class DetectLangs {

	Scanner sc = new Scanner(System.in);

	String source; // 번역할 원문 코드
	String clientId = "qWkpgWMfqNCUANUaMcW2"; // Client ID
	String clientSecret = "MbURpRoJun"; // Client Secret
	String OriginalText = sc.nextLine(); // 번역할 원문 (임시로 입력받게 설정)
	String target = sc.nextLine(); // 번역할 언어 코드

	public DetectLangs() { // 언어 감지
		try {
			String query = URLEncoder.encode(OriginalText, "UTF-8");
			String apiURL = "https://openapi.naver.com/v1/papago/detectLangs";
			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("X-Naver-Client-Id", clientId);
			con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
			// post request
			String postParams = "query=" + query;
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
			StringBuffer sb = new StringBuffer();

			while ((inputLine = br.readLine()) != null) {
				sb.append(inputLine);
			}
			br.close();

			// JSON 파싱
			Gson gson = new Gson();
			LangCode code = gson.fromJson(sb.toString(), LangCode.class);

			source = code.getLangCode(); // 입력받은 문장의 언어를 감지해서 언어 코드 출력

		} catch (Exception e) {
			System.out.println(e);
		}
	}
	public static void main(String[] args) {
		new DetectLangs();
	}
}