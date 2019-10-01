package Translate;

// 번역
public class JSON {
	private Message message;

	public Message getMessage() {
		return message;
	}
}

class Message {
	private String type;
	private String service;
	private String version;
	private Result result;
	
	public String getType() {
		return type;
	}
	public String getService() {
		return service;
	}
	public String getVersion() {
		return version;
	}
	public Result getResult() {
		return result;
	}
}

class Result {
	private String srcLangType;
	private String tarLangType;
	private String translatedText;
	
	public String getSrcLangType() {
		return srcLangType;
	}
	public String getTarLangType() {
		return tarLangType;
	}
	public String getTranslatedText() {
		return translatedText;
	}
}

// 언어 감지
class LangCode {
	private String langCode;
	
	public String getLangCode() {
		return langCode;
	}
}