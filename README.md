# SpringBookShop
Book shop Application created using Spring.
CRUD operations are performed for all model classes, including findAll. 
Tests were written in Junit. 

## Running as a Docker Container

The bookshop container uses a custom base image which must be built first
The base image Dockerfile is in the spring-jdk11-base directory
Within this directory the following command must be used:

```
sudo docker build -t "spring-jdk11-base" .
```
To then build the bookshop docker container, from the bookshop-spring-1 directory use the following command:
```
sudo docker build -t "name" .
```

Following this the container can be run with:
```
sudo docker run -p 8080:8080 -t name
```
The -p 8080:8080 flag is necessary to expose port 8080 and requests can be sent to this container in the format:
http://[ip address]:8080/[url]
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
