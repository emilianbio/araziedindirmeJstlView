<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<style>
input {
	width: 5em;
}

.date {
	width: 9em;
}
</style>
<div id='chartContainer'
	style="width: 500px; border: 1px; border-color: red;">

	<form:form action="#" method="post">
		<table border="1" class="table"
			style="width: 235px; text-align: center;">
			<thead>
				<tr>
					<td colspan="11"><strong>3083</strong> NOLU YASA KAPSAMINA
						GİREN TARIM ARAZİLERİNİN <strong>SATIŞ</strong> YOLUYLA DEVRİ</td>
				</tr>
			</thead>
			<tr>
				<td rowspan="2">TARİH</td>
				<td rowspan="2">İLÇE</td>
				<td rowspan="2">EVRAK NO</td>
				<td rowspan="2">MAHALLE</td>
				<td colspan="2">DEVRİ İSTENEN</td>
				<td colspan="2">İZİN VERİLEN</td>
				<td colspan="2">İZİN VERİLMEYEN</td>
				<td rowspan="2">NİTELİK</td>
			</tr>
			<tr>
				<td>Parsel Sayısı</td>
				<td>Alan (m2)</td>
				<td>Parsel Sayısı</td>
				<td>Alan (m2)</td>
				<td>Parsel Sayısı</td>
				<td>Alan (m2)</td>
			</tr>
			<tr>
				<td><input id="date" class="date" type="date"
					style="border: none;" placeholder="Tarih "></td>
				<td><select style="border: none;"><option>---</option>
						<option>Seyhan</option>
						<option>Yüreğir</option>
						<option>Çukurova</option>
						<option>Sarıçam</option>
						<option>Tufanbeyli</option>
						<option>Saimbeyli</option>
						<option>Feke</option>
						<option>Aladağ</option>
						<option>Kozan</option>
						<option>İmamoğlu</option>
						<option>Karaisalı</option>
						<option>Pozantı</option>
						<option>Karataş</option>
						<option>Ceyhan</option>
						<option>Yumurtalık</option>

				</select></td>
				<td><input style="border: none;"></td>
				<td><input style="border: none;"></td>
				<td><input style="border: none;"></td>
				<td><input style="border: none;"></td>
				<td><input style="border: none;"></td>
				<td><input style="border: none;"></td>
				<td><input style="border: none;"></td>
				<td><input style="border: none;"></td>
				<td><input
					style="border: none; size: 5px; margin: 0px; padding: 0px;"></td>
			</tr>
			<tr style="background-color: #ffd480">
				<td colspan="11"><input type="submit" value="Kaydet"
					style="border: none; background-color: #ffcc66"></td>
			</tr>
		</table>
	</form:form>
</div>
