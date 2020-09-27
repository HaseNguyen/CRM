<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="container-fluid">
	<div class="row bg-title">
		<div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
			<h4 class="page-title">Cập nhật dự án</h4>
		</div>
	</div>
	<!-- /.row -->
	<!-- .row -->
	<div class="row">
		<div class="col-md-2 col-12"></div>
		<div class="col-md-8 col-xs-12">
			<div class="white-box">
				<form class="form-horizontal form-material"
					action="<c:url value="/job/edit"/>" method="post">
					<input type="hidden" name="id" value="${job.id }">
					<div class="form-group">
						<label class="col-md-12">Tên dự án</label>
						<div class="col-md-12">
							<input type="text" placeholder="Tên công việc"
								value="${job.name }" class="form-control form-control-line"
								name="jobName">
						</div>
					</div>
					<div class="form-group">
						<label for="example-email" class="col-md-12">Ngày bắt đầu</label>
						<div class="col-md-12">
							<input type="text" placeholder="dd/MM/yyyy"
								value="${job.startDate }" class="form-control form-control-line"
								id="example-email" name="jobStart">
						</div>
					</div>
					<div class="form-group">
						<label for="example-email" class="col-md-12">Ngày kết thúc</label>
						<div class="col-md-12">
							<input type="text" placeholder="dd/MM/yyyy"
								value="${job.endDate }" class="form-control form-control-line"
								id="example-email" name="jobEnd">
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-12">
							<button type="submit" class="btn btn-success">Edit</button>
							<a href="<c:url value="/job" />" class="btn btn-primary">Quay
								lại</a>
						</div>
					</div>
				</form>
			</div>
		</div>
		<div class="col-md-2 col-12"></div>
	</div>
	<!-- /.row -->
</div>