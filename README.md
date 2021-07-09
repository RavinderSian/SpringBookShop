# SpringBookShop
Book shop Application created using Spring.
CRUD operations are performed for all model classes, including findAll. 
Tests were written in Junit. 

## Postman Requests

The requests can imported into postman by using the file in the postman directory.
When imported the following can be seen in postman:
![](https://github.com/RavinderSian/SpringBookShop/blob/master/bookshop-spring-1/screenshots/BookShop%20Requests.JPG)

There is a GET request for getting objects by id for each model class, this request returns the object as a response:
![](https://github.com/RavinderSian/SpringBookShop/blob/master/bookshop-spring-1/screenshots/Get%20By%20Id.JPG)

All requests that contain an Id in the URL have error handling for when an entry is not present for a given id:
![](https://github.com/RavinderSian/SpringBookShop/blob/master/bookshop-spring-1/screenshots/Id%20Not%20Found.JPG)

Objects can be deleted by passing the id in the request:
![](https://github.com/RavinderSian/SpringBookShop/blob/master/bookshop-spring-1/screenshots/Deleted.JPG)

To save an object a POST request is used:
![](https://github.com/RavinderSian/SpringBookShop/blob/master/bookshop-spring-1/screenshots/Add%20Book.JPG)

There are also POST requests to update properties of some objects for example a books title:
![](https://github.com/RavinderSian/SpringBookShop/blob/master/bookshop-spring-1/screenshots/Update%20Book%20title.JPG)

There are GET requests to find all entires of an object type:
![](https://github.com/RavinderSian/SpringBookShop/blob/master/bookshop-spring-1/screenshots/Get%20All%20Books.JPG)

Mapped objects can also be retrived for most objects using a GET request, for examples the sales of a book:
![](https://github.com/RavinderSian/SpringBookShop/blob/master/bookshop-spring-1/screenshots/Get%20Sales.JPG)
