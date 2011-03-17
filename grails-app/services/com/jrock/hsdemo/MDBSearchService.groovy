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

    static destination = "HibernateSearchController"

//    public void onMessage(Message message){
//        log.debug "${Calendar.instance.getTime()} Received message from slave node $message"
//
//        super.onMessage message
//    }

    @Override protected Session getSession() {
//        return sessionFactory.getCurrentSession()
        mySession = sessionFactory.openSession()
        return mySession
    }

    @Override protected void cleanSessionIfNeeded(Session session) {
        mySession?.close() //do nothing, managed environment
    }
}
