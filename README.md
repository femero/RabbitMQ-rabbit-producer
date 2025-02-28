# RabbitMQ Docker Setup

## Build the Docker Image

To build the Docker image, run the following command:

```sh
docker build -t rabbitmq-image .
```


## Run the Docker Container

To run the Docker container, use the following command:

```sh
docker run -d --name rabbitmq-container -p 5672:5672 -p 15672:15672 -e RABBITMQ_DEFAULT_USER=user -e RABBITMQ_DEFAULT_PASS=user rabbitmq-image
```

## Alternative Option

You can also execute the start.sh script to build and run the Docker container:

```sh
./start.sh
```