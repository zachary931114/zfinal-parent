<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="com.jhsmk.smk.common.web.ueditor.UeditorActionEnter"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%

    request.setCharacterEncoding( "utf-8" );
	response.setHeader("Content-Type" , "text/html");
	
	String rootPath = application.getRealPath( "/" );
	
	out.write( new UeditorActionEnter( request, rootPath ).exec() );
	
%>