<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<meta http-equiv="Content-Type" content=" charset=UTF-8">
<nav id="header" class="navbar navbar-fixed-top">
	<div class="container-fluid" style="background-color: #ff9900;">
		<div class="navbar-header" style="background-color: #ff9900;">
			<div id="sidebar-toggle-button">
				<i class="fa fa-bars" aria-hidden="true"></i>
			</div>
			<div class="brand" style="background-color: #ff9900;">

				<a href="/araziedindirme"><img alt=""
					src="${pageContext.request.contextPath}/assets/gthbLogo.png"
					width="35px"> ADANA İL GIDA TARIM VE HAYVANCILIK MÜDÜRLÜĞÜ <span
					class="hidden-xs text-muted"> ARAZİ EDİNDİRME ŞUBESİ </span> </a> <span
					class="hidden-xs text-muted fa fa-user" style="margin-left: 500px">
					<!-- 		.replace("?","ğ") --> <a
					href="${pageContext.request.contextPath}/kullanici-islemleri/kullanici">${cookie.isim.value}-ID
						:${cookie.id.value} </a> <%-- <br>SessionID :${cookie.JSESSIONID.value	} --%>
					&nbsp;<a href="/araziedindirme">ÇIKIŞ</a>
				</span>
			</div>
		</div>
	</div>
</nav>
