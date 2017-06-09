<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<style>
.pic_ina {
	height: 90px;
	width: 30%;
	float: left;
}

.con_ina {
	height: 100px;
	width: 67%;
	margin-left: 3%;
	float: left;
}

.btn_inr {
	float: right;
	margine-top: 25px;
}
</style>

<c:set value="${fn:length(kullaniciListesi)}" var="size" />
<script type="text/javascript">
	var size = '${size}';
	var bootstrapClass = [ "alert-info", "alert-danger", "alert-warning",
			"alert-success" ];

	jq(document).ready(
			function() {
				for (var i = 0; i < size; i++) {

					jq("#alert" + i).addClass(
							bootstrapClass[Math.floor(Math.random()
									* bootstrapClass.length)]);

					console.log(bootstrapClass[Math.floor(Math.random()
							* bootstrapClass.length)]);

				}

			});
</script>

<div class="container">
	<div class="row">
		<h6></h6>
	</div>
	<div class="row">
		<div class="container">
			<c:forEach items="${kullaniciListesi}" var="kullanici"
				varStatus="sira">
				<div class="col-md-4">
					<div class="alert " id="alert${sira.index}">
						${sira.count}. Sıra
						<div class="panel-body" onload="">
							<c:if test="${!empty kullanici.pic  }">

								<img
									src="${pageContext.request.contextPath}/kullanici-islemleri/photoGoster2?id=${kullanici.id} "
									class="pic_ina " />
							</c:if>

							<c:if test="${empty kullanici.pic }">

								<img src="<c:url value="/assets/images/personelimage/avatar"/>"
									class="pic_ina " />

							</c:if>

							<div class="con_ina">
								<b> ${kullanici.adi}-${kullanici.unvan} </b><br> Thank you
								for using this snipp <br> Add me as a frnd on facebook . i
								will accept all requests
							</div>
						</div>
						<a
							href="${pageContext.request.contextPath}/kullanici-islemleri/kullaniciGuncelle/${kullanici.id} "><button
								type="button" class="btn btn-danger btn-xs btn_inr">Düzenle</button></a>
						<a href="https://www.facebook.com/ajithjojo999" target="blank"><button
								type="button" class="btn btn-info btn-xs btn_inr">Profilime
								Git</button></a> <a href="https://www.facebook.com/ajithjojo999"
							target="blank"><button type="button"
								class="btn btn-success btn-xs btn_inr">Go to my fb</button></a>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
</div>
