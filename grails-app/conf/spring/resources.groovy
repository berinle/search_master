import org.apache.activemq.ActiveMQConnectionFactory

// Place your Spring DSL code here
beans = {
    jmsConnectionFactory(ActiveMQConnectionFactory) {
         brokerURL = "tcp://localhost:61616"
    }
}
