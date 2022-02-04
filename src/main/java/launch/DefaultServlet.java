package launch;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.io.Writer;
import java.util.Map;
import java.util.Collections;
import java.util.stream.Collectors;

public class DefaultServlet extends HttpServlet {
  protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException {
    try {
      res.setContentType("text/html; charset=utf-8");
      Writer writer = res.getWriter();
      writer.write("<html>\n<body>\n<h2>Damn Vulnerable log4j Web Application</h2>\n");
      writer.write("<p><a href='servlet'>Click here</a> to reach the vulnerable endpoint.</p>\n");
      writer.write("<p>Use the HTTP Header 'x-log' for triggering the vulnerability.</p>\n");
      writer.write("</p>\n</body>\n</html>\n");
      writer.close();
    } catch(Exception e) {
      throw new ServletException(e.getMessage(), e);
    }
  }
}

