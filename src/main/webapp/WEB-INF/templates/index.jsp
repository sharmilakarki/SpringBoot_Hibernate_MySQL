<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML>
<html>
<head> 
    <title>Getting Started: Serving Web Content</title> 
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>
<body>
<p>hello people</p>

<form action="/sms/to-all" modelAttribute="smsForm" method="post">
	<input type="text" name="text" value=" type your text here "/>
	<input type="text" name="sender" value=""/>
	<input type="submit" name="SEND" value="SEND" />
</form>

</body>
</html>