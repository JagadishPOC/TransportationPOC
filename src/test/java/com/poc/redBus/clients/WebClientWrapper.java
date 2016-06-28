package com.poc.redBus.clients;

import javax.net.ssl.SSLContext;

import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.security.cert.CertificateException;
import javax.security.cert.X509Certificate;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;


/* 
 * This code is public domain: you are free to use,
 *  link and/or modify it in any way you want, 
 *  for all purposes including commercial applications.  */


public class WebClientWrapper {  

	public static HttpClient wrapClient(HttpClient base) { 
		try { 
			SSLContext ctx = SSLContext.getInstance("TLS");
			X509TrustManager tm = new X509TrustManager() {
				public void checkClientTrusted(X509Certificate[] xcs, 
						String string) throws CertificateException { }  
				public void checkServerTrusted(X509Certificate[] xcs, 
						String string) throws CertificateException { }  
				public java.security.cert.X509Certificate[] getAcceptedIssuers() { 
					return null; 
				}
				public void checkClientTrusted(
						java.security.cert.X509Certificate[] arg0, String arg1)
								throws java.security.cert.CertificateException {
					// TODO Auto-generated method stub

				}
				public void checkServerTrusted(
						java.security.cert.X509Certificate[] arg0, String arg1)
								throws java.security.cert.CertificateException {
					// TODO Auto-generated method stub

				}
			}; 
			ctx.init(null, new TrustManager[]{tm}, null); 
			SSLSocketFactory ssf = new SSLSocketFactory(ctx); 
			ssf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
			ClientConnectionManager ccm = base.getConnectionManager(); 
			SchemeRegistry sr = ccm.getSchemeRegistry(); 
			sr.register(new Scheme("https", ssf, 443));
			ThreadSafeClientConnManager cm = new ThreadSafeClientConnManager(sr);
			cm.setMaxTotal(1000);
			cm.setDefaultMaxPerRoute(1000);
			return new DefaultHttpClient(ccm, base.getParams()); 
		} catch (Exception ex) { 
			ex.printStackTrace(); return null; } 
	} 
} 


