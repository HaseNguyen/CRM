<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="page-wrapper">
	<div class="container-fluid">
		<div class="row bg-title">
			<div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
				<h4 class="page-title">Danh sách dự án</h4>
			</div>
			<div class="col-lg-9 col-sm-8 col-md-8 col-xs-12 text-right">
				<a href="<c:url value = "/job/add"/>" class="btn btn-sm btn-success">Thêm
					mới</a>
			</div>
			<!-- /.col-lg-12 -->
		</div>
		<!-- /row -->
		<div class="row">
			<div class="col-sm-12">
				<div class="white-box">
					<div class="table-responsive">
						<table class="table" id="example">
							<thead>
								<tr>
									<th>STT</th>
									<th>Tên Dự Án</th>
									<th>Ngày Bắt Đầu</th>
									<th>Ngày Kết Thúc</th>
									<th>#</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="job" items="${jobList }" varStatus= "loop">
									<tr>
										<td>${loop.index + 1 }</td>
										<td>${job.name }</td>
										<td>${job.startDate }</td>
										<td>${job.endDate }</td>
										<td><a href="<c:url value="/job/edit?id=${job.id }"/>"
											class="btn btn-sm btn-primary">Sửa</a> <a
											href="<c:url value="/job/delete?id=${job.id }"/>"
											class="btn btn-sm btn-danger">Xóa</a> <a
											href="<c:url value="/job/detail?id=${job.id }"/>"
											class="btn btn-sm btn-info">Xem</a></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
		<!-- /.row -->
	</div>
</div>