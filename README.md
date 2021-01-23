# Mabaya Online Sponsored Ads
### By Tamir Mayblat

As an ad tech company we want to develop a module that enables sellers to create campaigns for promoting their products.

This application uses Mongodb as a database, in some old Java version a handshake parameter needed. 
Please add this parameter to the server's vm options:
```java
-Djdk.tls.client.protocols=TLSv1.2
```

The application uses Swagger-ui to visualize the apis, feel free to use it as needed.

### Features:
* The DB resets when the app starts, 
    * 3 categories will be created (smartphone, shoes, shirts)
    * 3 products will be automatically created for each category 
* For your convenience you can see all categories, products and campaigns using the api.
* You can create new categories, products and campaigns using the end-points in the api.
* Use Create campaign end-point to create a new campaign
    * Make sure the category you choose exists and start date should be formatted dd.MM.yyyy .
    * A campaign should be created and returned to the client as json from the DB.
    * The campaign model contains an endDate field which will be filled with an epoch millis timestamp for 10 days after start date.
* Use Serve end-point to get a single product from an active campaign (10 days max) filtered the category you input with the maximum value of the Bid field. 
<br> If no campaign found with matching category the api will search again ignoring the category resulting in a single product from the highest bid campaign.   

Thanks!
