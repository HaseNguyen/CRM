<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="container-fluid">
	<div class="row bg-title">
		<div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
			<h4 class="page-title">Thêm mới thành viên</h4>
		</div>
	</div>
	<!-- /.row -->
	<!-- .row -->
	<div class="row">
		<div class="col-md-2 col-12"></div>
		<div class="col-md-8 col-xs-12">
			<div class="white-box">
				<form class="form-horizontal form-material"
					action="<c:url value="/user/add"/>" method="post">
					<div class="form-group">
						<label class="col-md-12">Full Name</label>
						<div class="col-md-12">
							<input type="text" placeholder="Johnathan Doe"
								class="form-control form-control-line" name="fullname">
						</div>
					</div>
					<div class="form-group">
						<label for="example-email" class="col-md-12">Email</label>
						<div class="col-md-12">
							<input type="email" placeholder="johnathan@admin.com"
								class="form-control form-control-line" id="example-email"
								name="email">
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-12">Password</label>
						<div class="col-md-12">
							<input type="password" class="form-control form-control-line"
								name="password">
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-12">Phone No</label>
						<div class="col-md-12">
							<input type="text" placeholder="123 456 7890"
								class="form-control form-control-line" name="phone">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-12">Select Roles</label>
						<div class="col-sm-12">
							<select class="form-control form-control-line" name="roleId">
								<c:forEach var="item" items="${roleList }">
									<option value="${item.roleId }" >${item.roleText }</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-12">
							<button type="submit" class="btn btn-success">Add User</button>
							<a href="<c:url value="/user" />" class="btn btn-primary">Quay
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