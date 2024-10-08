<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Compose Mail</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
        }
        .container {
            max-width: 756px;
            margin: 0 auto;
            padding: 20px;
            background-color: #ffffff;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
        }
        h2 {
            text-align: center;
            color: #343a40;
        }
        label {
            margin: 10px 0 5px;
            font-weight: bold;
            color: #343a40; 
        }
        input[type="email"],
        input[type="text"],
        textarea {
            width: 97%;
            padding: 10px;
            margin-bottom: 20px;
            border: 1px solid #ced4da;
            border-radius: 5px;
            font-size: 1em;
            color: #495057;
        }
        textarea {
            height: 100px;
            resize: vertical;
        }
        #composeButton {
            background-color: #28a745;
            color: white;
            border: none;
            padding: 12px 20px;
            border-radius: 5px;
            cursor: pointer;
            font-size: 1em;
            width: 100%;
        }
        #composeButton:hover {
            background-color: #218838; 
        }
        
        #cancelButton{
        	margin-left: 725px;
        	border: none;
        	padding: 3px 8px; 
        	background: transparent;
        	cursor: pointer;
        	font-size: 18px;
        	outline: none;
        	border-radius: 3px;
        }
        #cancelButton:hover{
        	background-color: #999;
        }
    </style>
    
    <script>
    	function closeCompose(id){
    		if(id==="cancelButton"){
    			document.getElementById("composeForm").action="DraftMail";
    			document.getElementById("composeForm").submit();
    			parent.postMessage('closeIframe', '*');    			    			
    		}
    		else if(id==="composeButton"&&document.getElementById('composeForm').checkValidity()){
    			parent.postMessage('closeIframe', '*');  
    		}
    	}
    </script>
</head>
<body>
<%
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");

	HttpSession ses=request.getSession();
	if(ses==null || ses.getAttribute("email")==null){
		response.sendRedirect("LoginPage.jsp");
	}
%>

<div class="container">
	<button id="cancelButton" onclick="closeCompose('cancelButton')">&#x2716;</button> 
    <h2>Compose Mail</h2>
    <form id="composeForm" action="Mails" method="POST">
        <label for="to">To:</label>
        <input type="email" id="to" name="to" required placeholder="Enter recipient's email">

        <label for="subject">Subject:</label>
        <input type="text" id="subject" name="subject" required placeholder="Enter subject">

        <label for="message">Message:</label>
        <textarea id="message" name="message" required placeholder="Type your message here..."></textarea>

        <button id="composeButton" type="submit" onclick="closeCompose('composeButton')">Send</button>
    </form>
</div>
	
</body>
</html>
