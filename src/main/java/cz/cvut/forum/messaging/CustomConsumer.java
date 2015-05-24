package cz.cvut.forum.messaging;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import cz.cvut.forum.bean.LoggedUser;
import cz.cvut.forum.dto.CategoryDTO;
import cz.cvut.forum.dto.TopicDTO;
import cz.cvut.forum.service.*;
import org.apache.commons.lang.SerializationUtils;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.ShutdownSignalException;
import org.primefaces.context.RequestContext;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.push.EventBus;
import org.primefaces.push.EventBusFactory;


@ManagedBean(eager=true) // immediate start
@ApplicationScoped
public class CustomConsumer extends Endpoint implements Runnable, Consumer {

    public CustomConsumer() throws IOException {
        super("queue");
    }

    private TopicService service;

    @PostConstruct
    public void init() {
        this.service = new TopicServiceImpl();
        this.run();
    }

    @PreDestroy
    public void destroy() {
        try {
            this.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run() {
        try {
            channel.basicConsume(queueName, true, this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleConsumeOk(String consumerTag) {
        System.out.println("handleConsume " + consumerTag);
    }

    public void handleDelivery(String consumerTag, Envelope env, BasicProperties props, byte[] body) throws IOException {
        Long id = (Long) SerializationUtils.deserialize(body);

        TopicDTO topic = service.get(id);

        EventBus eventBus = EventBusFactory.getDefault().eventBus();
//        eventBus.publish("/actions", message);
//        eventBus.publish("/actions", new FacesMessage("Vytvořeno vlákno!", topic.getTitle()));
        eventBus.publish("/actions", topic);

    }

    public void handleCancel(String consumerTag) {
        System.out.println("handleCancel" + consumerTag);
    }
    public void handleCancelOk(String consumerTag) {
        System.out.println("handleCancelOk" + consumerTag);
    }
    public void handleRecoverOk(String consumerTag) {
        System.out.println("handleRecoverOk" + consumerTag);
    }
    public void handleShutdownSignal(String consumerTag, ShutdownSignalException arg1) {
        System.out.println("handleShutdownSignal" + consumerTag + "/" + arg1.getMessage());
    }
}