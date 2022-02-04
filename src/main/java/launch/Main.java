package launch;

import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.Context;

import java.io.File;
import java.io.IOException;

import dvl4wa.VulnServlet;
import launch.DefaultServlet;

public class Main {
    private static final int PORT = 8888;

    public static void main(String[] args) throws Exception {
	String contextPath = "/app";
	String appBase = ".";
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(PORT);
        tomcat.getHost().setAppBase(appBase);
	File docBase = new File(System.getProperty("java.io.tmpdir"));
	Context ctx = tomcat.addContext(contextPath, docBase.getAbsolutePath());

	Class servletClass = VulnServlet.class;
	Class servletClassDefault = DefaultServlet.class;
	tomcat.addServlet(ctx, servletClass.getSimpleName(), servletClass.getName());
	tomcat.addServlet(ctx, servletClassDefault.getSimpleName(), servletClassDefault.getName());
	ctx.addServletMappingDecoded("/servlet/*", servletClass.getSimpleName());
	ctx.addServletMappingDecoded("/", servletClassDefault.getSimpleName());

        tomcat.start();
        tomcat.getServer().await();
    }
}
