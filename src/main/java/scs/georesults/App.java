package scs.georesults;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.ProtectionDomain;

import javax.servlet.ServletException;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

public class App {

	public static void main(final String[] args) throws ServletException, LifecycleException, URISyntaxException, IOException {
		final ProtectionDomain domain = App.class.getProtectionDomain();
		final URL location = domain.getCodeSource().getLocation();
		final File jarFile = new File(location.toURI().getPath());
		final File webapp = new File(jarFile.getParentFile().getParentFile(), "webapp");

		final Tomcat tomcat = new Tomcat();
		tomcat.setPort(8080);
		final Context ctx = tomcat.addWebapp("", webapp.getCanonicalPath());
		System.out.println("lifecycle listeners in: "+ctx);
		for (Object o : ctx.getApplicationLifecycleListeners()) {
			System.out.println("lcl: "+o);
		}
		
		tomcat.start();
		tomcat.getServer().await();
	}
	
}
