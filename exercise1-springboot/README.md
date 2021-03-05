# Example 1 - SpringBoot

Technologies used :

- Spring Boot 2.1.2.RELEASE
- Spring 5.1.4.RELEASE
- Spring Data JPA 2.1.4.RELEASE
- H2 In-memory Database 1.4.197
- Tomcat Embed 9.0.14
- JUnit 4.12
- Maven 3
- Java 8

## REST controller to create the following REST API endpoints :

| HTTP Method| URI|	Description |
|---|---|---|
| GET|	/books|	List all books.|
| POST|	/books|	Save a book.|
| GET|	/books/{id}|	Find a book where id = {:id}.|
| PUT|	/books/{id}|	Update a book where id = {:id}, or save it.|
| PATCH|	/books/{id}|	Update a single field where id = {:id}.|
| DELETE|	/books/{id}|	Delete a book where id = {:id}.|

## Test application

### Find All – GET /books

```sh
curl -v localhost:8080/books
```
Response
```json
*   Trying ::1...
* TCP_NODELAY set
* Connected to localhost (::1) port 8080 (#0)
> GET /books HTTP/1.1
> Host: localhost:8080
> User-Agent: curl/7.64.1
> Accept: */*
> 
< HTTP/1.1 200 
< Content-Type: application/json
< Transfer-Encoding: chunked
< Date: Fri, 05 Mar 2021 19:48:05 GMT
< 
* Connection #0 to host localhost left intact
[
  {"id":1,"name":"War art","author":"Sun Tzu","price":15.41},
  {"id":2,"name":"One Hundred Years of Solitude","author":"Gabriel García Márquez","price":9.69},
  {"id":3,"name":"The leader who had no title","author":"Robin S. Sharma","price":47.99}
]*
```

## Test 404 – GET /books/5
```
curl -v localhost:8080/books/5
```

Response
```json
*   Trying ::1...
* TCP_NODELAY set
* Connected to localhost (::1) port 8080 (#0)
> GET /books/5 HTTP/1.1
> Host: localhost:8080
> User-Agent: curl/7.64.1
> Accept: */*
> 
< HTTP/1.1 404 
< Content-Type: application/json
< Transfer-Encoding: chunked
< Date: Fri, 05 Mar 2021 19:52:27 GMT
< 
{
    "timestamp":"2021-03-05T19:52:27.399+00:00",
    "status":404,
    "error":"Not Found",
    "message":"Book Id not found: 5",
    "path":"/books/5"}
```

## Test Save – POST /books -d {json}

```
curl -v -X POST localhost:8080/books -H "Content-type:application/json" -d "{\"name\":\"Spring REST \",\"author\":\"alejo\",\"price\":\"9.99\"}"
```

Response
```json
*   Trying ::1...
* TCP_NODELAY set
* Connected to localhost (::1) port 8080 (#0)
> POST /books HTTP/1.1
> Host: localhost:8080
> User-Agent: curl/7.64.1
> Accept: */*
> Content-type:application/json
> Content-Length: 65
> 
* upload completely sent off: 65 out of 65 bytes
< HTTP/1.1 201 
< Content-Type: application/json
< Transfer-Encoding: chunked
< Date: Fri, 05 Mar 2021 19:59:52 GMT
< 
* Connection #0 to host localhost left intact
{
  "id":4,
  "name":"Spring REST ",
  "author":"alejo",
  "price":9.99
}
```

### Test Update – PUT /books/4 -d {json}

```sh
curl -v -X PUT localhost:8080/books/4 -H "Content-type:application/json" -d "{\"name\":\"Spring Boot\",\"author\":\"Pivotal\",\"price\":\"9.99\"}"
```

Response

```json
*   Trying ::1...
* TCP_NODELAY set
* Connected to localhost (::1) port 8080 (#0)
> PUT /books/4 HTTP/1.1
> Host: localhost:8080
> User-Agent: curl/7.55.1
> Accept: <strong>/</strong>
> Content-type:application/json
> Content-Length: 59
>
* upload completely sent off: 59 out of 59 bytes
< HTTP/1.1 200
< Content-Type: application/json;charset=UTF-8
< Transfer-Encoding: chunked
< Date: Tue, 19 Feb 2019 07:36:49 GMT
<
{
    "id":4,
    "name":"Spring Forever",
    "author":"pivotal",
    "price":9.99
}

> curl localhost:8080/books/4
{
  "id":4,
  "name":"Spring Boot",
  "author":"Pivotal",
  "price":9.99
}
```

### Test Update a ‘author’ field – PATCH /books/4 -d {json}

```sh
curl -v -X PATCH localhost:8080/books/4 -H "Content-type:application/json" -d "{\"author\":\"oracle\"}"
```

Response

```json
*   Trying ::1...
* TCP_NODELAY set
* Connected to localhost (::1) port 8080 (#0)
> PATCH /books/4 HTTP/1.1
> Host: localhost:8080
> User-Agent: curl/7.55.1
> Accept: <strong>/</strong>
> Content-type:application/json
> Content-Length: 19
>
* upload completely sent off: 19 out of 19 bytes
< HTTP/1.1 200
< Content-Type: application/json;charset=UTF-8
< Transfer-Encoding: chunked
< Date: Tue, 19 Feb 2019 07:39:53 GMT

{	
    "id":4,
    "name":"Spring Boot",
    "author":"oracle",
    "price":9.99
}
```

### Test delete – DELETE /books/4

```
curl -v -X DELETE localhost:8080/books/4
```

Response

```json
*   Trying ::1...
* TCP_NODELAY set
* Connected to localhost (::1) port 8080 (#0)
> DELETE /books/4 HTTP/1.1
> Host: localhost:8080
> User-Agent: curl/7.55.1
> Accept: <strong>/</strong>
>
< HTTP/1.1 200
< Content-Length: 0
< Date: Tue, 19 Feb 2019 07:44:24 GMT
<
```