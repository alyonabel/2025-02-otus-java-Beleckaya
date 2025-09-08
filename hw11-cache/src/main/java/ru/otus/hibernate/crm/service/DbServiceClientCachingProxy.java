package ru.otus.hibernate.crm.service;

import ru.otus.cachehw.HwCache;
import ru.otus.cachehw.HwListener;
import ru.otus.hibernate.crm.model.Client;

import java.util.List;
import java.util.Optional;

public class DbServiceClientCachingProxy implements DBServiceClient{

    private final HwCache<String, Client> cache;
    private final DBServiceClient dbServiceClient;

    public DbServiceClientCachingProxy(DbServiceClientImpl dbServiceClient, HwCache<String, Client> cache) {
        this.dbServiceClient = dbServiceClient;
        this.cache = cache;
    }

    @SafeVarargs
    public DbServiceClientCachingProxy(DbServiceClientImpl dbServiceClient, HwCache<String, Client> cache, HwListener<String, Client>... cacheListeners) {
        this.dbServiceClient = dbServiceClient;
        this.cache = cache;

        for (var cacheListener : cacheListeners) {
            this.cache.addListener(cacheListener);
        }
    }

    public Client getKey(long id){
        var key = String.valueOf(id);
        return cache.get(key);
    }

    public void putKey(long id, Client client){
        var key = String.valueOf(id);
        cache.put(key, client);
    }

    @Override
    public Client saveClient(Client client) {
        var savedClient = dbServiceClient.saveClient(client);
        putKey(savedClient.getId(),savedClient);
        return savedClient;
    }

    @Override
    public Optional<Client> getClient(long id) {
        var clientInCache = getKey(id);
        if (clientInCache == null) {
            var client = dbServiceClient.getClient(id);
            client.ifPresent(value -> putKey(id,value));
            return client;
        }
        return Optional.of(clientInCache);
    }

    @Override
    public List<Client> findAll() {
        var clients = dbServiceClient.findAll();
        clients.forEach(it -> putKey(it.getId(),it));
        return clients;
    }
}
