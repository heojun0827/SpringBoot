package snippet;

public class Snippet {
	<!DOCTYPE html>
	<html xmlns:th="http://www.thymeleaf.org"
		  xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout">
		  
	<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	
		<th:block layout:fragment="script"></th:block>
		<th:block layout:fragment="css"></th:block>
		
	</head>
	<body>
		
			<div th:replace="fragments/header::header"></div>
			
			<div layout:fragment="content">
			
			</div>
			
			<div th:replace="fragments/footer::footer"></div>
	</body>
	</html>
}

