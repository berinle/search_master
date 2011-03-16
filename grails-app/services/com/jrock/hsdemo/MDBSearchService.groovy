package com.jrock.hsdemo

import org.hibernate.search.backend.impl.jms.AbstractJMSHibernateSearchController
import org.hibernate.Session

class MDBSearchService extends AbstractJMSHibernateSearchController {

    static transactional = false
    static exposes = ['jms']
    static destination = "queue.hibernatesearch"

    def onMessage(it) {
        log.debug "Received message $it"
    }

    @Override protected Session getSession() {
        return session;
    }

    @Override protected void cleanSessionIfNeeded(Session session) {
        //session.close() //do nothing, managed environment
    }
}
