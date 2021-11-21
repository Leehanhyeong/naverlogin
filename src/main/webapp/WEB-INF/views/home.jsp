<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
   <title>Home</title>
   <Style>
      #naver_login{
         width: 250px;
         height: 90px;
         cursor : pointer;
      }
      #naver_login img{
         width: 100%;
      }
   </Style>
</head>
<body>
<h1>
   네이버 로그인  
</h1>
   <p>
      <form>
         <table>
            <tbody>
               <tr>
                  <td><label>ID:</label></td>
                  <td>
                     <input name="id"/>
                  </td>
               </tr>
               <tr>
                  <td><label>PW:</label></td>
                  <td>
                     <input name="pw" type="password"/>
                  </td>
               </tr>
               <tr>
                  <td colspan="2">
                     <button type="button">로그인</button>
                  </td>
               </tr>
               <tr>
                  <td colspan="2">
                     <div id="naver_login" onclick="naver_login()">
                        <img src="resources/images/naver_login.png"/>
                     </div>
                  </td>
               </tr>
            </tbody>
         </table>
      </form>
   </p>
   
   
   <script>
  		function naver_login() {
			
  			location.href="naverLogin.inc";
		} 
   
   </script>
   
   
   
   
   
   
   
</body>

</html>