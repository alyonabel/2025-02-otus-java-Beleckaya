package ru.otus.demo;

import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.core.repository.DataTemplateHibernate;
import ru.otus.core.repository.HibernateUtils;
import ru.otus.core.sessionmanager.TransactionManagerHibernate;
import ru.otus.crm.dbmigrations.MigrationsExecutorFlyway;
import ru.otus.crm.model.AddressDataSet;
import ru.otus.crm.model.Client;
import ru.otus.crm.model.PhoneDataSet;
import ru.otus.crm.service.DbServiceClientImpl;

import java.util.List;

public class DbServiceDemo {

    private static final Logger log = LoggerFactory.getLogger(DbServiceDemo.class);

    public static final String HIBERNATE_CFG_FILE = "hibernate.cfg.xml";

    public static void main(String[] args) {
        var configuration = new Configuration().configure(HIBERNATE_CFG_FILE);

        var dbUrl = configuration.getProperty("hibernate.connection.url");
        var dbUserName = configuration.getProperty("hibernate.connection.username");
        var dbPassword = configuration.getProperty("hibernate.connection.password");

        new MigrationsExecutorFlyway(dbUrl, dbUserName, dbPassword).executeMigrations();

        var sessionFactory = HibernateUtils.buildSessionFactory(configuration, Client.class, AddressDataSet.class, PhoneDataSet.class);

        var transactionManager = new TransactionManagerHibernate(sessionFactory);
///
        var clientTemplate = new DataTemplateHibernate<>(Client.class);
///
        var dbServiceClient = new DbServiceClientImpl(transactionManager, clientTemplate);

        var client = new Client("dbServiceFirst");
        var addressDataSet = new AddressDataSet();
        addressDataSet.setStreet("RedStreet");
        client.setAddressDataSet(addressDataSet);
        addressDataSet.setClient(client);

        var phoneDataSet = new PhoneDataSet("140-350");
        phoneDataSet.setClient(client);
        var phonaDataSet2 = new PhoneDataSet("940-650");
        phonaDataSet2.setClient(client);

        client.setPhoneDataSets(List.of(phoneDataSet, phonaDataSet2));

        var savedClient = dbServiceClient.saveClient(client);

        var clientSelected = dbServiceClient.getClient(savedClient.getId())
                .orElseThrow(() -> new RuntimeException("Client not found, id:" + client.getId()));
        log.info("clientSecondSelected:{}", clientSelected);
///
        clientSelected.setName("NewUpdatedName");
        clientSelected.getPhoneDataSets().removeIf(it -> it.getNumber().equals("940-650"));

        dbServiceClient.saveClient(clientSelected);
        var clientUpdated = dbServiceClient.getClient(clientSelected.getId())
                .orElseThrow(() -> new RuntimeException("Client not found, id:" + clientSelected.getId()));
        log.info("clientUpdated:{}", clientUpdated);

        log.info("All clients");
        dbServiceClient.findAll().forEach(it -> log.info("client:{}", it));
    }
}
