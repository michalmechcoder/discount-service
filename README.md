# Discount Service

Service provide a REST API for calculating a given product's price based on the number of products ordered.

### Build project
Project is created using Maven, so you can easily build it via command
`mvn package`. If you want to run build with unit and integration tests go with `mvn verify`.

### Create image
There is a `Dockerfile` at your disposal. So run `docker build -t discount-service .` to create an image.

### Run project
To run container go with `docker run -d -p 8080:8080 discount-service`

### Service functionality
Service provide a REST API for calculating a given product's price based on the number
of products ordered.

All details can be found under [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

By default, service calculates discount based on products count. You can change it be editing `application.yml` properties file. I believe it's self-explanatory.