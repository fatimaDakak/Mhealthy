<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.min.js" integrity="sha384-cuYeSxntonz0PPNlHhBs68uyIAVpIIOZZ5JqeqvYYIcEL727kskC66kF92t6Xl2V" crossorigin="anonymous"></script>
    <title>Login</title>
</head>
<body>
<body>
<!-- to display createed successfuly alert
 <c:if test=${not empty message}>
			<div class="alert alert-success alert-dismissible">
				${{message}}
					    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
				
			</div>
		</c:if>
end successfuly alert -->
 
 
  <%
String Errormessage = (String) session.getAttribute("errorMessage");
if (Errormessage != null && !Errormessage.isEmpty()) {
%>

 <div class="alert alert-warning alert-dismissible" role="alert">
	    <strong>Oups !</strong> <%=Errormessage%>
	    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
	  </div>

<%
session.removeAttribute("errorMessage");
}
%>


<section class="vh-100" style="background-color: #9A616D;">
    <div class="container py-5 h-100">
      <div class="row d-flex justify-content-center align-items-center h-100">
        <div class="col col-xl-7">
          <div class="card" style="border-radius: 1rem;">
            <div class="row g-0">
              <div class="col-md-8 col-lg-5 d-none d-md-block">
                <img src="./images/login.jpg"
                  alt="login form" class="img-fluid" style="border-radius: 1rem 0 0 1rem;" />
              </div>
              <div class="col-md-6 col-lg-7 d-flex align-items-center">
                <div class="card-body p-4 p-lg-5 text-black">
  
                  <form method="post" action=""> 
  
                    <div class="d-flex align-items-center mb-3 pb-1">
                      <i class="fas fa-cubes fa-2x me-3" style="color: #ff6219;"></i>
                      <span class="h1 fw-bold mb-0">Mhealthy</span>
                    </div>
  
                    <h5 class="fw-normal mb-3 pb-3" style="letter-spacing: 1px;">Sign into your account</h5>
  
                    <div class="form-outline mb-4">
                      <label class="form-label" for="form2Example17" >Email address</label>
                    
                      <input type="email" id="form2Example17" class="form-control form-control-lg" name="email" placeholder="example@gmail.com" required/>
                    </div>
  
                    <div class="form-outline mb-4">
                    <label class="form-label" for="form2Example27">Password</label>
                      <input type="password" id="form2Example27" class="form-control form-control-lg"  name="password" placeholder="your password here" required/>
                      
                    </div>
  
                    <div class="pt-1 mb-4">
                      <button class="btn btn-dark btn-lg btn-block" type="submit" name="login">Login</button>
                    </div>
  
                    <p class="mb-5 pb-lg-2" style="color: #393f81;">Don't have an account? <a href="signup"
                        style="color: #393f81;">Register here</a></p>
                    
                  </form>
  
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

