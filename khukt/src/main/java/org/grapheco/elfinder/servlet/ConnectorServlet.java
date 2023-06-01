package org.grapheco.elfinder.servlet;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.grapheco.elfinder.controller.ConnectorController;
import org.grapheco.elfinder.controller.executor.CommandExecutorFactory;
import org.grapheco.elfinder.controller.executor.DefaultCommandExecutorFactory;
import org.grapheco.elfinder.controller.executors.MissingCommandExecutor;
import org.grapheco.elfinder.impl.DefaultFsService;
import org.grapheco.elfinder.impl.DefaultFsServiceConfig;
import org.grapheco.elfinder.impl.FsSecurityCheckForAll;
import org.grapheco.elfinder.impl.StaticFsServiceFactory;
import org.grapheco.elfinder.localfs.LocalFsVolume;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * ConnectorServlet is an example servlet it creates a ConnectorController on
 * init() and use it to handle requests on doGet()/doPost()
 *
 * users should extend from this servlet and customize required protected
 * methods
 *
 * @author bluejoe
 *
 */
@CrossOrigin(origins = {"http://localhost:8080","http://10.168.1.196:8080"})
@Configuration
public class ConnectorServlet extends HttpServlet {
    // core member of this Servlet

    ConnectorController _connectorController;


    @Bean
    public DataSource dataSource()
    {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("org.postgresql.Driver");
        dataSourceBuilder.url("jdbc:postgresql://10.168.1.196:5496/khukt_dongnam");
        dataSourceBuilder.username("postgres");
        dataSourceBuilder.password("dtq309");
        return dataSourceBuilder.build();
    }
    /**
     * create a command executor factory
     *
     * @param config
     * @return
     */
    protected CommandExecutorFactory createCommandExecutorFactory(
            ServletConfig config) {
        DefaultCommandExecutorFactory defaultCommandExecutorFactory = new DefaultCommandExecutorFactory();
        defaultCommandExecutorFactory
                .setClassNamePattern("org.grapheco.elfinder.controller.executors.%sCommandExecutor");
        defaultCommandExecutorFactory
                .setFallbackCommand(new MissingCommandExecutor());
        return defaultCommandExecutorFactory;
    }

    /**
     * create a connector controller
     *
     * @param config
     * @return
     */
    protected ConnectorController createConnectorController(ServletConfig config) throws SQLException {
        ConnectorController connectorController = new ConnectorController();

        connectorController
                .setCommandExecutorFactory(createCommandExecutorFactory(config));
        connectorController.setFsServiceFactory(createServiceFactory(config));

        return connectorController;
    }

    protected DefaultFsService createFsService() throws SQLException {
        Connection conn = dataSource().getConnection();
        if (conn == null) {
            return null;
        }
        DefaultFsService fsService = new DefaultFsService();
        fsService.setSecurityChecker(new FsSecurityCheckForAll());

        DefaultFsServiceConfig serviceConfig = new DefaultFsServiceConfig();
        serviceConfig.setTmbWidth(80);

        fsService.setServiceConfig(serviceConfig);

        String[] strVolumn = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
        String sql = "SELECT t.ten, t.pathroot FROM tbl_tailieu AS t";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        int dem=0;
        while (rs.next()) {
            fsService.addVolume(strVolumn[dem],
                createLocalFsVolume(rs.getString("ten"), new File(rs.getString("pathroot"))));
            dem = dem+1;
        }
        rs.close();
        stmt.close();
        conn.close();
        
        return fsService;
    }

    private LocalFsVolume createLocalFsVolume(String name, File rootDir) {
        LocalFsVolume localFsVolume = new LocalFsVolume();
        localFsVolume.setName(name);
        localFsVolume.setRootDir(rootDir);
        return localFsVolume;
    }

    /**
     * create a service factory
     *
     * @param config
     * @return
     */
    protected StaticFsServiceFactory createServiceFactory(ServletConfig config) throws SQLException {
        StaticFsServiceFactory staticFsServiceFactory = new StaticFsServiceFactory();
        DefaultFsService fsService = createFsService();

        staticFsServiceFactory.setFsService(fsService);
        return staticFsServiceFactory;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        _connectorController.connector(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        _connectorController.connector(req, resp);
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        try {
            _connectorController = createConnectorController(config);
        } catch (SQLException ex) {
            Logger.getLogger(ConnectorServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
