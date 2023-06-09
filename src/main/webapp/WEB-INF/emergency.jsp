<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
    
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/em.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.min.js" integrity="sha384-cuYeSxntonz0PPNlHhBs68uyIAVpIIOZZ5JqeqvYYIcEL727kskC66kF92t6Xl2V" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bodymovin/5.10.2/lottie.min.js" integrity="sha512-fTTVSuY9tLP+l/6c6vWz7uAQqd1rq3Q/GyKBN2jOZvJSLC5RjggSdboIFL1ox09/Ezx/AKwcv/xnDeYN9+iDDA==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <title>Emergency contacts</title>
</head>
<body>
    <!--navbar-->
     <nav class="navbar navbar-expand-lg navbar-light bg-light px-0 py-2 ">
  <div class="container-xl">
    <!-- Logo -->
    <a class="navbar-brand" href="/Test1">
      <img src="./images/logo1.png" width="80px" alt="...">
    </a>
    <!-- Navbar toggle -->
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <!-- Collapse -->
    <div class="collapse navbar-collapse" id="navbarCollapse">
      <!-- Nav -->
      <div class="navbar-nav mx-lg-auto">
        <a class="nav-item nav-link" href="home" aria-current="page">Home</a>
        <a class="nav-item nav-link active" href="contacts">Emergency Contacts</a>
      </div>
      <!-- Right navigation -->
      <div class="d-flex align-items-lg-center mt-3 mt-lg-0">
       
           <form class="d-flex" role="search" action="Logout" method="POST">
             <button class="btn btn-dark"  onclick="confirmLogout()">Log out</button>
            </form>
            <script>
			function confirmLogout() {
			    if (confirm("Are you sure you want to logout?")) {
			        window.location.href = "Logout";
			    }
			}
			</script>
      </div>
    </div>
  </div>
</nav>
      <!--fin navbar-->
      <!--fin navbar-->
      
   <%
String successMsg = (String) session.getAttribute("successMsg");
if (successMsg != null && !successMsg.isEmpty()) {
%>
<script>
alert("<%=successMsg%>");
</script>
<%
session.removeAttribute("successMsg");
}
%>
      <!--liste contact-->
<div class="container mt-5 d-flex flex-column">
   <div class="col-4 m-3">
          <!-- Button trigger modal -->

    <button type="button" class="btn btn-dark" data-bs-toggle="modal" data-bs-target="#add">
      Ajouter Contact    </button>  
     </div>
<div class="m-2">
  <table class="table">
    <thead>
      <tr>
        <th>Name</th>
        <th>Email</th>
        <th>Phone Number</th>
        <th>Edit</th>

        <th>Delete</th>
      </tr>
    </thead>
    <tbody>
      <c:if test="${not empty contacts}">
        <c:forEach items="${contacts}" var="contact">
          <tr>
            <td>${contact.getName()}</td>
            <td>${contact.getMail()}</td>
            <td>${contact.getPhone()}</td>
            
            <td>
              <a data-bs-toggle="modal" data-bs-target="#delete${contact.getId()}">
                <i class="fa-solid fa-trash"></i>
              </a>
            </td>
          <td> <i class="fa-solid fa-pen-to-square"></i></td>
          </tr>


          <!--Modal to delete-->
          <div class="modal fade" id="delete${contact.getId()}" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog">
              <div class="modal-content">
                <div class="modal-header">
                  <h5 class="modal-title" id="exampleModalLabel">Delete Contact</h5>
                  <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                  <form action="DeleteContactById" method="GET">
                    <input type="hidden" name="id" value="${contact.getId()}"/>
                    <h5>Are you sure you want to delete this contact?</h5>
                    <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Cancel</button>
                    <input type="submit" class="btn btn-dark" name="submit" value="Delete">
                  </form>
                </div>
              </div>
            </div>
          </div>

        </c:forEach>
      </c:if>
      <!--fin list cotact-->

      <!--modal for adding contact-->
   <div class="modal fade" id="add" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Add contact</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <form method="post" action="Emergency">
          <label >Name</label>
          <input type="text" class="form-control mb-2" name="name" >
                    <input type="text" class="form-control mb-2" name="id_user" value="${sessionScope.user.id}" hidden >
          
          <label >Email</label>
          <input type="email" class="form-control mb-2" name="email">
          <label >Phon number</label>
          <input type="text" class="form-control mb-2" name="phone">
          <input type="submit" class="btn btn-dark" name="submit" value="Send">
        </form>
      </div>
     
    </div>
  </div>
</div>

      </div>
    </div>
  </div>
</body>
</html>