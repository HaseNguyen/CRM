<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="page-wrapper">
	<div class="container-fluid">
		<div class="row bg-title">
			<div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
				<h4 class="page-title">Thêm mới công việc</h4>
			</div>
		</div>
		<!-- /.row -->
		<!-- .row -->
		<div class="row">
			<div class="col-md-2 col-12"></div>
			<div class="col-md-8 col-xs-12">
				<div class="white-box">
					<form action="<c:url value ="/task/add"/>" method="post"
						class="form-horizontal form-material">
						<div class="form-group">
							<label class="col-md-12">Tên công việc</label>
							<div class="col-md-12">
								<input type="text" placeholder="Tên công việc"
									class="form-control form-control-line" name="taskName">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-12">Ngày bắt đầu</label>
							<div class="col-md-12">
								<input type="text" placeholder="dd/MM/yyyy"
									class="form-control form-control-line" name="taskStart">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-12">Ngày kết thúc</label>
							<div class="col-md-12">
								<input type="text" placeholder="dd/MM/yyyy"
									class="form-control form-control-line" name="taskEnd">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-12">Người Đảm Nhận</label>
							<div class="col-md-12">
								<select class="form-control form-control-line" name="userGet">
									<c:forEach var="user" items="${userList }">
										<option value="${user.id }">${user.fullname }</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-12">Dự Án</label>
							<div class="col-md-12">
								<select class="form-control form-control-line" name="jobGet">
									<c:forEach var="job" items="${jobList }">
										<option value="${job.id }">${job.name }</option>
									</c:forEach>
								</select>
							</div>
						</div>
				</div>
				<div class="form-group">
					<div class="col-sm-12">
						<button type="submit" class="btn btn-success">Lưu lại</button>
						<a href="<c:url value="/task"/>" class="btn btn-primary">Quay
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
<!-- /.container-fluid -->
</div>