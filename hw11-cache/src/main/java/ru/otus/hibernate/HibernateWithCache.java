package ru.otus.hibernate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.hibernate.cfg.Configuration;

import ru.otus.cachehw.HwListener;
import ru.otus.cachehw.MyCache;
import ru.otus.hibernate.core.repository.DataTemplateHibernate;
import ru.otus.hibernate.core.repository.HibernateUtils;
import ru.otus.hibernate.core.sessionmanager.TransactionManagerHibernate;
import ru.otus.hibernate.crm.model.AddressDataSet;
import ru.otus.hibernate.crm.model.Client;
import ru.otus.hibernate.crm.model.PhoneDataSet;
import ru.otus.hibernate.crm.service.DbServiceClientCachingProxy;
import ru.otus.hibernate.crm.service.DbServiceClientImpl;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static ru.otus.hibernate.demo.DbServiceDemo.HIBERNATE_CFG_FILE;

public class HibernateWithCache {

    private static final Logger logger = LoggerFactory.getLogger(HibernateWithCache.class);

    public static void main(String[] args) {
        HwListener<String, Client> listener = new HwListener<String, Client>() {
            @Override
            public void notify(String key, Client value, String action) {
                logger.info("key: {}, clientId: {}, action: {}", key, value == null ? null : value.getId(), action);
            }
        };

        var configuration = new Configuration().configure(HIBERNATE_CFG_FILE);

        var sessionFactory = HibernateUtils.buildSessionFactory(configuration, Client.class, AddressDataSet.class, PhoneDataSet.class);

        var transactionManager = new TransactionManagerHibernate(sessionFactory);

        var clientTemplate = new DataTemplateHibernate<>(Client.class);

        var dbServiceClient = new DbServiceClientImpl(transactionManager, clientTemplate);

        var client = createClient(dbServiceClient);

        logger.info("----------Without cache----------");
        long startTimeWithoutCache = System.nanoTime();
        var client1 = dbServiceClient.getClient(client.getId())
                .orElseThrow(() -> new RuntimeException("Client not found, id:" + client.getId()));
        long estimatedTimeWithoutCache = System.nanoTime() - startTimeWithoutCache;
        logger.info("Without cache. Client: {}, Time: {}", client1, estimatedTimeWithoutCache);

        logger.info("----------With cache----------");

        var dbServiceClientCachingProxy = new DbServiceClientCachingProxy(dbServiceClient, new MyCache<>(), listener);

        var clientFromDb = dbServiceClientCachingProxy.getClient(client.getId())
                .orElseThrow(() -> new RuntimeException("Client not found, id:" + client.getId()));

        long startTimeWithCache = System.nanoTime();
        var clientFromCache = dbServiceClientCachingProxy.getClient(clientFromDb.getId())
                .orElseThrow(() -> new RuntimeException("Client not found, id:" + clientFromDb.getId()));
        long estimatedTimeWithCache = System.nanoTime() - startTimeWithCache;
        logger.info("With cache. Client: {}, Time: {}", clientFromCache, estimatedTimeWithCache);

        logger.info("Time without cache: {}, time with cache: {}. Different: {}",
                TimeUnit.MILLISECONDS.convert(estimatedTimeWithoutCache, TimeUnit.NANOSECONDS),
                TimeUnit.MILLISECONDS.convert(estimatedTimeWithCache, TimeUnit.NANOSECONDS),
                estimatedTimeWithoutCache / estimatedTimeWithCache);

    }

    private static Client createClient(DbServiceClientImpl dbServiceClient) {
        var client1 = new Client("dbServiceFirst");

        var addressDataSet1 = new AddressDataSet();
        addressDataSet1.setStreet("street1");
        client1.setAddressDataSet(addressDataSet1);
        addressDataSet1.setClient(client1);

        var phoneDataSet1 = new PhoneDataSet("+7911");
        phoneDataSet1.setClient(client1);
        var phoneDataSet2 = new PhoneDataSet("+7952");
        phoneDataSet2.setClient(client1);

        client1.setPhoneDataSets(List.of(phoneDataSet1, phoneDataSet2));

        return dbServiceClient.saveClient(client1);
    }
}
