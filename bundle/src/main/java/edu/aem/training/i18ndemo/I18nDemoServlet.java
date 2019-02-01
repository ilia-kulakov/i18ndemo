package edu.aem.training.i18ndemo;

import com.day.cq.i18n.I18n;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.ResourceBundle;

import com.day.cq.wcm.api.Page;

@SlingServlet(
    resourceTypes = {"sling/servlet/default"},
    methods = {"GET"},
    extensions = {"csv"}
)
public class I18nDemoServlet extends SlingSafeMethodsServlet {

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
        throws ServletException, IOException {

        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("text/html; charset=utf-8");
        PrintWriter writer = response.getWriter();

        String resourcePath = request.getRequestPathInfo().getResourcePath();
        Resource r = request.getResourceResolver().resolve(resourcePath);
        Page page = r.adaptTo(Page.class);

        Locale l = page.getLanguage(false);
        ResourceBundle resourceBundle = request.getResourceBundle(l);
        I18n i18n = new I18n(resourceBundle);

        String greeting = i18n.get("csv.greeting");

        writer.write("<h1>" + greeting + "</h1>");
        writer.flush();
        writer.close();

    }
}
