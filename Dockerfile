# Use the official RabbitMQ image from Docker Hub
FROM rabbitmq:latest

# Expose ports
EXPOSE 4369 5671 5672 25672 15672 61613 61614

# Enable RabbitMQ management plugin
RUN rabbitmq-plugins enable --offline rabbitmq_management

# Set environment variables if needed
# ENV RABBITMQ_DEFAULT_USER=user
# ENV RABBITMQ_DEFAULT_PASS=user

# Copy custom configuration files if needed
# COPY conf/rabbitmq.conf /etc/rabbitmq/rabbitmq.conf

# Start RabbitMQ server
CMD ["rabbitmq-server"]