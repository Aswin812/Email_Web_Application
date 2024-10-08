<%@ page language="java" contentType="text/html; charset=UTF-8" 
	import="java.util.List, java.util.ArrayList, com.emailApplication.dto.MailsDTO, com.emailApplication.mails.Mails, com.emailApplication.mails.Fav"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Email Application</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <style>
        body {
            font-family: 'Arial', sans-serif;
            margin: 0;
            display: flex;
            height: 100vh;
            background-color: #f0f2f5;
        }
        .sidebar {
            width: 250px;
            background-color: #e9ecef;
            color: #343a40;
            padding: 20px;
            box-shadow: 2px 0 15px rgba(0, 0, 0, 0.1);
            display: flex;
            flex-direction: column;
            justify-content: flex-start;
        }
        .sidebar h3 {
            margin: 0 0 20px;
            font-size: 1.5em;
            font-weight: bold;
        }
        .sidebar a, input {
            color: #343a40;
            text-decoration: none;
            padding: 10px;
            display: block;
            border-radius: 5px;
            transition: background 0.3s;
            margin: 10px 0;
            font-weight: 500;
        }
        .sidebar input{
        	width: 100%;
        	border: none;
        	font-size : 16px;
        	background-color: rgb(255, 32, 32);
        	color: white;
        	cursor: pointer;
        }
        .sidebar a:hover {
            background: #adb5bd;
        }
        .sidebar input:hover {
            
        }
        .main-content {
            flex: 1;
            display: flex;
            flex-direction: column;
            padding: 30px;
            background-color: #ffffff;
            border-left: 1px solid #dee2e6; 
            position: relative; 
        }
        .navbar {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 20px;
        }
        .navbar h2 {
            margin: 0;
            color: #343a40;
            font-size: 1.8em;
        }
        .compose-button {
            background-color: #28a745;
            color: white;
            border: none;
            padding: 12px 20px;
            border-radius: 5px;
            cursor: pointer;
            font-weight: bold;
            font-size: 1em;
            position: absolute;
            bottom: 30px;
            right: 40px;
        }
        .compose-button:hover {
            background-color: #218840;
        }
        .email-list { 
            flex: 1;
            overflow-y: auto;
            margin-top: 20px;
            border-top: 2px solid #dee2e6;
            padding-bottom: 60px; 
        }
        .email-item {
            background: #f8f9fa; 
            padding: 15px;
            margin-bottom: 10px;
            border-radius: 8px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            cursor: pointer;
            transition: background 0.3s, box-shadow 0.2s;
          
        }
        .email-item:hover {
            background: #e2e6ea;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
        }
        .email-item h4 {
            margin: 0;
            color: #343a40; 
            font-weight: bold;
        }
        .email-item p {
            margin: 5px 0;
            color: #6c757d; 
        }
        .email-item small {
            color: #adb5bd; 
            font-size: 0.9em;
        }
        .email-item .mail {
        	  display: flex; 
            justify-content: space-between; 
            align-items: center;
        }
        .email-item #option{
        	color : gray; 
        	display: none;
        }
        .email-item #option i:hover{
        	color : black; 
        }
        .favorite-button {
            background: transparent;
            border: none;
            cursor: pointer;
            color: #ffc107; 
            font-size: 2em; 
            transition: color 0.3s;
        }
        .favorite-button:hover {
            color: #ffca2c; 
        }
        .delete{
        	display: block; 
        }
        #screen{
        	width: 100%;
        	height: 100%;
        	position: absolute;
        	background-color : #333;  
        	opacity : 0.6; 
        	z-index: 1;  
        	display: none; 
        }
        iframe {
			border: none;
			border-radius: 8px; 
			position: absolute;
			width: 800px;
			padding: 0;
			height: 500px;
			z-index: 5;
			top: 50%;
			left: 50%;
			transform : translate(-50%, -50%);
			display: none;
		}
    </style>
    
    <script>
    	function openComposePage(){
    		document.getElementById("composePage").style.display="block";
    		document.getElementById("screen").style.display="block";
    	}
    	
    	function handleIframeMessage(event) {
            if (event.data === 'closeIframe') {
                document.getElementById('composePage').style.display = 'none';
                document.getElementById("screen").style.display="none";
                location.reload();
            }
        }

        window.addEventListener('message', handleIframeMessage, true);

    	
        function loadFolder(folder) {
            window.location.href = 'MainPage.jsp?folder=' + folder;
        }
        
        function favButton(){
        	console.log("b");
        	var fav=document.querySelector('.favorite-button');
        	if(fav.innerText==="☆") fav.innerText="★";
        	else fav.innerText="☆";
        	//innerText="&#9734;"?"&#9733;":"&#9734;";
        }
        
        function deleteButton(){
        	document.getElementById("mail-box").action="TrashMail";
        	document.getElementById("mail-box").submit(); 
        }
        
        function showOption(currentBox){
        	let option=currentBox.querySelector('#option');
        	if(option.style.display==="none"){
        		option.style.display="block";
        	}
        	else{
        		option.style.display="none";
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

<iframe id="composePage" src="ComposeMailPage.jsp"></iframe>
<div id="screen"></div>

<div class="sidebar">
    <h3>Email Folders</h3>
    <a href="#" onclick="loadFolder('Inbox')">Inbox</a>
    <a href="#" onclick="loadFolder('Sent')">Sent</a>
    <a href="#" onclick="loadFolder('Draft')">Drafts</a>
    <a href="#" onclick="loadFolder('Favorite')">Favorites</a>
    <a href="#" onclick="loadFolder('Trash')">Trash</a>
    <form action="Logout" method="post">
    	<input type="submit" value="logout"> 
    </form>
</div>

<div class="main-content">
    		<%String folder=request.getParameter("folder");%>
    <div class="navbar">
    	<%
    		if(folder==null) folder="Inbox";
    	%>
        <h2><%=folder %></h2>
    </div>
    <div class="email-list">
    	<% HttpSession s=request.getSession();
    		String email=(String)s.getAttribute("email");
    		String emptyMessage="";
    		List<MailsDTO> emailList;
    		
    		if ("Sent".equals(folder)) {
                emailList = new Mails().getSentMailList(email);
                emptyMessage="Send Mail List is Empty";
            } else if ("Draft".equals(folder)) {
                emailList = new Mails().getDraftMailList(email);
                emptyMessage="Draft Mail List is Empty";
            } else if ("Favorite".equals(folder)) {
                emailList = new Mails().getFavList(email);
                emptyMessage="Favorites List is Empty";
            }else if("Trash".equals(folder)){
            	emailList = new Mails().getDeletedList(email);
            	emptyMessage="Trash List is Empty";
            }else {
                emptyMessage="Inbox List is Empty";
                emailList = new Mails().getInboxList(email);
            }
    	%>
    	<%
    		Fav fav=new Fav();
    		if(emailList==null||emailList.isEmpty()){
    	%>
    	<p> <%= emptyMessage %> </p>
    	<% 
    		}
    		else{
    			for(MailsDTO msg: emailList){

    	%>
            <form id="mail-box" action="Fav" method="post">
            		<input type="hidden" name="folder" value="<%= folder %>">
    			<div class="email-item" onclick="showOption(this)">
    				<div class="mail">
    					<div>
    						<input type="hidden" name="to" value="<%= msg.getToEmail()%>">
    						<input type="hidden" name="sub" value="<%= msg.getSubject()%>">
    						<input type="hidden" name="content" value="<%= msg.getContent()%>">
    						<h4>From: <%= msg.getToEmail() %></h4>
    						<p><strong>Subject:</strong> <%= msg.getSubject() %></p>
                			<small>Content: <%= msg.getContent() %></small>	
                		</div>
                		<%
                			String favButton="&#9734";
                			if(fav.isFav(email, msg.getToEmail(), msg.getSubject(), msg.getContent())){
                				favButton="&#9733";
                			}
                			else favButton="&#9734";
                		%>
	            			<button class="favorite-button" type="submit" onclick="favButton()"><%=favButton %></button>
    				</div>	            		
    				<div id="option">
    					<i class="fas fa-trash-alt" onclick="deleteButton()"></i> 
    				</div>	
        		</div>
           </form>
    	<%
    			}
    		}
    	%>
    	
    </div>
    <button class="compose-button" onclick="openComposePage()">Compose</button>
</div>


</body>
</html>
