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

import org.json.JSONException;
import org.json.JSONObject;

import ng.jms.hbm.HDB;
import ng.jms.hbm.Table_news;

@JMSDestinationDefinitions(
	    value = {
	        @JMSDestinationDefinition(
	            name = "java:jboss/exported/jms/topic/society",
	            interfaceName = "javax.jms.Topic",
	            destinationName = "TopicSocietyMDB"
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
@MessageDriven(name = "TopicSocietyMDB", activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jboss/exported/jms/topic/society"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic"),
    @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge") })
public class TopicSocietyMDB implements MessageListener {

    private final static Logger LOGGER = Logger.getLogger(TopicTestMDB.class.toString());

    /**
     * @see MessageListener#onMessage(Message)
     */
    public void onMessage(Message rcvMessage) {
        TextMessage msg = null;
        try {
            if (rcvMessage instanceof TextMessage) {
                msg = (TextMessage) rcvMessage;
                LOGGER.info("Received Message from topic society: " + msg.getText());

                JSONObject obj = new JSONObject(msg.getText());
                Table_news table_news = new Table_news();
                table_news.setCntnt( obj.getString("cntnt"));
                table_news.setTime( obj.getLong("time"));
                table_news.setTitle( obj.getString("title"));
                table_news.setTopicid( obj.getInt("topicid"));
                table_news.setUserid( obj.getInt("userid"));
                
                HDB.getInstance().save(table_news);
            } else {
                LOGGER.warning("Message of wrong type society: " + rcvMessage.getClass().getName());
            }
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }catch (JSONException e){
        	LOGGER.warning("Cannot resolve json in TopicSocietyMDB.");
        }
    }
}
