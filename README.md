
## Movie Rating Portal
 
It is clone of IMDB website. To get a better understanding of the project, check out imdb.com.

In this application, users can create, rate and write comments about movies and tv series. Users can create favorite lists and they can share their lists with other users.

There will be 5 main resource servers:
 - Movie Service
	 - CRUD operations for movies.
 - Tv Series Service
	 - CRUD operations for tv series.
 - Comment Service
	 - CRUD operations for comments.
	 - Users can write comment for movies and/or tv series.
 - User Service
	 - CRUD operations for users.
	 - Login and Register functionality.
 - Rating Service 
	 - CRUD operations for ratings.

Project have
- Discovery Server
- API Gateway
- Configuration Server
- OAuth 2 Server (Keycloak)
- Tracing (Zipkin and Sleuth)
- Vault 


####  Functional  Requirements
--- 
* Users can register to the system.
* Owners can create/remove/update movies and tv series.
* User can filter movies or tv series by :
	* released year,
	* rating,
	* genre,
	* director,
	* actor/actress
	* duration.
* Users can create/remove/update comments for movies/tv series
* Users can rate movies/tv series.
	* Rating is between 1 and 5.
* Users can add/remove movies/tv series to/from their favorite lists.
	* Users can have multiple favorite lists.

#### Technical Details
---
* Used inheritance for tv series and movies. 
* Used appropriate hibernate fetch strategies.
* Used RabbitMQ and Kafka.
* Each micro-service contain a Dockerfile to create a docker image.
* Implemented Eventual Consistency.
* Each service have `data.sql` for dummy data.
* Have 1 Kubernetes Deployment Configuration file.
* Created a Postman collection for each endpoint.
* Created API documentation with Swagger.
* Prepared a docker compose file to run project.
* Used Circuit Breaker pattern for 5 methods.
* Used ELK Stack to store application logs.
	* Please refer to https://www.elastic.co/what-is/elk-stack
