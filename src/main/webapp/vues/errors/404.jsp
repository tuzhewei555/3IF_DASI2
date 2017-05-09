<%
    if (null == request.getAttribute("view")) {
        request.setAttribute("title", "Erreur 404");
        request.setAttribute("view", "/vues/errors/404.jsp");
%>

<jsp:include page="/vues/layouts/default.jsp" />

<% } else { %>

<h1>Collect'IF &ndash; Erreur 404</h1>
<p>Erreur 404</p>

<% } %>
