# Enctor Booking API

This REST API is build using JEE and Jetty only. For the testing its using Junit and mockito. Solution can be deployed 
as a linux service ( scripts/booking.service) or as a container solution.

## Endpoints

There are two API endpoints provided.

#### Check the availability of the seats for a given date
GET /api/booking

```shell
$ curl http://localhost:8080/api/booking?date=2021-10-23&passengerCount=5&origin=A&destination=D
```

```javascript
{
    id: 1630484279654,
        origin: "A",
        destination: "D",
        type: "FIRST_TRIP",
        passengerCount: 2,
        totalPrice: 300,
        date: "Oct 23, 2021, 12:00:00 AM",
        distance: 90,
        duration: 180,
        seats: [
        "1A",
        "1B",
        "1C",
        "1D",
    ]
}
```

**Request Parameters**

| Name | Description | Is Mandatory
| ------ | ------ |------ |
| Origin | Start point | Yes |
| Destination | End point | Yes |
| Date | Date of the trip | Yes |
| Passenger Count | Number of people taking the journey | No |

**Response Parameters**

| Code | Description
| ------ | ------ |
| Id | Ticket id |
| Origin | Start point |
| Destination | End point |
| Type | Type of the trip (First or Second) |
| Passenger Count | Number of people taking the journey |
| Total Price | Total price of the trip |
| Date | Date of the trip |
| Distance | The distance of the trip |
| Duration | The duration of the trip |

**Response Codes**

| Name | Description
| ------ | ------ |
| 200 | SUCCESS |
| 400 | BAD REQUEST |

####  To reserve seats for a given date
POST /api/booking

```shell
$ curl -X POST http://localhost:8080/api/booking?date=2021-10-23&passengerCount=5&origin=A&destination=D
```

```javascript
{
    id: 1630484279654,
        origin: "A",
        destination: "D",
        type: "FIRST_TRIP",
        passengerCount: 2,
        totalPrice: 300,
        date: "Oct 23, 2021, 12:00:00 AM",
        distance: 90,
        duration: 180,
        seats: [
        "1A",
        "1B",
        "1C",
        "1D",
    ]
}
```

**Request Parameters**

| Name | Description | Is Mandatory
| ------ | ------ |------ |
| Origin | Start point | Yes |
| Destination | End point | Yes |
| Date | Date of the trip | Yes |
| Passenger Count | Number of people taking the journey | No |

**Response Parameters**

| Code | Description
| ------ | ------ |
| Id | Ticket id |
| Origin | Start point |
| Destination | End point |
| Type | Type of the trip (First or Second) |
| Passenger Count | Number of people taking the journey |
| Total Price | Total price of the trip |
| Date | Date of the trip |
| Distance | The distance of the trip |
| Duration | The duration of the trip |

**Response Codes**

| Name | Description
| ------ | ------ |
| 200 | SUCCESS |
| 400 | BAD REQUEST |
## Development
This application is developed using openjdk (11.0.11) and maven (3.6.3).

#### Building for source

Generating jar for distribution:

```shell
$ mvn clean install
```

## Docker

By default, the Docker will expose port 8080, so change this within the
Dockerfile if necessary.

```shell
$ docker build --tag=reservation-server:latest .
```

This will create the docker image and pull in the necessary dependencies. 
Once done, run the Docker image and map the port 8080 on your host.

```shell
$ docker run -p8080:8080 reservation-server:latest
```

Verify the deployment by navigating to your server address in your preferred browser.

```shell
127.0.0.1:8080
```

## Improvements and Security

- By using a RET framework (Spring boot, Jersy) development/maintenance effort can be significantly reduced
- Use of API design tool like swagger will reduce development time by generating client stubs easily
- Wrap the service endpoint with HTTPS
- Integrate with SSO will be needed.
- As the API grows need to implement a Role based access control mechanism 