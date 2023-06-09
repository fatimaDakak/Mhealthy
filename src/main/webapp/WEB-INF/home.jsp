 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.myapp.beans.Mood" %>
<%@ page import="com.myapp.beans.Contact" %>

<%@ page import="java.util.ArrayList" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./css/style.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <script src="https://cdnjs.cloudflare.com/ajax/libs/bodymovin/5.10.2/lottie.min.js" integrity="sha512-fTTVSuY9tLP+l/6c6vWz7uAQqd1rq3Q/GyKBN2jOZvJSLC5RjggSdboIFL1ox09/Ezx/AKwcv/xnDeYN9+iDDA==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.min.js" integrity="sha384-cuYeSxntonz0PPNlHhBs68uyIAVpIIOZZ5JqeqvYYIcEL727kskC66kF92t6Xl2V" crossorigin="anonymous"></script>
       <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.4/jquery.min.js" integrity="sha512-pumBsjNRGGqkPzKHndZMaAG+bir374sORyzM3uulLV14lN5LyykqNk8eEeUlUkB3U0M4FApyaHraT65ihJhDpQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>        
    
    <title>Home</title>
    
 
</head>

<body>

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
        <a class="nav-item nav-link active" href="#" aria-current="page">Home</a>
        <a class="nav-item nav-link" href="contacts">Emergency Contacts</a>
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
      <!-- Alert of success when the emergency message is sent -->
      <div id="animation-container" class="py-5" ></div>
      <div  class="container col-lg-8 " id="divCont">
	   <%
	   String SOS = (String) request.getAttribute("sent");
	
	   if (SOS != null && !SOS.isEmpty()) {
	   %> 
	    <div class="modal fade" id="alertModal" tabindex="-1" aria-labelledby="alertModalLabel" aria-hidden="true">
	      <div class="modal-dialog modal-dialog-centered">
	        <div class="modal-content">
	          <div class="modal-header">
	            <h5 class="modal-title" id="alertModalLabel">Emergency Message Sent!</h5>
	            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"  onclick="closeAlert()"></button>
	         </div>
	         <div class="modal-body">
	           <strong><%= SOS %></strong>
	         </div>
	      
		       <div class="modal-footer">
		       
		       </div>
		      
		      
			    </div>
			  </div>
			</div>

			<script>
			  $(document).ready(function() {
			    $("#alertModal").modal("show");
			  });
			  
			  function closeAlert() {
			    const newUrl = '<%= request.getContextPath() %>/home';
			    const pageTitle = 'Home';
			    const url =  newUrl;
			    window.history.pushState({ path: url }, pageTitle, url);
			  }
			</script> 

			<%
			  request.removeAttribute("sent");
			}
			%>

      <!-- fin alert success emergency -->
      
      <!-- Greating the user -->
       <c:if test="${!empty sessionScope.user.name}">
		  <h3 class="text-center mb-4 mt-4 fw-lighter">Hello ${sessionScope.user.name}, how do you feel today?</h3>
		</c:if>
		<form method="post" action="Interpreter">
		  <textarea class="form-control mb-2" name="mood" placeholder="write what do you feel here..."></textarea>
		  <input type="submit" class="btn btn-dark" name="submit" value="Generate Tips">
		</form>
		<br>

   <br>
   <% List<String> resultList = (List<String>) request.getAttribute("results");
if (resultList != null && !resultList.isEmpty()) {
%>
<!-- Modal to display mental health tips -->
<div class="modal fade" id="mentalHealthTipsModal" tabindex="-1" aria-labelledby="mentalHealthTipsModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered modal-lg">
    <div class="modal-content ">
      <div class="modal-header">
        <h5 class="modal-title" id="mentalHealthTipsModalLabel">Mental Health Tips</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close" onclick="closeAlert()"></button>
      </div>
      <div class="modal-body d-flex justify-content-center">
        <strong>
          <div id="mentalHealthTipsCarousel" class="carousel slide" data-bs-ride="carousel">
            <div class="carousel-inner text-center">
              <% for (int i = 0; i < resultList.size(); i++) { %>
              <div class="carousel-item <%= i == 0 ? "active" : "" %>">
                <ul>
                  <li><%= resultList.get(i) %></li>
                </ul>
              </div>
              <% } %>
            </div>
            <button class="carousel-control-prev" type="button" data-bs-target="#mentalHealthTipsCarousel" data-bs-slide="prev">
              <span class="carousel-control-prev-icon text-dark" aria-hidden="true"></span>
              <span class="visually-hidden">Previous</span>
            </button>
            <button class="carousel-control-next text-black" type="button" data-bs-target="#mentalHealthTipsCarousel" data-bs-slide="next">
              <span class="carousel-control-next-icon text-dark" aria-hidden="true"></span>
              <span class="visually-hidden">Next</span>
            </button>
          </div>
        </strong>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal" onclick="closeAlert()">Close</button>
      </div>
    </div>
  </div>
</div>
<% } %>

	<script>
	  // Trigger the modal on page load
	  $(document).ready(function() {
	    $('#mentalHealthTipsModal').modal('show');
	  });
	</script>


     <script src="js/home.js"></script>
      <div class="d-flex justify-content-center">
      <button type="button"  class="bouton" name="emergency" data-bs-toggle="modal" data-bs-target="#urgence">Emergency Button</button>
        </div>
        </div>
        
 <!--    	 
  <div class="alert alert-warning alert-dismissible" role="alert">
	    <strong>You don't have any emergency contacts!</strong> Please add at least one emergency contact before using the emergency button. Click here <a href="contacts">Add an emergency contact</a>
	    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
	  </div>
	 -->
	
		<!--modal for emergency contact -->
		<div class="modal fade" id="urgence" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
		  <div class="modal-dialog">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h5 class="modal-title" id="exampleModalLabel">Emergency</h5>
		        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"onclick="closeAlert()"></button>
		      </div>
		      <div class="modal-body m-5">
		        <form method="post" action="SMSservlet">
		          <div class="mb-3">
		            <h6>Are you sure you having a panic attack ?</h6><BR>
		          
		          </div>
		          <div class="modal-footer">
		            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal" onclick="closeAlert()">Close</button>
		            <input type="submit" class="btn btn-danger" value="Yes" >
		          </div>
		        </form>
		      </div>
		    </div>
		  </div>
		</div>
		<script>
				function closeAlert() {
					const newUrl = '<%= request.getContextPath() %>/home';
					const pageTitle = 'Home';
					const url =  newUrl;
					window.history.pushState({ path: url }, pageTitle, url);
				}
			</script> 
<br><br><br><br>
<section class="pt-5 pb-5">
    <div class="container">
        <div class="row">
            
                <h3 class="text-center mb-4 mt-4 fw-lighter"><strong>To take care of yourself </strong></h3>
            
            
            </div>
            <div class="col-12">
                <div id="carouselExampleIndicators2" class="carousel slide" data-ride="carousel">

                    <div class="carousel-inner">
                        <div class="carousel-item active">
                            <div class="row">

                                <div class="col-md-4 mb-3">
                                    <div class="card">
                                        <img class="img-fluid" alt="100%x280" src="./images/breath.jpg">
                                        <div class="card-body">
                                            <h4 class="card-title">Practice breathing exercices</h4>
                                            <p class="card-text fw-lighter">Here are some tips to practice breathing techniques.</p>

                                        </div>

                                    </div>
                                </div>
                                <div class="col-md-4 mb-3">
                                    <div class="card">
                                        <img class="img-fluid" alt="100%x280" src="./images/music.jpg">
                                        <div class="card-body">
                                            <h4 class="card-title">Listen to relaxing music</h4>
                                            <p class="card-text fw-lighter">Here are some tips to practice relaxing techniques while listening to music.</p>

                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-4 mb-3">
                                    <div class="card">
                                        <img class="img-fluid" alt="100%x280" src="./images/friends.jpg">
                                        <div class="card-body">
                                            <h4 class="card-title">Have Fun!</h4>
                                            <p class="card-text fw-lighter">Spending time with loved ones can be a great way to have fun and make lasting memories.</p>

                                        </div>
                                    </div>
                                </div>

                            </div>
                        </div>
                        <div class="carousel-item">
                            <div class="row">

                                <div class="col-md-4 mb-3">
                                    <div class="card">
                                        <img class="img-fluid" alt="100%x280" src="https://images.unsplash.com/photo-1532771098148-525cefe10c23?ixlib=rb-0.3.5&amp;q=80&amp;fm=jpg&amp;crop=entropy&amp;cs=tinysrgb&amp;w=1080&amp;fit=max&amp;ixid=eyJhcHBfaWQiOjMyMDc0fQ&amp;s=3f317c1f7a16116dec454fbc267dd8e4">
                                        <div class="card-body">
                                            <h4 class="card-title">Special title treatment</h4>
                                            <p class="card-text">With supporting text below as a natural lead-in to additional content.</p>

                                        </div>

                                    </div>
                                </div>
                                <div class="col-md-4 mb-3">
                                    <div class="card">
                                        <img class="img-fluid" alt="100%x280" src="https://images.unsplash.com/photo-1532715088550-62f09305f765?ixlib=rb-0.3.5&amp;q=80&amp;fm=jpg&amp;crop=entropy&amp;cs=tinysrgb&amp;w=1080&amp;fit=max&amp;ixid=eyJhcHBfaWQiOjMyMDc0fQ&amp;s=ebadb044b374504ef8e81bdec4d0e840">
                                        <div class="card-body">
                                            <h4 class="card-title">Special title treatment</h4>
                                            <p class="card-text">With supporting text below as a natural lead-in to additional content.</p>

                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-4 mb-3">
                                    <div class="card">
                                        <img class="img-fluid" alt="100%x280" src="https://images.unsplash.com/photo-1506197603052-3cc9c3a201bd?ixlib=rb-0.3.5&amp;q=80&amp;fm=jpg&amp;crop=entropy&amp;cs=tinysrgb&amp;w=1080&amp;fit=max&amp;ixid=eyJhcHBfaWQiOjMyMDc0fQ&amp;s=0754ab085804ae8a3b562548e6b4aa2e">
                                        <div class="card-body">
                                            <h4 class="card-title">Special title treatment</h4>
                                            <p class="card-text">With supporting text below as a natural lead-in to additional content.</p>

                                        </div>
                                    </div>
                                </div>

                            </div>
                        </div>
                        <div class="carousel-item">
                            <div class="row">

                                <div class="col-md-4 mb-3">
                                    <div class="card">
                                        <img class="img-fluid" alt="100%x280" src="https://images.unsplash.com/photo-1507525428034-b723cf961d3e?ixlib=rb-0.3.5&amp;q=80&amp;fm=jpg&amp;crop=entropy&amp;cs=tinysrgb&amp;w=1080&amp;fit=max&amp;ixid=eyJhcHBfaWQiOjMyMDc0fQ&amp;s=ee8417f0ea2a50d53a12665820b54e23">
                                        <div class="card-body">
                                            <h4 class="card-title">Special title treatment</h4>
                                            <p class="card-text">With supporting text below as a natural lead-in to additional content.</p>

                                        </div>

                                    </div>
                                </div>
                                <div class="col-md-4 mb-3">
                                    <div class="card">
                                        <img class="img-fluid" alt="100%x280" src="https://images.unsplash.com/photo-1532777946373-b6783242f211?ixlib=rb-0.3.5&amp;q=80&amp;fm=jpg&amp;crop=entropy&amp;cs=tinysrgb&amp;w=1080&amp;fit=max&amp;ixid=eyJhcHBfaWQiOjMyMDc0fQ&amp;s=8ac55cf3a68785643998730839663129">
                                        <div class="card-body">
                                            <h4 class="card-title">Special title treatment</h4>
                                            <p class="card-text">With supporting text below as a natural lead-in to additional content.</p>

                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-4 mb-3">
                                    <div class="card">
                                        <img class="img-fluid" alt="100%x280" src="https://images.unsplash.com/photo-1532763303805-529d595877c5?ixlib=rb-0.3.5&amp;q=80&amp;fm=jpg&amp;crop=entropy&amp;cs=tinysrgb&amp;w=1080&amp;fit=max&amp;ixid=eyJhcHBfaWQiOjMyMDc0fQ&amp;s=5ee4fd5d19b40f93eadb21871757eda6">
                                        <div class="card-body">
                                            <h4 class="card-title">Special title treatment</h4>
                                            <p class="card-text">With supporting text below as a natural lead-in to additional content.</p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
  
</body>
</html>