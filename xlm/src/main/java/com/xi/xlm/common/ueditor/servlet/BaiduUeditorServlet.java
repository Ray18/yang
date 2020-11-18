package com.xi.xlm.common.ueditor.servlet;

import com.xi.xlm.common.ueditor.ActionEnter;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 百度编辑器文件上传(管理)入口
 */
public class BaiduUeditorServlet extends HttpServlet implements Servlet {

    private String configRootPath;
    private String uploadPath;
    private String rootDirName;
    private String viewType;

    public void init(ServletConfig config){
        configRootPath = config.getInitParameter("configRootPath");
        uploadPath = config.getInitParameter("uploadPath");
        rootDirName = config.getInitParameter("rootDirName");
        viewType = config.getInitParameter("viewType");
    }

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding( "utf-8" );
        response.setHeader("Content-Type" , "text/html");

        if(StringUtils.isBlank(configRootPath)){
            configRootPath = "/plugin/ueditor";
        }
        response.getWriter().write( new ActionEnter( request, configRootPath, uploadPath, rootDirName, viewType ).exec() );
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
