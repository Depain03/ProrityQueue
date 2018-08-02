# Prerequisites

  Java 1.7 or above

  STS

  Maven
  
# API's

1. EndPoint to add work orders in Queue.
   
   URI: /workorder

   Method: POST
   
   Body: 
   {
  "date": "2018-08-02",
  "requestId": 5
	}
   
2. End Point to delete the top id fromthe Queue.
   
   URI: /workorder/delete

   Method: DELETE

3. EndPoint to get the Queue for work order.
   
   URI: /workorder

   Method: GET   
   
4. EndPoint to delete particualr id from the Queue.
   
   URI: /workorder/{request_ID}/delete

   Method: DELETE
   
5. Endpoint to get the index of a particular id. (Index is starting from 0)
   URI: /workorder/{request_ID}/index

   Method: GET
   
6. EndPoint to get the average (mean) number of seconds that each ID has been waiting in the Queue.

   URI: /workorder/waittime

   Method: GET


# Swagger UI

Swagger UI allows anyone — be it your development team or your end consumers — to visualize and interact with the API’s resources without having any of the implementation logic in place. It’s automatically generated from your Swagger specification, with the visual documentation making it easy for back end implementation and client side consumption.

URI: /swagger-ui.html
