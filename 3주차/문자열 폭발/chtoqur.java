import java.io.*;

public class chtoqur {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String str = br.readLine();		// 문자열
		String bombStr = br.readLine();	// 폭발 문자열
		int bombLen = bombStr.length();

		for (int i = 0; i < str.length(); i++) {
			sb.append(str.charAt(i));

			if (sb.length() >= bombLen && sb.charAt(sb.length() - bombLen) == bombStr.charAt(0)) {
				int sbLen = sb.length();
				boolean isMatch = true;
				
				for (int j = 0; j < bombLen; j++) {
					if (sb.charAt(sbLen - bombLen + j) != bombStr.charAt(j)) {
						isMatch = false;
						break;
					}
				}
				if (isMatch) {
					sb.delete(sb.length() - bombLen, sbLen);
				}
			}
		}

		System.out.println(sb.length() == 0 ? "FRULA" : sb.toString());
	}
}