/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.boldust.gui;

import java.io.Serializable;
import java.sql.Connection;
import java.util.Map;
import java.util.Set;
import javax.naming.NamingException;
import javax.naming.Reference;
import org.hibernate.Cache;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionBuilder;
import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;
import org.hibernate.StatelessSessionBuilder;
import org.hibernate.TypeHelper;
import org.hibernate.engine.spi.FilterDefinition;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.metadata.CollectionMetadata;
import org.hibernate.stat.Statistics;

/**
 *
 * @author oefish
 */
public abstract class BoldustGuiSessionFactory implements SessionFactory {

    @Override
    public Session openSession() throws HibernateException {
        System.err.println("openSession");
        throw new UnsupportedOperationException("OpenSession Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Session getCurrentSession() throws HibernateException {
        System.err.println("getCurrentSession");
        throw new UnsupportedOperationException("GetCurrentSession Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void close() throws HibernateException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
