<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Synchronous Page</title>
</head>
<body>
  <h1>SHOUT-OUT!</h1>
        <form method="POST" action="chat">
            <table>
                <tr>
                    <td>Your name:</td>
                    <td><input type="text" id="name" name="name"/></td>
                </tr>
                <tr>
                    <td>Your shout:</td>
                    <td><input type="text" id="message" name="message" /></td>
                </tr>
                <tr>
                    <td><input type="submit" value="SHOUT" /></td>
                </tr>
            </table>
        </form>
        <h2> Current Shouts </h2>
        <div id="content">
            <% if (application.getAttribute("Syncmessages") != null) {%>
            <%= application.getAttribute("Syncmessages")%>
            <% }%>
        </div>
</body>
</html>