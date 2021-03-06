package hello;

import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * <p>
 * A simple Message Driven Bean that asynchronously receives and processes the messages that are sent to the queue.
 * </p>
 *
 */
/*
@MessageDriven(name = "WorkerMDB", activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "queue/test"),
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge")})
*/
@MessageDriven(name = "WorkerMDB", activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "testQueue"),
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
        @ActivationConfigProperty(propertyName = "user", propertyValue = "quickstartUser"),
        @ActivationConfigProperty(propertyName = "password", propertyValue = "quickstartPwd1!"),
        @ActivationConfigProperty(propertyName = "connectionParameters", propertyValue = "host=jbossm;port=5445"),
        @ActivationConfigProperty(propertyName = "connectorClassName", propertyValue = "org.apache.activemq.artemis.core.remoting.impl.netty.NettyConnectorFactory")
}, mappedName = "testQueue")
public class WorkerMDB  implements MessageListener {

    private static final Logger LOGGER = Logger.getLogger(WorkerMDB.class.toString());

    /**
     * @see MessageListener#onMessage(Message)
     */
    public void onMessage(Message rcvMessage) {
        TextMessage msg = null;
        try {
            if (rcvMessage instanceof TextMessage) {
                msg = (TextMessage) rcvMessage;
                LOGGER.info("Received Message from queue: " + msg.getText());
            } else {
                LOGGER.warning("Message of wrong type: " + rcvMessage.getClass().getName());
            }
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            LOGGER.info("InterruptedException: " + e.getMessage());
        }
    }
}
