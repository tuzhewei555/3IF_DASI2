<%-- Ceci est un commentaire JSP --%>
<%@page contentType="text/html"%>
<%@page import="java.util.*"%>
<%
    String view = (String) request.getAttribute("view");
    if (null == view) {
        view = "/vues/errors/404.jsp";
    }

    String title = (String) request.getAttribute("title");
    if (null == title) {
        title = "Title";
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <title><%=title%></title>

        <script>
            // Il faut que l'URL termine par un "/"
            if (window.location.href[window.location.href.length - 1] !== '/')
                window.location.replace(window.location.href + "/");
        </script>

        <style>
            <jsp:include page="../css/main.css"/>
        </style>
        
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        <!-- Optional theme -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
        <!-- Latest compiled and minified JQuery -->
        <script src="https://code.jquery.com/jquery-3.2.1.min.js" integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4=" crossorigin="anonymous"></script>
        <!-- Latest compiled and minified JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    </head>
    <body>
        <div class="container">
            <nav class="navbar navbar-default">
                <div class="container-fluid">
                    <div class="navbar-header">
                        <a class="navbar-brand" href="#">Collect'IF</a>
                    </div>

                    <div style="float: right">
                        <p class="navbar-text">Signed in as <a href="./" class="navbar-link">Mark Otto</a></p>
                        <ul class="nav navbar-nav">
                            <li class="btn btn-default navbar-btn">Connexion</li>
                            <li class="btn btn-default navbar-btn">Inscription</li>
                        </ul>
                    </div>
                </div>
            </nav>

            <jsp:include page="<%=view%>"/>

            <footer>
                <div id="admin_div">
                    <a href="./admin">Interface d'administration</a>
                </div>
            </footer>
        </div>
    </body>
</html>
