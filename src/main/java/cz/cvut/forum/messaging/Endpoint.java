package cz.cvut.forum.messaging;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public abstract class Endpoint {

    protected Channel channel;
    protected Connection connection;
    protected String queueName;

    public Endpoint(String queueName) throws IOException {
        this.queueName = queueName;
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        connection = factory.newConnection();
        channel = connection.createChannel();


        channel.exchangeDeclare("logs", "fanout");
        String queue = channel.queueDeclare().getQueue();
        this.queueName = queue;
        channel.queueBind(queue, "logs", "");


//        channel.queueDeclare(queueName, false, false, false, null);
    }


    public void close() throws IOException, TimeoutException {
        this.channel.close();
        this.connection.close();
    }
}
