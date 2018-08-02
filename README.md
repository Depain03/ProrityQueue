# Prerequisites

  Java 1.7 or above

  STS

  Maven
  
# API's

1. URI: /workorder

   Method: POST
   
   Body: 
   {
  "date": "2018-08-02",
  "requestId": 5
	}
   
2. URI: /workorder/delete

   Method: DELETE

3. URI: /workorder

   Method: GET   
   
4. URI: /workorder/{request_ID}/delete

   Method: DELETE
   
5. URI: /workorder/{request_ID}/index

   Method: GET
   
   Body: {
	"registration": "",
	"colour": "",
	"slot": 0,
	"level": 0
  }

3. URI: /workorder/waittime

   Method: GET


# Swagger UI

Swagger UI allows anyone — be it your development team or your end consumers — to visualize and interact with the API’s resources without having any of the implementation logic in place. It’s automatically generated from your Swagger specification, with the visual documentation making it easy for back end implementation and client side consumption.

URI: /swagger-ui.html
