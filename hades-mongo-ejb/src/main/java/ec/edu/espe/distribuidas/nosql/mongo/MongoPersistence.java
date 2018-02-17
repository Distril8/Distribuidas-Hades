/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.distribuidas.nosql.mongo;

import ec.edu.espe.distribuidas.nosql.mongo.config.ConfigDTO;
import ec.edu.espe.distribuidas.nosql.mongo.config.ConfigReader;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import org.bson.Document;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;


/**
 *
 * @author Henry Coral
 */
@Singleton
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
@Startup
public class MongoPersistence {

    private static final Logger LOG = Logger.getLogger(MongoPersistence.class.getName());

    private Datastore datastore;
    private ConfigDTO config;
    private MongoClient mongoClient = null;

    @PostConstruct
    private void postConstruct() {
        LOG.info("--------------------------------------------------------");
        LOG.info("Creando conexion");
        LOG.info("--------------------------------------------------------");
        ConfigReader reader = new ConfigReader();
        config = reader.getConfiguration();
        if (config != null) {
            MongoClientOptions mongoOptions = MongoClientOptions.builder().socketTimeout(config.getSocketTimeout())
                    .connectTimeout(config.getConnectionTimeout())
                    .maxConnectionIdleTime(config.getMaxIdleTime())
                    .applicationName(config.getAppName()).build(); 

            try {
                mongoClient = new MongoClient(new ServerAddress(config.getHost(), config.getPort()), mongoOptions);
            } catch (Exception e) {
                throw new RuntimeException("Error initializing MongoDB", e);
            }
            
            Morphia morphia = new Morphia();
            for (String packageN : config.getPackages()) {
                morphia.mapPackage(packageN, true);
            }
            datastore = morphia.createDatastore(mongoClient, config.getDatabase());
            
            datastore.ensureIndexes();
            LOG.info("Connection: " + config.toString() + " initialized");
        } else {
            LOG.info("Configuracion invalida, persistencia no disponible.");
            datastore = null;
            mongoClient = null;
        }
    }

    public MongoCollection<Document> getCollection(String name) {
        MongoDatabase db = mongoClient.getDatabase(config.getDatabase());
        return db.getCollection(name);
    }

    @Lock(LockType.READ)
    public Datastore context() {
        return this.datastore;
    }

   

}
