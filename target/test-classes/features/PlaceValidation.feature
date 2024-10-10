Feature: Validating Place API's
@AddPlace @Regression
Scenario Outline: Verify if place is being successfuly addedd using AddPlaceApi

 Given Add Place API "<name>", "<addrees>", "<language>";
 When  User calls "AddPlaceAPI" with "POST" http request
 Then  the API call got success with statuscode 200
 And   "status" in response body is "OK"
 And   "scope" in response body is "APP"
 And Verify  Place_Id created maps to "<name>" using "getPlaceAPI"
 
 
 Examples:
 |name           |addrees              |language|
 |Frontline house|side layout, cohen 09|English|
# |Sweet house|side layout,USA|English|

@DeletePlace @Regression
Scenario: Verify if Delete Place functionality is working

 Given DeletePlace Payload
 When  User calls "deletePlaceAPI" with "POST" http request
 Then  the API call got success with statuscode 200
 And   "status" in response body is "OK"
