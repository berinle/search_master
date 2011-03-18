import com.jrock.hsdemo.Movie
import org.hibernate.search.Search

class BootStrap {

    def init = { servletContext ->

        //build initial index?
        if(Movie.list() == 0){
            println " seeding the database "
            new Movie(title:'The Dutchess', runtime:120, genre:"Drama").save(flush:true)
            new Movie(title:'Are we there yet?', runtime:90, genre:"Comedy").save(flush:true)
            new Movie(title:'Air Force One', runtime:120, genre:"Action").save(flush:true)
        }

        Movie.withSession { session ->
            def fts = Search.getFullTextSession(session)
            fts.beginTransaction()
            /*fts.createIndexer(Movie.class)
            .batchSizeToLoadObjects(30)
            .threadsForSubsequentFetching(8)
            .threadsToLoadObjects(4)
            .startAndWait()*/

            def list = fts.createQuery("from Movie").list()
            list.each {
                fts.index(it)
            }
            fts.flushToIndexes()
            fts.close()

            fts.getTransaction().commit()
        }

        log.info "Done building initial index"

    }
    def destroy = {
    }
}
