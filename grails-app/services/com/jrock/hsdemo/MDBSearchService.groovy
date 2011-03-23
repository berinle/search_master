package com.jrock.hsdemo

import org.hibernate.search.backend.impl.jms.AbstractJMSHibernateSearchController
import org.hibernate.Session
import javax.jms.Message

class MDBSearchService extends AbstractJMSHibernateSearchController {

    static transactional = false
    static exposes = ['jms']
    def sessionFactory
    def mySession

//    static destination = "queue.hibernatesearch"
//    static destination = "java:comp/env/queue/hibernatesearch"

    static destination = "HSQ"

    public void onMessage(Message message){
        //log.debug "${Calendar.instance.getTime()} Received message from slave node $message"
        println "onMessage called(): received message from slave node"
        try{
        super.onMessage message
        } catch(ex){
            println("was unable to successfully consume message " + message)
            log.error("was unable to successfully consume message " + message)
            ex.printStackTrace()
        }
    }

    @Override protected Session getSession() {
//        return sessionFactory.getCurrentSession()
        mySession = sessionFactory.openSession()
        return mySession
    }

    @Override protected void cleanSessionIfNeeded(Session session) {
        mySession?.close() //do nothing, managed environment
    }
}
