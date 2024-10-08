<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Email Application</title>

<style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .container {
            background-color: white;
            padding: 20px 35px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 300px;
        }
        .container h2 {
            margin-bottom: 20px;
            text-align: center;
        }
        .container label {
            display: block;
            margin-bottom: 8px;
        }
        .container input[type="email"],
        .container input[type="password"] {
            width: 100%;
            padding: 8px;
            margin-bottom: 12px;
            border: 1px solid #ccc;
            border-radius: 4px;
            outline: none;
        }
        .container input[type="submit"] {
            width: 100%;
            padding: 10px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        .container input[type="submit"]:hover {
            background-color: #45a049;
        }
        .container p {
            text-align: center;
        }
        .container a {
            color: #007BFF;
            text-decoration: none;
        }
        .container a:hover {
        
            text-decoration: underline;
        }
    </style>
</head>
<body>

	<div class="container">
        <h2>Login</h2>
        <form action="Login" method="post">
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" required>
            
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required>
            <%
				String errorMessage=(String) request.getAttribute("errorMessage");
				if(errorMessage!=null){
			%>
			<p style="color:red"><%= errorMessage %></p>
			<%} %>
            
            <input type="submit" value="Login">
        </form>
        <p>Don't have an account? <a href="CreateAccountPage.jsp">Create Account</a></p>
    </div>

	
</body>
</html>
