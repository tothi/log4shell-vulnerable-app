package dvl4wa;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.io.Writer;
import java.util.Map;
import java.util.Collections;
import java.util.stream.Collectors;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class VulnServlet extends HttpServlet {
  protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException {
    Logger logger = LogManager.getLogger(VulnServlet.class);
    try {
      Map<String, String> headers = Collections.list(req.getHeaderNames()).stream().collect(Collectors.toMap(h -> h, req::getHeader));
      res.setContentType("text/plain; charset=utf-8");
      Writer writer = res.getWriter();
      if(headers.containsKey("x-log")) {
        writer.write("Logging to console using vulnerable log4j2!\n");
        logger.info(headers.get("x-log"));
      } else {
        writer.write("Hello world\n");
      }
      writer.close();
    } catch(Exception e) {
      throw new ServletException(e.getMessage(), e);
    }
  }
}

