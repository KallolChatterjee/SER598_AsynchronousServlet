<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Asynchronous Servlet Demo</title>
</head>
<body>
<h1>SHOUT-OUT!</h1>
        <form>
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
                    <td><input type="button" onclick="postMessage();" value="SHOUT" /></td>
                </tr>
            </table>
        </form>
        <h2> Current Shouts </h2>
        <div id="content">
            <% if (application.getAttribute("messages") != null) {%>
            <%= application.getAttribute("messages")%>
            <% }%>
        </div>
        <script>
        	function postMessage() {
	            var xmlhttp = new XMLHttpRequest();
	            xmlhttp.open("POST", "chatAsync?t="+new Date(), false);
	            xmlhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	            var nameText = escape(document.getElementById("name").value);
	            var messageText = escape(document.getElementById("message").value);
	            document.getElementById("message").value = "";
	            xmlhttp.send("name="+nameText+"&message="+messageText);
        	}
            var messagesWaiting = false;
            function getMessages(){
                if(!messagesWaiting){
                    messagesWaiting = true;
                    var xmlhttp = new XMLHttpRequest();
                    xmlhttp.onreadystatechange=function(){
                        if (xmlhttp.readyState==4 && xmlhttp.status==200) {
                            messagesWaiting = false;
                            var contentElement = document.getElementById("content");
                            contentElement.innerHTML = xmlhttp.responseText + contentElement.innerHTML;
                        }
                    }
                    xmlhttp.open("GET", "chatAsync?t="+new Date(), true);
                    xmlhttp.send();
                }
            }
            setInterval(getMessages, 100);
        </script>
</body>
</html>