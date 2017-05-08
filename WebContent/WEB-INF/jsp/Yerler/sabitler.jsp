<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<div class="container">
	<form:form method="get" action="sabitonay" commandName="tips"
		class="form-inline">
		<form:hidden path="id" />
		<div class="col-sm-8">
			<table clAss="table">

				<tr>
					<td><label> ÜLKE</label></td>

					<td><form:select path="tip.tip.tip.id" class="form-control"
							onChange="ikisibirada(this.value)" id="slctTipler">
							<form:option value="0">Seçiniz</form:option>
							<form:options items="${tipListesi}" itemValue="id"
								itemLabel="isim" />
						</form:select></td>
				</tr>

				<tr>
					<td><form:label path="id">Şehir</form:label></td>
					<td><form:select path="tip.tip.id" id="slctAltTip"
							class="form-control" onChange="ikisibiradamarkalar(this.value)">
							<form:option value="0">Seçiniz</form:option>
							<form:options items="${altTipListesi}" itemValue="id"
								itemLabel="isim" />
						</form:select></td>
				</tr>
				<tr>
					<td><label>İlçe</label></td>
					<td><form:select path="tip.id" id="slctMarka"
							class="form-control" onChange="modelGetir(this.value)">
							<form:option value="0">Seçiniz</form:option>
							<form:options items="${markaListesi}" itemValue="id"
								itemLabel="isim" />
						</form:select></td>
				</tr>
				<tr>
					<td><label>Durum</label></td>
					<td><form:select path="durum" id="durum" class="form-control"
							onChange="modelGetir1(this.value)">
							<form:option value="1">Aktif</form:option>
							<form:option value="0">Pasif</form:option>
						</form:select></td>
				</tr>
				<tr>
					<td><form:label path="isim" >Mahalle/Köy</form:label></td>
					<td><form:input path="isim" class="form-control"></form:input></td>
				</tr>
				<tr>
					<c:if test="${tusYazisi == 'Ekle'}">&nbsp;
				<td colspan=2><input type="submit" value="${tusYazisi}"
							class="btn btn-info" /></td>
					</c:if>

					<c:if test="${tusYazisi == 'Güncelle'}">
						<td colspan=2><input type="submit" value="${tusYazisi}"
							class="btn btn-info" />&nbsp; <input type="button"
							value="Vazgeç" class="btn btn-danger"
							onclick="javascript:location.href='./vazgec'" /></td>
					</c:if>
				</tr>
			</table>
		</div>
	</form:form>
</div>
<table id="models" class="table table-striped">
	<tr>
		<th>Sil</th>
		<th>edit</th>
		<th>Sıra</th>
		<th>isim</th>
		<th>ekleme tarihi</th>
		<th>durum</th>
		<!-- <th>kaydeden</th> -->
	</tr>

	<c:forEach items="${modelListesi}" var="sabitTips" varStatus="no">
		<tr id="satirno${sabitTips.id}">
			<td><input type="image"
				src="<c:url value="/assets/images/Delete-32.png" />" width="21px"
				onclick="tipsil(${sabitTips.id})" title="Silmek İçin Tıklayın" /></td>
			<td><a
				href="${pageContext.request.contextPath}/yer-ekleme/edit/${sabitTips.id}"><input
					type="image" src="<c:url value="/assets/images/duzenle.png" />"
					width="21px" title="Değiştirmek İçin Tıklayın" /></a></td>
			<td class="td">${no.count}</td>
			<td class="td">${sabitTips.isim}</td>
			<td class="td"><fmt:formatDate value="${sabitTips.eklemezamani}"
					pattern="dd.MM.yyyy HH:mm:ss" /></td>
			<td class="td">${sabitTips.durum}</td>
			<%-- <td class="td">${sabitTips.memurs.isim}</td> --%>
		</tr>
	</c:forEach>
</table>

