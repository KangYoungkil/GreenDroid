package greendroid.util;

import javax.net.ssl.SSLException;

import org.apache.http.conn.ssl.AbstractVerifier;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 * A helper class for HTTP clients
 * Basically sets some lovely stuff to make SSL work
 * (mainly for oAuth, thought you guys might use this because
 * oAuth2 is becoming more mainstream!)
 * Anyway, code is based on this:
 * http://stackoverflow.com/questions/3135679/android-httpclient-hostname-in-certificate-didnt-match-example-com-exa
 * @author kennydude
 *
 */
public class HttpClientHelper {
	static class MyVerifier extends AbstractVerifier {

	    private final X509HostnameVerifier delegate;

	    public MyVerifier(final X509HostnameVerifier delegate) {
	        this.delegate = delegate;
	    }

	    public void verify(String host, String[] cns, String[] subjectAlts)
	                throws SSLException {
	        boolean ok = false;
	        try {
	            delegate.verify(host, cns, subjectAlts);
	        } catch (SSLException e) {
	            for (String cn : cns) {
	                if (cn.startsWith("*.")) {
	                    try {
	                          delegate.verify(host, new String[] { 
	                                cn.substring(2) }, subjectAlts);
	                          ok = true;
	                    } catch (Exception e1) { }
	                }
	            }
	            if(!ok) throw e;
	        }
	    }
	}

	/**
	 * Returns a DefaultHttpClient which has been configures to allow
	 * any SSL connections
	 * @return Http client that works with all SSL
	 */
	public static DefaultHttpClient getTolerantClient() {
	    DefaultHttpClient client = new DefaultHttpClient();
	    SSLSocketFactory sslSocketFactory = (SSLSocketFactory) client
	            .getConnectionManager().getSchemeRegistry().getScheme("https")
	            .getSocketFactory();
	    final X509HostnameVerifier delegate = sslSocketFactory.getHostnameVerifier();
	    if(!(delegate instanceof MyVerifier)) {
	        sslSocketFactory.setHostnameVerifier(new MyVerifier(delegate));
	    }
	    return client;
	}
}
