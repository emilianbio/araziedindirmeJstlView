<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="sidebar" class="sidebar-toggle">
	<ul class="nav nav-sidebar">
		<li><a href="${pageContext.request.contextPath}/anasayfa"> <i
				class="fa fa-home" aria-hidden="true"></i> <span>ANASAYFA</span>
		</a></li>
		<li role="separator" class="divider"></li>
		
			<!-- chart types -->
			<li data-toggle="collapse" href="#chart-types" aria-expanded="false"
				aria-controls="chart-types"><a href="#"> <i
					class="fa fa-area-chart" aria-hidden="true"></i><span>SATIŞLAR</span>
			</a></li>
			<li>
				<!-- !HATIRLATMA! sayfa yenilendiğinde aktif olan link sidebar kısmında seçili olarak geliyor -->
				<ul id="chart-types"
					class="sub-menu collapse ${fn:contains(pageContext.request.requestURI,'satis-cesitleri') ? 'in' : ''}">
					<!-- HATIRLATMA SONU! -->
					<li><a
						href="${pageContext.request.contextPath}/satis-cesitleri/satis">Satış
							Yoluyla Devir (5403)</a></li>
					<%-- <li><a
					href="${pageContext.request.contextPath}/satis-cesitleri/miras">Miras
						Yoluyla Devir (5403)</a></li>
				<li><a
					href="${pageContext.request.contextPath}/satis-cesitleri/3083">3083</a></li>
				<li><a
					href="${pageContext.request.contextPath}/satis-cesitleri/vasif">Vasıf</a></li> --%>
				</ul>
			</li>
			<c:if test="${cookie.id.value==1 || cookie.id.value==9}">
			<!-- /chart types -->
			<li role="separator" class="divider"></li>
			<li data-toggle="collapse" href="#features" aria-expanded="false"
				aria-controls="features"><a href="#"> <i
					class="fa fa-indent" aria-hidden="true"></i><span>RAPORLAR</span>
			</a></li>
			<li>
				<!-- !HATIRLATMA! sayfa yenilendiğinde aktif olan link sidebar kısmında seçili olarak geliyor -->
				<ul id="features"
					class="sub-menu collapse ${fn:contains(pageContext.request.requestURI,'raporlar') ? 'in' : ''}">
					<!-- HATIRLATMA SONU! -->
					<li><a
						href="${pageContext.request.contextPath}/raporlar/satisrapor">Satış
							Yoluyla Devir (5403)</a></li>
				</ul>
			</li>
			<!-- /chart types -->


	
		<li role="separator" class="divider"></li>

		<li data-toggle="collapse" href="#izin" aria-expanded="false"
			aria-controls="features"><a href="#"> <i
				class="fa fa-map-marker" aria-hidden="true"></i><span>İZİN
					İŞLEMLERİ</span>
		</a></li>
		<li>
			<!-- !HATIRLATMA! sayfa yenilendiğinde aktif olan link sidebar kısmında seçili olarak geliyor -->
			<ul id="izin"
				class="sub-menu collapse ${fn:contains(pageContext.request.requestURI,'izin-islemleri') ? 'in' : ''}">
				<!-- HATIRLATMA SONU! -->
				<li><a
					href="${pageContext.request.contextPath}/izin-islemleri/izin-formu">İzin
						İşlemleri</a></li>
			</ul>
		</li>
		</c:if>
		<!-- /chart types -->
		<li role="separator" class="divider"></li>
		<li data-toggle="collapse" href="#arazi" aria-expanded="false"
			aria-controls="features"><a href="#"> <i class="fa fa-star"
				aria-hidden="true"></i><span>ARAZİ ÇIKIŞLARI</span>
		</a></li>
		<li>
			<!-- !HATIRLATMA! sayfa yenilendiğinde aktif olan link sidebar kısmında seçili olarak geliyor -->
			<ul id="arazi"
				class="sub-menu collapse ${fn:contains(pageContext.request.requestURI,'arazi-cikislari') ? 'in' : ''}">
				<!-- HATIRLATMA SONU! -->
				<li><a
					href="${pageContext.request.contextPath}/arazi-cikislari/arac-islemleri">Araç
						İşlemleri</a></li>
			</ul>
		</li>

	<c:if test="${cookie.id.value==1 }">
		<!-- /chart types -->
		<li role="separator" class="divider"></li>
		<li data-toggle="collapse" href="#sabitler" aria-expanded="false"
			aria-controls="features"><a href="#"> <i class="fa fa-star"
				aria-hidden="true"></i><span>YÖNETİM PANELİ</span>
		</a></li>
	
			<li>
				<!-- !HATIRLATMA! sayfa yenilendiğinde aktif olan link sidebar kısmında seçili olarak geliyor -->
				<ul id="sabitler"
					class="sub-menu collapse ${fn:contains(pageContext.request.requestURI,'yer-ekleme') ? 'in' : ''}">
					<!-- HATIRLATMA SONU! -->
					<li><a
						href="${pageContext.request.contextPath}/yer-ekleme/sabitler">Köy/Mahalle/İlçe/İl
							Ekleme</a></li>
				</ul>
			</li>
		</c:if>
	</ul>
</div>