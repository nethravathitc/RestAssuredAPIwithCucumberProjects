Feature: place validations

Scenario Outline: Validate Add place API
Given Payload of the the add place API with "<name>" "<address>" "<language>"
When Request is made with "AddPlaceAPI" and method "POST"
Then Verify "status" in response body as "OK"
And Verify "scope" in response body as "APP"
And Verify place_id created maps to "<name>" using "GetPlaceAPI"

Examples:
|name|address|language|
|Vasundra|Annapoorneshwari Bengaluru|kannada|
|Belli nilaya|Kalyananagar | kannada|

