# RabbitMQ Docker Setup

## RabbitMQ Choco Setup

Si no aparece en ningún lado: reinstala forzando

```sh
choco uninstall rabbitmq -y
choco install rabbitmq -y --force
```

Pega aquí la salida de:

```sh
Get-ChildItem "C:\" -Recurse -Filter "rabbitmq-service.bat" -ErrorAction SilentlyContinue
```

Una vez que encuentres erlsrv.exe, configura ERLANG_HOME

```sh
setx ERLANG_HOME "C:\Program Files\Erlang OTP" /M
```

Luego cierra y reabre PowerShell como Admin, y vuelve a intentar:

```sh
cd "C:\Program Files\RabbitMQ Server\rabbitmq_server-4.1.0\sbin"
.\rabbitmq-service.bat install
.\rabbitmq-service.bat start
```

Open:

```sh
http://localhost:15672
Login:
guest / guest
```

## RabbitMQ Docker Setup

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