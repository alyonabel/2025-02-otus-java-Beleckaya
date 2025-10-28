package ru.otus.appcontainer;

import ru.otus.appcontainer.api.AppComponent;
import ru.otus.appcontainer.api.AppComponentsContainer;
import ru.otus.appcontainer.api.AppComponentsContainerConfig;

import java.util.*;

@SuppressWarnings("squid:S1068")

public class AppComponentsContainerImpl implements AppComponentsContainer {

    private final List<Object> appComponents = new ArrayList<>();
    private final Map<String, Object> appComponentsByName = new HashMap<>();

    public AppComponentsContainerImpl(Class<?> initialConfigClass) {
        processConfig(initialConfigClass);
    }

    private void processConfig(Class<?>... initialConfigClasses) {
        try {
            processConfigs(initialConfigClasses);
        } catch (Exception e) {
            throw new RuntimeException("It's not possible to create container", e);
        }
    }

    private void processConfigs(Class<?>... configClasses) throws Exception {
        checkConfigClass(configClasses);
        var configClassesSorted = Arrays.stream(configClasses)
                .filter(it -> it.isAnnotationPresent(AppComponentsContainerConfig.class))
                .sorted(Comparator.comparingInt(it -> it.getAnnotation(AppComponentsContainerConfig.class).order()))
                .toList();

        for (var configClass : configClassesSorted) {
            var sortedMethods = Arrays.stream(configClass.getMethods())
                    .filter(it -> it.isAnnotationPresent(AppComponent.class))
                    .sorted(Comparator.comparingInt(it -> it.getAnnotation(AppComponent.class).order()))
                    .toList();

            var instance = configClass.getDeclaredConstructor().newInstance();

            for (var method : sortedMethods) {
                var parameters = method.getParameterTypes();
                var args = Arrays.stream(parameters).map(this::getAppComponent).toArray();

                var component = method.invoke(instance, args);

                var componentName = method.getAnnotation(AppComponent.class).name();

                if (appComponentsByName.containsKey(componentName)) {
                    throw new IllegalArgumentException("Duplicate component name: " + componentName);
                }

                appComponentsByName.put(componentName, component);
                appComponents.add(component);

            }
        }
    }

    private void checkConfigClass(Class<?>[] configClass) {
        for (Class<?> clazz : configClass) {
            if (!clazz.isAnnotationPresent(AppComponentsContainerConfig.class)) {
                throw new IllegalArgumentException(String.format("This class is not config %s", clazz.getName()));
            }
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public <C> C getAppComponent(Class<C> componentClass) {
        Object component = appComponents.stream()
                .filter(componentClass::isInstance)
                .findFirst().orElseThrow(() -> new IllegalArgumentException("No such component"));
        return (C) component;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <C> C getAppComponent(String componentName) {
        return (C) Optional.ofNullable(appComponentsByName.get(componentName))
                .orElseThrow(() -> new IllegalArgumentException("No such component"));

    }
}
