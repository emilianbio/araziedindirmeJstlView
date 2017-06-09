<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<div class="col-lg-12">
	<div class="container">
		<div class="table-responsive">
			<div id='uyari' class="conteiner-fluid"
				style='z-index: 20000; margin-left: 10px; padding: 0px; align: center; border-radius: 5px; text-align: center; top: 50%; box-sizing: border-box; left: 50%; border: 2px groove #ff9900; font-size: 20px; display: inline; position: fixed; background-color: white; -ms-transform: translateZ(10px); transform: translate(12px, 50%); -webkit-transform: translate(-50%, -50%); opacity: 1; filter: alpha(opacity = 90); width: 100%; height: 100%;'>
				<div
					style='text-align: center; position: fixed; top: 10%; box-sizing: border-box; left: 30%; width: auto; height: auto; border: 7px outset #ff8800; border-radius: 10px;'>
					<div class="brand">
						<a href="/arazisatis"><img alt=""
							src="${pageContext.request.contextPath}/assets/gthbLogo.png"
							width="65<px"></a>
					</div>
					<br> LÜTFEN KULLANICI BİLGİLERİNİZİ GİRİNİZ <br>
					<form:form action="login" method="post" commandName="kullanici"
						autocomplete="off">
						<form:hidden path="id" />
						<table class="table">
							<tr>
								<td align="left">Kullanıcı Adınız</td>
								<td>: <form:input path="isimSoyisim" type="text"
										style="border-radius: 5px; border: none;"
										placeholder=" Lütfen adınızı giriniz..!" />
								</td>
							</tr>
							<tr>
								<td align="left">Şifreniz</td>
								<td>: <form:input path="sifre" type="password"
										style="border-radius: 5px; border: none;"
										placeholder=" Lütfen şifrenizi giriniz..!" />
								</td>
							</tr>
						</table>
						<input type="submit" value='GİRİŞ' class="btn btn-success">
					</form:form>
				</div>
			</div>
		</div>
	</div>
</div>
