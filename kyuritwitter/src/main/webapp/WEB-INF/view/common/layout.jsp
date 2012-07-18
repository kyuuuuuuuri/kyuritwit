<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="Content-Style-Type" content="text/css">
<meta http-equiv="Content-Script-Type" content="text/javascript">

<link rel="Stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
<link rel="Stylesheet" href="${pageContext.request.contextPath}/css/cssfile.css" />
<link rel="Stylesheet" href="${pageContext.request.contextPath}/css/loginStyle.css" />

<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>


<title><tiles:getAsString name="title" /></title>


</head>
<body>

<table width="80%">

  <tr><td colspan="2"><tiles:insert page="/WEB-INF/view/common/header.jsp" /></td></tr>
  <tr>
	<td class="contents" ><tiles:insert attribute="content" /></td>
    <td class="menu" width="20%" valign="top"><tiles:insert page="/WEB-INF/view/common/menu.jsp" /></td>
  </tr>
  <tr><td colspan="2"><tiles:insert page="/WEB-INF/view/common/footer.jsp" /></td></tr>
</table>

</body>
</html>