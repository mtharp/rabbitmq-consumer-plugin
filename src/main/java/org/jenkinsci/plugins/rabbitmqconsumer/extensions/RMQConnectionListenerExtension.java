package org.jenkinsci.plugins.rabbitmqconsumer.extensions;
import org.jenkinsci.plugins.rabbitmqconsumer.RMQConnection;

import hudson.ExtensionList;
import hudson.ExtensionPoint;
import jenkins.model.Jenkins;


public abstract class RMQConnectionListenerExtension implements ExtensionPoint {

    public abstract void onConnectionOpened(RMQConnection rmqConnection);
    public abstract void onConnectionClosed(RMQConnection rmqConnection);

    public static void fireOnConnectionOpened(RMQConnection rmqConnection) {
        for (RMQConnectionListenerExtension l : all()) {
            l.onConnectionOpened(rmqConnection);
        }
    }

    public static void fireOnConnectionClosed(RMQConnection rmqConnection) {
        for (RMQConnectionListenerExtension l : all()) {
            l.onConnectionClosed(rmqConnection);
        }
    }

    public static ExtensionList<RMQConnectionListenerExtension> all() {
        return Jenkins.getInstance().getExtensionList(RMQConnectionListenerExtension.class);
    }
}
