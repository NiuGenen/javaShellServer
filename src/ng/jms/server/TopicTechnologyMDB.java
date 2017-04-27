package ng.jms.server;

import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSDestinationDefinition;
import javax.jms.JMSDestinationDefinitions;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@JMSDestinationDefinitions(
	    value = {
	        @JMSDestinationDefinition(
	            name = "java:jboss/exported/jms/topic/technology",
	            interfaceName = "javax.jms.Topic",
	            destinationName = "TopicTechnologyMDB"
	        )
	    })

/**
 * <p>
 * A simple Message Driven Bean that asynchronously receives and processes the messages that are sent to the topic.
 * </p>
 *
 * @author Serge Pagop (spagop@redhat.com)
 *
 */
@MessageDriven(name = "TopicTestMDB", activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jboss/exported/jms/topic/technology"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic"),
    @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge") })
public class TopicTechnologyMDB implements MessageListener {

    private final static Logger LOGGER = Logger.getLogger(TopicTestMDB.class.toString());

    /**
     * @see MessageListener#onMessage(Message)
     */
    public void onMessage(Message rcvMessage) {
        TextMessage msg = null;
        try {
            if (rcvMessage instanceof TextMessage) {
                msg = (TextMessage) rcvMessage;
                LOGGER.info("Received Message from topic technology: " + msg.getText());
            } else {
                LOGGER.warning("Message of wrong type technology: " + rcvMessage.getClass().getName());
            }
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
    }
}
