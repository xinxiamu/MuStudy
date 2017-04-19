package co.jufeng.util;

import java.lang.Character.UnicodeBlock;

public class Transcoding {
	
	public static final String IS_KR = "[\uAC00-\uD7A3]+";

	public static String utf8ToUnicode(String str) {
		char[] myBuffer = str.toCharArray();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < str.length(); i++) {
			UnicodeBlock ub = UnicodeBlock.of(myBuffer[i]);
			if (ub == UnicodeBlock.BASIC_LATIN) {
				// 英文及数字等
				sb.append(myBuffer[i]);
			} else if (ub == UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
				// 全角半角字符
				int j = (int) myBuffer[i] - 65248;
				sb.append((char) j);
			} else {
				// 汉字
				short s = (short) myBuffer[i];
				String hexS = Integer.toHexString(s);
				String unicode = "\\" + 'u' + hexS;
				unicode = unicode.replaceAll("ffff", "");
				sb.append(unicode.toLowerCase());
			}
		}
		return sb.toString();
	}

	public static String unicodeToUtf8(String theString) {
		char aChar;
		int len = theString.length();
		StringBuffer outBuffer = new StringBuffer(len);
		for (int x = 0; x < len;) {
			aChar = theString.charAt(x++);
			if (aChar == '\\') {
				aChar = theString.charAt(x++);
				if (aChar == 'u') {
					// Read the xxxx
					int value = 0;
					for (int i = 0; i < 4; i++) {
						aChar = theString.charAt(x++);
						switch (aChar) {
						case '0':
						case '1':
						case '2':
						case '3':
						case '4':
						case '5':
						case '6':
						case '7':
						case '8':
						case '9':
							value = (value << 4) + aChar - '0';
							break;
						case 'a':
						case 'b':
						case 'c':
						case 'd':
						case 'e':
						case 'f':
							value = (value << 4) + 10 + aChar - 'a';
							break;
						case 'A':
						case 'B':
						case 'C':
						case 'D':
						case 'E':
						case 'F':
							value = (value << 4) + 10 + aChar - 'A';
							break;
						default:
							throw new IllegalArgumentException(
									"Malformed \\uxxxx encoding.");
						}
					}
					outBuffer.append((char) value);
				} else {
					if (aChar == 't')
						aChar = '\t';
					else if (aChar == 'r')
						aChar = '\r';
					else if (aChar == 'n')
						aChar = '\n';
					else if (aChar == 'f')
						aChar = '\f';
					outBuffer.append(aChar);
				}
			} else
				outBuffer.append(aChar);
		}
		return outBuffer.toString();
	}

	// 转化十六进制编码为字符串
	public static String toStringHex(String s) {
		byte[] baKeyword = new byte[s.length() / 2];
		for (int i = 0; i < baKeyword.length; i++) {
			try {
				baKeyword[i] = (byte) (0xff & Integer.parseInt(
						s.substring(i * 2, i * 2 + 2), 16));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		try {
			s = new String(baKeyword, "utf-8");// UTF-16le:Not
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return s;
	}

	// 将字符串转换成二进制，以空格相隔
	public static String toBinary(String str) {
		char[] strChar = str.toCharArray();
		String result = "";
		for (int i = 0; i < strChar.length; i++) {
			result += Integer.toBinaryString(strChar[i]) + " ";
		}
		return result;
	}

	// 将二进制转换成字符串
	public static String toBinaryString(String binary) {
		String[] tempStr = strToStrArray(binary);
		char[] tempChar = new char[tempStr.length];
		for (int i = 0; i < tempStr.length; i++) {
			tempChar[i] = toBinaryChar(tempStr[i]);
		}
		return String.valueOf(tempChar);
	}

	// 格式化成全16位带空格的Binary
	public static String toBinary16(String object) {
		StringBuffer output = new StringBuffer();
		String[] tempStr = strToStrArray(object);
		for (int i = 0; i < tempStr.length; i++) {
			for (int j = 16 - tempStr[i].length(); j > 0; j--)
				output.append('0');
			output.append(tempStr[i] + " ");
		}
		return output.toString();
	}

	// 二进制字串转化为boolean型数组 输入16位有空格的Binstr
	public static boolean[] toBinary16Boolean(String input) {
		String[] tempStr = strToStrArray(input);
		boolean[] output = new boolean[tempStr.length * 16];
		for (int i = 0, j = 0; i < input.length(); i++, j++)
			if (input.charAt(i) == '1')
				output[j] = true;
			else if (input.charAt(i) == '0')
				output[j] = false;
			else
				j--;
		return output;
	}

	// boolean型数组转化为二进制字串 返回带0前缀16位有空格的Binstr
	public static String toBooleanBinary16(boolean[] input) {
		StringBuffer output = new StringBuffer();
		for (int i = 0; i < input.length; i++) {
			if (input[i])
				output.append('1');
			else
				output.append('0');
			if ((i + 1) % 16 == 0)
				output.append(' ');
		}
		output.append(' ');
		return output.toString();
	}

	// 将二进制字符串转换为char
	public static char toBinaryChar(String binStr) {
		int[] temp = binstrToIntArray(binStr);
		int sum = 0;
		for (int i = 0; i < temp.length; i++) {
			sum += temp[temp.length - 1 - i] << i;
		}
		return (char) sum;
	}

	// 将初始二进制字符串转换成字符串数组，以空格相隔
	public static String[] strToStrArray(String str) {
		return str.split(" ");
	}

	// 将二进制字符串转换成int数组
	public static int[] binstrToIntArray(String binStr) {
		char[] temp = binStr.toCharArray();
		int[] result = new int[temp.length];
		for (int i = 0; i < temp.length; i++) {
			result[i] = temp[i] - 48;
		}
		return result;
	}

	public static String getASCII(String input) {
		char[] temp = input.toCharArray();
		StringBuilder builder = new StringBuilder();
		for (char each : temp) {
			builder.append((int) each);
		}
		return builder.toString();
	}

	public static String byteToString(byte buf[]) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < buf.length; i++) {
			String hex = Integer.toHexString(buf[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			sb.append(hex.toLowerCase());
		}
		return sb.toString();
	}

	public static String stringToByte(String hexString) {
		if (hexString == null || hexString.length() % 2 != 0)
			return null;
		String bString = "", tmp;
		for (int i = 0; i < hexString.length(); i++) {
			tmp = "0000"
					+ Integer.toBinaryString(Integer.parseInt(
							hexString.substring(i, i + 1), 16));
			bString += tmp.substring(tmp.length() - 4);
		}
		return bString;
	}

	public static String bina210() {
		return Integer.valueOf("0101", 2).toString();
	}
	
	public static boolean isUTF8(String string) {
		try {
			byte[] bytes = string.replace('?', 'a').getBytes("ISO-8859-1");
			for (int i = 0; i < bytes.length; i++) {
				if (bytes[i] == 63) {
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public static boolean isKR(String string) {
		return string.matches(IS_KR);
	}
	
	public static void main(String[] args) {
		System.out.println(isUTF8("\ud68c\uc0ac \uc18c\uac1c"));
		System.out.println(isKR("검색"));
	}
	
	public static boolean isGBK(String string)
			throws java.io.UnsupportedEncodingException {
		byte[] bytes = string.replace('?', 'a').getBytes("ISO-8859-1");
		for (int i = 0; i < bytes.length; i++) {
			if (bytes[i] == 63) {
				return true;
			}
		}

		return false;
	}

	public static String toGBK(String string)
			throws java.io.UnsupportedEncodingException {
		if (string == null)
			return "";

		if (!isGBK(string)) {
			return new String(string.getBytes("ISO-8859-1"), "GBK");
		}

		return string;
	}

	public static String toISO_8859_1(String string)
			throws java.io.UnsupportedEncodingException {
		if (string == null)
			return "";
		if (isGBK(string)) {
			return new String(string.getBytes("GBK"), "ISO-8859-1");
		}

		return string;
	}
}
