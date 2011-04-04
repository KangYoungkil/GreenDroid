package greendroid.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Util {

	public final static String md5(String s) {

		if (null != s) {
			try {
				MessageDigest sMd5MessageDigest = MessageDigest.getInstance("MD5");
				StringBuilder sStringBuilder = new StringBuilder();

				sMd5MessageDigest.reset();
				sMd5MessageDigest.update(s.getBytes());

				byte digest[] = sMd5MessageDigest.digest();

				String tmp;
				sStringBuilder.setLength(0);
				for (int i = 0; i < digest.length; i++) {
					tmp = Integer.toHexString(0xFF & digest[i]);
					if (tmp.length() < 2) {
						sStringBuilder.append("0");
					}
					sStringBuilder.append(tmp);
					tmp = null;
				}

				return sStringBuilder.toString();
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
