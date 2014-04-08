// File: grails-app/conf/spring/resources.groovy

import com.sfg.Book
import grails.rest.render.json.JsonCollectionRenderer
import grails.rest.render.xml.XmlCollectionRenderer

beans = {
    // The name of the component is not relevant. 
    // The constructor argument Book sets the componentType for
    // the collection renderer.
    jsonBookCollectionRenderer(JsonCollectionRenderer, Book)
    xmlBookCollectionRenderer(XmlCollectionRenderer, Book)

    // Uncomment the following to register collection renderers
    // for all domain classes in the application.
    // for (domainClass in grailsApplication.domainClasses) {
    //     "json${domainClass.shortName}CollectionRenderer(JsonCollectionRenderer, domainClass.clazz)
    //     "xml${domainClass.shortName}CollectionRenderer(XmlCollectionRenderer, domainClass.clazz)
    // }
}